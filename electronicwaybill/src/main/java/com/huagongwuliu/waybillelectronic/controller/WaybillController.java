package com.huagongwuliu.waybillelectronic.controller;

import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;
import com.huagongwuliu.waybillelectronic.pojo.ResultInfo;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import com.huagongwuliu.waybillelectronic.service.ShipperService;
import com.huagongwuliu.waybillelectronic.service.WaybillService;
import com.huagongwuliu.waybillelectronic.utils.DateUtil;
import com.huagongwuliu.waybillelectronic.utils.QRCodeGenerator;
import com.huagongwuliu.waybillelectronic.utils.StringUtil;
import com.huagongwuliu.waybillelectronic.utils.WaybillUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/waybill")
public class WaybillController {

    @Autowired
    private WaybillService waybillService;

    @Autowired
    private ShipperService shipperService;

    @GetMapping("/show")
    public String show() {
        return "第一个测试入口";
    }

    @GetMapping("/findall")
    public List<Waybill> findAll() throws Exception {
        return this.waybillService.findAll();
    }

    @PostMapping("/findwaybillsbyid")
    @ResponseBody
    public Waybill findwaybillsById(@RequestParam("id") Long id) throws Exception {
        return this.waybillService.queryById(id);
    }

    @RequestMapping("/yd")
    public String aa(@RequestParam("id") Long id, ModelMap modelMap) throws Exception {
        Waybill waybill = this.waybillService.queryById(id);
        modelMap.addAttribute("yd", waybill);
        return "yd";
    }

    @PostMapping("/findbyidanduserid")
    @ResponseBody
    public ResultInfo findwaybillsByIdAndUserId(@RequestParam("id") Long id, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill> waybills = this.waybillService.queryByIdAndUserId(id, userId);
        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }


    @PostMapping("/list")
    @ResponseBody
    public ResultInfo findwaybillsUserId(@RequestParam String userId, @RequestParam int pageNum, @RequestParam int pageSize) {

        if (userId.length() == 0 || userId == null) {
            return new ResultInfo(1001, null, "请输入用户id");
        }
        try {
            PageInfo<Waybill> waybills = this.waybillService.queryByUserId(userId, pageNum, pageSize);
            return new ResultInfo(0, waybills, "成功");
        } catch (Exception e) {

            return new ResultInfo(1001, null, "失败");
        }
    }

    @PostMapping("/addwaybill")
    @ResponseBody
    public ResultInfo findwaybillsUserId(@RequestBody Waybill waybill) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();

        if (waybill.getUserId() == null || waybill.getUserId().length() == 0) {
            info.setResult_code(1);
            info.setResult_msg("请输入用户id");
            return info;
        }

