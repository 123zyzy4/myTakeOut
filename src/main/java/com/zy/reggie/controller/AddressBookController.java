package com.zy.reggie.controller;

import com.zy.reggie.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AddressBookController
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/8 23:48
 * @Version 1.0
 */
@RestController
@RequestMapping("/address")
@Slf4j
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;
}
