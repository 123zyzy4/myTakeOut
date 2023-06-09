package com.zy.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.reggie.dto.DishDto;
import com.zy.reggie.entity.Dish;

/**
 * @ClassName DishService
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/3 21:39
 * @Version 1.0
 */
public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
    public DishDto getByIDWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);
}
