package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.Workday;
import top.vita.emos.wx.mapper.WorkdayMapper;
import top.vita.emos.wx.service.WorkdayService;

/**
 * (Workday)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Service("workdayService")
public class WorkdayServiceImpl extends ServiceImpl<WorkdayMapper, Workday> implements WorkdayService {

}

