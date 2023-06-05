package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.ConfigService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Config)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:28:20
 */
@RestController
@RequestMapping("/config")
public class ConfigController{

    @Autowired
    private ConfigService configService;

}
