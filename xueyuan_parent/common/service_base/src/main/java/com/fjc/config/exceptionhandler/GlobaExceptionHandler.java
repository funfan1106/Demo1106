package com.fjc.config.exceptionhandler;


import com.fjc.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: guli_parent
 * @description: 统一异常返回类
 * @author: Mr.Fan
 * @create: 2021-02-05 16:25
 **/

@ControllerAdvice
public class GlobaExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }
}
