package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.CityService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 疫情城市列表(City)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@RestController
@RequestMapping("/city")
public class CityController{

    @Autowired
    private CityService cityService;

}
