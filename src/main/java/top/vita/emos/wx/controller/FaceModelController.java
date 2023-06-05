package top.vita.emos.wx.controller;



import top.vita.emos.wx.service.FaceModelService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (FaceModel)表控制层
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@RestController
@RequestMapping("/faceModel")
public class FaceModelController{

    @Autowired
    private FaceModelService faceModelService;

}
