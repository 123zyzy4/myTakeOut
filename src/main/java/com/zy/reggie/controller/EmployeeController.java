package com.zy.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.reggie.common.R;
import com.zy.reggie.entity.Employee;
import com.zy.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @ClassName EmployeeController
 * @Description 员工模块功能
 * @Author zhangyu
 * @Date 2023/4/29 16:07
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * @description: TODO
     * @author zhangyu
     * @param: employee
     * @param: httpServletRequest
     * @return R<Employee>
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest httpServletRequest){
        String pwd = employee.getPassword();
        pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
        LambdaQueryWrapper<Employee> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee e = employeeService.getOne(queryWrapper);
        if(e == null){
            return R.error("登录失败");
        }
        if(!e.getPassword().equals(pwd)){
            return R.error("登录失败");
        }
        if(e.getStatus()==0){
            return R.error("账号禁用");
        }
        httpServletRequest.getSession().setAttribute("employee",e.getId());
        return R.success(e);
    }
    
    /**
     * @description  员工退出
     * @author  zhangyu
     * @param httpServletRequest
     * @return  R<String>
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }


    /**
     * @description  新建员工
     * @author  zhangyu
     * @param httpServletRequest
     * @param e
     * @return  R<String>
     */
    @PostMapping
    public R<String> save(HttpServletRequest httpServletRequest,@RequestBody Employee e){
        e.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        e.setCreateTime(LocalDateTime.now());
        e.setUpdateTime(LocalDateTime.now());
        Long userId = (Long) httpServletRequest.getSession().getAttribute("employee");
        e.setCreateUser(userId);
        e.setUpdateUser(userId);

        employeeService.save(e);
        return R.success("新建员工成功");


    }

    /**
     * @description  分页查询
     * @author  zhangyu
     * @param page
     * @param pageSize
     * @param name
     * @return  R<Page>
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize, String name){
        log.info("page:{}  pageSize:{}  name:{}",page,pageSize,name);
        Page pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getUsername,name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    /**
     * @description: 更新员工状态
     * @author zhangyu
     * @param: employee
     * @return R<String>
     */

    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser((Long)request.getSession().getAttribute("employee"));
        employeeService.updateById(employee);
        return R.success("更新成功");

    }
}
