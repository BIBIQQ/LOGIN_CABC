package com.ff.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author FF
 * @date 2021/12/11
 * @TIME:15:45
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @GetMapping("/name.do")
    public Map  loginName(){
        //获取登录得用户名字
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        HashMap map = new HashMap();
        map.put("name",name);
        return map;
    }
}
