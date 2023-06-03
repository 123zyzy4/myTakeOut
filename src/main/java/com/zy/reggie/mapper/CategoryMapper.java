package com.zy.reggie.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName CategoryMapper
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/3 14:55
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
