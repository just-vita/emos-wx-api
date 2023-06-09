package top.vita.emos.wx.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.vita.emos.wx.config.shiro.JwtUtils;
import top.vita.emos.wx.controller.form.SearchMyMeetingListByPageForm;
import top.vita.emos.wx.service.MeetingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import top.vita.emos.wx.util.R;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 会议表(Meeting)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Api("会议模块接口")
@RestController
@RequestMapping("/meeting")
public class MeetingController{

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private MeetingService meetingService;

    @ApiOperation("查询会议列表分页数据")
    @PostMapping("/searchMyMeetingListByPage")
    public R searchMyMeetingListByPage(@Valid @RequestBody SearchMyMeetingListByPageForm form, @RequestHeader("token") String token){
        int userId=jwtUtil.getUserId(token);
        int page=form.getPage();
        int length=form.getLength();
        long start=(page-1)*length;
        HashMap map=new HashMap();
        map.put("userId",userId);
        map.put("start",start);
        map.put("length",length);
        ArrayList list=meetingService.searchMyMeetingListByPage(map);
        return R.ok().put("result",list);
    }
}
