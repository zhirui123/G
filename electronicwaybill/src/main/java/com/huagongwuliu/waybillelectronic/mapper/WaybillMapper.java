package com.huagongwuliu.waybillelectronic.mapper;

import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import org.apache.ibatis.annotations.Mapper;
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

    @Select("select * from tb_eway  where user_id = #{userId} ")
    List<Waybill> queryByUserId(String userId);

    @Update("UPDATE tb_eway SET status = #{status}  where id = #{id}")
    int changeStatus(String status,Long id);



    /**
     * 根据托运人查询
     * @param shipperName
     * @param userId
     * @return
     */
    @Select("select * from tb_eway where shipper_name like concat('%',#{shipperName},'%') and user_id = #{userId}")
    List<Waybill> queryByShipperNameAndUserId(String shipperName,String userId);


    /**
     * 根据收货人查询
     * @param shiptoName
     * @param userId
     * @return
     */
    @Select("select * from tb_eway where shipto_name LIKE concat('%',#{shiptoName},'%') and user_id = #{userId}")
    List<Waybill> queryByShiptoNameAndUserId(String shiptoName,String userId);


    /**
     * 根据装货人名称查询
     * @param shipmentName
     * @param userId
     * @return
     */
    @Select("select * from tb_eway where shipment_name LIKE concat('%',#{shipmentName},'%') and user_id = #{userId}")
    List<Waybill> queryByShipmentNameAndUserId(String shipmentName,String userId);


    /**
     * 根据承运人查询
     * @param carriageName
     * @param userId
     * @return
     */
    @Select("select * from tb_eway where carriage_name LIKE concat('%',#{carriageName},'%') and user_id = #{userId}")
    List<Waybill> queryByCarriageNameAndUserId(String carriageName,String userId);
}