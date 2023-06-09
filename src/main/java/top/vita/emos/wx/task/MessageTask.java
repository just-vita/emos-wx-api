package top.vita.emos.wx.task;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import top.vita.emos.wx.entity.MessageEntity;
import top.vita.emos.wx.entity.MessageRefEntity;
import top.vita.emos.wx.exception.EmosException;
import top.vita.emos.wx.service.MessageService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author vita
 * @Date 2023/6/9 17:42
 */
@Slf4j
@Component
public class MessageTask {

    @Autowired
    private ConnectionFactory factory;

    @Autowired
    private MessageService messageService;

    public void send(String topic, MessageEntity entity) {
        String id = messageService.insertMessage(entity);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(topic, true, false, false, null);
            HashMap map = new HashMap();
            map.put("messageId", id);
            AMQP.BasicProperties properties =
                    new AMQP.BasicProperties()
                            .builder()
                            .headers(map)
                            .build();
            channel.basicPublish("", topic, properties, entity.getMsg().getBytes());
            log.debug("消息发送成功");
        } catch (Exception e) {
            log.error("发生异常：", e);
            throw new EmosException("向MQ发送消息失败");
        }
    }

    @Async
    public void sendAsync(String topic, MessageEntity entity) {
        send(topic, entity);
    }

    public int receive(String topic) {
        int i = 0;
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            channel.queueDeclare(topic, true, false, false, null);
            while (true) {
                final GetResponse response = channel.basicGet(topic, false);
                if (response != null) {
                    final Map<String, Object> headers = response.getProps().getHeaders();
                    final String messageId = headers.get("messageId").toString();
                    final byte[] body = response.getBody();
                    final String message = new String(body);
                    log.debug("从RabbitMQ接收的消息：" + message);

                    final MessageRefEntity ref = new MessageRefEntity();
                    ref.setMessageId(messageId);
                    ref.setReceiverId(Integer.parseInt(topic));
                    ref.setReadFlag(false);
                    ref.setLastFlag(true);
                    messageService.insertRef(ref);

                    final long deliveryTag = response.getEnvelope().getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                    i++;
                } else {
                    break;
                }

            }
        } catch (Exception e) {
            log.error("执行异常", e);
            throw new EmosException("接收消息失败");
        }
        return i;
    }

    @Async
    public int receiveAsync(String topic) {
        return receive(topic);
    }

    public void deleteQueue(String topic){
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            channel.queueDelete(topic);
            log.debug("消息队列成功删除");
        }catch (Exception e) {
            log.error("删除队列失败", e);
            throw new EmosException("删除队列失败");
        }
    }

    @Async
    public void deleteQueueAsync(String topic){
        deleteQueue(topic);
    }
}