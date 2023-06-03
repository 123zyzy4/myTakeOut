package com.zy.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.zy.reggie.common.BaseContext;
import com.zy.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName LoginCheckFilter
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/5/31 16:55
 * @Version 1.0
 */
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    private AntPathMatcher pathMatcher=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String[] urls=new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        String currentUrl=request.getRequestURI();
        log.info("拦截到请求：{}",currentUrl);
        boolean check=check(urls,currentUrl);
        if(check){
            //直接放行
            log.info("放行：{}",currentUrl);
            filterChain.doFilter(request,response);
            return;
        }
        if(request.getSession().getAttribute("employee")!=null){
            log.info("用户{}已经登录",request.getSession().getAttribute("employee"));
            BaseContext.setThreadLocal((Long) request.getSession().getAttribute("employee"));
            filterChain.doFilter(request,response);
            return;
        }
        //根据backend/js/request.js里面的内容返回对应响应，让前端跳转
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
        
    }
    public boolean check(String[] urls,String currentUrl){
        for (String url : urls) {
            boolean match = pathMatcher.match(url, currentUrl);
            if(match){
                return true;
            }
        }
        return false;
    }
}
