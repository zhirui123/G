package com.huagongwuliu.waybillelectronic.controller;


import com.huagongwuliu.waybillelectronic.pojo.ResultInfo;
import com.huagongwuliu.waybillelectronic.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    GoodsTypeService service;


    @GetMapping("/show")
    public String show(){
        return  "第一个测试入口";
    }


    @GetMapping("/goodsType")
    @ResponseBody
    public ResultInfo getGoodsType(){
        //验证码错误
        ResultInfo info = new ResultInfo();
        info.setResult_data(service.findAll());
        return info;
    }
}
