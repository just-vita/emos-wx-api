package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.Holidays;
import top.vita.emos.wx.mapper.HolidaysMapper;
import top.vita.emos.wx.service.HolidaysService;

/**
 * 节假日表(Holidays)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:56
 */
@Service("holidaysService")
public class HolidaysServiceImpl extends ServiceImpl<HolidaysMapper, Holidays> implements HolidaysService {

}

