package top.vita.emos.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vita.emos.wx.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * 用户表(User)表服务接口
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
public interface UserService extends IService<User> {

    int registerUser(String registerCode, String code, String nickname, String photo);

    Set<String> searchUserPermissions(int id);

    Integer login(String code);

    User searchById(int userId);

    String searchUserHiredate(int userId);

    HashMap searchUserSummary(int userId);

    ArrayList<HashMap> searchUserGroupByDept(String keyword);
}

