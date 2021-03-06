package com.chengxiaoxiao.seckillshop.controller;

import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;
import com.chengxiaoxiao.seckillshop.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    MiaoshaUserService miaoshaService;


    @RequestMapping("/to_list")
    public String toList(Model model, @CookieValue(value = miaoshaService.LOGIN_COOKIE_TOKEN, required = false) String cookieToken,
                         @RequestParam(value = miaoshaService.LOGIN_COOKIE_TOKEN, required = false) String paramsToken) {

        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramsToken)) {
            return "login";
        }
        String token = StringUtils.isEmpty(paramsToken) ? cookieToken : paramsToken;

        MiaoshaUser user = miaoshaService.getMiaoshaUserByToken(token);
        //
        model.addAttribute("user", user);
        return "goodlist";
    }

}
