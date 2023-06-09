package com.zy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.reggie.entity.ShoppingCart;
import com.zy.reggie.mapper.ShoppingCartMapper;
import com.zy.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName ShoppingCartServiceImpl
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/9 1:19
 * @Version 1.0
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
