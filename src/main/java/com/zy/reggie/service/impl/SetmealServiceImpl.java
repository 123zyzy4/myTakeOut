package com.zy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.reggie.dto.SetmealDto;
import com.zy.reggie.entity.Setmeal;
import com.zy.reggie.entity.SetmealDish;
import com.zy.reggie.mapper.SetmealMapper;
import com.zy.reggie.service.SetmealDishService;
import com.zy.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * @description: 新增套餐，同时保存套餐与菜品的关联关系
     * @author zhangyu
     * @param: setmealDto
     * @return void
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.forEach(f->f.setSetmealId(setmealDto.getId()));
        setmealDishService.saveBatch(setmealDishes);

    }
}
