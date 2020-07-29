package com.huagongwuliu.waybillelectronic.mapper;

import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//@Mapper
//public interface WaybillMapper  extends tk.mybatis.mapper.common.Mapper<Waybill> {
//    void delete(Long id);
//}
//@Mapper
//@Repository
@Mapper
public interface WaybillMapper   extends tk.mybatis.mapper.common.Mapper<Waybill> {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from tb_eway")
    List<Waybill> findAll();

    @Select("select * from tb_eway where id = #{id}")
    Waybill queryById(Long id);

    @Select("select * from tb_eway where id = #{id} and user_id = #{userId}")
    List<Waybill> queryByIdAndUserId(Long id,String userId);

    @Select("select * from tb_eway where user_id = #{userId}  order by add_time desc")
    List<Waybill> queryByUserId(String userId);





    @Select("SELECT COUNT(1)  FROM tb_eway  WHERE user_id = #{userId} and add_time >= #{dayStartTimestamp} and add_time <= #{dayEndTimestamp}")
    Integer queryCountByUserIdAndCreteDate(String userId ,Long dayStartTimestamp,Long dayEndTimestamp);




    @Update("<script>" +
            " UPDATE tb_eway " +
            "<set>" +
            "<if test=' status != null '> status = #{status}, </if>" +
            "<if test=' goodsNum != 0 '> goods_num = #{goodsNum} </if>" +
            "</set>" +
            "where id = #{id}" +
            "</script>")
    int changeStatus(@Param("status") String status,@Param("goodsNum") int goodsNum, Long id);

    /**
     * 更改货物运输的数量
     * @param goodsNum
     * @param id
     * @param userId
     * @return
     */
    @Update("UPDATE tb_eway SET goods_num = #{goodsNum}  where id = #{id} and  user_id = #{userId}")
    int changeGoodsNum(String goodsNum,Long id,String userId);


    /**
     * 根据托运人查询
     * @param shipperName
     * @param userId
     * @return
     */



