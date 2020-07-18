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


}