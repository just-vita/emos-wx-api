package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.City;
import top.vita.emos.wx.mapper.CityMapper;
import top.vita.emos.wx.service.CityService;

/**
 * 疫情城市列表(City)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@Service("cityService")
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

}

