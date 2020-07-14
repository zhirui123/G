package com.huagongwuliu.waybillelectronic.controller;

import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;
import com.huagongwuliu.waybillelectronic.pojo.ResultInfo;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import com.huagongwuliu.waybillelectronic.service.WaybillService;
import com.huagongwuliu.waybillelectronic.utils.QRCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    @RequestMapping("/3")
    public String showa(){
        return  "a1";
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




    @GetMapping("/showwaybill")
    public String showWaybill(@RequestParam("id") Long id, Model model) throws Exception {
       Waybill waybill = this.waybillService.queryById(id);

       model.addAttribute("waybill",waybill);
       return  "showwaybill";
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
    public   ResultInfo addWaybills(@RequestBody Waybill waybill) throws  Exception{
        //验证码错误
        ResultInfo info = new ResultInfo();

        if (waybill.getUserId() == null || waybill.getUserId().length() == 0){
            info.setResult_code(1);
            info.setResult_msg("请输入用户id");
            return  info;
        }

        try {

            waybill.setWaybillCode(UUID.randomUUID().toString().replace("-",""));
            waybill.setStatus("0");
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



    @GetMapping(value="/qrimage")
    public ResponseEntity<byte[]> getQRImage(@RequestParam String codestr) {

        //二维码内的信息
        String info = codestr;

        byte[] qrcode = null;
        try {
            qrcode = QRCodeGenerator.getQRCodeImage(info, 360, 360);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }

        // Set headers
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]> (qrcode, headers, HttpStatus.CREATED);
    }



}
