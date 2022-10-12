package com.ajie.config.exception;

import com.ajie.config.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:hyj
 * @date:2022/10/12
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail(null).message("执行了全局异常处理");
    }

    //特定异常处理 --ArithmeticException
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.fail(null).message("执行了特定异常ArithmeticException处理");
    }

    //自定义异常处理 -- MyException
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e) {
        e.printStackTrace();
        return Result.fail(null).code(e.getCode()).message(e.getMessage());
    }

}
