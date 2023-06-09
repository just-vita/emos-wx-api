package top.vita.emos.wx.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.Meeting;
import top.vita.emos.wx.exception.EmosException;
import top.vita.emos.wx.mapper.MeetingMapper;
import top.vita.emos.wx.mapper.UserMapper;
import top.vita.emos.wx.service.MeetingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 会议表(Meeting)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Service("meetingService")
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements MeetingService {

    @Autowired
    private MeetingMapper meetingMapper;
    @Autowired
    private UserMapper userMapper;
    @Value("${workflow.url}")
    private String workflow;
    @Value("${emos.recieveNotify}")
    private String recieveNotify;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void insertMeeting(Meeting entity) {
        int row = meetingMapper.insertMeeting(entity);
        if (row != 1) {
            throw new EmosException("会议添加失败");
        }
        //开启审批工作流
        startMeetingWorkflow(entity.getUuid(), entity.getCreatorId().intValue(), entity.getDate(), entity.getStart());
    }

    @Override
    public ArrayList<HashMap> searchMyMeetingListByPage(HashMap param) {
        ArrayList<HashMap> list = meetingMapper.searchMyMeetingListByPage(param);
        String date = null;
        ArrayList resultList = new ArrayList();
        HashMap resultMap = null;
        JSONArray array = null;
        for (HashMap map : list) {
            String temp = map.get("date").toString();
            if (!temp.equals(date)) {
                date = temp;
                resultMap = new HashMap();
                resultMap.put("date", date);
                array = new JSONArray();
                resultMap.put("list", array);
                resultList.add(resultMap);
            }
            array.put(map);
        }
        return resultList;
    }

    @Override
    public HashMap searchMeetingById(int id) {
        return null;
    }

    @Override
    public void updateMeetingInfo(HashMap param) {

    }

    @Override
    public void deleteMeetingById(int id) {

    }

    @Override
    public Long searchRoomIdByUUID(String uuid) {
        return null;
    }

    @Override
    public List<String> searchUserMeetingInMonth(HashMap param) {
        return null;
    }

    private void startMeetingWorkflow(String uuid, int creatorId, String date, String start) {
        HashMap info = userMapper.searchUserInfo(creatorId);
        JSONObject json = new JSONObject();
        json.set("url", recieveNotify);
        json.set("uuid", uuid);
        json.set("openId", info.get("openId"));
//        json.set("code", code);
        json.set("date", date);
        json.set("start", start);
        String[] roles = info.get("roles").toString().split("，");
        if (!ArrayUtil.contains(roles, "总经理")) {
            Integer managerId = userMapper.searchDeptManagerId(creatorId);
            json.set("managerId", managerId);
            Integer gmId = userMapper.searchGmId();
            json.set("gmId", gmId);
            boolean bool = meetingMapper.searchMeetingMembersInSameDept(uuid);
            json.set("sameDept", bool);
        }
        String url = workflow + "/workflow/startMeetingProcess";
        HttpResponse resp = HttpRequest.post(url).header("Content-Type", "application/json")
                .body(json.toString()).execute();
        if (resp.getStatus() == 200) {
            json = JSONUtil.parseObj(resp.body());
            String instanceId = json.getStr("instanceId");
            HashMap param = new HashMap();
            param.put("uuid", uuid);
            param.put("instanceId", instanceId);
            int row = meetingMapper.updateMeetingInstanceId(param);
            if (row != 1) {
                throw new EmosException("保存会议工作流实例ID失败");
            }
        }
    }

}

