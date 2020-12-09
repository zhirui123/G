package com.huagongwuliu.waybillelectronic.mapper;

import com.huagongwuliu.waybillelectronic.pojo.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {

    /**
     * @Select("select * from tb_eway where id = #{id}")
     *     Waybill queryById(Long id);
     */

//    @Select("select * from tb_goods  order by id desc  where goods_name LIKE concat('%',#{goodName},'%')")


    @Select( "<script>" +
            " select * from tb_goods " +
            "<if test=' goodName != null '> where goods_name like concat('%',#{goodName},'%') </if>" +
            "</script>")
     List<Goods> queryByGoodsName(String goodName);

    @Select( "<script>" +
            " select * from tb_goods " +
            "<if test=' goodName != null '> where goods_name= #{goodName} </if>" +
            "</script>")
    List<Goods> queryByGoodsName2(String goodName);



    @Insert("insert into tb_goods (goods_name,un_num,goods_type,goods_packing_norms,goods_packing_type,goods_num,goods_company,user_id) VALUES" +
            "(#{goods.goodsName},#{goods.unNum},#{goods.goodsType},#{goods.goodsPackingNorms},#{goods.goodsPackingType},#{goods.goodsNum},#{goods.goodsCompany},#{goods.userId})")
    @Options(useGeneratedKeys=true, keyProperty="goods.id",keyColumn = "id")
    Integer addGoodsData(@Param("goods") Goods goods);



//    @Insert("insert into yd_shipper (shipper_name,shipper_contact,shipper_phone,user_id,yd_id) VALUES(#{shipper.shipperName},#{shipper.shipperContact},#{shipper.shipperPhone},#{shipper.userId},#{shipper.ydId})")
//    @Options(useGeneratedKeys=true, keyProperty="shipper.id",keyColumn = "id")
//    Integer insertShipper(@Param("shipper") Shipper shipper);



//    @Update("<script>" +
//            " UPDATE tb_goods " +
//            "<set>" +
//            "<if test=' goods.goodsName != null '> goods_name = #{goods.goodsName}, </if>" +
//            "<if test=' goods.unNum != null '> un_num = #{goods.unNum} , </if>" +
//            "<if test=' goods.goodsType != null '> goods_type = #{goods.goodsType} , </if>" +
//            "<if test=' goods.goodsPackingNorms != null '> goods_packing_norms = #{goods.goodsPackingNorms} , </if>" +
//            "<if test=' goods.goodsPackingType != null '> goods_packing_type = #{goods.goodsPackingType} , </if>" +
//            "<if test=' goods.goodsNum != null '> goods_num = #{goods.goodsNum} , </if>" +
//            "<if test=' goods.goodsCompany != null '> goods_company = #{goods.goodsCompany}  </if>" +
//            "</set>" +
//            "where id = #{goods.id}" +
//            "</script>")
    @Update("UPDATE tb_goods set goods_name = #{goods.goodsName},un_num = #{goods.unNum} ,goods_type = #{goods.goodsType} " +
            ",goods_packing_norms = #{goods.goodsPackingNorms} ,goods_packing_type = #{goods.goodsPackingType} ," +
            "goods_num = #{goods.goodsNum} ,goods_company = #{goods.goodsCompany} where id = #{goods.id}")
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
