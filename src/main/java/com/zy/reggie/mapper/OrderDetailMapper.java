package com.zy.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.reggie.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName OrderDetailMapper
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/9 15:55
 * @Version 1.0
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
