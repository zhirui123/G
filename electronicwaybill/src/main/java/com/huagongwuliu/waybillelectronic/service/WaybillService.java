package com.huagongwuliu.waybillelectronic.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huagongwuliu.waybillelectronic.mapper.WaybillMapper;
import com.huagongwuliu.waybillelectronic.pojo.Shipper;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import com.huagongwuliu.waybillelectronic.pojo.WaybillLog;
import com.huagongwuliu.waybillelectronic.utils.DateUtil;
import com.huagongwuliu.waybillelectronic.utils.StringUtil;
import com.huagongwuliu.waybillelectronic.utils.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.List;

@Service
public class WaybillService {

    @Resource
    private WaybillMapper waybillMapper;

    @Resource
    private ShipperService shipperService;

    @Resource
    private  UserService userService;

    @Resource
    private  WaybillLogService waybillLogService;



    public List<Waybill> findAll() throws Exception {
        return this.waybillMapper.findAll();
    }

    public Waybill queryById(Long id) throws Exception {

        Waybill waybill = this.waybillMapper.queryById(id);

//        if (StringUtil.isNotEmpty(waybill.getUserCompanyName()) && StringUtil.isNotEmpty(waybill.getIsOfficeSealLicense())&& StringUtil.isNotEmpty(waybill.getUserId())){
//            User user = this.userService.queryByUserId(waybill.getUserId());
//            waybill.setIsAuth(user.getIsAuthentication());
//        }

        return waybill;
    }

    public  Waybill queryByWaybillCode(String waybillCode) throws  Exception{
        return  this.waybillMapper.queryByWaybillCode(waybillCode);
    }



    public List<Waybill> queryByIdAndUserId(Long id, String userId) throws Exception {
        List<Waybill> ws = this.waybillMapper.queryByIdAndUserId(id, userId);
        System.out.println(ws);
        return ws;
    }


    public PageInfo<Waybill> queryByUserId(String userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Waybill> waybills = this.waybillMapper.queryByUserId(userId);
        PageInfo<Waybill> pageInfo = new PageInfo<>(waybills);
        return pageInfo;
    }

    public int updateWaybillByWaubillObj(Waybill waybill) throws Exception {
        return this.waybillMapper.updateByPrimaryKeySelective(waybill);
    }

    public Integer insertWaybillByWaubillObj(Waybill waybill) throws Exception {


        int i = this.waybillMapper.insertWay(waybill);

        WaybillLog waybillLog = new WaybillLog();
        waybillLog.setId(UuidUtil.getUUID());
        waybillLog.setUserId(waybill.getUserId());
        waybillLog.setWaybillId(waybill.getId().toString());
        waybillLog.setStatus(0);
        waybillLog.setContent("电子运单创建成功,待接单");
        waybillLog.setAddTime(DateUtil.getNowTimestamp());
        waybillLog.setUpdateTime(DateUtil.getNowTimestamp());

        this.waybillLogService.insertWaubillLog(waybillLog);




        return i;

    }


    public int changeStatusAction(String status, String goodsNum, Long id) {
        return this.waybillMapper.changeStatus(status, goodsNum, id);
    }


    public int changeStatusAction348(String status, String goodsNum, Long id,String userId)  {



        String str = "接单完成，等待装货";
        switch (status) {
            case "1":
                str = "接单完成，等待装货";
                break;
            case "2":
                str = "到达装货地，开始装货";
                break;
            case "3":
                str = "装货完成，装货数量"+ goodsNum+"，开始运输";
                break;
            case "4":
                str = "卸货完成，任务结束";
                break;
            case "6":
                str = "电子运单删除成功";
                break;

        }


        WaybillLog waybillLog = new WaybillLog();
        waybillLog.setId(UuidUtil.getUUID());
        waybillLog.setUserId(userId);
        waybillLog.setStatus(Integer.valueOf(status));
        waybillLog.setWaybillId(id.toString());
        waybillLog.setContent(str);
        waybillLog.setAddTime(DateUtil.getNowTimestamp());
        waybillLog.setUpdateTime(DateUtil.getNowTimestamp());

        this.waybillLogService.insertWaubillLog(waybillLog);

        return this.waybillMapper.changeStatus348(status, goodsNum, id,userId);
    }

    /**
     * 修改货物运输的数量
     *
     * @param goodsNum
     * @param id
     * @param userId
     * @return
     */
    public int changeGoodsNum(String goodsNum, Long id, String userId) {
        return this.waybillMapper.changeGoodsNum(goodsNum, id, userId);
    }

    /**
     * 根据托运人，模糊查询
     *
     * @param shipperName
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Waybill> queryByShipperNameAndUserId(String shipperName, String userId) throws Exception {
        return this.waybillMapper.queryByShipperNameAndUserId(shipperName, userId);
    }


    /**
     * 根据收货人查询
     *
     * @param shiptoName
     * @param userId
     * @return
     */
    public List<Waybill> queryByShiptoNameAndUserId(String shiptoName, String userId) throws Exception {
        return this.waybillMapper.queryByShiptoNameAndUserId(shiptoName, userId);
    }

