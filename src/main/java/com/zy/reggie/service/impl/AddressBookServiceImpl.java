package com.zy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.reggie.entity.AddressBook;
import com.zy.reggie.mapper.AddressBookMapper;
import com.zy.reggie.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName AddressBookServiceImpl
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/8 23:47
 * @Version 1.0
 */
@Slf4j
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService{
}
