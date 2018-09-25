package com.yhao.exception;

/**
 * @author 杨浩
 * @create 2018-09-17 15:41
 **/
public class BusinessException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException(String msg) {
        super(msg);
        this.code = -1;
    }
    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

