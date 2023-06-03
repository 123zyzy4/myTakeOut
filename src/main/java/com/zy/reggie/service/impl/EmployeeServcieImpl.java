package com.zy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.reggie.entity.Employee;
import com.zy.reggie.mapper.EmployeeMapper;
import com.zy.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName EmployeeServcieImpl
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/4/29 16:06
 * @Version 1.0
 */
@Service
@Slf4j
public class EmployeeServcieImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
