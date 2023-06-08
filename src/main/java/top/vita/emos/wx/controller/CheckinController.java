package top.vita.emos.wx.controller;


import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;
import top.vita.emos.wx.config.shiro.JwtUtils;
import top.vita.emos.wx.controller.form.CheckinForm;
import top.vita.emos.wx.service.CheckinService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import top.vita.emos.wx.util.R;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * 签到表(Checkin)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@Api(tags = "签到模块")
@RestController
@RequestMapping("/checkin")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;
    @Autowired
    private JwtUtils jwtUtils;

    @ApiOperation("验证用户是否可以签到")
    @GetMapping("/validCanCheckIn")
    public R validCanCheckin(@RequestHeader("token") String token) {
        int userId = jwtUtils.getUserId(token);
        String result = checkinService.validCanCheckin(userId, DateUtil.today());
        return R.ok(result);
    }

    @ApiOperation("签到")
    @PostMapping("/checkin")
    public R checkin(@Valid CheckinForm form,
                     @RequestParam("photo") MultipartFile file,
                     @RequestHeader("token") String token) {
        if (file == null) {
            return R.error("没有上传文件");
        }
        int userId = jwtUtils.getUserId(token);
        HashMap param = new HashMap();
        param.put("userId", userId);
        param.put("city", form.getCity());
        param.put("district", form.getDistrict());
        param.put("address", form.getAddress());
        param.put("country", form.getCountry());
        param.put("province", form.getProvince());
        checkinService.checkin(param);
        return R.ok("签到成功");
    }

}
