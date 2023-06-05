package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.Meeting;
import top.vita.emos.wx.mapper.MeetingMapper;
import top.vita.emos.wx.service.MeetingService;

/**
 * 会议表(Meeting)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Service("meetingService")
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements MeetingService {

}

