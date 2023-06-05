package top.vita.emos.wx.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Workday)表实体类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_workday")
public class Workday  {
    //主键@TableId
    private Integer id;
    //日期
    private Date date;

}

