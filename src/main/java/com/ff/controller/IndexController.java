package com.ff.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/test1")
    public String test1() {
        return "success1";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/test2")
    public String test2() {
        return "success2";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/test3")
    public String test3() {
        return "success3";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/test4")
    public String test4() {
        return "success4";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/test5")
    public String test() {
        return "success5";
    }


}
