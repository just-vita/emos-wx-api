package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.Config;
import top.vita.emos.wx.mapper.ConfigMapper;
import top.vita.emos.wx.service.ConfigService;

/**
 * (Config)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:28:20
 */
@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

}

