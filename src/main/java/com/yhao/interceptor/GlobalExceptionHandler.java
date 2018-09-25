package com.yhao.interceptor;

import com.yhao.enums.ResultStatus;
import com.yhao.exception.BusinessException;
import com.yhao.result.BaseResult;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseResult handlerException(Exception e) {

        //如果是自定义的异常，返回对应的错误信息
        if (e instanceof BusinessException) {
            e.printStackTrace();
            BusinessException exception = (BusinessException) e;
            return new BaseResult(exception.getCode(), exception.getMessage(), false);
        } else {
            //如果不是已知异常，返回系统异常
            e.printStackTrace();
            return BaseResult.failure(ResultStatus.FAILURE);
        }
    }

}