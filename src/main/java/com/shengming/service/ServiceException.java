package com.shengming.service;

import com.shengming.exception.BaseException;

/**
 * Service层异常
 * @author XuChenFeng
 * @Date 2020/8/13 13:52
 */
public class ServiceException extends BaseException {
    private static final long serialVersionUID = 6058294324031642376L;

    public ServiceException() {}

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(code, message);
    }

}