    /**
     * 根据装货人查询
     *
     * @param shipmentName
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Waybill> queryByShipmentNameAndUserId(String shipmentName, String userId) throws Exception {
        return this.waybillMapper.queryByShipmentNameAndUserId(shipmentName, userId);
    }

    /**
     * 根据承运人查询
     *
     * @param carriageName
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Waybill> queryByCarriageNameAndUserId(String carriageName, String userId) throws Exception {
        return this.waybillMapper.queryByCarriageNameAndUserId(carriageName, userId);
    }


    /**
     * 根据车牌号查询
     *
     * @param licensePlateNum
     * @param userId
     * @return
     */

    public List<Waybill> queryByLicensePlateNumAndUserId(String licensePlateNum, String userId) throws Exception {
        return this.waybillMapper.queryByLicensePlateNumAndUserId(licensePlateNum, userId);
    }


    /**
     * 根据罐体编号查询
     *
     * @param canbodyNum
     * @param userId
     * @return
     */

    public List<Waybill> queryByCanbodyNumAndUserId(String canbodyNum, String userId) throws Exception {
        return this.waybillMapper.queryByCanbodyNumAndUserId(canbodyNum, userId);
    }


    /**
     * 根据押运员
     *
     * @param escortName
     * @param userId
     * @return
     */
    public List<Waybill> queryByEscortNameAndUserId(String escortName, String userId) throws Exception {
        return this.waybillMapper.queryByEscortNameAndUserId(escortName, userId);
    }


    /**
     * 根据货物名称查询
     *
     * @param goodsName
     * @param userId
     * @return
     */

    public List<Waybill> queryByGoodsNameAndUserId(String goodsName, String userId) {


        return this.waybillMapper.queryByGoodsNameAndUserId(goodsName, userId);
    }


    /**
     * 根据用户UserId
     * 查询
     *
     * @param userId
     * @return
     */
    public List<Waybill> queryByUserId(String userId) {
        return this.waybillMapper.queryByUserId(userId);
    }


    /**
     * 根据运单编号查询
     *
     * @param waybillCode
     * @param userId
     * @return
     */

    public List<Waybill> queryByWaybillCodeAndUserId(String waybillCode, String userId) {
        return this.waybillMapper.queryByWaybillCodeAndUserId(waybillCode, userId);
    }


    /**
     * 根据userId和create_date 查询所有数量
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public Integer queryCountByUserIdAndCreteDate(String userId) throws Exception {

        return this.waybillMapper.queryCountByUserIdAndCreteDate(userId, DateUtil.getDayStartTimestamp(), DateUtil.getDayEndTimestamp());
    }


    void addToRelatedTables(Waybill waybill) {

        try {

            if (StringUtil.isNotEmpty(waybill.getShipperName())) {

                Shipper shipper = new Shipper();
                shipper.setShipperName(waybill.getShipperName());
                shipper.setShipperContact(waybill.getShipperContact());
                shipper.setShipperPhone(waybill.getShipperPhone());
                shipper.setUserId(waybill.getUserId());
                shipper.setYdId(waybill.getWaybillCode());

                System.out.println("阿萨德红粉金刚就爱上的看法");
                this.shipperService.insertShipper(shipper);
                System.out.println("1324567890、、、、、、、、、");


            }


        } catch (Exception e) {


        }
    }


    /**
     * 后台查询所有数据
     *
     * @param waybill
     * @return
     */
    public PageInfo<Waybill> querylistbackstagelist(Waybill waybill) {
        PageHelper.startPage(waybill.pageNum, waybill.pageSize);
        List<Waybill> waybills = this.waybillMapper.queryallbackstage(waybill);
        PageInfo<Waybill> pageInfo = new PageInfo<>(waybills);
        return pageInfo;

    }


    /**
     * 根据Waybill对象中的内容，查询，userId必传
     *
     * @param waybill
     * @return
     */

    public List<Waybill> queryByWaybill(Waybill waybill) throws Exception {
        return this.waybillMapper.queryByWaybill(waybill);
    }


