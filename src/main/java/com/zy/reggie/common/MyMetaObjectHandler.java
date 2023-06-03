package com.zy.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName MyMetaObjectHandler
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/3 12:14
 * @Version 1.0
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler{
    /**
     * @description: 插入操作时自动填充
     * @author zhangyu
     * @param: metaObject
     * @return void
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContext.getThreadLocal());
        metaObject.setValue("updateUser", BaseContext.getThreadLocal());
    }
    /**
     * @description: 更新操作时自动填充
     * @author zhangyu
     * @param: metaObject
     * @return void
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info(metaObject.toString());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getThreadLocal());
    }
}
