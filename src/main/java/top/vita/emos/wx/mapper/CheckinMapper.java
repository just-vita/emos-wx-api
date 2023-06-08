package top.vita.emos.wx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.vita.emos.wx.entity.Checkin;

import java.util.HashMap;

/**
 * 签到表(Checkin)表数据库访问层
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@Mapper
public interface CheckinMapper extends BaseMapper<Checkin> {
    @Select("select id " +
            "from tb_checkin " +
            "where user_id = #{userId} " +
            "and date = current_date " +
            "and create_time between #{start} and #{end} " +
            "limit 1 ")
    Integer haveCheckin(HashMap param);
    
    @Insert("<script>" +
            " INSERT INTO tb_checkin " +
            " SET user_id=#{userId}, " +
            " <if test=\"address!=null\"> " +
            "     address=#{address}, " +
            " </if> " +
            " <if test=\"country!=null\"> " +
            "     country=#{country}, " +
            " </if> " +
            " <if test=\"province!=null\"> " +
            "     province=#{province}, " +
            " </if> " +
            " <if test=\"city!=null\"> " +
            "     city=#{city}, " +
            " </if> " +
            " <if test=\"district!=null\"> " +
            "     district=#{district}, " +
            " </if> " +
            " status=#{status}, " +
            " <if test=\"risk!=null\"> " +
            "     risk=#{risk}, " +
            " </if> " +
            " date=#{date}, " +
            " create_time=#{createTime}" +
            "</script>")
    void insertCheckin(Checkin checkin);

}

