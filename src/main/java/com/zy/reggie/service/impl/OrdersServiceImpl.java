package com.zy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.reggie.entity.Orders;
import com.zy.reggie.mapper.OrdersMapper;
import com.zy.reggie.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrdersServiceImpl
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/9 15:54
 * @Version 1.0
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
}
