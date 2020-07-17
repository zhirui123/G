package com.huagongwuliu.waybillelectronic.mapper;

import com.huagongwuliu.waybillelectronic.pojo.Shipper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShipperMapper {

    @Select("select * from yd_shipper where id = #{id}")
     Shipper queryById(Long id);

    @Select("select * from yd_shipper where user_id = #{userId}")
     List<Shipper>  queryByUserId(String userId);


    @Select("select * from yd_shipper where user_id = #{userId} and yd_id = #{ydId}")
    List<Shipper>  queryByUserIdAndYdId(String userId,Long ydId);

    @Select("select * from yd_shipper where shipper_name = #{shipperName} and  user_id = #{userId}")
    List<Shipper>  queryByShipperNameAndUserId(String shipperName, String userId);

    @Insert("insert into yd_shipper (shipper_name,shipper_contact,shipper_phone,user_id,ydId) VALUES(#{shipper.shipperName},#{shipper.shipperContact},#{shipper.shipperPhone},#{shipper.userId},#{shipper.ydId})")
    @Options(useGeneratedKeys=true, keyProperty="shipper.id")
    Shipper insertShipper(@Param("shipper") Shipper shipper);

}
