package com.huagongwuliu.waybillelectronic.service;


import com.huagongwuliu.waybillelectronic.mapper.ShipperMapper;
import com.huagongwuliu.waybillelectronic.pojo.Shipper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipperService {
    @Autowired
    private ShipperMapper shipperMapper;



    Shipper insertShipper(Shipper shipper) throws  Exception{
        return  this.shipperMapper.insertShipper(shipper);
    }


}
