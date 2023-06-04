package com.zy.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.reggie.common.R;
import com.zy.reggie.entity.Category;
import com.zy.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/3 14:59
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService  categoryService;

    /**
     * @description: 新增分类
     * @author zhangyu
     * @param: category
     * @return R<String>
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info(category.toString());
        categoryService.save(category);
        return R.success("新增成功");
    }

    /**
     * @description: 分页查询
     * @author zhangyu
     * @param: page
     * @param: pageSize
     * @return R<String>
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        Page<Category> pageInfo=new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    /**
     * @description: 删除分类
     * @author zhangyu
     * @param: id
     * @return R<String>
     */
    @DeleteMapping
    public R<String> delete(Long id){
        categoryService.remove(id);
        return R.success("分类信息删除成功");
    }
    /**
     * @description: 修改分类信息
     * @author zhangyu
     * @param: category
     * @return R<String>
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("修改分类：{}",category.toString());
        categoryService.updateById(category);
        return R.success("修改分类成功");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        LambdaQueryWrapper<Category> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        lambdaQueryWrapper.orderByAsc(Category::getSort);
        List<Category> list = categoryService.list(lambdaQueryWrapper);
        return R.success(list);
    }
}
