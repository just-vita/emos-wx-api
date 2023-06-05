package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.Checkin;
import top.vita.emos.wx.mapper.CheckinMapper;
import top.vita.emos.wx.service.CheckinService;

/**
 * 签到表(Checkin)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@Service("checkinService")
public class CheckinServiceImpl extends ServiceImpl<CheckinMapper, Checkin> implements CheckinService {

}

