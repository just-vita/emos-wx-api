package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.DeptService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Dept)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@RestController
@RequestMapping("/dept")
public class DeptController{

    @Autowired
    private DeptService deptService;

}
