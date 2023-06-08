package com.zy.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.reggie.common.CustomException;
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

    /**
     * @description: 删除套餐
     * @author zhangyu
     * @param: ids
     * @return void
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Setmeal::getId,ids);
        lambdaQueryWrapper.eq(Setmeal::getStatus,1);
        int count = this.count(lambdaQueryWrapper);
        if(count>0){
            throw new CustomException("套餐售卖中，无法删除");
        }
        this.removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper2=new LambdaQueryWrapper<>();
        lambdaQueryWrapper2.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(lambdaQueryWrapper2);

    }
}
