package com.huagongwuliu.waybillelectronic.controller;

import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;
import com.huagongwuliu.waybillelectronic.constant.Constants;
import com.huagongwuliu.waybillelectronic.constant.ErrorCode;
import com.huagongwuliu.waybillelectronic.pojo.*;
import com.huagongwuliu.waybillelectronic.service.GoodsService;
import com.huagongwuliu.waybillelectronic.service.ShipperService;
import com.huagongwuliu.waybillelectronic.service.WaybillLogService;
import com.huagongwuliu.waybillelectronic.service.WaybillService;
import com.huagongwuliu.waybillelectronic.utils.DateUtil;
import com.huagongwuliu.waybillelectronic.utils.QRCodeGenerator;
import com.huagongwuliu.waybillelectronic.utils.StringUtil;
import com.huagongwuliu.waybillelectronic.utils.WaybillUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/waybill")
@Api(value = "WaybillController|一个用来测试swagger注解的控制器")
public class WaybillController {

    @Autowired
    private WaybillService waybillService;

    @Autowired
    private ShipperService shipperService;


    @Autowired
    private WaybillLogService waybillLogService;


    @Autowired
    private GoodsService goodsService;

    @GetMapping("/findall")
    public Result findAll() {
        log.info(Constants.REQ + ErrorCode.SUCCESS.getCode() + "|waybill/findall|运单findall接口");
        try {
            List<Waybill> waybills = waybillService.findAll();
            return new Result(ErrorCode.SUCCESS, waybills);
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/findall|运单findall接口报错：", e);
            return new Result(ErrorCode.E_10001);
        }
    }


    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/findwaybillsbyid")
    @ResponseBody
    public Result findwaybillsById(@RequestParam("id") Long id) throws Exception {
        log.info(Constants.REQ + ErrorCode.SUCCESS.getCode() + "|waybill/findwaybillsbyid|运单findwaybillsbyid接口");
        try {
            Waybill waybill = waybillService.queryById(id);
            return new Result(ErrorCode.SUCCESS, waybill);
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/findwaybillsbyid|运单findwaybillsbyid接口报错：", e);
            return new Result(ErrorCode.E_10001);
        }
    }

//    @RequestMapping("/yd")
//    public String aa(@RequestParam("id") Long id, @RequestParam("isAuth") String isAuth, ModelMap modelMap) {
//        Waybill waybill = null;
//        try {
//            waybill = this.waybillService.queryById(id);
//            modelMap.addAttribute("yd", waybill);
//        } catch (Exception e) {
//            log.error(Constants.RES + "|waybill/yd| ：运单界面", e);
//            e.printStackTrace();
//        }
//        return "yd";
//    }


