package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.RoleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色表(Role)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@RestController
@RequestMapping("/role")
public class RoleController{

    @Autowired
    private RoleService roleService;

}
