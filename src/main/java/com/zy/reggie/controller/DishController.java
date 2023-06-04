package com.zy.reggie.controller;

import com.zy.reggie.service.DishFlavorService;
import com.zy.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DishFlavorController
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/4 21:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;


}
