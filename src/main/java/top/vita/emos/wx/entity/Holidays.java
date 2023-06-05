package top.vita.emos.wx.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 节假日表(Holidays)表实体类
 *
 * @author vita
 * @since 2023-06-05 13:27:56
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_holidays")
public class Holidays  {
    //主键@TableId
    private Integer id;
    //日期
    private Date date;

}

