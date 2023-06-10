package top.vita.emos.wx.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.MessageEntity;
import top.vita.emos.wx.entity.User;
import top.vita.emos.wx.exception.EmosException;
import top.vita.emos.wx.mapper.DeptMapper;
import top.vita.emos.wx.mapper.UserMapper;
import top.vita.emos.wx.service.UserService;
import top.vita.emos.wx.task.MessageTask;

import java.util.*;

/**
 * 用户表(User)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageTask messageTask;

    @Autowired
    private DeptMapper deptMapper;

    private String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap param = new HashMap();
        param.put("appid", appId);
        param.put("secret", appSecret);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String response = HttpUtil.post(url, param);
        JSONObject json = JSONUtil.parseObj(response);
        String openid = json.getStr("openid");
        System.out.println(response);
        if (openid == null || openid.length() == 0) {
            throw new RuntimeException("临时登陆凭证错误");
        }
        return openid;
    }

    @Override
    public int registerUser(String registerCode, String code, String nickname, String photo) {
        if ("000000".equals(registerCode)) {
            boolean flag = userMapper.haveRootUser();
            if (!flag) {
                String openId = getOpenId(code);
                HashMap param = new HashMap();
                param.put("openId", openId);
                param.put("nickname", nickname);
                param.put("photo", photo);
                param.put("role", "[0]");
                param.put("status", 1);
                param.put("createTime", new Date());
                param.put("hiredate", new Date());
                param.put("root", true);
                userMapper.registerUser(param);
                final Integer id = userMapper.searchIdByOpenId(openId);

                // 异步发送消息
                MessageEntity entity = new MessageEntity();
                entity.setSenderId(0);
                entity.setSenderName("系统消息");
                entity.setUuid(IdUtil.simpleUUID());
                entity.setMsg("欢迎您注册成为超级管理员，请及时更新你的员工个人信息。");
                entity.setSendTime(new Date());
                messageTask.sendAsync(id + "", entity);

                return id;
            } else {
                throw new EmosException("无法绑定超级管理员账号");
            }
        }
        return 0;
    }

    @Override
    public Set<String> searchUserPermissions(int id) {
        return userMapper.searchUserPermissions(id);
    }

    @Override
    public Integer login(String code) {
        String openId = getOpenId(code);
        Integer userId = userMapper.searchIdByOpenId(openId);
        if (userId == null) {
            throw new EmosException("账户不存在");
        }
        // messageTask.receiveAsync(userId + "");
        return userId;
    }

    @Override
    public User searchById(int userId) {
        return userMapper.searchById(userId);
    }

    @Override
    public String searchUserHiredate(int userId) {
        return userMapper.searchUserHiredate(userId);
    }

    @Override
    public HashMap searchUserSummary(int userId) {
        return userMapper.searchUserSummary(userId);
    }

    @Override
    public ArrayList<HashMap> searchUserGroupByDept(String keyword) {
        ArrayList<HashMap> list_1 = deptMapper.searchDeptMembers(keyword);
        ArrayList<HashMap> list_2 = userMapper.searchUserGroupByDept(keyword);
        for (HashMap map_1 : list_1) {
            long deptId = (Long) map_1.get("id");
            ArrayList members = new ArrayList();
            for (HashMap map_2 : list_2) {
                long id = (Long) map_2.get("deptId");
                if (deptId == id) {
                    members.add(map_2);
                }
            }
            map_1.put("members", members);
        }
        return list_1;
    }

    @Override
    public ArrayList<HashMap> searchMembers(List param) {
        return userMapper.searchMembers(param);
    }

}

