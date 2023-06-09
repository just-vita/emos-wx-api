package top.vita.emos.wx.entity;

import java.time.LocalTime;
import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 会议表(Meeting)表实体类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_meeting")
public class Meeting  {
    //主键@TableId
    private Long id;
    //UUID
    private String uuid;
    //会议题目
    private String title;
    //创建人ID
    private Long creatorId;
    //日期
    private String date;
    //开会地点
    private String place;
    //开始时间
    private String start;
    //结束时间
    private String end;
    //会议类型（1在线会议，2线下会议）
    private short type;
    //参与者
    private String members;
    //会议内容
    private String desc;
    //工作流实例ID
    private String instanceId;
    //状态（1未开始，2进行中，3已结束）
    private short status;
    //创建时间
    private Date createTime;

}

