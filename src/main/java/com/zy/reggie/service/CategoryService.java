package com.zy.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.reggie.entity.Category;

/**
 * @ClassName CategoryService
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/3 14:56
 * @Version 1.0
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
