package com.zy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.reggie.entity.OrderDetail;
import com.zy.reggie.mapper.OrderDetailMapper;
import com.zy.reggie.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderDetailService
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/9 15:56
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
