package top.vita.emos.wx.mapper;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import top.vita.emos.wx.entity.MessageEntity;
import top.vita.emos.wx.entity.MessageRefEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author vita
 * @Date 2023/6/9 16:55
 */
@Repository
public class MessageDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public String insert(MessageEntity entity) {
        Date sendTime = entity.getSendTime();
        sendTime = DateUtil.offset(sendTime, DateField.HOUR, 8);
        entity.setSendTime(sendTime);
        entity = mongoTemplate.save(entity);
        return entity.get_id();
    }

    public List<HashMap> searchMessageByPage(int userId, long start, int length) {
        JSONObject json = new JSONObject();
        // 将message表中的"_id"字段转换成字符串
        // 表对应的是后续"mongoTemplate.aggregate"中传入的表
        json.set("$toString", "$_id");
        Aggregation aggregation = Aggregation.newAggregation(
                // 在结果中增加一个字段
                // 将message_ref表中的_id字段转为字符串，并改名为id
                Aggregation.addFields().addField("id").withValue(json).build(),
                // 以"id"字段连接"message_ref"表的"messageId"字段，作为合并结果的"ref"字段
                // 相当于where中的on语句
                Aggregation.lookup("message_ref", "id", "messageId", "ref"),
                Aggregation.match(Criteria.where("ref.receiverId").is(userId)),
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "sendTime")),
                Aggregation.skip(start),
                Aggregation.limit(length)
        );
        AggregationResults<HashMap> result = mongoTemplate.aggregate(aggregation, "message", HashMap.class);
        List<HashMap> list = result.getMappedResults();
        list.forEach(message -> {
            // 得到合并结果中的ref字段
            // 因为聚合操作可能返回多个文档，所以返回结果是一个 List 对象
            List<MessageRefEntity> refList = (List<MessageRefEntity>) message.get("ref");
            MessageRefEntity refEntity = refList.get(0);
            // 将ref字段的参数设置成普通字段
            message.put("readFlag", refEntity.getReadFlag());
            message.put("refId", refEntity.get_id());
            // 删除原本的ref字段
            message.remove("ref");
            // 删除合并后的表中残留的_id字段（message与ref存储的id相同）
            message.remove("_id");

            Date sendTime = (Date) message.get("sendTime");
            sendTime = DateUtil.offset(sendTime, DateField.HOUR, -8);
            String today = DateUtil.today();
            if (today.equals(DateUtil.date(sendTime).toDateStr())) {
                message.put("sendTime", DateUtil.format(sendTime, "HH:mm"));
            } else {
                message.put("sendTime", DateUtil.format(sendTime, "yyyy/MM/dd"));
            }
        });
        return list;
    }

    public HashMap searchMessageById(String id) {
        HashMap message = mongoTemplate.findById(id, HashMap.class, "message");
        Date sendTime = (Date) message.get("sendTime");
        sendTime = DateUtil.offset(sendTime, DateField.HOUR, -8);
        message.replace("sendTime", DateUtil.format(sendTime, "yyyy-MM-dd HH:mm"));
        return message;
    }
}
