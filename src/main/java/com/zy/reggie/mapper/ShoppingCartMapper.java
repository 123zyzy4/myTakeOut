package com.zy.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName ShoppingCartMapper
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/9 1:18
 * @Version 1.0
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
