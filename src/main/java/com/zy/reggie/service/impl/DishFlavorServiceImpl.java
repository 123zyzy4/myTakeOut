package com.zy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.reggie.entity.DishFlavor;
import com.zy.reggie.mapper.DishFlavorMapper;
import com.zy.reggie.service.DishFlavorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName DishFlavorServiceImpl
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/4 21:24
 * @Version 1.0
 */
@Service
@Slf4j
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
