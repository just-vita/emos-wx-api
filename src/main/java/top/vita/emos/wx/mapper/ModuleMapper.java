package top.vita.emos.wx.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.vita.emos.wx.entity.Module;

/**
 * 模块资源表(Module)表数据库访问层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Mapper
public interface ModuleMapper extends BaseMapper<Module> {

}

