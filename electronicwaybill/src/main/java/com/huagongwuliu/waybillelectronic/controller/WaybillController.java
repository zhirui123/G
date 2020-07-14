package com.huagongwuliu.waybillelectronic.controller;

import com.github.pagehelper.PageInfo;
import com.huagongwuliu.waybillelectronic.pojo.ResultInfo;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import com.huagongwuliu.waybillelectronic.service.WaybillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/waybill")
public class WaybillController {

    @Autowired
    private WaybillService waybillService;

    @GetMapping("/show")
    public String show(){
        return  "第一个测试入口";
    }

    @GetMapping("/findall")
    public List<Waybill> findAll() throws Exception {
        return this.waybillService.findAll();
    }

    @PostMapping("/findwaybillsbyid")
    @ResponseBody
    public  Waybill findwaybillsById(@RequestParam("id") Long id) throws  Exception{
        return this.waybillService.queryById(id);
    }


    @PostMapping("/findbyidanduserid")
    @ResponseBody
    public   ResultInfo findwaybillsByIdAndUserId(@RequestParam("id") Long id,@RequestParam("userId") String userId) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill>  waybills =  this.waybillService.queryByIdAndUserId(id,userId);
        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        }catch (Exception e){
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }



    @PostMapping("/list")
    @ResponseBody
    public ResultInfo findwaybillsUserId(@RequestParam String userId,@RequestParam int pageNum,@RequestParam int pageSize) {

        if (userId.length() ==0 || userId == null){
            return new ResultInfo(1001,null,"请输入用户id");
        }

        try {
            PageInfo<Waybill> waybills =  this.waybillService.queryByUserId(userId,pageNum,pageSize);
            return new ResultInfo(0,waybills,"成功");
        }catch (Exception e){

            return new ResultInfo(1001,null,"失败");
        }
    }

    @PostMapping("/addwaybill")
    @ResponseBody
    public   ResultInfo findwaybillsUserId(@RequestBody Waybill waybill) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();

        if (waybill.getUserId() == null || waybill.getUserId().length() == 0){
            info.setResult_code(1);
            info.setResult_msg("请输入用户id");
            return  info;
        }

        try {

            waybill.setWaybillCode(UUID.randomUUID().toString().replace("-",""));
            int recode  =  this.waybillService.insertWaybillByWaubillObj(waybill);
            if (recode == 0){
                info.setResult_code(1);
                info.setResult_msg("失败");
            }else{
                info.setResult_code(0);
                info.setResult_msg("成功");
            }
            info.setResult_data(recode);
            return info;
        }catch (Exception e){
            info.setResult_code(1);
            info.setResult_data(0);
            info.setResult_msg("失败");
            return info;
        }

    }


    @PostMapping("/updatewaybill")
    @ResponseBody
    public   ResultInfo updateWaybill(@RequestBody Waybill waybill) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();

        if (waybill.getUserId() == null || waybill.getUserId().length() == 0){
            info.setResult_code(1);
            info.setResult_msg("请输入用户id");
            return  info;
        }


        if (waybill.getId() == null){
            info.setResult_code(1);
            info.setResult_msg("电子运单id为空");
            return  info;
        }
        try {
            int recode  =  this.waybillService.updateWaybillByWaubillObj(waybill);
            if (recode == 0){
                info.setResult_code(1);
                info.setResult_msg("失败");
            }else{
                info.setResult_code(0);
                info.setResult_msg("成功");
            }
            info.setResult_data(recode);
            return info;
        }catch (Exception e){
            info.setResult_code(1);
            info.setResult_data(0);
            info.setResult_msg("失败");
            return info;
        }

    }

    @PostMapping("/status")
    @ResponseBody
    public   ResultInfo changeWaybillStatus(@RequestParam String status,@RequestParam Long id) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();

        if (status == null || status.length() == 0){
            status = "0";
        }
        try {
            int recode  =  this.waybillService.changeStatusAction(status,id);
            if (recode == 0){
                info.setResult_code(1);
                info.setResult_msg("失败");
            }else{
                info.setResult_code(0);
                info.setResult_msg("成功");
            }
            info.setResult_data(recode);
            return info;
        }catch (Exception e){
            info.setResult_code(1);
            info.setResult_data(0);
            info.setResult_msg("失败");
            return info;
        }
    }



    /**
     * 根据装货人查询
     * @param shipperName
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("/findbyshippername")
    @ResponseBody
    public   ResultInfo queryByShipmentNameAndUserId(@RequestParam("shipperName") String shipperName,@RequestParam("userId") String userId) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill>  waybills =  this.waybillService.queryByShipperNameAndUserId(shipperName, userId);
        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        }catch (Exception e){
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }







}
