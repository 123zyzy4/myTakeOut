package com.zy.reggie.common;

/**
 * @ClassName CustomException
 * @Description 自定义分类异常
 * @Author zhangyu
 * @Date 2023/6/3 22:11
 * @Version 1.0
 */
public class CustomException extends RuntimeException{
    public CustomException(String msg){
        super(msg);
    }
}
