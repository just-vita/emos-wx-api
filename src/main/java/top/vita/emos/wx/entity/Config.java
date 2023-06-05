package top.vita.emos.wx.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Config)表实体类
 *
 * @author vita
 * @since 2023-06-05 13:28:20
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_config")
public class Config  {
    //主键@TableId
    private Integer id;
    //参数名
    private String paramKey;
    //参数值
    private String paramValue;
    //状态
    private Boolean status;
    //备注
    private String remark;

}

