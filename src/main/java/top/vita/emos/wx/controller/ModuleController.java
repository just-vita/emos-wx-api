package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.ModuleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块资源表(Module)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@RestController
@RequestMapping("/module")
public class ModuleController{

    @Autowired
    private ModuleService moduleService;

}
