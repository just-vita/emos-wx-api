package top.vita.emos.wx.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.vita.emos.wx.service.ActionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import top.vita.emos.wx.util.R;

/**
 * 行为表(Action)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Api(tags = "活动模块")
@RestController
@RequestMapping("/action")
public class ActionController{

    @Autowired
    private ActionService actionService;

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public R test() {
        return R.ok("success");
    }
}
