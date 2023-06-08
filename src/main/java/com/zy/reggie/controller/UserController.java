package com.zy.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zy.reggie.common.R;
import com.zy.reggie.entity.User;
import com.zy.reggie.service.UserService;
import com.zy.reggie.utils.SMSUtils;
import com.zy.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author zhangyu
 * @Date 2023/6/8 20:11
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @description: 验证码发送
     * @author zhangyu
     * @param: user
     * @param: request
     * @return R<String>
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpServletRequest request){
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            String validateCode = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}",validateCode);
//            SMSUtils.sendMessage("瑞吉外卖","发送方手机号（没注册用不了）",phone,validateCode);
            request.getSession().setAttribute(phone,validateCode);
            request.getSession().setAttribute("user",user.getId());

            return R.success("短信发送成功");
        }
        return R.error("短信发送失败");

    }

    /**
     * @description: 用户登录
     * @author zhangyu
     * @param: map
     * @param: request
     * @return R<User>
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map,HttpServletRequest request){
        log.info(map.toString());
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        String trueCode = request.getSession().getAttribute(phone).toString();
        if(trueCode!=null&&trueCode.equals(code)){

            LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(lambdaQueryWrapper);
            if(user==null){
                user=new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }

            return R.success(user);
        }
        return R.error("登陆失败");

    }

}
