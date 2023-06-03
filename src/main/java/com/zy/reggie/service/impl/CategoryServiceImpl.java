package com.zy.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.reggie.common.CustomException;
import com.zy.reggie.entity.Category;
import com.zy.reggie.entity.Dish;
import com.zy.reggie.entity.Setmeal;
import com.zy.reggie.mapper.CategoryMapper;
import com.zy.reggie.service.CategoryService;
import com.zy.reggie.service.DishService;
import com.zy.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/3 14:57
 * @Version 1.0
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;


    /**
     * @description: 重写remove方法，使得在删除分类时不删除关联着菜品和套餐的分类
     * @author zhangyu
     * @param: queryWrapper
     * @return boolean
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper=new LambdaQueryWrapper();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        if(count1>0){
            throw new CustomException("当前分类下关联着菜品");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper=new LambdaQueryWrapper();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if(count2>0){
            throw new CustomException("当前分类下关联着套餐");
        }

        super.removeById(id);

    }
}
