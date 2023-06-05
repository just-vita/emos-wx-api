package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.Permission;
import top.vita.emos.wx.mapper.PermissionMapper;
import top.vita.emos.wx.service.PermissionService;

/**
 * (Permission)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:56
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}

