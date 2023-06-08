package com.zy.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.reggie.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName AddressBookMapper
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/8 23:46
 * @Version 1.0
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
