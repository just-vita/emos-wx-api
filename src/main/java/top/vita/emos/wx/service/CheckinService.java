package top.vita.emos.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.vita.emos.wx.entity.Checkin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 签到表(Checkin)表服务接口
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
public interface CheckinService extends IService<Checkin> {
    String validCanCheckin(int userId, String date);

    boolean checkin(HashMap param);

    HashMap searchTodayCheckin(int userId);

    long searchCheckinDays(int userId);

    ArrayList<HashMap> searchWeekCheckin(HashMap param);

}

