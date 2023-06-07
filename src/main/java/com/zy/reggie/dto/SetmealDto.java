package com.zy.reggie.dto;

import com.zy.reggie.entity.Setmeal;
import com.zy.reggie.entity.SetmealDish;
import com.zy.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
