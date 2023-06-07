package top.vita.emos.wx.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.config.SystemConstants;
import top.vita.emos.wx.entity.Checkin;
import top.vita.emos.wx.mapper.CheckinMapper;
import top.vita.emos.wx.mapper.CityMapper;
import top.vita.emos.wx.mapper.HolidaysMapper;
import top.vita.emos.wx.mapper.WorkdayMapper;
import top.vita.emos.wx.service.CheckinService;

import java.util.HashMap;

/**
 * 签到表(Checkin)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@Scope("prototype")
@Service("checkinService")
public class CheckinServiceImpl extends ServiceImpl<CheckinMapper, Checkin> implements CheckinService {
    @Autowired
    private CheckinMapper checkinMapper;

    @Autowired
    private SystemConstants systemConstants;

    @Autowired
    private HolidaysMapper holidaysMapper;

    @Autowired
    private WorkdayMapper workdayMapper;

    @Autowired
    private CityMapper cityMapper;

    @Override
    public String validCanCheckin(int userId, String date) {
        boolean flag_1 = workdayMapper.searchTodayIsWorkday() != null;
        boolean flag_2 = holidaysMapper.searchTodayIsHoliday() != null;
        String type = "工作日";
        if (DateUtil.date().isWeekend()) {
            type = "节假日";
        }

        if (flag_1) {
            type = "工作日";
        } else if (flag_2) {
            type = "节假日";
        }

        if (type.equals("节假日")) {
            return "节假日不需要考勤";
        } else {
            DateTime now = DateUtil.date();
            String start = DateUtil.today() + " " + systemConstants.attendanceStartTime;
            String end = DateUtil.today() + " " + systemConstants.attendanceEndTime;
            DateTime attendanceStartTime = DateUtil.parse(start);
            DateTime attendanceEndTime = DateUtil.parse(end);
            if (now.before(attendanceStartTime)) {
                return "没到上班考勤开始时间";
            } else if (now.after(attendanceEndTime)) {
                return "超过上班考勤结束时间";
            } else {
                HashMap map = new HashMap();
                map.put("userId", userId);
                map.put("date", date);
                map.put("start", start);
                map.put("end", end);
                boolean flag = checkinMapper.haveCheckin(map) != null;
                return flag ? "今日已经考勤，不用重复考勤" : "可以考勤";
            }
        }
    }
}

