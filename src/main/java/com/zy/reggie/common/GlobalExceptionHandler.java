package com.zy.reggie.common;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/5/31 21:19
 * @Version 1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

//ResponseBody将返回的结果作为json返回回去
@ControllerAdvice(annotations = {Controller.class, RestController.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException e){
        log.error(e.getMessage());
        if(e.getMessage().contains("Duplicate entry")){
            String[] s = e.getMessage().split(" ");
            String msg="用户名 "+s[2].substring(1,s[2].length()-1)+" 已存在";
            return R.error(msg);
        }
        return R.error("未知错误");
    }

}
