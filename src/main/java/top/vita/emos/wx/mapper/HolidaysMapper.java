package top.vita.emos.wx.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.vita.emos.wx.entity.Holidays;

/**
 * 节假日表(Holidays)表数据库访问层
 *
 * @author vita
 * @since 2023-06-05 13:27:56
 */
@Mapper
public interface HolidaysMapper extends BaseMapper<Holidays> {
    @Select("select id from tb_holidays where date = current_date limit 1;")
    Integer searchTodayIsHoliday();
}