    @RequestMapping("/yd")
    public void tbbb(HttpServletResponse response,@RequestParam("id") Long id, @RequestParam("isAuth") String isAuth){
        try{
            String uStr = "http://tms.huagongwuliu.com/wb/#/id=" +  id;
            response.sendRedirect(uStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }











    @RequestMapping("/yundan")
    public String queryYundan(@RequestParam("waybillCode") String waybillCode, @RequestParam("isAuth") String isAuth, ModelMap modelMap) {
        Waybill waybill = null;
        try {
            waybill = this.waybillService.queryByWaybillCode(waybillCode);
            modelMap.addAttribute("yd", waybill);
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/yd| ：运单界面", e);
            e.printStackTrace();
        }

        return "yd";
    }








    @PostMapping("/findbyidanduserid")
    @ResponseBody
    public ResultInfo findwaybillsByIdAndUserId(@RequestParam("id") String id, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        ResultInfo info = new ResultInfo();
        List<Waybill> waybills = this.waybillService.queryByIdAndUserId(id, userId);
        try {
            info.setResult_code(0);
            info.setResult_data(waybills);
            info.setResult_msg("成功");
        } catch (Exception e) {
            log.error("报错:" + e.getMessage());
            info.setResult_code(1);
            info.setResult_data(waybills);
            info.setResult_msg("失败");
        }
        return info;
    }


    /**
     * 后台查询所有数据
     *
     * @param waybill 运单模型
     * @return
     */
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/backstagelist")
    @ResponseBody
    public Result querylistbackstagelist(@RequestBody Waybill waybill) {

        try {
            PageInfo<Waybill> waybills = this.waybillService.querylistbackstagelist(waybill);
            return new Result(ErrorCode.SUCCESS, waybills);
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/list|获取列表：", e);
            return new Result(ErrorCode.E_10001);
        }
    }


    @PostMapping("/list")
    @ResponseBody
    public Result findwaybillsUserId(@RequestParam String userId, @RequestParam int pageNum, @RequestParam int pageSize) {

        if (userId.length() == 0 || userId == null) {
            return new Result(1001, "请输入用户id", null);
        }
        try {
            PageInfo<Waybill> waybills = this.waybillService.queryByUserId(userId, pageNum, pageSize);
            return new Result(ErrorCode.SUCCESS, waybills);
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/list|获取列表：", e);
            return new Result(ErrorCode.E_10001);
        }
    }


    /**
     * 根据用户id，查询搜索
     *
     * @param waybill
     * @return
     */
    @PostMapping("/querywaybilllist")
    @ResponseBody
    public Result queryByWaybill(@RequestBody Waybill waybill) {

        if (waybill.getUserId() == null || waybill.getUserId().length() == 0) {
            new Result(ErrorCode.E_10001, "请输入用户id");
        }
        try {
            List<Waybill> waybills = this.waybillService.queryByWaybill(waybill);
            return new Result(ErrorCode.SUCCESS, waybills);
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/list|获取列表：", e);
            return new Result(ErrorCode.E_10001);
        }
    }


    @GetMapping("/findall1")
    public Result findAll1() {
        log.info(Constants.REQ + ErrorCode.SUCCESS.getCode() + "|waybill/findall|运单findall接口");
        try {
            List<Waybill> waybills = waybillService.findAll();
            return new Result(ErrorCode.SUCCESS, waybills);
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/findall|运单findall接口报错：", e);
            return new Result(ErrorCode.E_10001);
        }
    }

    @PostMapping("/addwaybill")
    @ResponseBody
    public Result findwaybillsUserId(@RequestBody Waybill waybill) throws Exception {
        //验证码错误
        Result info = new Result();
        log.info(Constants.REQ + ErrorCode.SUCCESS.getCode() + "|waybill/findall|运单findall接口");
        if (waybill.getUserId() == null || waybill.getUserId().length() == 0) {
            info.setResult_code(1);
            info.setResult_msg("请输入用户id");
            return info;
        }

        String  carriageLicensekeyNum = StringUtil.getNumeric(waybill.getCarriageLicensekey()); //承运人许可证号，获取里面的数字许可证号
        if (carriageLicensekeyNum.length() < 7) {
            info.setResult_code(1);
            info.setResult_msg("请正确输入承运人许可证号");
            return info;
        }

        try {


            Integer serialNum = this.waybillService.queryCountByUserIdAndCreteDate(waybill.getUserId());
            waybill.setId(WaybillUtils.getUserCode().toString());
            waybill.setAddTime(DateUtil.getNowTimestamp());
            waybill.setStatus("0");
            waybill.setWaybillCode(WaybillUtils.creatYDOrderNum(carriageLicensekeyNum, serialNum + 1));
            if (!StringUtil.isEmpty(waybill.getLicensePlateNum())) {
                waybill.setLicensePlateNum(waybill.getLicensePlateNum().toUpperCase());
            }
            if (!StringUtil.isEmpty(waybill.getTrailerNum())) {
                waybill.setTrailerNum(waybill.getTrailerNum().toUpperCase());
            }

            int recode = this.waybillService.insertWaybillByWaubillObj(waybill);
            if (recode == 0) {
                info.setResult_code(1);
                info.setResult_msg("失败");
            } else {
                info.setResult_code(ErrorCode.SUCCESS.getCode());
                info.setResult_msg("成功");
                info.setResult_data(waybill);
            }

//            List<Waybill> waybillList = this.waybillService.queryByWaybillCodeAndUserId(waybill.getWaybillCode(), waybill.getUserId());
//            if (waybillList.size() > 0) {
//                Waybill waybill1 = waybillList.get(0);
//                info.setResult_data(waybill1);
//            } else {
//                info.setResult_data(recode);
//            }
            return info;
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/addwaybill|插入数据的接口：", e);
            return new Result(ErrorCode.E_10001);
        }

    }


    @PostMapping("/updatewaybill")
    @ResponseBody
    public Result updateWaybill(@RequestBody Waybill waybill) throws Exception {
        //验证码错误
        Result info = new Result();

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
            if (!StringUtil.isEmpty(waybill.getLicensePlateNum())) {
                waybill.setLicensePlateNum(waybill.getLicensePlateNum().toUpperCase());
            }


            if (!StringUtil.isEmpty(waybill.getTrailerNum())) {
                waybill.setTrailerNum(waybill.getTrailerNum().toUpperCase());
            }


            int recode = this.waybillService.updateWaybillByWaubillObj(waybill);
            if (recode == 0) {
                info.setResult_code(1);
                info.setResult_msg("请求失败");
            } else {
                info.setResult_code(0);
                info.setResult_msg("成功");
            }
            info.setResult_data(recode);
            return info;
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/updatewaybill|更新数据的接口：", e);
            return new Result(ErrorCode.E_10001);
        }
    }



    @PostMapping("/deletequery")
    @ResponseBody
    public Result deletequeryTipsWaybill(@RequestBody Waybill waybill) throws Exception {
        //验证码错误
        Result info = new Result();

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
            if (!StringUtil.isEmpty(waybill.getLicensePlateNum())) {
                waybill.setLicensePlateNum(waybill.getLicensePlateNum().toUpperCase());
            }


            if (!StringUtil.isEmpty(waybill.getTrailerNum())) {
                waybill.setTrailerNum(waybill.getTrailerNum().toUpperCase());
            }


            int recode = this.waybillService.deletequeryTipsWaybill(waybill);
            if (recode == 0) {
                info.setResult_code(1);
                info.setResult_msg("请求失败");
            } else {
                info.setResult_code(0);
                info.setResult_msg("成功");
            }
            info.setResult_data(recode);
            return info;
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/updatewaybill|更新数据的接口：", e);
            return new Result(ErrorCode.E_10001);
        }
    }





