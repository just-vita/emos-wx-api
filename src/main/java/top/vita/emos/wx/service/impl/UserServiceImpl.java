package top.vita.emos.wx.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.User;
import top.vita.emos.wx.exception.EmosException;
import top.vita.emos.wx.mapper.UserMapper;
import top.vita.emos.wx.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

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

    private String getOpenId(String code){
        String url="https://api.weixin.qq.com/sns/jscode2session";
        HashMap param = new HashMap();
        param.put("appid", appId);
        param.put("secret", appSecret);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String response = HttpUtil.post(url, param);
        JSONObject json = JSONUtil.parseObj(response);
        String openid = json.getStr("openid");
        System.out.println(response);
        if (openid == null || openid.length() == 0){
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
                param.put("root", true);
                userMapper.registerUser(param);
                return userMapper.searchIdByOpenId(openId);
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
}

