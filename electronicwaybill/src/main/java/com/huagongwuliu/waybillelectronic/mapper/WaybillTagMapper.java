package com.huagongwuliu.waybillelectronic.mapper;


import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaybillTagMapper {

    // 单条新增客户基本信息
    @Insert("<script>" +
            "insert into tb_eway_tag  " +
            "<trim prefix='(' suffix=')' suffixOverrides=','>" +
            "<if test='id != null'>" +
            "  id," +
            "</if>" +
            "<if test='shipperName != null'>" +
            "  shipper_name," +
            "</if>" +
            "<if test='shipperContact != null'>" +
            "  shipper_contact," +
            "</if>" +
            "<if test='shipperPhone != null'>" +
            "  shipper_phone," +
            "</if>" +
            "<if test='shiptoName != null'>" +
            "  shipto_name," +
            "</if>" +
            "<if test='shiptoPhone != null'>" +
            "  shipto_phone," +
            "</if>" +
            "<if test='shipmentName != null'>" +
            "  shipment_name," +
            "</if>" +
            "<if test='shipmentPhone != null'>" +
            "  shipment_phone," +
            "</if>" +
            "<if test='shipmentStarttime != null'>" +
            "  shipment_starttime," +
            "</if>" +
            "<if test='shipmentFromAddress != null'>" +
            "  shipment_from_address," +
            "</if>" +
            "<if test='shipmentFromDetails != null'>" +
            "  shipment_from_details," +
            "</if>" +
            "<if test='cityExpress != null'>" +
            "  city_express," +
            "</if>" +
            "<if test='shipmentToAddress != null'>" +
            "  shipment_to_address," +
            "</if>" +
            "<if test='shipmentToDetails != null'>" +
            "  shipment_to_details," +
            "</if>" +




            "<if test='carriageName != null'>" +
            "  carriage_name," +
            "</if>" +
            "<if test='carriagePhone != null'>" +
            "  carriage_phone," +
            "</if>" +
            "<if test='carriageLicensekey != null'>" +
            "  carriage_licensekey," +
            "</if>" +


            "<if test='licensePlateNum != null'>" +
            "  license_plate_num," +
            "</if>" +
            "<if test='licensePlateColor != null'>" +
            "  license_plate_color," +
            "</if>" +
            "<if test='roadTransportPermitNum != null'>" +
            "  road_transport_permit_num," +
            "</if>" +
            "<if test='trailerNum != null'>" +
            "  trailer_num," +
            "</if>" +
            "<if test='trailerRoadRansportPermit != null'>" +
            "  trailer_road_ransport_permit," +
            "</if>" +
            "<if test='canbodyNum != null'>" +
            "  canbody_num," +
            "</if>" +
            "<if test='canbodyVolume != null'>" +
            "  canbody_volume," +
            "</if>" +


            "<if test='driverName != null'>" +
            "  driver_name," +
            "</if>" +
            "<if test='driverCertificate != null'>" +
            "  driver_certificate," +
            "</if>" +
            "<if test='driverPhone != null'>" +
            "  driver_phone," +
            "</if>" +
            "<if test='escortName != null'>" +
            "  escort_name," +
            "</if>" +
            "<if test='escortPhone != null'>" +
            "  escort_phone," +
            "</if>" +
            "<if test='escortCertificate != null'>" +
            "  escort_certificate," +
            "</if>" +



            "<if test='goodsName != null'>" +
            "  goods_name," +
            "</if>" +
            "<if test='unNum != null'>" +
            "  un_num," +
            "</if>" +

            "<if test='goodsType != null'>" +
            "  goods_type," +
            "</if>" +
            "<if test='goodsPackingNorms != null'>" +
            "  goods_packing_norms," +
            "</if>" +


            "<if test='goodsPackingType != null'>" +
            "  goods_packing_type," +
            "</if>" +
            "<if test='goodsNum != null'>" +
            "  goods_num," +
            "</if>" +
            "<if test='goodsCompany != null'>" +
            "  goods_company," +
            "</if>" +

            "<if test='dispatcherName != null'>" +
            "  dispatcher_name," +
            "</if>" +
            "<if test='dispatcherDate != null'>" +
            "  dispatcher_date," +
            "</if>" +


            "<if test='userId != null'>" +
            "  user_id," +
            "</if>" +
            "<if test='shipperStatus != null'>" +
            "  shipper_status," +
            "</if>" +
            "<if test='shiptoStatus != null'>" +
            "  shipto_status," +
            "</if>" +

            "<if test='shipmentStatus != null'>" +
            "  shipment_status," +
            "</if>" +
            "<if test='vehicleStatus != null'>" +
            "  vehicle_status," +
            "</if>" +

            "<if test='goodsStatus != null'>" +
            "  goods_status," +
            "</if>" +
            "<if test='carriageStatus != null'>" +
            "  carriage_status," +
            "</if>" +

            "<if test='addTime != null'>" +
            "  add_time," +
            "</if>" +
            "<if test='updateTime != null'>" +
            "  update_time," +
            "</if>" +






            "</trim>" +
            "<trim prefix='values (' suffix=')' suffixOverrides=','>" +
            "<if test='id != null'>" +
            "   #{id}," +
            "</if>" +
            "<if test='shipperName != null'>" +
            "  #{shipperName}," +
            "</if>" +
            "<if test='shipperContact != null'>" +
            "  #{shipperContact}," +
            "</if>" +
            "<if test='shipperPhone != null'>" +
            "  #{shipperPhone}," +
            "</if>" +



            "<if test='shiptoName != null'>" +
            "  #{shiptoName}," +
            "</if>" +
            "<if test='shiptoPhone != null'>" +
            "  #{shiptoPhone}," +
            "</if>" +




            "<if test='shipmentName != null'>" +
            "  #{shipmentName}," +
            "</if>" +
            "<if test='shipmentPhone != null'>" +
            "  #{shipmentPhone}," +
            "</if>" +
            "<if test='shipmentStarttime != null'>" +
            "  #{shipmentStarttime}," +
            "</if>" +
            "<if test='shipmentFromAddress != null'>" +
            "  #{shipmentFromAddress}," +
            "</if>" +
            "<if test='shipmentFromDetails != null'>" +
            "  #{shipmentFromDetails}," +
            "</if>" +
            "<if test='cityExpress != null'>" +
            "  #{cityExpress}," +
            "</if>" +
            "<if test='shipmentToAddress != null'>" +
            "  #{shipmentToAddress}," +
            "</if>" +
            "<if test='shipmentToDetails != null'>" +
            "  #{shipmentToDetails}," +
            "</if>" +



            "<if test='carriageName != null'>" +
            "  #{carriageName}," +
            "</if>" +
            "<if test='carriagePhone != null'>" +
            "  #{carriagePhone}," +
            "</if>" +
            "<if test='carriageLicensekey != null'>" +
            "  #{carriageLicensekey}," +
            "</if>" +



            "<if test='licensePlateNum != null'>" +
            "  #{licensePlateNum}," +
            "</if>" +
            "<if test='licensePlateColor != null'>" +
            "  #{licensePlateColor}," +
            "</if>" +
            "<if test='roadTransportPermitNum != null'>" +
            "  #{roadTransportPermitNum}," +
            "</if>" +
            "<if test='trailerNum != null'>" +
            "  #{trailerNum}," +
            "</if>" +
            "<if test='trailerRoadRansportPermit != null'>" +
            "  #{trailerRoadRansportPermit}," +
            "</if>" +
            "<if test='canbodyNum != null'>" +
            "  #{canbodyNum}," +
            "</if>" +
            "<if test='canbodyVolume != null'>" +
            "  #{canbodyVolume}," +
            "</if>" +


            "<if test='driverName != null'>" +
            "  #{driverName}," +
            "</if>" +
            "<if test='driverCertificate != null'>" +
            "  #{driverCertificate}," +
            "</if>" +
            "<if test='driverPhone != null'>" +
            "  #{driverPhone}," +
            "</if>" +
            "<if test='escortName != null'>" +
            "  #{escortName}," +
            "</if>" +
            "<if test='escortPhone != null'>" +
            "  #{escortPhone}," +
            "</if>" +
            "<if test='escortCertificate != null'>" +
            "  #{escortCertificate}," +
            "</if>" +



            "<if test='goodsName != null'>" +
            "  #{goodsName}," +
            "</if>" +
            "<if test='unNum != null'>" +
            "  #{unNum}," +
            "</if>" +
            "<if test='goodsType != null'>" +
            "  #{goodsType}," +
            "</if>" +
            "<if test='goodsPackingNorms != null'>" +
            "  #{goodsPackingNorms}," +
            "</if>" +
            "<if test='goodsPackingType != null'>" +
            "  #{goodsPackingType}," +
            "</if>" +
            "<if test='goodsNum != null'>" +
            "  #{goodsNum}," +
            "</if>" +
            "<if test='goodsCompany != null'>" +
            "  #{goodsCompany}," +
            "</if>" +
            "<if test='dispatcherName != null'>" +
            "  #{dispatcherName}," +
            "</if>" +
            "<if test='dispatcherDate != null'>" +
            "  #{dispatcherDate}," +
            "</if>" +


            "<if test='userId != null'>" +
            "  #{userId}," +
            "</if>" +
            "<if test='shipperStatus != null'>" +
            "  #{shipperStatus}," +
            "</if>" +
            "<if test='shiptoStatus != null'>" +
            "  #{shiptoStatus}," +
            "</if>" +
            "<if test='shipmentStatus != null'>" +
            "  #{shipmentStatus}," +
            "</if>" +
            "<if test='vehicleStatus != null'>" +
            "  #{vehicleStatus}," +
            "</if>" +
            "<if test='goodsStatus != null'>" +
            "  #{goodsStatus}," +
            "</if>" +
            "<if test='carriageStatus != null'>" +
            "  #{carriageStatus}," +
            "</if>" +
            "<if test='addTime != null'>" +
            "  #{addTime}," +
            "</if>" +
            "<if test='updateTime != null'>" +
            "  #{updateTime}," +
            "</if>" +


            "</trim>" +
            "</script>")
    int add(Waybill waybill);



    @Update("<script>" +
            "UPDATE tb_eway_tag  " +
            "    <set>" +

            "<if test='shipperName != null'>" +
            "  shipper_name = #{shipperName}," +
            "</if>" +
            "<if test='shipperContact != null'>" +
            "  shipper_contact = #{shipperContact}," +
            "</if>" +
            "<if test='shipperPhone != null'>" +
            "  shipper_phone = #{shipperPhone}," +
            "</if>" +
            "<if test='shiptoName != null'>" +
            "  shipto_name = #{shiptoName}," +
            "</if>" +
            "<if test='shiptoPhone != null'>" +
            "  shipto_phone = #{shiptoPhone}," +
            "</if>" +


            "<if test='shipmentName != null'>" +
            "  shipment_name = #{shipmentName}," +
            "</if>" +
            "<if test='shipmentPhone != null'>" +
            "  shipment_phone = #{shipmentPhone}," +
            "</if>" +
            "<if test='shipmentStarttime != null'>" +
            "  shipment_starttime = #{shipmentStarttime}," +
            "</if>" +
            "<if test='shipmentFromAddress != null'>" +
            "  shipment_from_address = #{shipmentFromAddress}," +
            "</if>" +
            "<if test='shipmentFromDetails != null'>" +
            "  shipment_from_details = #{shipmentFromDetails}," +
            "</if>" +
            "<if test='cityExpress != null'>" +
            "  city_express = #{cityExpress}," +
            "</if>" +
            "<if test='shipmentToAddress != null'>" +
            "  shipment_to_address = #{shipmentToAddress}," +
            "</if>" +
            "<if test='shipmentToDetails != null'>" +
            "  shipment_to_details = #{shipmentToDetails}," +
            "</if>" +




            "<if test='carriageName != null'>" +
            "  carriage_name = #{carriageName}," +
            "</if>" +
            "<if test='carriagePhone != null'>" +
            "  carriage_phone = #{carriagePhone}," +
            "</if>" +
            "<if test='carriageLicensekey != null'>" +
            "  carriage_licensekey = #{carriageLicensekey}," +
            "</if>" +


            "<if test='licensePlateNum != null'>" +
            "  license_plate_num = #{licensePlateNum}," +
            "</if>" +
            "<if test='licensePlateColor != null'>" +
            "  license_plate_color = #{licensePlateColor}," +
            "</if>" +
            "<if test='roadTransportPermitNum != null'>" +
            "  road_transport_permit_num = #{roadTransportPermitNum}," +
            "</if>" +
            "<if test='trailerNum != null'>" +
            "  trailer_num = #{trailerNum}," +
            "</if>" +
            "<if test='trailerRoadRansportPermit != null'>" +
            "  trailer_road_ransport_permit = #{trailerRoadRansportPermit}," +
            "</if>" +
            "<if test='canbodyNum != null'>" +
            "  canbody_num = #{canbodyNum}," +
            "</if>" +
            "<if test='canbodyVolume != null'>" +
            "  canbody_volume = #{canbodyVolume}," +
            "</if>" +


            "<if test='driverName != null'>" +
            "  driver_name = #{driverName}," +
            "</if>" +
            "<if test='driverCertificate != null'>" +
            "  driver_certificate = #{driverCertificate}," +
            "</if>" +
            "<if test='driverPhone != null'>" +
            "  driver_phone = #{driverPhone}," +
            "</if>" +
            "<if test='escortName != null'>" +
            "  escort_name = #{escortName}," +
            "</if>" +
            "<if test='escortPhone != null'>" +
            "  escort_phone = #{escortPhone}," +
            "</if>" +
            "<if test='escortCertificate != null'>" +
            "  escort_certificate = #{escortCertificate}," +
            "</if>" +



            "<if test='goodsName != null'>" +
            "  goods_name = #{goodsName}," +
            "</if>" +
            "<if test='unNum != null'>" +
            "  un_num = #{unNum}," +
            "</if>" +

            "<if test='goodsType != null'>" +
            "  goods_type = #{goodsType}," +
            "</if>" +
            "<if test='goodsPackingNorms != null'>" +
            "  goods_packing_norms = #{goodsPackingNorms}," +
            "</if>" +


            "<if test='goodsPackingType != null'>" +
            "  goods_packing_type = #{goodsPackingType}," +
            "</if>" +
            "<if test='goodsNum != null'>" +
            "  goods_num = #{goodsNum}," +
            "</if>" +
            "<if test='goodsCompany != null'>" +
            "  goods_company = #{goodsCompany}," +
            "</if>" +

            "<if test='dispatcherName != null'>" +
            "  dispatcher_name = #{dispatcherName}," +
            "</if>" +
            "<if test='dispatcherDate != null'>" +
            "  dispatcher_date = #{dispatcherDate}," +
            "</if>" +


//            "<if test='userId != null'>" +
//            "  user_id = #{userId}," +
//            "</if>" +
            "<if test='shipperStatus != null'>" +
            "  shipper_status = #{shipperStatus}," +
            "</if>" +
            "<if test='shiptoStatus != null'>" +
            "  shipto_status = #{shiptoStatus}," +
            "</if>" +

            "<if test='shipmentStatus != null'>" +
            "  shipment_status = #{shipmentStatus}," +
            "</if>" +
            "<if test='vehicleStatus != null'>" +
            "  vehicle_status = #{vehicleStatus}," +
            "</if>" +

            "<if test='goodsStatus != null'>" +
            "  goods_status = #{goodsStatus}," +
            "</if>" +
            "<if test='carriageStatus != null'>" +
            "  carriage_status = #{carriageStatus}," +
            "</if>" +

            "<if test='addTime != null'>" +
            "  add_time = #{addTime}," +
            "</if>" +
            "<if test='updateTime != null'>" +
            "  update_time = #{updateTime}," +
            "</if>" +

            "    </set>" +
            "       WHERE id = #{id} " +
            "</script>")
    int update(Waybill waybill);



    @Select("<script>" +
            "SELECT  * from tb_eway_tag where id = #{id}" +
            "</script>")
    Waybill get(String id);


    @Delete("<script>" +
            "DELETE FROM tb_eway_tag WHERE id IN " +
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>" +
            " #{id} " +
            "</foreach>;" +
            "</script>")
    int delete(@Param("ids") List<String> ids);

    @Select("<script>" +
            " select * from tb_eway_tag where  user_id = #{userId}  and status != 6  and goods_status = 0" +
            "<if test=' goodsName != null '> and goods_name like concat('%',#{goodsName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByGoodsNameAndUserId(String goodsName, String userId);

    @Select("<script>" +
            "SELECT * from tb_eway_tag " +
            "<where>" +
            "    <if test=\"shipperName != null and shipperName != ''\"> " +
            "      AND shipper_name = #{shipperName}" +
            "    </if> " +
            "    <if test=\"shipperContact != null and shipperContact != ''\"> " +
            "      AND shipper_contact = #{shipperContact}" +
            "    </if> " +
            "    <if test=\"shipperPhone != null and shipperPhone != ''\"> " +
            "      AND shipper_phone = #{shipperPhone}" +
            "    </if> " +

            "    <if test=\"shiptoName != null and shiptoName != ''\"> " +
            "      AND shipto_name = #{shiptoName}" +
            "    </if> " +
            "    <if test=\"shiptoPhone != null and shiptoPhone != ''\"> " +
            "      AND shipto_phone = #{shiptoPhone} " +
            "    </if> " +

            "    <if test=\"shipmentName != null and shipmentName != ''\"> " +
            "      AND shipment_name = #{shipmentName}" +
            "    </if> " +
            "    <if test=\"shipmentPhone != null and shipmentPhone != ''\"> " +
            "      AND shipment_phone = #{shipmentPhone}" +
            "    </if> " +
            "    <if test=\"shipmentStarttime != null and shipmentStarttime != ''\"> " +
            "      AND shipment_starttime = #{shipmentStarttime} " +
            "    </if> " +
            "    <if test=\"shipmentFromAddress != null and shipmentFromAddress != ''\"> " +
            "      AND shipment_from_address = #{shipmentFromAddress}" +
            "    </if> " +
            "    <if test=\"shipmentFromDetails != null and shipmentFromDetails != ''\"> " +
            "      AND shipment_from_details = #{shipmentFromDetails} " +
            "    </if> " +
            "    <if test=\"cityExpress != null \"> " +
            "      AND city_express = #{cityExpress} " +
            "    </if> " +
            "    <if test=\"shipmentToAddress != null and shipmentToAddress != ''\"> " +
            "      AND shipment_to_address = #{shipmentToAddress} " +
            "    </if> " +
            "    <if test=\"shipmentToDetails != null and shipmentToDetails != ''\"> " +
            "      AND shipment_to_details = #{shipmentToDetails}" +
            "    </if> " +



            "    <if test=\"carriageName != null and carriageName != ''\"> " +
            "      AND carriage_name = #{carriageName} " +
            "    </if> " +
            "    <if test=\"carriagePhone != null and carriagePhone != ''\"> " +
            "      AND carriage_phone = #{carriagePhone}" +
            "    </if> " +
            "    <if test=\"carriageLicensekey != null and carriageLicensekey != ''\"> " +
            "      AND carriage_licensekey = #{carriageLicensekey}" +
            "    </if> " +



            "    <if test=\"licensePlateNum != null and licensePlateNum != ''\"> " +
            "      AND license_plate_num = #{licensePlateNum}" +
            "    </if> " +
            "    <if test=\"licensePlateColor != null and licensePlateColor != ''\"> " +
            "      AND license_plate_color = #{licensePlateColor} " +
            "    </if> " +
            "    <if test=\"roadTransportPermitNum != null and roadTransportPermitNum != ''\"> " +
            "      AND road_transport_permit_num = #{roadTransportPermitNum} " +
            "    </if> " +

            "    <if test=\"trailerNum != null and trailerNum != ''\"> " +
            "      AND trailer_num = #{trailerNum} " +
            "    </if> " +

            "    <if test=\"trailerRoadRansportPermit != null and trailerRoadRansportPermit != ''\"> " +
            "      AND trailer_road_ransport_permit = #{trailerRoadRansportPermit} " +
            "    </if> " +
            "    <if test=\"canbodyNum != null and canbodyNum != ''\"> " +
            "      AND canbody_num = #{canbodyNum} " +
            "    </if> " +
            "    <if test=\"canbodyVolume != null and canbodyVolume != ''\"> " +
            "      AND canbody_volume = #{canbodyVolume} " +
            "    </if> " +

            "    <if test=\"driverName != null and driverName != ''\"> " +
            "      AND driver_name = #{driverName}  " +
            "    </if> " +
            "    <if test=\"driverCertificate != null and driverCertificate != ''\"> " +
            "      AND driver_certificate = #{driverCertificate}  " +
            "    </if> " +
            "    <if test=\"driverPhone != null and driverPhone != ''\"> " +
            "      AND driver_phone = #{driverPhone}  " +
            "    </if> " +
            "    <if test=\"escortName != null and escortName != ''\"> " +
            "      AND escort_name = #{escortName}  " +
            "    </if> " +
            "    <if test=\"escortPhone != null and escortPhone != ''\"> " +
            "      AND escort_phone = #{escortPhone}  " +
            "    </if> " +
            "    <if test=\"escortCertificate != null and escortCertificate != ''\"> " +
            "      AND escort_certificate = #{escortCertificate}  " +
            "    </if> " +


            "    <if test=\"goodsName != null and goodsName != ''\"> " +
            "      AND goods_name = #{goodsName}  " +
            "    </if> " +
            "    <if test=\"unNum != null and unNum != ''\"> " +
            "      AND un_num = #{unNum}  " +
            "    </if> " +
            "    <if test=\"goodsType != null and goodsType != ''\"> " +
            "      AND goods_type = #{goodsType}  " +
            "    </if> " +
            "    <if test=\"goodsPackingNorms != null and goodsPackingNorms != ''\"> " +
            "      AND goods_packing_norms = #{goodsPackingNorms}  " +
            "    </if> " +
            "    <if test=\"goodsPackingType != null and goodsPackingType != ''\"> " +
            "      AND goods_packing_type = #{goodsPackingType}  " +
            "    </if> " +
            "    <if test=\"goodsNum != null and goodsNum != ''\"> " +
            "      AND goods_num = #{goodsNum}  " +
            "    </if> " +
            "    <if test=\"goodsCompany != null and goodsCompany != ''\"> " +
            "      AND goods_company = #{goodsCompany}  " +
            "    </if> " +
            "    <if test=\"dispatcherName != null and dispatcherName != ''\"> " +
            "      AND dispatcher_name = #{dispatcherName}  " +
            "    </if> " +
            "    <if test=\"dispatcherDate != null and dispatcherDate != ''\"> " +
            "      AND dispatcher_date = #{dispatcherDate}  " +
            "    </if> " +
//            "    <if test=\"userId != null and userId != ''\"> " +
            "      AND user_id = #{userId}  " +
//            "    </if> " +
            "    <if test=\"shipperStatus != null \"> " +
            "      AND shipper_status = #{shipperStatus}  " +
            "    </if> " +
            "    <if test=\"shipmentStatus != null \"> " +
            "      AND shipment_status = #{shipmentStatus}  " +
            "    </if> " +
            "    <if test=\"vehicleStatus != null \"> " +
            "      AND vehicle_status = #{vehicleStatus}  " +
            "    </if> " +
            "    <if test=\"goodsStatus != null \"> " +
            "      AND goods_status = #{goodsStatus}  " +
            "    </if> " +
            "    <if test=\"carriageStatus != null \"> " +
            "      AND carriage_status = #{carriageStatus}  " +
            "    </if> " +
            "</where>" +
            "ORDER BY add_time DESC " +
            "</script>")
    List<Waybill> list(Waybill waybill);
}
