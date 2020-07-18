package com.huagongwuliu.waybillelectronic.mapper;

import com.huagongwuliu.waybillelectronic.pojo.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsTypeMapper extends tk.mybatis.mapper.common.Mapper<GoodsType> {

    @Select("select * from wh_zhr")
    List<GoodsType> findALl();
}
