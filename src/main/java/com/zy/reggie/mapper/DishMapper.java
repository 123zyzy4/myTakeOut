package com.zy.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName DishMapepr
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/3 21:38
 * @Version 1.0
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
