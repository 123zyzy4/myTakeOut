package com.zy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.reggie.entity.Dish;
import com.zy.reggie.mapper.DishMapper;
import com.zy.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName DishServiceImpl
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/3 21:40
 * @Version 1.0
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService{
}
