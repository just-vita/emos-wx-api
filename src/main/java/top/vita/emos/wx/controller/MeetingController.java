package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.MeetingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 会议表(Meeting)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@RestController
@RequestMapping("/meeting")
public class MeetingController{

    @Autowired
    private MeetingService meetingService;

}
