package com.huagongwuliu.waybillelectronic.service;


import com.huagongwuliu.waybillelectronic.mapper.ShipperMapper;
import com.huagongwuliu.waybillelectronic.pojo.Shipper;
import com.huagongwuliu.waybillelectronic.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperService {
    @Autowired
    private ShipperMapper shipperMapper;

    Shipper insertShipper(Shipper shipper) throws  Exception{
        this.shipperMapper.insertShipper(shipper);
        return     shipper;
    }

    /**
     * 根据托运人和用户名查询
     * @param shipperName
     * @param userId
     * @return
     * @throws Exception
     */

    public List<Shipper> queryByShipperNameAndUserId(String shipperName, String userId) throws Exception{

        if (StringUtil.isNotEmpty(shipperName)){
            return  this.shipperMapper.queryByShipperNameAndUserId(shipperName, userId);
        }else{
            return  this.shipperMapper.queryByUserId(userId);
        }
    }

    /**
     * 根据id和用户UserId删除调托运人记录
     * @param id
     * @param userId
     * @return
     */
    int removeShipperByIdAndUserId(Long id,String userId){
        return  this.shipperMapper.removeShipperByIdAndUserId(id, userId);
    }


}
