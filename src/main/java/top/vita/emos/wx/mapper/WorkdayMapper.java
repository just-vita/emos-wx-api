package top.vita.emos.wx.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.vita.emos.wx.entity.Workday;

/**
 * (Workday)表数据库访问层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Mapper
public interface WorkdayMapper extends BaseMapper<Workday> {
    @Select("select id from tb_workday where date = current_date limit 1;")
    Integer searchTodayIsWorkday();
}

