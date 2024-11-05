package top.houyuji.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.houyuji.common.base.R;
import top.houyuji.common.base.exception.ServiceException;

import static top.houyuji.common.base.enums.ErrorCodeEnums.PERMISSION_DENIED;
import static top.houyuji.common.base.enums.ErrorCodeEnums.USER_NOT_LOGIN;

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
                String defaultMessage = fieldError.getDefaultMessage();
                if (defaultMessage != null && defaultMessage.startsWith("Failed to convert property value")) {
                    message = "参数" + fieldError.getField() + "类型错误";
                } else {
                    message = "参数" + fieldError.getField() + defaultMessage;
                }
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
        return R.NG(USER_NOT_LOGIN, null);
    }

    @ResponseBody
    @ExceptionHandler(value = {NotPermissionException.class, NotRoleException.class})
    public R<Object> handleNotPermissionException(Exception e) {
        return R.NG(PERMISSION_DENIED, null);
    }

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public R<Object> handleSerialException(ServiceException e) {
        return R.NG(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R<Object> handleUnknownException(Exception e) {
        log.error("未知异常", e);
        return R.NG("未知异常，请联系管理员");
    }
}
