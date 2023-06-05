package top.vita.emos.wx.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.vita.emos.wx.entity.Config;

/**
 * (Config)表数据库访问层
 *
 * @author vita
 * @since 2023-06-05 13:28:20
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

}

