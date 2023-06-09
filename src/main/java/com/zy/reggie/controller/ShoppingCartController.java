package com.zy.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zy.reggie.common.BaseContext;
import com.zy.reggie.common.R;
import com.zy.reggie.entity.ShoppingCart;
import com.zy.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName ShoppingCartController
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/9 1:19
 * @Version 1.0
 */
@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        log.info(shoppingCart.toString());
        Long userId = BaseContext.getThreadLocal();
        shoppingCart.setUserId(userId);

        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId,userId);

        if(shoppingCart.getDishId()!=null){
            lambdaQueryWrapper.eq(ShoppingCart::getDishId,shoppingCart.getDishId());
        }else{
            lambdaQueryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }

        ShoppingCart shoppingCart2 = shoppingCartService.getOne(lambdaQueryWrapper);
        if(shoppingCart2!=null){
            Integer number = shoppingCart2.getNumber();
            shoppingCart2.setNumber(number+1);
            shoppingCartService.updateById(shoppingCart2);

        }else {

            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            shoppingCart2=shoppingCart;
        }


        return R.success(shoppingCart2);

    }

    /**
     * @description: 购物车内容展示
     * @author zhangyu
     * @param:
     * @return R<List<ShoppingCart>>
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        Long userId = BaseContext.getThreadLocal();
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId,userId);
        lambdaQueryWrapper.orderByDesc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list = shoppingCartService.list(lambdaQueryWrapper);
        return R.success(list);

    }

    /**
     * @description: 清空购物车
     * @author zhangyu
     * @param:
     * @return R<String>
     */
    @DeleteMapping("/clean")
    public R<String> clean(){
        Long userId = BaseContext.getThreadLocal();
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId,userId);
        shoppingCartService.remove(lambdaQueryWrapper);
        return R.success("清空购物车成功");
    }


}
