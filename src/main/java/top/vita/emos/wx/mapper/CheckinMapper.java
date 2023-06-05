package top.vita.emos.wx.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.vita.emos.wx.entity.Checkin;

/**
 * 签到表(Checkin)表数据库访问层
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@Mapper
public interface CheckinMapper extends BaseMapper<Checkin> {

}

