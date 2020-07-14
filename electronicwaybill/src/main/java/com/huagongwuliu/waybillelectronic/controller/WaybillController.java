package com.huagongwuliu.waybillelectronic.controller;

import com.github.pagehelper.PageInfo;
import com.huagongwuliu.waybillelectronic.pojo.ResultInfo;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import com.huagongwuliu.waybillelectronic.service.WaybillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
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
    @RequestMapping("/yd")
    public String aa(@RequestParam("id") Long id, ModelMap modelMap) throws Exception {
        Waybill waybill = this.waybillService.queryById(id);
        modelMap.addAttribute("yd",waybill);
        return "yd";
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
    public   ResultInfo queryByShipperNameAndUserId(@RequestParam("shipperName") String shipperName,@RequestParam("userId") String userId) throws  Exception{
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

    /**
     * 根据收货人查询
     * @param shiptoName
     * @param userId
     * @return
     * @throws Exception
     */

    @PostMapping("/findbyshiptoname")
    @ResponseBody
    public   ResultInfo queryByShiptoNameAndUserId(@RequestParam("shiptoName") String shiptoName,@RequestParam("userId") String userId) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill>  waybills =  this.waybillService.queryByShiptoNameAndUserId(shiptoName,userId);
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

    /**
     * 根据装货人查询
     * @param shipmentName
     * @param userId
     * @return
     * @throws Exception
     */

    @PostMapping("/findbyshipmentname")
    @ResponseBody
    public   ResultInfo queryByShipmentNameAndUserId(@RequestParam("shipmentName") String shipmentName,@RequestParam("userId") String userId) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill>  waybills =  this.waybillService.queryByShipmentNameAndUserId(shipmentName, userId);
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

    /**
     * 根据承运人修改
     * @param carriageName
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("/findbycarriageName")
    @ResponseBody
    public   ResultInfo queryByCarriageNameAndUserId(@RequestParam("carriageName") String carriageName,@RequestParam("userId") String userId) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill>  waybills =  this.waybillService.queryByCarriageNameAndUserId(carriageName, userId);
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


    /**
     * 根据车牌号查询
     * @param licensePlateNum
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("/findbylicenseplatenum")
    @ResponseBody
    public   ResultInfo queryByLicensePlateNumAndUserId(@RequestParam("licensePlateNum") String licensePlateNum,@RequestParam("userId") String userId) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill>  waybills =  this.waybillService.queryByLicensePlateNumAndUserId(licensePlateNum, userId);
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

    /**
     * 根据罐体编号查询
     * @param canbodyNum
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("/findbycanbodynum")
    @ResponseBody
    public   ResultInfo queryByCanbodyNumAndUserId(@RequestParam("canbodyNum") String canbodyNum,@RequestParam("userId") String userId) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill>  waybills =  this.waybillService.queryByCanbodyNumAndUserId(canbodyNum, userId);
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

    /**
     * 根据押运员姓名查询
     * @param escortName
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("/findbyescortname")
    @ResponseBody
    public   ResultInfo queryByEscortNameAndUserId(@RequestParam("escortName") String escortName,@RequestParam("userId") String userId) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill>  waybills =  this.waybillService.queryByEscortNameAndUserId(escortName, userId);
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
