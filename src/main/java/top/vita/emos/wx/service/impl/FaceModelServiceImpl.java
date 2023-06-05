package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.FaceModel;
import top.vita.emos.wx.mapper.FaceModelMapper;
import top.vita.emos.wx.service.FaceModelService;

/**
 * (FaceModel)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Service("faceModelService")
public class FaceModelServiceImpl extends ServiceImpl<FaceModelMapper, FaceModel> implements FaceModelService {

}

