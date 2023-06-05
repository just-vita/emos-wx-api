package top.vita.emos.wx.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 模块资源表(Module)表实体类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_module")
public class Module  {
    //主键@TableId
    private Integer id;
    //模块编号
    private String moduleCode;
    //模块名称
    private String moduleName;

}

