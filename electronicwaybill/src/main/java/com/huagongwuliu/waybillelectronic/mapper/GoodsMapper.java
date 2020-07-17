package com.huagongwuliu.waybillelectronic.mapper;

import com.huagongwuliu.waybillelectronic.pojo.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsMapper {

    /**
     * @Select("select * from tb_eway where id = #{id}")
     *     Waybill queryById(Long id);
     */

    @Select("select * from tb_goods  order by id desc  where goods_name LIKE concat('%',#{goodName},'%')")
     List<Goods> queryByGoodsName(String goodName);



    @Insert("insert into tb_goods (goods_name,un_num,goods_type,goods_packing_norms,goods_packing_type,goods_num,goods_company,user_id) VALUES" +
            "(#{goods.goodsName},#{goods.unNum},#{goods.goodsType},#{goods.goodsPackingNorms},#{goods.goodsPackingType},#{goods.goodsNum},#{goods.goodsCompany},#{goods.userId})")
    @Options(useGeneratedKeys=true, keyProperty="goods.id",keyColumn = "id")
    Integer addGoodsData(@Param("goods") Goods goods);



//    @Insert("insert into yd_shipper (shipper_name,shipper_contact,shipper_phone,user_id,yd_id) VALUES(#{shipper.shipperName},#{shipper.shipperContact},#{shipper.shipperPhone},#{shipper.userId},#{shipper.ydId})")
//    @Options(useGeneratedKeys=true, keyProperty="shipper.id",keyColumn = "id")
//    Integer insertShipper(@Param("shipper") Shipper shipper);



    @Update("<script>" +
            " UPDATE tb_goods " +
            "<set>" +
            "<if test=' goods_name != null '> goods_name = #{goods.goodsName}, </if>" +
            "<if test=' un_num != null '> un_num = #{goods.unNum} , </if>" +
            "<if test=' goods_type != null '> goods_type = #{goods.goodsType} , </if>" +
            "<if test=' goods_packing_norms != null '> goods_packing_norms = #{goods.goodsPackingNorms} , </if>" +
            "<if test=' goods_packing_type != null '> goods_packing_type = #{goods.goodsPackingType} , </if>" +
            "<if test=' goods_num != null '> goods_num = #{goods.goodsNum} , </if>" +
            "<if test=' goods_company != null '> goods_company = #{goods.goodsCompany}  </if>" +
            "</set>" +
            "where id = #{id}" +
            "</script>")
    int changeGoodsData(@Param("goods") Goods goods);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Delete("delete * from  tb_goods where id = #{id}")
    int deleteGoodsById(Long id);


    @Select("select * from tb_goods order by id desc  where user_id = #{userId}")
    List<Goods> queryByUserId(String userId);

    @Select("select * from tb_goods   where id = #{id}")
    Goods queryById(Long id);


}
