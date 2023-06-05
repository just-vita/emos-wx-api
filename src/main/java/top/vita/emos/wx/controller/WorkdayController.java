package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.WorkdayService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Workday)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@RestController
@RequestMapping("/workday")
public class WorkdayController{

    @Autowired
    private WorkdayService workdayService;

}
