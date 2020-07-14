package com.huagongwuliu.waybillelectronic.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/show")
    public String show(){
        return  "第一个测试入口";
    }


}