    @PostMapping("/status")
    @ResponseBody
    public Result changeWaybillStatus(@RequestParam String status, @RequestParam(value =  "goodsNum", required = false) String goodsNum, @RequestParam Long id) {
        //验证码错误
        Result info = new Result();

        if (status == null || status.length() == 0) {
            status = "0";
        }
        if ((!"4".equals(status)) && (!"6".equals(status))) {
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
                    case "6":
                        str = "电子运单删除成功";
                        break;

                }
                info.setResult_code(0);
                info.setResult_data(str);
                info.setResult_msg("成功");
            }
//            info.setResult_data(recode);
            return info;
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/status|运单状态：", e);
            return new Result(ErrorCode.E_10001);
        }
    }



    @PostMapping("/3.4.8/status")
    @ResponseBody
    public Result changeWaybillStatus347(@RequestParam String status, @RequestParam(value =  "goodsNum", required = false) String goodsNum, @RequestParam(value = "id",required = true) Long id,  @RequestParam(value = "userId",required = true) String userId) {
        //验证码错误
        Result info = new Result();


        if (id == 0) {
            return   new Result(ErrorCode.E_10001, "请输入电子运单用户id");
        }
        if (userId.isEmpty()){
            return   new Result(ErrorCode.E_10001, "请输入用户id");
        }


        if (status == null || status.length() == 0) {
            status = "0";
        }
        if ((!"4".equals(status)) && (!"6".equals(status))) {
            status = String.valueOf(Integer.parseInt(status) + 1);
        }

        try {
            int recode = this.waybillService.changeStatusAction348(status, goodsNum, id,userId);
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
                    case "6":
                        str = "电子运单删除成功";
                        break;

                }
                info.setResult_code(0);
                info.setResult_data(str);
                info.setResult_msg("成功");
            }
