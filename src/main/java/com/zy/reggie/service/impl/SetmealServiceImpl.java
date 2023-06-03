package com.zy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.reggie.entity.Setmeal;
import com.zy.reggie.mapper.SetmealMapper;
import com.zy.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName SetmealServiceImpl
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/3 21:41
 * @Version 1.0
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
