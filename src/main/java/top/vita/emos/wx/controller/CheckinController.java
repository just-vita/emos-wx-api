package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.CheckinService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 签到表(Checkin)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@RestController
@RequestMapping("/checkin")
public class CheckinController{

    @Autowired
    private CheckinService checkinService;

}
