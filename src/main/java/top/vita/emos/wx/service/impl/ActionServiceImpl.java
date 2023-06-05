package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.Action;
import top.vita.emos.wx.mapper.ActionMapper;
import top.vita.emos.wx.service.ActionService;

/**
 * 行为表(Action)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:57
 */
@Service("actionService")
public class ActionServiceImpl extends ServiceImpl<ActionMapper, Action> implements ActionService {

}

