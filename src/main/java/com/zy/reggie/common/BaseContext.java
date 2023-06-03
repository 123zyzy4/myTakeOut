package com.zy.reggie.common;

/**
 * @ClassName BaseContext
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/3 13:59
 * @Version 1.0
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static void setThreadLocal(Long id){
        threadLocal.set(id);
    }

    public static Long getThreadLocal(){
        return threadLocal.get();
    }
}
