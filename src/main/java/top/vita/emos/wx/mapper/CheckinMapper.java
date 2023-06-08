package top.vita.emos.wx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.vita.emos.wx.entity.Checkin;

import java.util.ArrayList;
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

    @Select("SELECT " +
           "  u.name, " +
           "  u.photo, " +
           "  d.dept_name AS deptName, " +
           "  ck.address, " +
           "  CASE " +
           "    WHEN ck.status=1 THEN '正常' " +
           "    WHEN ck.status=2 THEN '迟到' " +
           "    END as status, " +
           "  CASE " +
           "    WHEN ck.risk=1 THEN '低风险' " +
           "    WHEN ck.risk=2 THEN '中风险' " +
           "    WHEN ck.risk=3 THEN '高风险' " +
           "    END as risk, " +
           "  DATE_FORMAT(ck.create_time,\"%H:%i\") AS checkinTime, " +
           "  ck.date " +
           "FROM tb_user u " +
           "       LEFT JOIN tb_dept d ON u.dept_id=d.id " +
           "       LEFT JOIN tb_checkin ck ON u.id=ck.user_id AND ck.date=CURRENT_DATE " +
           "WHERE u.id=#{userId} AND u.status=1")
    HashMap searchTodayCheckin(int userId);

    @Select("SELECT COUNT(*) AS count " +
            "FROM tb_checkin " +
            "WHERE user_id=#{userId}")
    long searchCheckinDays(int userId);

    @Select("SELECT " +
            "  CAST(date AS CHAR) AS date, " +
            "  IF(status=1,\"正常\",\"迟到\") AS status " +
            "FROM tb_checkin " +
            "WHERE user_id=#{userId} " +
            "  AND date BETWEEN #{startDate} AND #{endDate}")
    ArrayList<HashMap> searchWeekCheckin(HashMap param);
    
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

