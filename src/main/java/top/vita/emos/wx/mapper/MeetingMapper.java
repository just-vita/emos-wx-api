package top.vita.emos.wx.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.vita.emos.wx.entity.Meeting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 会议表(Meeting)表数据库访问层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Mapper
public interface MeetingMapper extends BaseMapper<Meeting> {

    int insertMeeting(Meeting entity);

    ArrayList<HashMap> searchMyMeetingListByPage(HashMap param);

    boolean searchMeetingMembersInSameDept(String uuid);

    int updateMeetingInstanceId(HashMap map);

    HashMap searchMeetingById(int id);

    ArrayList<HashMap> searchMeetingMembers(int id);

    int updateMeetingInfo(HashMap param);

    int deleteMeetingById(int id);

    List<String> searchUserMeetingInMonth(HashMap param);
}

