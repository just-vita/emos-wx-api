package top.vita.emos.wx.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Dept)表实体类
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_dept")
public class Dept  {
    //主键@TableId
    private Integer id;
    //部门名称
    private String deptName;

}