    @Select( "<script>" +
            " select * from tb_eway where  user_id = #{userId} and shipper_status = 0" +
            "<if test=' shipperName != null '> and shipper_name like concat('%',#{shipperName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByShipperNameAndUserId(String shipperName,String userId);


    /**
     * 根据收货人查询
     * @param shiptoName
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where shipto_name LIKE concat('%',#{shiptoName},'%') and user_id = #{userId}  and shipto_status = 0 order by add_time desc")


    @Select( "<script>" +
            " select * from tb_eway where  user_id = #{userId} and shipto_status = 0" +
            "<if test=' shiptoName != null '> and shipto_name like concat('%',#{shiptoName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByShiptoNameAndUserId(String shiptoName,String userId);


    /**
     * 根据装货人名称查询
     * @param shipmentName
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where shipment_name LIKE concat('%',#{shipmentName},'%') and user_id = #{userId}  and shipment_status = 0 order by add_time desc")

    @Select( "<script>" +
            " select * from tb_eway where  user_id = #{userId} and shipment_status = 0" +
            "<if test=' shipmentName != null '> and shipment_name like concat('%',#{shipmentName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByShipmentNameAndUserId(String shipmentName,String userId);


    /**
     * 根据承运人查询
     * @param carriageName
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where carriage_name LIKE concat('%',#{carriageName},'%') and user_id = #{userId}  and carriage_status = 0 order by add_time desc")
    @Select( "<script>" +
            " select * from tb_eway where  user_id = #{userId} and carriage_status = 0" +
            "<if test=' carriageName != null '> and carriage_name like concat('%',#{carriageName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByCarriageNameAndUserId(String carriageName,String userId);

    /**
     * 根据车牌号查询
     * @param licensePlateNum
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where license_plate_num LIKE concat('%',#{licensePlateNum},'%') and user_id = #{userId}  and vehicle_status = 0 order by add_time desc")
    @Select( "<script>" +
            " select * from tb_eway where  user_id = #{userId} and vehicle_status = 0" +
            "<if test=' licensePlateNum != null '> and license_plate_num like concat('%',#{licensePlateNum},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByLicensePlateNumAndUserId(String licensePlateNum,String userId);


    /**
     * 根据罐体编号查询
     * @param canbodyNum
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where canbody_num LIKE concat('%',#{canbodyNum},'%') and user_id = #{userId}  and vehicle_status = 0 order by add_time desc")

    @Select( "<script>" +
            " select * from tb_eway where  user_id = #{userId} and vehicle_status = 0" +
            "<if test=' canbodyNum != null '> and canbody_num like concat('%',#{canbodyNum},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByCanbodyNumAndUserId(String canbodyNum,String userId);


    /**
     * 根据押运员
     * @param escortName
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where escort_name LIKE concat('%',#{escortName},'%') and user_id = #{userId}  and vehicle_status = 0 order by add_time desc")

    @Select( "<script>" +
            " select * from tb_eway where  user_id = #{userId} and vehicle_status = 0" +
            "<if test=' escortName != null '> and escort_name like concat('%',#{escortName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByEscortNameAndUserId(String escortName,String userId);


    /**
     * 根据货物名称查询
     * @param goodsName
     * @param userId
     * @return
     */

//    @Select("select * from tb_eway where goods_name LIKE concat('%',#{goodsName},'%') and user_id = #{userId}  and goods_status = 0 order by add_time desc")

    @Select( "<script>" +
            " select * from tb_eway where  user_id = #{userId} and goods_status = 0" +
            "<if test=' goodsName != null '> and goods_name like concat('%',#{goodsName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByGoodsNameAndUserId(String goodsName,String userId);



    /**
     * 根据运单编号查询
     * @param waybillCode
     * @param userId
     * @return
     */
    @Select("select * from tb_eway where waybill_code LIKE concat('%',#{waybillCode},'%') and user_id = #{userId} order by add_time desc")
    List<Waybill> queryByWaybillCodeAndUserId(String waybillCode,String userId);

    @Select( "<script>" +
            " select * from tb_eway where  1=1  " +
            "<if test=' waybill.shipperName != null '>and shipper_name like concat('%',#{waybill.shipperName},'%') </if>" +
            "<if test=' waybill.shipperContact != null '>and shipper_contact like concat('%',#{waybill.shipperContact},'%') </if>" +
            "<if test=' waybill.shipperPhone != null '>and shipper_phone like concat('%',#{waybill.shipperPhone},'%') </if>" +

            "<if test=' waybill.shiptoName != null '>and shipto_name like concat('%',#{waybill.shiptoName},'%') </if>" +
            "<if test=' waybill.shiptoPhone != null '>and shipto_phone like concat('%',#{waybill.shiptoPhone},'%') </if>" +

            "<if test=' waybill.shipmentName != null '>and shipment_name like concat('%',#{waybill.shipmentName},'%') </if>" +
            "<if test=' waybill.shipmentPhone != null '>and shipment_phone like concat('%',#{waybill.shipmentPhone},'%') </if>" +
            "<if test=' waybill.shipmentStarttime != null '>and shipment_starttime like concat('%',#{waybill.shipmentStarttime},'%') </if>" +
            "<if test=' waybill.shipmentFromAddress != null '>and shipment_from_address like concat('%',#{waybill.shipmentFromAddress},'%') </if>" +
            "<if test=' waybill.shipmentFromDetails != null '>and shipment_from_details like concat('%',#{waybill.shipmentFromDetails},'%') </if>" +
            "<if test=' waybill.cityExpress != null '>and city_express like concat('%',#{waybill.cityExpress},'%') </if>" +
            "<if test=' waybill.shipmentToAddress != null '>and shipment_to_address like concat('%',#{waybill.shipmentToAddress},'%') </if>" +
            "<if test=' waybill.shipmentToDetails != null '>and shipment_to_details like concat('%',#{waybill.shipmentToDetails},'%') </if>" +

            "<if test=' waybill.carriageName != null '>and carriage_name like concat('%',#{waybill.carriageName},'%') </if>" +
            "<if test=' waybill.carriagePhone != null '>and carriage_phone like concat('%',#{waybill.carriagePhone},'%') </if>" +
            "<if test=' waybill.carriageLicensekey != null '>and carriage_licensekey like concat('%',#{waybill.carriageLicensekey},'%') </if>" +

            "<if test=' waybill.licensePlateNum != null '>and license_plate_num like concat('%',#{waybill.licensePlateNum},'%') </if>" +
            "<if test=' waybill.licensePlateColor != null '>and license_plate_color like concat('%',#{waybill.licensePlateColor},'%') </if>" +
            "<if test=' waybill.roadTransportPermitNum != null '>and road_transport_permit_num like concat('%',#{waybill.roadTransportPermitNum},'%') </if>" +
            "<if test=' waybill.trailerRoadRansportPermit != null '>and trailer_road_ransport_permit like concat('%',#{waybill.trailerRoadRansportPermit},'%') </if>" +
            "<if test=' waybill.canbodyNum != null '>and canbody_num like concat('%',#{waybill.canbodyNum},'%') </if>" +
            "<if test=' waybill.driverName != null '>and driver_name like concat('%',#{waybill.driverName},'%') </if>" +
            "<if test=' waybill.driverCertificate != null '>and driver_certificate like concat('%',#{waybill.driverCertificate},'%') </if>" +
            "<if test=' waybill.driverPhone != null '>and driver_phone like concat('%',#{waybill.driverPhone},'%') </if>" +
            "<if test=' waybill.escortName != null '>and escort_name like concat('%',#{waybill.escortName},'%') </if>" +
            "<if test=' waybill.escortPhone != null '>and escort_phone like concat('%',#{waybill.escortPhone},'%') </if>" +
            "<if test=' waybill.escortCertificate != null '>and escort_certificate like concat('%',#{waybill.escortCertificate},'%') </if>" +

            "<if test=' waybill.goodsName != null '>and goods_name like concat('%',#{waybill.goodsName},'%') </if>" +
            "<if test=' waybill.unNum != null '>and un_num like concat('%',#{waybill.unNum},'%') </if>" +
            "<if test=' waybill.goodsType != null '>and goods_type like concat('%',#{waybill.goodsType},'%') </if>" +
            "<if test=' waybill.goodsPackingNorms != null '>and goods_packing_norms like concat('%',#{waybill.goodsPackingNorms},'%') </if>" +

            "<if test=' waybill.goodsPackingType != null '>and goods_packing_type like concat('%',#{waybill.goodsPackingType},'%') </if>" +
            "<if test=' waybill.goodsNum != null '>and goods_num like concat('%',#{waybill.goodsNum},'%') </if>" +
            "<if test=' waybill.goodsCompany != null '>and goods_company like concat('%',#{waybill.goodsCompany},'%') </if>" +

            "<if test=' waybill.dispatcherName != null '>and dispatcher_name like concat('%',#{waybill.dispatcherName},'%') </if>" +
            "<if test=' waybill.dispatcherDate != null '>and dispatcher_date like concat('%',#{waybill.dispatcherDate},'%') </if>" +
            "<if test=' waybill.remarks != null '>and remarks like concat('%',#{waybill.remarks},'%') </if>" +

            "<if test=' waybill.userId != null '>and user_id like concat('%',#{waybill.userId},'%') </if>" +
            "<if test=' waybill.waybillCode != null '>and waybill_code like concat('%',#{waybill.waybillCode},'%') </if>" +

            "<if test=' waybill.status != null '>and status like concat('%',#{waybill.status},'%') </if>" +
            "<if test=' waybill.userCompanyName != null '>and user_company_name like concat('%',#{waybill.userCompanyName},'%') </if>" +

            "<if test=' waybill.id > 0 '>and id = #{waybill.id} </if>" +


            "<if test=' startTime > 0 '> AND add_time <![CDATA[>=]]> #{startTime} </if>" +
            "<if test=' endTime > 0'> AND add_time <![CDATA[<=]]>#{endTime} </if>" +
            " order by add_time " +
            "<if test=' desc == true '> desc </if>" +
            "<if test=' desc == false '> asc </if>" +
            "</script>")
    List<Waybill> queryallbackstage(Waybill waybill,Long startTime, Long endTime, Boolean desc);



    @Select( "<script>" +
            " select * from tb_eway where  user_id = #{waybill.userId}  " +
            "<if test=' waybill.shipperName != null '>and shipper_name like concat('%',#{waybill.shipperName},'%') </if>" +
            "<if test=' waybill.shipperContact != null '>and shipper_contact like concat('%',#{waybill.shipperContact},'%') </if>" +
            "<if test=' waybill.shipperPhone != null '>and shipper_phone like concat('%',#{waybill.shipperPhone},'%') </if>" +

            "<if test=' waybill.shiptoName != null '>and shipto_name like concat('%',#{waybill.shiptoName},'%') </if>" +
            "<if test=' waybill.shiptoPhone != null '>and shipto_phone like concat('%',#{waybill.shiptoPhone},'%') </if>" +

            "<if test=' waybill.shipmentName != null '>and shipment_name like concat('%',#{waybill.shipmentName},'%') </if>" +
            "<if test=' waybill.shipmentPhone != null '>and shipment_phone like concat('%',#{waybill.shipmentPhone},'%') </if>" +
            "<if test=' waybill.shipmentStarttime != null '>and shipment_starttime like concat('%',#{waybill.shipmentStarttime},'%') </if>" +
            "<if test=' waybill.shipmentFromAddress != null '>and shipment_from_address like concat('%',#{waybill.shipmentFromAddress},'%') </if>" +
            "<if test=' waybill.shipmentFromDetails != null '>and shipment_from_details like concat('%',#{waybill.shipmentFromDetails},'%') </if>" +
            "<if test=' waybill.cityExpress != null '>and city_express like concat('%',#{waybill.cityExpress},'%') </if>" +
            "<if test=' waybill.shipmentToAddress != null '>and shipment_to_address like concat('%',#{waybill.shipmentToAddress},'%') </if>" +
            "<if test=' waybill.shipmentToDetails != null '>and shipment_to_details like concat('%',#{waybill.shipmentToDetails},'%') </if>" +

            "<if test=' waybill.carriageName != null '>and carriage_name like concat('%',#{waybill.carriageName},'%') </if>" +
            "<if test=' waybill.carriagePhone != null '>and carriage_phone like concat('%',#{waybill.carriagePhone},'%') </if>" +
            "<if test=' waybill.carriageLicensekey != null '>and carriage_licensekey like concat('%',#{waybill.carriageLicensekey},'%') </if>" +

            "<if test=' waybill.licensePlateNum != null '>and license_plate_num like concat('%',#{waybill.licensePlateNum},'%') </if>" +
            "<if test=' waybill.licensePlateColor != null '>and license_plate_color like concat('%',#{waybill.licensePlateColor},'%') </if>" +
            "<if test=' waybill.roadTransportPermitNum != null '>and road_transport_permit_num like concat('%',#{waybill.roadTransportPermitNum},'%') </if>" +
            "<if test=' waybill.trailerRoadRansportPermit != null '>and trailer_road_ransport_permit like concat('%',#{waybill.trailerRoadRansportPermit},'%') </if>" +
            "<if test=' waybill.canbodyNum != null '>and canbody_num like concat('%',#{waybill.canbodyNum},'%') </if>" +
            "<if test=' waybill.driverName != null '>and driver_name like concat('%',#{waybill.driverName},'%') </if>" +
            "<if test=' waybill.driverCertificate != null '>and driver_certificate like concat('%',#{waybill.driverCertificate},'%') </if>" +
            "<if test=' waybill.driverPhone != null '>and driver_phone like concat('%',#{waybill.driverPhone},'%') </if>" +
            "<if test=' waybill.escortName != null '>and escort_name like concat('%',#{waybill.escortName},'%') </if>" +
            "<if test=' waybill.escortPhone != null '>and escort_phone like concat('%',#{waybill.escortPhone},'%') </if>" +
            "<if test=' waybill.escortCertificate != null '>and escort_certificate like concat('%',#{waybill.escortCertificate},'%') </if>" +

            "<if test=' waybill.goodsName != null '>and goods_name like concat('%',#{waybill.goodsName},'%') </if>" +
            "<if test=' waybill.unNum != null '>and un_num like concat('%',#{waybill.unNum},'%') </if>" +
            "<if test=' waybill.goodsType != null '>and goods_type like concat('%',#{waybill.goodsType},'%') </if>" +
            "<if test=' waybill.goodsPackingNorms != null '>and goods_packing_norms like concat('%',#{waybill.goodsPackingNorms},'%') </if>" +
            "<if test=' waybill.goodsPackingType != null '>and goods_packing_type like concat('%',#{waybill.goodsPackingType},'%') </if>" +
            "<if test=' waybill.goodsNum != null '>and goods_num like concat('%',#{waybill.goodsNum},'%') </if>" +
            "<if test=' waybill.goodsCompany != null '>and goods_company like concat('%',#{waybill.goodsCompany},'%') </if>" +
            "<if test=' waybill.dispatcherName != null '>and dispatcher_name like concat('%',#{waybill.dispatcherName},'%') </if>" +
            "<if test=' waybill.dispatcherDate != null '>and dispatcher_date like concat('%',#{waybill.dispatcherDate},'%') </if>" +
            "<if test=' waybill.remarks != null '>and remarks like concat('%',#{waybill.remarks},'%') </if>" +
            "<if test=' waybill.userId != null '>and user_id like concat('%',#{waybill.userId},'%') </if>" +
            "<if test=' waybill.waybillCode != null '>and waybill_code like concat('%',#{waybill.waybillCode},'%') </if>" +
            "<if test=' waybill.status != null '>and status like concat('%',#{waybill.status},'%') </if>" +
            "<if test=' waybill.userCompanyName != null '>and user_company_name like concat('%',#{waybill.userCompanyName},'%') </if>" +
            " order by add_time desc" +
            "</script>")
    List<Waybill> queryByWaybill(Waybill waybill);


}