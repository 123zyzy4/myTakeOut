package com.zy.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.reggie.common.R;
import com.zy.reggie.dto.SetmealDto;
import com.zy.reggie.entity.Category;
import com.zy.reggie.entity.Setmeal;
import com.zy.reggie.service.CategoryService;
import com.zy.reggie.service.SetmealDishService;
import com.zy.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SetmealDishController
 * @Description 套餐管理
 * @Author zhangyu
 * @Date 2023/6/6 0:22
 * @Version 1.0
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private CategoryService categoryService;

    /**
     * @description: 新建菜品
     * @author zhangyu
     * @param: setmealDto
     * @return R<SetmealDto>
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDto);
        return R.success("套餐保存成功");
    }

    /**
     * @description: 套餐信息分页查询
     * @author zhangyu
     * @param: page
     * @param: pageSize
     * @param: name
     * @return R<Page>
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<Setmeal> setmealPage=new Page<>(page,pageSize);
        Page<SetmealDto> setmealDtoPage=new Page<>();

        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Setmeal::getName,name);
        lambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(setmealPage,lambdaQueryWrapper);

        BeanUtils.copyProperties(setmealPage,setmealDtoPage,"records");
        List<Setmeal> setmealRecords = setmealPage.getRecords();
        List<SetmealDto> setmealDtoRecords=setmealRecords.stream().map((item)->{
            Long categoryId = item.getCategoryId();
            SetmealDto setmealDto=new SetmealDto();
            Category category = categoryService.getById(categoryId);
            if(category!=null){
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            BeanUtils.copyProperties(item,setmealDto);
            return setmealDto;
        }).collect(Collectors.toList());
        setmealDtoPage.setRecords(setmealDtoRecords);

        return R.success(setmealDtoPage);
    }

    /**
     * @description: 套餐删除
     * @author zhangyu
     * @param: ids
     * @return R<String>
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        setmealService.removeWithDish(ids);
        return R.success("删除套餐成功");
    }

    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(setmeal.getCategoryId()!=null,Setmeal::getCategoryId,setmeal.getCategoryId());
        lambdaQueryWrapper.eq(setmeal.getStatus()!=null,Setmeal::getStatus,setmeal.getStatus());
        lambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);
        List<Setmeal> list = setmealService.list(lambdaQueryWrapper);

        return R.success(list);
    }
}
