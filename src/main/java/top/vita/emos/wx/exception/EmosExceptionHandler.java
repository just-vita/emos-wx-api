package top.vita.emos.wx.exception;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.vita.emos.wx.util.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一异常处理
 *
 * @Author vita
 * @Date 2023/6/5 17:23
 */
@RestControllerAdvice
public class EmosExceptionHandler {
    @ExceptionHandler(EmosException.class)
    public R returnEmosException(EmosException e) {
        return R.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R returnMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        Map<String, String> map = getErrors(result);
        return R.error(map);
    }

    public Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> errorList = result.getFieldErrors();
        for (FieldError ff : errorList) {
            // 错误所对应的属性字段名
            String field = ff.getField();
            // 错误的信息
            String msg = ff.getDefaultMessage();
            map.put(field, msg);
        }
        return map;
    }
}
