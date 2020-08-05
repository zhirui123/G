package com.huagongwuliu.waybillelectronic.mapper;

import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface WaybillMapper  extends tk.mybatis.mapper.common.Mapper<Waybill> {
//    void delete(Long id);
//}
//@Mapper
//@Repository
@Mapper
public interface WaybillMapper extends tk.mybatis.mapper.common.Mapper<Waybill> {

    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("select e.*,u.type isAuth,u.company_name userCompanyName from tb_eway e LEFT JOIN app_user_authentication u ON e.user_id = u.user_id")
    List<Waybill> findAll();

    @Insert("INSERT INTO tb_eway (id, shipper_name,shipper_contact,shipper_phone,shipto_name,shipto_phone,shipment_name,shipment_phone," +
            "shipment_starttime,shipment_from_address,shipment_from_details,city_express,shipment_to_address,shipment_to_details," +
            "carriage_name,carriage_phone,carriage_licensekey,license_plate_num,license_plate_color,road_transport_permit_num," +
            "trailer_num,trailer_road_ransport_permit,canbody_num,canbody_volume,driver_name,driver_certificate,driver_phone," +
            "escort_name,escort_phone,escort_certificate,goods_name,un_num,goods_type,goods_packing_norms,goods_packing_type,goods_num," +
            "goods_company,dispatcher_name,dispatcher_date,remarks,user_id,waybill_code,status,shipper_status,shipto_status,carriage_status," +
            "shipment_status,vehicle_status,goods_status,add_time,update_time,user_company_name " +
            " ) VALUES(#{id}, #{shipperName},#{shipperContact},#{shipperPhone},#{shiptoName},#{shiptoPhone},#{shipmentName},#{shipmentPhone}," +
            "#{shipmentStarttime},#{shipmentFromAddress},#{shipmentFromDetails},#{cityExpress},#{shipmentToAddress},#{shipmentToDetails}," +
            "#{carriageName},#{carriagePhone},#{carriageLicensekey},#{licensePlateNum},#{licensePlateColor},#{roadTransportPermitNum}," +
            "#{trailerNum},#{trailerRoadRansportPermit},#{canbodyNum},#{canbodyVolume},#{driverName},#{driverCertificate},#{driverPhone}," +
            "#{escortName},#{escortPhone},#{escortCertificate},#{goodsName},#{unNum},#{goodsType},#{goodsPackingNorms},#{goodsPackingType},#{goodsNum}," +
            "#{goodsCompany},#{dispatcherName},#{dispatcherDate},#{remarks},#{userId},#{waybillCode},#{status},#{shipperStatus},#{shiptoStatus},#{carriageStatus}," +
            "#{shipmentStatus},#{vehicleStatus},#{goodsStatus},#{addTime},#{updateTime},#{userCompanyName} )")
    int insertWay(Waybill waybill);

    @Select("select  e.*,u.company_name userCompanyName,u.is_office_seal_license isOfficeSealLicense from tb_eway e LEFT JOIN app_user_authentication u ON e.user_id = u.user_id where e.id = #{id}")
    Waybill queryById(Long id);

    @Select("select * from tb_eway where waybill_code = #{waybillCode}")
    Waybill queryByWaybillCode(String waybillCode);

    @Select("select  e.*,u.company_name userCompanyName from tb_eway e LEFT JOIN app_user_authentication u ON e.user_id = u.user_id where e.id = #{id} and e.user_id = #{userId}")
    List<Waybill> queryByIdAndUserId(Long id, String userId);


//    @Select("select  e.*,u.type isAuth,u.company_name userCompanyName from tb_eway e LEFT JOIN app_user_authentication u ON e.user_id = u.user_id where e.user_id = #{userId}  order by add_time desc")

    @Select("select * from tb_eway where user_id = #{userId}  order by add_time desc")
    List<Waybill> queryByUserId(String userId);


    @Select("SELECT COUNT(1)  FROM tb_eway  WHERE user_id = #{userId} and add_time >= #{dayStartTimestamp} and add_time <= #{dayEndTimestamp}")
    Integer queryCountByUserIdAndCreteDate(String userId, Long dayStartTimestamp, Long dayEndTimestamp);


    @Update("<script>" +
            " UPDATE tb_eway " +
            "<set>" +
            "<if test=' status != null '> status = #{status}, </if>" +
            "<if test=' goodsNum != null '> goods_num = #{goodsNum} </if>" +
            "</set>" +
            "where id = #{id}" +
            "</script>")
    int changeStatus(@Param("status") String status, @Param("goodsNum") String goodsNum, Long id);

    /**
     * 更改货物运输的数量
     *
     * @param goodsNum
     * @param id
     * @param userId
     * @return
     */
    @Update("UPDATE tb_eway SET goods_num = #{goodsNum}  where id = #{id} and  user_id = #{userId}")
    int changeGoodsNum(String goodsNum, Long id, String userId);


    /**
     * 根据托运人查询
     *
     * @param shipperName
     * @param userId
     * @return
     */


    @Select("<script>" +
            " select * from tb_eway where  user_id = #{userId} and shipper_status = 0" +
            "<if test=' shipperName != null '> and shipper_name like concat('%',#{shipperName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByShipperNameAndUserId(String shipperName, String userId);


    /**
     * 根据收货人查询
     *
     * @param shiptoName
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where shipto_name LIKE concat('%',#{shiptoName},'%') and user_id = #{userId}  and shipto_status = 0 order by add_time desc")
    @Select("<script>" +
            " select * from tb_eway where  user_id = #{userId} and shipto_status = 0" +
            "<if test=' shiptoName != null '> and shipto_name like concat('%',#{shiptoName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByShiptoNameAndUserId(String shiptoName, String userId);


    /**
     * 根据装货人名称查询
     *
     * @param shipmentName
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where shipment_name LIKE concat('%',#{shipmentName},'%') and user_id = #{userId}  and shipment_status = 0 order by add_time desc")
    @Select("<script>" +
            " select * from tb_eway where  user_id = #{userId} and shipment_status = 0" +
            "<if test=' shipmentName != null '> and shipment_name like concat('%',#{shipmentName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByShipmentNameAndUserId(String shipmentName, String userId);


    /**
     * 根据承运人查询
     *
     * @param carriageName
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where carriage_name LIKE concat('%',#{carriageName},'%') and user_id = #{userId}  and carriage_status = 0 order by add_time desc")
    @Select("<script>" +
            " select * from tb_eway where  user_id = #{userId} and carriage_status = 0" +
            "<if test=' carriageName != null '> and carriage_name like concat('%',#{carriageName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByCarriageNameAndUserId(String carriageName, String userId);

    /**
     * 根据车牌号查询
     *
     * @param licensePlateNum
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where license_plate_num LIKE concat('%',#{licensePlateNum},'%') and user_id = #{userId}  and vehicle_status = 0 order by add_time desc")
    @Select("<script>" +
            " select * from tb_eway where  user_id = #{userId} and vehicle_status = 0" +
            "<if test=' licensePlateNum != null '> and license_plate_num like concat('%',#{licensePlateNum},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByLicensePlateNumAndUserId(String licensePlateNum, String userId);


    /**
     * 根据罐体编号查询
     *
     * @param canbodyNum
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where canbody_num LIKE concat('%',#{canbodyNum},'%') and user_id = #{userId}  and vehicle_status = 0 order by add_time desc")
    @Select("<script>" +
            " select * from tb_eway where  user_id = #{userId} and vehicle_status = 0" +
            "<if test=' canbodyNum != null '> and canbody_num like concat('%',#{canbodyNum},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByCanbodyNumAndUserId(String canbodyNum, String userId);


    /**
     * 根据押运员
     *
     * @param escortName
     * @param userId
     * @return
     */
//    @Select("select * from tb_eway where escort_name LIKE concat('%',#{escortName},'%') and user_id = #{userId}  and vehicle_status = 0 order by add_time desc")
    @Select("<script>" +
            " select * from tb_eway where  user_id = #{userId} and vehicle_status = 0" +
            "<if test=' escortName != null '> and escort_name like concat('%',#{escortName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByEscortNameAndUserId(String escortName, String userId);


    /**
     * 根据货物名称查询
     *
     * @param goodsName
     * @param userId
     * @return
     */

//    @Select("select * from tb_eway where goods_name LIKE concat('%',#{goodsName},'%') and user_id = #{userId}  and goods_status = 0 order by add_time desc")
    @Select("<script>" +
            " select * from tb_eway where  user_id = #{userId} and goods_status = 0" +
            "<if test=' goodsName != null '> and goods_name like concat('%',#{goodsName},'%') </if>" +
            "order by add_time desc" +
            "</script>")
    List<Waybill> queryByGoodsNameAndUserId(String goodsName, String userId);


    /**
     * 根据运单编号查询
     *
     * @param waybillCode
     * @param userId
     * @return
     */
    @Select("select * from tb_eway where waybill_code LIKE concat('%',#{waybillCode},'%') and user_id = #{userId} order by add_time desc")
    List<Waybill> queryByWaybillCodeAndUserId(String waybillCode, String userId);

    @Select("<script>" +
            " select * from tb_eway e LEFT JOIN app_user_info u ON e.user_id = u.id " +
            "<where>" +
            "<if test=' userPhone != null '>  and u.phone = #{userPhone}  </if>" +
            "<if test=' shipperName != null '>and e.shipper_name like concat('%',#{shipperName},'%') </if>" +
            "<if test=' shipperContact != null '>and e.shipper_contact like concat('%',#{shipperContact},'%') </if>" +
            "<if test=' shipperPhone != null '>and e.shipper_phone like concat('%',#{shipperPhone},'%') </if>" +

            "<if test=' shiptoName != null '>and e.shipto_name like concat('%',#{shiptoName},'%') </if>" +
            "<if test=' shiptoPhone != null '>and e.shipto_phone like concat('%',#{shiptoPhone},'%') </if>" +

            "<if test=' shipmentName != null '>and e.shipment_name like concat('%',#{shipmentName},'%') </if>" +
            "<if test=' shipmentPhone != null '>and e.shipment_phone like concat('%',#{shipmentPhone},'%') </if>" +
            "<if test=' shipmentStarttime != null '>and e.shipment_starttime like concat('%',#{shipmentStarttime},'%') </if>" +
            "<if test=' shipmentFromAddress != null '>and e.shipment_from_address like concat('%',#{shipmentFromAddress},'%') </if>" +
            "<if test=' shipmentFromDetails != null '>and e.shipment_from_details like concat('%',#{shipmentFromDetails},'%') </if>" +
            "<if test=' cityExpress != null '>and e.city_express like concat('%',#{cityExpress},'%') </if>" +
            "<if test=' shipmentToAddress != null '>and e.shipment_to_address like concat('%',#{shipmentToAddress},'%') </if>" +
            "<if test=' shipmentToDetails != null '>and e.shipment_to_details like concat('%',#{shipmentToDetails},'%') </if>" +

            "<if test=' carriageName != null '>and e.carriage_name like concat('%',#{carriageName},'%') </if>" +
            "<if test=' carriagePhone != null '>and e.carriage_phone like concat('%',#{carriagePhone},'%') </if>" +
            "<if test=' carriageLicensekey != null '>and e.carriage_licensekey like concat('%',#{carriageLicensekey},'%') </if>" +

            "<if test=' licensePlateNum != null '>and e.license_plate_num like concat('%',#{licensePlateNum},'%') </if>" +
            "<if test=' licensePlateColor != null '>and e.license_plate_color like concat('%',#{licensePlateColor},'%') </if>" +
            "<if test=' roadTransportPermitNum != null '>and e.road_transport_permit_num like concat('%',#{roadTransportPermitNum},'%') </if>" +
            "<if test=' trailerRoadRansportPermit != null '>and e.trailer_road_ransport_permit like concat('%',#{trailerRoadRansportPermit},'%') </if>" +
            "<if test=' canbodyNum != null '>and e.canbody_num like concat('%',#{canbodyNum},'%') </if>" +
            "<if test=' driverName != null '>and e.driver_name like concat('%',#{driverName},'%') </if>" +
            "<if test=' driverCertificate != null '>and e.driver_certificate like concat('%',#{driverCertificate},'%') </if>" +
            "<if test=' driverPhone != null '>and e.driver_phone like concat('%',#{driverPhone},'%') </if>" +
            "<if test=' escortName != null '>and e.escort_name like concat('%',#{escortName},'%') </if>" +
            "<if test=' escortPhone != null '>and e.escort_phone like concat('%',#{escortPhone},'%') </if>" +
            "<if test=' escortCertificate != null '>and e.escort_certificate like concat('%',#{escortCertificate},'%') </if>" +

            "<if test=' goodsName != null '>and e.goods_name like concat('%',#{goodsName},'%') </if>" +
            "<if test=' unNum != null '>and e.un_num like concat('%',#{unNum},'%') </if>" +
            "<if test=' goodsType != null '>and e.goods_type like concat('%',#{goodsType},'%') </if>" +
            "<if test=' goodsPackingNorms != null '>and e.goods_packing_norms like concat('%',#{goodsPackingNorms},'%') </if>" +

            "<if test=' goodsPackingType != null '>and e.goods_packing_type like concat('%',#{goodsPackingType},'%') </if>" +
            "<if test=' goodsNum != null '>and e.goods_num like concat('%',#{goodsNum},'%') </if>" +
            "<if test=' goodsCompany != null '>and e.goods_company like concat('%',#{goodsCompany},'%') </if>" +

            "<if test=' dispatcherName != null '>and e.dispatcher_name like concat('%',#{dispatcherName},'%') </if>" +
            "<if test=' dispatcherDate != null '>and e.dispatcher_date like concat('%',#{dispatcherDate},'%') </if>" +
            "<if test=' remarks != null '>and e.remarks like concat('%',#{remarks},'%') </if>" +

            "<if test=' userId != null '>and e.user_id = #{userId} </if>" +
            "<if test=' waybillCode != null '>and e.waybill_code like concat('%',#{waybillCode},'%') </if>" +

            "<if test=' status != null '>and e.status like concat('%',#{status},'%') </if>" +
            "<if test=' userCompanyName != null '>and e.user_company_name like concat('%',#{userCompanyName},'%') </if>" +

//            "<if test=' id > 0 '>and e.id = #{id} </if>" +

            "<if test=' startTime > 0 '> AND e.add_time <![CDATA[>=]]> #{startTime} </if>" +
            "<if test=' endTime > 0'> AND e.add_time <![CDATA[<=]]>#{endTime} </if>" +
            "</where>" +
            " order by e.add_time desc" +
            "</script>")
    List<Waybill> queryallbackstage(Waybill waybill);


//    "<if test=' shiptoPhone != null '>and (shipto_phone like concat('%',#{shiptoPhone},'%') " +
//            "or shipment_phone like concat('%',#{shipmentPhone},'%') " +
//            "or carriage_phone like concat('%',#{carriagePhone},'%') " +
//            "or driver_phone like concat('%',#{driverPhone},'%') " +
//            "or  escort_phone like concat('%',#{escortPhone},'%') " +
//            ") </if>" +


    @Select("<script>" +
            " select * from tb_eway where  user_id = #{userId}  " +
            "<if test=' shipperName != null '>and shipper_name like concat('%',#{shipperName},'%') </if>" +
            "<if test=' shipperContact != null '>and shipper_contact like concat('%',#{shipperContact},'%') </if>" +
            "<if test=' shipperPhone != null '>and shipper_phone like concat('%',#{shipperPhone},'%') </if>" +

            "<if test=' shiptoName != null '>and shipto_name like concat('%',#{shiptoName},'%') </if>" +
            "<if test=' shiptoPhone != null '>and shipto_phone like concat('%',#{shiptoPhone},'%') </if>" +

            "<if test=' shipmentName != null '>and shipment_name like concat('%',#{shipmentName},'%') </if>" +
            "<if test=' shipmentPhone != null '>and shipment_phone like concat('%',#{shipmentPhone},'%') </if>" +
            "<if test=' shipmentStarttime != null '>and shipment_starttime like concat('%',#{shipmentStarttime},'%') </if>" +
            "<if test=' shipmentFromAddress != null '>and shipment_from_address like concat('%',#{shipmentFromAddress},'%') </if>" +
            "<if test=' shipmentFromDetails != null '>and shipment_from_details like concat('%',#{shipmentFromDetails},'%') </if>" +
            "<if test=' cityExpress != null '>and city_express like concat('%',#{cityExpress},'%') </if>" +
            "<if test=' shipmentToAddress != null '>and shipment_to_address like concat('%',#{shipmentToAddress},'%') </if>" +
            "<if test=' shipmentToDetails != null '>and shipment_to_details like concat('%',#{shipmentToDetails},'%') </if>" +

            "<if test=' carriageName != null '>and carriage_name like concat('%',#{carriageName},'%') </if>" +
            "<if test=' carriagePhone != null '>and carriage_phone like concat('%',#{carriagePhone},'%') </if>" +
            "<if test=' carriageLicensekey != null '>and carriage_licensekey like concat('%',#{carriageLicensekey},'%') </if>" +

            "<if test=' licensePlateNum != null '>and license_plate_num like concat('%',#{licensePlateNum},'%') </if>" +
            "<if test=' licensePlateColor != null '>and license_plate_color like concat('%',#{licensePlateColor},'%') </if>" +
            "<if test=' roadTransportPermitNum != null '>and road_transport_permit_num like concat('%',#{roadTransportPermitNum},'%') </if>" +
            "<if test=' trailerRoadRansportPermit != null '>and trailer_road_ransport_permit like concat('%',#{trailerRoadRansportPermit},'%') </if>" +
            "<if test=' canbodyNum != null '>and canbody_num like concat('%',#{canbodyNum},'%') </if>" +
            "<if test=' driverName != null '>and driver_name like concat('%',#{driverName},'%') </if>" +
            "<if test=' driverCertificate != null '>and driver_certificate like concat('%',#{driverCertificate},'%') </if>" +
            "<if test=' driverPhone != null '>and driver_phone like concat('%',#{driverPhone},'%') </if>" +
            "<if test=' escortName != null '>and escort_name like concat('%',#{escortName},'%') </if>" +
            "<if test=' escortPhone != null '>and escort_phone like concat('%',#{escortPhone},'%') </if>" +
            "<if test=' escortCertificate != null '>and escort_certificate like concat('%',#{escortCertificate},'%') </if>" +

            "<if test=' goodsName != null '>and goods_name like concat('%',#{goodsName},'%') </if>" +
            "<if test=' unNum != null '>and un_num like concat('%',#{unNum},'%') </if>" +
            "<if test=' goodsType != null '>and goods_type like concat('%',#{goodsType},'%') </if>" +
            "<if test=' goodsPackingNorms != null '>and goods_packing_norms like concat('%',#{goodsPackingNorms},'%') </if>" +
            "<if test=' goodsPackingType != null '>and goods_packing_type like concat('%',#{goodsPackingType},'%') </if>" +
            "<if test=' goodsNum != null '>and goods_num like concat('%',#{goodsNum},'%') </if>" +
            "<if test=' goodsCompany != null '>and goods_company like concat('%',#{goodsCompany},'%') </if>" +
            "<if test=' dispatcherName != null '>and dispatcher_name like concat('%',#{dispatcherName},'%') </if>" +
            "<if test=' dispatcherDate != null '>and dispatcher_date like concat('%',#{dispatcherDate},'%') </if>" +
            "<if test=' remarks != null '>and remarks like concat('%',#{remarks},'%') </if>" +
            "<if test=' userId != null '>and user_id like concat('%',#{userId},'%') </if>" +
            "<if test=' waybillCode != null '>and waybill_code like concat('%',#{waybillCode},'%') </if>" +
            "<if test=' status >=0 '>and status like concat('%',#{status},'%') </if>" +
            "<if test=' userCompanyName != null '>and user_company_name like concat('%',#{userCompanyName},'%') </if>" +
            " order by add_time desc" +
            "</script>")
    List<Waybill> queryByWaybill(Waybill waybill);


}