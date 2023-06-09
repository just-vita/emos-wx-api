package top.vita.emos.wx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.vita.emos.wx.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 用户表(User)表数据库访问层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT IF(COUNT(*),TRUE,FALSE) FROM tb_user WHERE root=1;")
    boolean haveRootUser();

    @Select("SELECT id FROM tb_user WHERE open_id=#{openId} AND status = 1")
    Integer searchIdByOpenId(String openId);

    @Insert("<script> " +
            "INSERT INTO tb_user " +
            "  SET " +
            "  <if test=\"openId!=null\"> " +
            "      open_id = #{openId}, " +
            "  </if> " +
            "  <if test=\"nickname!=null\"> " +
            "      nickname = #{nickname}, " +
            "  </if> " +
            "  <if test=\"photo!=null\"> " +
            "      photo = #{photo}, " +
            "  </if> " +
            "  <if test=\"name!=null\"> " +
            "      name = #{name}, " +
            "  </if> " +
            "  <if test=\"sex!=null\"> " +
            "      sex = #{sex}, " +
            "  </if> " +
            "  <if test=\"tel!=null\"> " +
            "      tel = #{tel}, " +
            "  </if> " +
            "  <if test=\"email!=null\"> " +
            "      email=#{email}, " +
            "  </if> " +
            "  <if test=\"hiredate!=null\"> " +
            "      hiredate = #{hiredate}, " +
            "  </if> " +
            "  role = #{role}, " +
            "  root = #{root}, " +
            "  <if test=\"deptName!=null\"> " +
            "      dept_id = ( SELECT id FROM tb_dept WHERE dept_name = #{deptName} ), " +
            "  </if> " +
            "  status = #{status}, " +
            "  create_time = #{createTime} " +
            "</script>")
    void registerUser(HashMap param);

    @Select("select p.permission_name " +
            "from tb_user u " +
            " join tb_role r on JSON_CONTAINS(u.role, CAST(r.id AS CHAR)) " +
            " join tb_permission p on JSON_CONTAINS(r.permissions, CAST(p.id AS CHAR)) " +
            "where u.id = #{userId} and u.status = 1")
    Set<String> searchUserPermissions(int id);

    @Select("SELECT " +
            " id, open_id, nickname, photo, name, sex, tel, role, root, dept_id, status, create_time " +
            "FROM tb_user WHERE id=#{userId} AND status=1 ")
    User searchById(int userId);

    @Select("SELECT hiredate FROM tb_user " +
            "WHERE id=#{userId} AND status=1 ")
    String searchUserHiredate(int userId);

    HashMap searchUserSummary(int userId);

    ArrayList<HashMap> searchUserGroupByDept(String keyword);

    ArrayList<HashMap> searchMembers(List param);
}

