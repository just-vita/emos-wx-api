package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.ActionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 行为表(Action)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@RestController
@RequestMapping("/action")
public class ActionController{

    @Autowired
    private ActionService actionService;

}
