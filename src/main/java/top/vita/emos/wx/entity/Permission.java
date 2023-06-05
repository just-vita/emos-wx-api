package top.vita.emos.wx.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Permission)表实体类
 *
 * @author vita
 * @since 2023-06-05 13:27:56
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_permission")
public class Permission  {
    //主键@TableId
    private Integer id;
    //权限
    private String permissionName;
    //模块ID
    private Integer moduleId;
    //行为ID
    private Integer actionId;

}

