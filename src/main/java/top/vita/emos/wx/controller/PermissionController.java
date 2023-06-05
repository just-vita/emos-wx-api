package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.PermissionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Permission)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:56
 */
@RestController
@RequestMapping("/permission")
public class PermissionController{

    @Autowired
    private PermissionService permissionService;

}
