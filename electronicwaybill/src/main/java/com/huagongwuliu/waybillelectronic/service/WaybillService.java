package com.huagongwuliu.waybillelectronic.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huagongwuliu.waybillelectronic.mapper.WaybillMapper;
import com.huagongwuliu.waybillelectronic.pojo.Shipper;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import com.huagongwuliu.waybillelectronic.utils.DateUtil;
import com.huagongwuliu.waybillelectronic.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WaybillService {

    @Resource
    private WaybillMapper waybillMapper;

    @Resource
    private ShipperService shipperService;

    @Resource
    private  UserService userService;


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

        return this.waybillMapper.insertWay(waybill);

    }


    public int changeStatusAction(String status, String goodsNum, Long id) {
        return this.waybillMapper.changeStatus(status, goodsNum, id);
    }


    public int changeStatusAction348(String status, String goodsNum, Long id,String userId) {
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

    ;


//
//
//    public void  deleteWaybillById(Long id) throws Exception{
//        this.waybillMapper.delete(id);
//    }
}
