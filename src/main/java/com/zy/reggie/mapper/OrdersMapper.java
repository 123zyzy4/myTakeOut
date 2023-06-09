package com.zy.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName OrdersMapper
 * @Description TODO
 * @Author zhangyu
* @Date 2023/6/9 15:54
 * @Version 1.0
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
