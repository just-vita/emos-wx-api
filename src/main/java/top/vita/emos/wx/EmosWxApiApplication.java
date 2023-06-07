package top.vita.emos.wx;

import cn.hutool.core.util.StrUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import top.vita.emos.wx.config.SystemConstants;
import top.vita.emos.wx.entity.Config;
import top.vita.emos.wx.mapper.ConfigMapper;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;

@SpringBootApplication
@MapperScan(basePackages = "top.vita.emos.wx.mapper")
@EnableMongoRepositories
@ServletComponentScan
public class EmosWxApiApplication {

    @Autowired
    private SystemConstants systemConstants;
    @Autowired
    private ConfigMapper configMapper;

    public static void main(String[] args) {
        SpringApplication.run(EmosWxApiApplication.class, args);
    }

    @PostConstruct
    public void init() {
        List<Config> configList = configMapper.selectAllParams();
        configList.forEach(config -> {
            String paramKey = config.getParamKey();
            // 转为驼峰命名法
            paramKey = StrUtil.toCamelCase(paramKey);
            String paramValue = config.getParamValue();
            try {
                Field field = systemConstants.getClass().getDeclaredField(paramKey);
                field.set(systemConstants, paramValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.err.println(e.getMessage());
            }
        });
    }
}
