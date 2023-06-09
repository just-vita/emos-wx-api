package top.vita.emos.wx;

import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.vita.emos.wx.entity.MessageEntity;
import top.vita.emos.wx.entity.MessageRefEntity;
import top.vita.emos.wx.service.MeetingService;
import top.vita.emos.wx.service.MessageService;

import java.util.Date;

@SpringBootTest
class EmosWxApiApplicationTests {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MeetingService meetingService;

    @Test
    void contextLoads() {
        for (int i = 1; i <= 100; i++) {
            MessageEntity message = new MessageEntity();
            message.setUuid(IdUtil.simpleUUID());
            message.setSenderId(0);
            message.setSenderName("系统消息");
            message.setMsg("这是第" + i + "条测试消息");
            message.setSendTime(new Date());
            String id = messageService.insertMessage(message);

            MessageRefEntity ref = new MessageRefEntity();
            ref.setMessageId(id);
            ref.setReceiverId(7); //接收人ID
            ref.setLastFlag(true);
            ref.setReadFlag(false);
            messageService.insertRef(ref);
        }
    }


}
