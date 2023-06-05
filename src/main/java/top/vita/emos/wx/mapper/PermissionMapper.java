package top.vita.emos.wx.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.vita.emos.wx.entity.Permission;

/**
 * (Permission)表数据库访问层
 *
 * @author vita
 * @since 2023-06-05 13:27:56
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}

