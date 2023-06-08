package com.zy.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.reggie.dto.SetmealDto;
import com.zy.reggie.entity.Setmeal;

import java.util.List;

/**
 * @ClassName SetmealService
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/3 21:39
 * @Version 1.0
 */
public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);
}
