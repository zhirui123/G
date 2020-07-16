package com.huagongwuliu.waybillelectronic.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huagongwuliu.waybillelectronic.mapper.WaybillMapper;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WaybillService {

    @Resource
    private WaybillMapper waybillMapper;

    public List<Waybill> findAll() throws Exception{
        return  this.waybillMapper.findAll();
    }

    public Waybill queryById(Long id) throws  Exception{
        return  this.waybillMapper.queryById(id);
    }

    public  List<Waybill> queryByIdAndUserId(Long id,String userId) throws  Exception{


        List<Waybill>  ws =  this.waybillMapper.queryByIdAndUserId(id,userId);
        System.out.println(ws);
        return  ws;
    }


    public PageInfo<Waybill> queryByUserId(String userId, int pageNum, int pageSize) {



        PageHelper.startPage(pageNum,pageSize,"id desc");

        List<Waybill> waybills =  this.waybillMapper.queryByUserId(userId);
        PageInfo<Waybill> pageInfo = new PageInfo<>(waybills);
        return pageInfo;
    }

    public int  updateWaybillByWaubillObj(Waybill waybill) throws Exception{
        return this.waybillMapper.updateByPrimaryKeySelective(waybill);
    }
    public Integer  insertWaybillByWaubillObj(Waybill waybill) throws Exception{
        return this.waybillMapper.insert(waybill);
    }


    public  int changeStatusAction(String status,Long id) throws Exception{
        return  this.waybillMapper.changeStatus(status,id);
    }


    /**
     * 根据托运人，模糊查询
     * @param shipperName
     * @param userId
     * @return
     * @throws Exception
     */
    public   List<Waybill> queryByShipperNameAndUserId(String shipperName,String userId) throws  Exception{
        return  this.waybillMapper.queryByShipperNameAndUserId(shipperName,userId);
    }


    /**
     * 根据收货人查询
     * @param shiptoName
     * @param userId
     * @return
     */
    public   List<Waybill> queryByShiptoNameAndUserId(String shiptoName,String userId) throws  Exception{
        return  this.waybillMapper.queryByShiptoNameAndUserId(shiptoName, userId);
    }

    /**
     * 根据装货人查询
     * @param shipmentName
     * @param userId
     * @return
     * @throws Exception
     */
    public   List<Waybill> queryByShipmentNameAndUserId(String shipmentName,String userId)throws Exception{
        return  this.waybillMapper.queryByShipmentNameAndUserId(shipmentName, userId);
    }

    /**
     * 根据承运人查询
     * @param carriageName
     * @param userId
     * @return
     * @throws Exception
     */
    public   List<Waybill> queryByCarriageNameAndUserId(String carriageName,String userId)throws  Exception{
        return  this.waybillMapper.queryByCarriageNameAndUserId(carriageName, userId);
    }



    /**
     * 根据车牌号查询
     * @param licensePlateNum
     * @param userId
     * @return
     */

    public   List<Waybill> queryByLicensePlateNumAndUserId(String licensePlateNum,String userId) throws  Exception{
        return  this.waybillMapper.queryByLicensePlateNumAndUserId(licensePlateNum, userId);
    }


    /**
     * 根据罐体编号查询
     * @param canbodyNum
     * @param userId
     * @return
     */

    public   List<Waybill> queryByCanbodyNumAndUserId(String canbodyNum,String userId)throws  Exception{
        return  this.waybillMapper.queryByCanbodyNumAndUserId(canbodyNum, userId);
    }


    /**
     * 根据押运员
     * @param escortName
     * @param userId
     * @return
     */
    public List<Waybill> queryByEscortNameAndUserId(String escortName,String userId)throws Exception{
        return  this.waybillMapper.queryByEscortNameAndUserId(escortName, userId);
    }




    /**
     * 根据货物名称查询
     * @param goodsName
     * @param userId
     * @return
     */

     public List<Waybill> queryByGoodsNameAndUserId(String goodsName,String userId){
         return  this.waybillMapper.queryByGoodsNameAndUserId(goodsName, userId);
     }


    /**
     * 根据用户UserId
     * 查询
     * @param userId
     * @return
     */
    public List<Waybill> queryByUserId(String userId){
        return  this.waybillMapper.queryByUserId(userId);
    }



    /**
     * 根据运单编号查询
     * @param waybillCode
     * @param userId
     * @return
     */

    public   List<Waybill> queryByWaybillCodeAndUserId(String waybillCode,String userId){
        return this.waybillMapper.queryByWaybillCodeAndUserId(waybillCode, userId);
    }



//
//
//    public void  deleteWaybillById(Long id) throws Exception{
//        this.waybillMapper.delete(id);
//    }
}
