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
        return this.waybillMapper.updateByPrimaryKey(waybill);
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




//
//
//    public void  deleteWaybillById(Long id) throws Exception{
//        this.waybillMapper.delete(id);
//    }
}
