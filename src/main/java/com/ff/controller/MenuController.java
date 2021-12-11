package com.ff.controller;

import com.ff.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/findMenusByLoginName.do")
    public List<Map> findMenusByLoginName(){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return menuService.findMenusByLoginName(name);
    }

}
