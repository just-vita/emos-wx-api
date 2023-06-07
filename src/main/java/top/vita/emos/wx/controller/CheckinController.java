package top.vita.emos.wx.controller;



import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.vita.emos.wx.config.shiro.JwtUtils;
import top.vita.emos.wx.service.CheckinService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import top.vita.emos.wx.util.R;

/**
 * 签到表(Checkin)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@Api(tags = "签到模块")
@RestController
@RequestMapping("/checkin")
public class CheckinController{

    @Autowired
    private CheckinService checkinService;
    @Autowired
    private JwtUtils jwtUtils;

    @ApiOperation("验证用户是否可以签到")
    @GetMapping("/validCanCheckin")
    public R validCanCheckin(@RequestHeader("token") String token) {
        int userId = jwtUtils.getUserId(token);
        String result = checkinService.validCanCheckin(userId, DateUtil.today());
        return R.ok(result);
    }

}
