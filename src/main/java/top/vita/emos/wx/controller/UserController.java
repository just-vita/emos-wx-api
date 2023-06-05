package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户表(User)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

}
