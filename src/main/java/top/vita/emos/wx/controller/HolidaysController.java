package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.HolidaysService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 节假日表(Holidays)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:56
 */
@RestController
@RequestMapping("/holidays")
public class HolidaysController{

    @Autowired
    private HolidaysService holidaysService;

}
