package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.Module;
import top.vita.emos.wx.mapper.ModuleMapper;
import top.vita.emos.wx.service.ModuleService;

/**
 * 模块资源表(Module)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Service("moduleService")
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements ModuleService {

}