    /**
     *  删除电子运单，相关搜索
     * @param wy
     * @return
     */
    @Transient
    public int deletequeryTipsWaybill(Waybill wy)  throws  Exception{

        List<Waybill> waybills = this.queryByIdAndUserId(wy.getId(), wy.getUserId());
        if (waybills.size() ==0|| waybills.get(0).getUserId().isEmpty() ) {
            return  0;
        }

        Waybill way = new Waybill();
        way.setUserId(waybills.get(0).getUserId());


        if (wy.getShipperStatus().equals("1")){


            way.setShipperName(waybills.get(0).getShipperName());
            way.setShipperContact(waybills.get(0).getShipperContact());
            way.setShipperPhone(waybills.get(0).getShipperPhone());
            way.setShipperStatus("0");


            List<Waybill> wayList = this.waybillMapper.queryByWaybill(way);

            for (Waybill waybill : wayList) {
                waybill.setShipperStatus("1");

                this.waybillMapper.updateByPrimaryKeySelective(waybill);
            }
            return  1;

        }else if(wy.getShiptoStatus().equals("1")){

            way.setShiptoName(waybills.get(0).getShiptoName());
            way.setShiptoName(waybills.get(0).getShiptoPhone());
            way.setShiptoStatus("0");

            List<Waybill> wayList =   this.waybillMapper.queryByWaybill(way);
            for (Waybill waybill : wayList) {
                waybill.setShiptoStatus("1");
                this.waybillMapper.updateByPrimaryKeySelective(waybill);
            }
            return  1;
        }else if (wy.getShipmentStatus().equals("1")){

            way.setShipmentName(waybills.get(0).getShipmentName());
            way.setShipmentPhone(waybills.get(0).getShipmentPhone());
            way.setShipmentFromAddress(waybills.get(0).getShipmentFromAddress());
            way.setShipmentToAddress(waybills.get(0).getShipmentToAddress());

            way.setShipmentFromDetails(waybills.get(0).getShipmentFromDetails());
            way.setShipmentToDetails(waybills.get(0).getShipmentToDetails());
            way.setShipmentStatus("0");

            List<Waybill> wayList =   this.waybillMapper.queryByWaybill(way);
            for (Waybill waybill : wayList) {
                waybill.setShipmentStatus("1");
                this.waybillMapper.updateByPrimaryKeySelective(waybill);
            }
            return  1;




        }else  if (wy.getCarriageStatus().equals("1")){

            way.setCarriageName(waybills.get(0).getCarriageName());
            way.setCarriagePhone(waybills.get(0).getCarriagePhone());
            way.setCarriageLicensekey(waybills.get(0).getCarriageLicensekey());
            way.setCarriageStatus("0");


            List<Waybill> wayList =   this.waybillMapper.queryByWaybill(way);
            for (Waybill waybill : wayList) {
                waybill.setCarriageStatus("1");
                this.waybillMapper.updateByPrimaryKeySelective(waybill);
            }
            return  1;

        }else if(wy.getVehicleStatus().equals("1")){

            way.setLicensePlateNum(waybills.get(0).getLicensePlateNum());

            way.setLicensePlateColor(waybills.get(0).getLicensePlateColor());
            way.setRoadTransportPermitNum(waybills.get(0).getRoadTransportPermitNum());
            way.setTrailerNum(waybills.get(0).getTrailerNum());
            way.setTrailerRoadRansportPermit(waybills.get(0).getTrailerRoadRansportPermit());
            way.setCanbodyNum(waybills.get(0).getCanbodyNum());
            way.setCanbodyVolume(waybills.get(0).getCanbodyVolume());

            way.setDriverName(waybills.get(0).getDriverName());
            way.setDriverCertificate(waybills.get(0).getDriverCertificate());
            way.setDriverPhone(waybills.get(0).getDriverPhone());
            way.setEscortName(waybills.get(0).getEscortName());
            way.setEscortPhone(waybills.get(0).getEscortPhone());
            way.setEscortCertificate(waybills.get(0).getEscortCertificate());

            way.setVehicleStatus("0");

            List<Waybill> wayList =   this.waybillMapper.queryByWaybill(way);
            for (Waybill waybill : wayList) {
                waybill.setVehicleStatus("1");
                this.waybillMapper.updateByPrimaryKeySelective(waybill);
            }
            return  1;
        }else if(wy.getGoodsStatus().equals("1")){

            way.setGoodsName(waybills.get(0).getGoodsName());
            way.setUnNum(waybills.get(0).getUnNum());
            way.setGoodsType(waybills.get(0).getGoodsType());
            way.setGoodsPackingNorms(waybills.get(0).getGoodsPackingNorms());
            way.setGoodsPackingType(waybills.get(0).getGoodsPackingType());
            way.setGoodsNum(waybills.get(0).getGoodsNum());
            way.setGoodsCompany(waybills.get(0).getGoodsCompany());
            way.setGoodsStatus("0");


            List<Waybill> wayList =   this.waybillMapper.queryByWaybill(way);
            for (Waybill waybill : wayList) {
                waybill.setGoodsStatus("1");
                this.waybillMapper.updateByPrimaryKeySelective(waybill);
            }
            return  1;
        }
        return 1;

    }












//
//
//    public void  deleteWaybillById(Long id) throws Exception{
//        this.waybillMapper.delete(id);
//    }
}
