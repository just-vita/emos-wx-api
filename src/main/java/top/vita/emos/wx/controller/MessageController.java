package top.vita.emos.wx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.vita.emos.wx.config.shiro.JwtUtils;
import top.vita.emos.wx.controller.form.DeleteMessageRefByIdForm;
import top.vita.emos.wx.controller.form.SearchMessageByIdForm;
import top.vita.emos.wx.controller.form.SearchMessageByPageForm;
import top.vita.emos.wx.controller.form.UpdateUnreadMessageForm;
import top.vita.emos.wx.service.MessageService;
import top.vita.emos.wx.util.R;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Api("消息模块接口")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private MessageService messageService;

//    @Autowired
//    private MessageTask messageTask;

    @ApiOperation("获取分页消息列表")
    @PostMapping("/searchMessageByPage")
    public R searchMessageByPage(@Valid @RequestBody SearchMessageByPageForm form, @RequestHeader("token") String token) {
        int userId = jwtUtil.getUserId(token);
        int page = form.getPage();
        int length = form.getLength();
        long start = (page - 1) * length;
        List<HashMap> list = messageService.searchMessageByPage(userId, start, length);
        return R.ok().put("result", list);
    }

    @ApiOperation("根据ID查询消息")
    @PostMapping("/searchMessageById")
    public R searchMessageById(@Valid @RequestBody SearchMessageByIdForm form) {
        HashMap map = messageService.searchMessageById(form.getId());
        return R.ok().put("result", map);
    }

    @ApiOperation("未读消息更新成已读消息")
    @PostMapping("/updateUnreadMessage")
    public R updateUnreadMessage(@Valid @RequestBody UpdateUnreadMessageForm form) {
        long rows = messageService.updateUnreadMessage(form.getId());
        return R.ok().put("result", rows == 1);
    }

    @ApiOperation("删除消息")
    @PostMapping("/deleteMessageRefById")
    public R deleteMessageRefById(@Valid @RequestBody DeleteMessageRefByIdForm form) {
        long rows = messageService.deleteMessageRefById(form.getId());
        return R.ok().put("result", rows == 1);
    }


}