        try {
            waybill.setAddTime(DateUtil.getNowTimestamp());
            waybill.setWaybillCode(WaybillUtils.getCode());
            if (!StringUtil.isEmpty(waybill.getLicensePlateNum())){
                waybill.setLicensePlateNum(waybill.getLicensePlateNum().toUpperCase());
            }

            if (!StringUtil.isEmpty(waybill.getTrailerNum())){
                waybill.setTrailerNum(waybill.getTrailerNum().toUpperCase());
            }

            int recode = this.waybillService.insertWaybillByWaubillObj(waybill);

            if (recode == 0) {
                info.setResult_code(1);
                info.setResult_msg("失败");
            } else {
                info.setResult_code(0);
                info.setResult_msg("成功");
            }

            List<Waybill> waybillList = this.waybillService.queryByWaybillCodeAndUserId(waybill.getWaybillCode(), waybill.getUserId());
            if (waybillList.size() > 0) {
                Waybill waybill1 = waybillList.get(0);
                info.setResult_data(waybill1);
            } else {
                info.setResult_data(recode);
            }
            return info;
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(0);
            info.setResult_msg("失败");
            return info;
        }

    }


    @PostMapping("/updatewaybill")
    @ResponseBody
    public ResultInfo updateWaybill(@RequestBody Waybill waybill) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();

        if (waybill.getUserId() == null || waybill.getUserId().length() == 0) {
            info.setResult_code(1);
            info.setResult_msg("请输入用户id");
            return info;
        }


        if (waybill.getId() == null) {
            info.setResult_code(1);
            info.setResult_msg("电子运单id为空");
            return info;
        }
        try {
            waybill.setUpdateTime(DateUtil.getNowTimestamp());
            if (!StringUtil.isEmpty(waybill.getLicensePlateNum())){
                waybill.setLicensePlateNum(waybill.getLicensePlateNum().toUpperCase());
            }

            if (!StringUtil.isEmpty(waybill.getTrailerNum())){
                waybill.setTrailerNum(waybill.getTrailerNum().toUpperCase());
            }




            int recode = this.waybillService.updateWaybillByWaubillObj(waybill);
            if (recode == 0) {
                info.setResult_code(1);
                info.setResult_msg("失败");
            } else {
                info.setResult_code(0);
                info.setResult_msg("成功");
            }
            info.setResult_data(recode);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            info.setResult_code(1);
            info.setResult_data(0);
            info.setResult_msg("失败");
            return info;
        }

    }

    @PostMapping("/status")
    @ResponseBody
    public ResultInfo changeWaybillStatus(@RequestParam String status, @RequestParam(defaultValue = "0") int goodsNum, @RequestParam Long id) {
        //验证码错误
        ResultInfo info = new ResultInfo();

        if (status == null || status.length() == 0) {
            status = "0";
        }
        if (!"4".equals(status)) {
            status = String.valueOf(Integer.parseInt(status) + 1);
        }

        try {
            int recode = this.waybillService.changeStatusAction(status, goodsNum, id);
            if (recode == 0) {
                info.setResult_code(1);
                info.setResult_msg("失败");
            } else {
                String str = "接单完成，等待装货";
                switch (status) {
                    case "1":
                        str = "接单完成，等待装货";
                        break;
                    case "2":
                        str = "开始装货";
                        break;
                    case "3":
                        str = "装货完成，开始运输";
                        break;
                    case "4":
                        str = "卸货完成，任务结束";
                        break;
                }
                info.setResult_code(0);
                info.setResult_data(str);
                info.setResult_msg("成功");
            }
//            info.setResult_data(recode);
            return info;
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(0);
            info.setResult_msg("失败");
            return info;
        }
    }


    /**
     * 根据托运人查询
     *
     * @param shipperName
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("/findbyshippername")
    @ResponseBody
    public ResultInfo queryByShipperNameAndUserId(@RequestParam("shipperName") String shipperName, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill> waybills = null;

//        if (StringUtil.isNotEmpty(shipperName)){
            waybills = this.waybillService.queryByShipperNameAndUserId(shipperName,userId);
//        }else{
//            waybills = this.waybillService.queryByUserId(userId);
//        }

        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }

    /**
     * 根据收货人查询
     *
     * @param shiptoName
     * @param userId
     * @return
     * @throws Exception
     */

    @PostMapping("/findbyshiptoname")
    @ResponseBody
    public ResultInfo queryByShiptoNameAndUserId(@RequestParam("shiptoName") String shiptoName, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();

        List<Waybill> waybills = null;

//        if (shiptoName.length() == 0 || shiptoName == null) {
            waybills = this.waybillService.queryByUserId(userId);
//        } else {
//            waybills = this.waybillService.queryByShiptoNameAndUserId(shiptoName, userId);
//        }

        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }

    /**
     * 根据装货人查询
     *
     * @param shipmentName
     * @param userId
     * @return
     * @throws Exception
     */

    @PostMapping("/findbyshipmentname")
    @ResponseBody
    public ResultInfo queryByShipmentNameAndUserId(@RequestParam("shipmentName") String shipmentName, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill> waybills = null;


//        if (shipmentName.length() == 0 || shipmentName == null) {
            waybills = this.waybillService.queryByUserId(userId);
//        } else {
//            waybills = this.waybillService.queryByShipmentNameAndUserId(shipmentName, userId);
//        }



        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }

    /**
     * 根据承运人修改
     *
     * @param carriageName
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("/findbycarriageName")
    @ResponseBody
    public ResultInfo queryByCarriageNameAndUserId(@RequestParam("carriageName") String carriageName, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill> waybills = null;
//        if (carriageName.length() == 0 || carriageName == null) {
            waybills = this.waybillService.queryByUserId(userId);
//        } else {
//            waybills = this.waybillService.queryByCarriageNameAndUserId(carriageName, userId);
//        }


        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }


    /**
     * 根据车牌号查询
     *
     * @param licensePlateNum
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("/findbylicenseplatenum")
    @ResponseBody
    public ResultInfo queryByLicensePlateNumAndUserId(@RequestParam("licensePlateNum") String licensePlateNum, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();

        List<Waybill> waybills = null;
//        if (licensePlateNum.length() == 0 || licensePlateNum == null) {
            waybills = this.waybillService.queryByUserId(userId);
//        } else {
//            waybills = this.waybillService.queryByLicensePlateNumAndUserId(licensePlateNum, userId);
//        }


        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }

    /**
     * 根据罐体编号查询
     *
     * @param canbodyNum
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("/findbycanbodynum")
    @ResponseBody
    public ResultInfo queryByCanbodyNumAndUserId(@RequestParam("canbodyNum") String canbodyNum, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();

        List<Waybill> waybills = null;
//        if (canbodyNum.length() == 0 || canbodyNum == null) {
            waybills = this.waybillService.queryByUserId(userId);
//        } else {
//            waybills = this.waybillService.queryByCanbodyNumAndUserId(canbodyNum, userId);
//        }


        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }

    /**
     * 根据押运员姓名查询
     *
     * @param escortName
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("/findbyescortname")
    @ResponseBody
    public ResultInfo queryByEscortNameAndUserId(@RequestParam("escortName") String escortName, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();

        List<Waybill> waybills = null;
//        if (escortName.length() == 0 || escortName == null) {
            waybills = this.waybillService.queryByUserId(userId);
//        } else {
//            waybills = this.waybillService.queryByEscortNameAndUserId(escortName, userId);
//        }


        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }

    @PostMapping("/findbyegoodsname")
    @ResponseBody
    public ResultInfo queryByGoodsNameAndUserId(@RequestParam("goodsName") String goodsName, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();

        List<Waybill> waybills = null;
//        if (goodsName.length() == 0 || goodsName == null) {
//            waybills = this.waybillService.queryByUserId(userId);
//        } else {
            waybills = this.waybillService.queryByGoodsNameAndUserId(goodsName, userId);
//        }


        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }


    /**
     * 生成二维码
     *
     * @return
     */
    @GetMapping(value = "/qrimage")
    public ResponseEntity<byte[]> getQRImage(@RequestParam String codestr) {
//
        //二维码内的信息
        String info = codestr;

        byte[] qrcode = null;
        try {
            qrcode = QRCodeGenerator.getQRCodeImage(info, 200, 200);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }

        // Set headers
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(qrcode, headers, HttpStatus.CREATED);
    }


//    @PostMapping("/findbygoodname")
//    @ResponseBody
//    public   ResultInfo queryByGoodsName(@RequestParam("goodsName") String goodsName) throws  Exception{
//        //验证码错误
//        ResultInfo info = new ResultInfo();
//        List<Goods> goodsList = this.goodsService.queryByGoodsName(goodsName);
//        try {
//            info.setResult_code(0);
//            info.setResult_data(goodsList);
//            info.setResult_msg("成功");
//        }catch (Exception e){
//            info.setResult_code(1);
//            info.setResult_data(goodsList);
//            info.setResult_msg("失败");
//        }
//        return info;
//    }

}