//            info.setResult_data(recode);
            return info;
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/status|运单状态：", e);
            return new Result(ErrorCode.E_10001);
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
    public Result queryByShipperNameAndUserId(@RequestParam("shipperName") String shipperName, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        Result info = new Result();
        List<Waybill> waybills = null;


        try {
            waybills = this.waybillService.queryByShipperNameAndUserId(shipperName, userId);
            List<Waybill> list = waybills.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getShipperName() + ";"
                    + o.getShipperContact() + ";" + o.getShipperPhone()))), ArrayList::new));//o代表属性值，根据此属性值去重

            info.setResult_code(0);
            info.setResult_data(list);
            info.setResult_msg("成功");
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/status|运单状态：", e);
            info.setResult_code(ErrorCode.E_10001.getCode());
            info.setResult_data(null);
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
    public Result queryByShiptoNameAndUserId(@RequestParam("shiptoName") String shiptoName, @RequestParam("userId") String userId) {
        //验证码错误
        Result info = new Result();
        List<Waybill> waybills = null;
        try {
            waybills = this.waybillService.queryByShiptoNameAndUserId(shiptoName, userId);
            List<Waybill> list = waybills.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getShiptoName() + ";"
                    + o.getShiptoPhone()))), ArrayList::new));//o代表属性值，根据此属性值去重
            info.setResult_code(0);
            info.setResult_data(list);
            info.setResult_msg("成功");
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/findbyshiptoname|根据收货人查询：", e);
            info.setResult_code(ErrorCode.E_10001.getCode());
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
    public Result queryByShipmentNameAndUserId(@RequestParam("shipmentName") String shipmentName, @RequestParam("userId") String userId) throws Exception {
        //验证码错误
        Result info = new Result();
        List<Waybill> waybills = null;

        try {
            waybills = this.waybillService.queryByShipmentNameAndUserId(shipmentName, userId);
            List<Waybill> list = waybills.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getShipmentName() + ";"
                    + o.getShipmentPhone() + ";" + o.getShipmentFromAddress() + ";" + o.getShipmentStarttime() + ";" + o.getShipmentFromDetails() + ";" + o.getCityExpress() + ";" + o.getShipmentToAddress() + ";" + o.getShipmentToDetails()))), ArrayList::new));//o代表属性值，根据此属性值去重

            info.setResult_code(0);
            info.setResult_data(list);
            info.setResult_msg("成功");
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(null);
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

        try {

            waybills = this.waybillService.queryByCarriageNameAndUserId(carriageName, userId);
            List<Waybill> list = waybills.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getCarriageName() + ";"
                    + o.getCarriagePhone() + ";" + o.getCarriageLicensekey()))), ArrayList::new));//o代表属性值，根据此属性值去重
            info.setResult_code(0);
            info.setResult_data(list);
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
        try {
            waybills = this.waybillService.queryByLicensePlateNumAndUserId(licensePlateNum, userId);
            List<Waybill> list = waybills.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getLicensePlateNum() + ";"
                    + o.getLicensePlateColor() + ";" + o.getRoadTransportPermitNum() + ";" + o.getTrailerNum() + ";" + o.getTrailerRoadRansportPermit() + ";" + o.getCanbodyNum() + ";"
                    + o.getCanbodyVolume() + ";" + o.getDriverName() + ";" + o.getDriverCertificate() + ";" + o.getDriverPhone() + ";"
                    + o.getEscortName() + ";" + o.getEscortCertificate() + ";" + o.getEscortPhone()))), ArrayList::new));
            info.setResult_code(0);
            info.setResult_data(list);
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
        try {
            waybills = this.waybillService.queryByCanbodyNumAndUserId(canbodyNum, userId);
            List<Waybill> list = waybills.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getLicensePlateNum() + ";"
                    + o.getLicensePlateColor() + ";" + o.getRoadTransportPermitNum() + ";" + o.getTrailerNum() + ";" + o.getTrailerRoadRansportPermit() + ";" + o.getCanbodyNum() + ";"
                    + o.getCanbodyVolume() + ";" + o.getDriverName() + ";" + o.getDriverCertificate() + ";" + o.getDriverPhone() + ";"
                    + o.getEscortName() + ";" + o.getEscortCertificate() + ";" + o.getEscortPhone()))), ArrayList::new));
            info.setResult_code(0);
            info.setResult_data(list);
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

        try {
            waybills = this.waybillService.queryByEscortNameAndUserId(escortName, userId);
            List<Waybill> list = waybills.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getLicensePlateNum() + ";"
                    + o.getLicensePlateColor() + ";" + o.getRoadTransportPermitNum() + ";" + o.getTrailerNum() + ";" + o.getTrailerRoadRansportPermit() + ";" + o.getCanbodyNum() + ";"
                    + o.getCanbodyVolume() + ";" + o.getDriverName() + ";" + o.getDriverCertificate() + ";" + o.getDriverPhone() + ";"
                    + o.getEscortName() + ";" + o.getEscortCertificate() + ";" + o.getEscortPhone()))), ArrayList::new));
            info.setResult_code(0);
            info.setResult_data(list);
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


        try {

            List<Goods> goodsList = this.goodsService.queryByGoodsName(goodsName,  userId);
//                List<Goods> list = goodsList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getGoodsName() + ";"
//                        + o.getUnNum() + ";" + o.getGoodsType() + ";" + o.getGoodsPackingNorms() + ";" + o.getGoodsPackingType() + ";" + o.getGoodsNum() + ";" + o.getGoodsCompany()))), ArrayList::new));//o代表属性值，根据此属性值去重

            info.setResult_code(0);
            info.setResult_data(goodsList);
            info.setResult_msg("成功");
        } catch (Exception e) {
            info.setResult_code(1);
            info.setResult_data(null);
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
    public ResponseEntity<byte[]> getQRImage(@RequestParam String codestr, @RequestParam("isAuth") String isAuth) throws Exception {
//        List<Goods> allByExcel = WaybillUtils.getAllByExcel();
//        for (Goods goods : allByExcel) {
//            this.goodsService.addGoodsData(goods);
//        }
        //二维码内的信息

        String info = codestr + "&isAuth=" + isAuth;

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







    @PostMapping("/querywaybilllog")
    @ResponseBody
    public Result queryByWaybillId(@RequestParam String waybillId) {


        if (StringUtil.isEmpty(waybillId)){
            return new Result(1001, "请输入运单id", null);
        }



        try {
            List<WaybillLog> waybillLog = this.waybillLogService.findByWaybillId(waybillId);
            return new Result(ErrorCode.SUCCESS, waybillLog);
        } catch (Exception e) {
            log.error(Constants.RES + "|waybill/list|获取列表：", e);
            return new Result(ErrorCode.E_10001);
        }
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
