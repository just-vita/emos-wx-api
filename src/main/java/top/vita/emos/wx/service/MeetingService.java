package top.vita.emos.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vita.emos.wx.entity.Meeting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 会议表(Meeting)表服务接口
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
public interface MeetingService extends IService<Meeting> {

    void insertMeeting(Meeting entity);

    ArrayList<HashMap> searchMyMeetingListByPage(HashMap param);

    HashMap searchMeetingById(int id);

    void updateMeetingInfo(HashMap param);

    void deleteMeetingById(int id);

    Long searchRoomIdByUUID(String uuid);

    List<String> searchUserMeetingInMonth(HashMap param);

}

