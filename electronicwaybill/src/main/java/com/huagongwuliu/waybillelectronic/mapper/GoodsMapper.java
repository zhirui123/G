package com.huagongwuliu.waybillelectronic.mapper;

import com.huagongwuliu.waybillelectronic.pojo.Goods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsMapper {

    /**
     * @Select("select * from tb_eway where id = #{id}")
     *     Waybill queryById(Long id);
     */

    @Select("select * from wh_huowu where good_name LIKE concat('%',#{goodName},'%')")
     List<Goods> queryByGoodsName(String goodName);

}
