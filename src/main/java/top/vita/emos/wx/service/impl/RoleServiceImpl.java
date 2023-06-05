package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.Role;
import top.vita.emos.wx.mapper.RoleMapper;
import top.vita.emos.wx.service.RoleService;

/**
 * 角色表(Role)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

