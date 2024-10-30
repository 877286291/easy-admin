package top.houyuji.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.houyuji.common.base.R;

/**
 * 全局异常处理
 * Created by Aurora on 2020/2/27.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R<Object> handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = "参数" + fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return R.NG(message);
    }


    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public R<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return R.NG(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = NotLoginException.class)
    public R<Object> handleNotLoginException(NotLoginException e) {
        return R.NG(R.USER_NOT_LOGIN, "用户未登录");
    }

    @ResponseBody
    @ExceptionHandler(value = NotPermissionException.class)
    public R<Object> handleNotPermissionException(NotPermissionException e) {
        return R.NG(R.PERMISSION_DENIED, "无权限");
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R<Object> handleUnknownException(Exception e) {
        log.error("未知异常", e);
        return R.NG("未知异常，请联系管理员");
    }
}
