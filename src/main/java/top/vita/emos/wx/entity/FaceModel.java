package top.vita.emos.wx.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (FaceModel)表实体类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_face_model")
public class FaceModel  {
    //主键值@TableId
    private Integer id;
    //用户ID
    private Integer userId;
    //用户人脸模型
    private String faceModel;

}

