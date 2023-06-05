package top.vita.emos.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.vita.emos.wx.entity.Dept;
import top.vita.emos.wx.mapper.DeptMapper;
import top.vita.emos.wx.service.DeptService;

/**
 * (Dept)表服务实现类
 *
 * @author vita
 * @since 2023-06-05 13:27:58
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}

