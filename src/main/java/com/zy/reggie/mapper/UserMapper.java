package com.zy.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/8 20:10
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
