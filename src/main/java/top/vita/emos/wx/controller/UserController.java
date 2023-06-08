package top.vita.emos.wx.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import top.vita.emos.wx.config.shiro.JwtUtils;
import top.vita.emos.wx.controller.form.LoginForm;
import top.vita.emos.wx.controller.form.RegisterForm;
import top.vita.emos.wx.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import top.vita.emos.wx.util.R;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 用户表(User)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Api(tags = "用户模块接口")
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${emos.jwt.cache-expire}")
    private int cacheExpire;

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public R register(@RequestBody @Valid RegisterForm form) {
        int id = userService.registerUser(form.getRegisterCode(), form.getCode(), form.getNickname(), form.getPhoto());
        String token = jwtUtil.createToken(id);
        Set<String> permissionSet = userService.searchUserPermissions(id);
        saveCacheToken(token, id);
        return R.ok("用户注册成功").put("token", token).put("permission", permissionSet);
    }
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R login(@RequestBody @Valid LoginForm form) {
        int id = userService.login(form.getCode());
        String token = jwtUtil.createToken(id);
        Set<String> permissionSet = userService.searchUserPermissions(id);
        saveCacheToken(token, id);
        return R.ok("登录成功").put("token", token).put("permission", permissionSet);
    }

    @ApiOperation("查询用户摘要信息")
    @GetMapping("/searchUserSummary")
    public R searchUserSummary(@RequestHeader("token") String token) {
        int userId = jwtUtil.getUserId(token);
        HashMap map = userService.searchUserSummary(userId);
        return R.ok().put("result", map);
    }

    private void saveCacheToken(String token, int userId) {
        redisTemplate.opsForValue().set(token, userId, cacheExpire, TimeUnit.DAYS);
    }



}
