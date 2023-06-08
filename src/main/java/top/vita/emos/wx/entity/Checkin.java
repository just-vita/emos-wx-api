package top.vita.emos.wx.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 签到表(Checkin)表实体类
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_checkin")
public class Checkin  {
    //主键@TableId
    private Integer id;
    //用户ID
    private Integer userId;
    //签到地址
    private String address;
    //国家
    private String country;
    //省份
    private String province;
    //城市
    private String city;
    //区划
    private String district;
    //考勤结果
    private Object status;
    //风险等级
    private Object risk;
    //签到日期
    private String date;
    //签到时间
    private Date createTime;

}

