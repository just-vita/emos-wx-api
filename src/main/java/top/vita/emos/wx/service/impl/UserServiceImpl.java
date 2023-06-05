package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.User;
import top.vita.emos.wx.mapper.UserMapper;
import top.vita.emos.wx.service.UserService;

/**
 * 用户表(User)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

