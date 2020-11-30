package com.huagongwuliu.waybillelectronic.mapper;

import com.huagongwuliu.waybillelectronic.pojo.WaybillLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaybillLogMapper {


    @Select("select * from tb_waybill_log where  waybill_id = #{waybillId} order by add_time desc")
    List<WaybillLog> findByWaybillId(String waybillId);



    // 单条新增客户基本信息
    @Insert("<script>" +
            "insert into tb_waybill_log  " +
            "<trim prefix='(' suffix=')' suffixOverrides=','>" +
            "<if test='id != null'>" +
            "  id," +
            "</if>" +
            "<if test='status != null'>" +
            "  status," +
            "</if>" +
            "<if test='userId != null'>" +
            "  user_id," +
            "</if>" +
            "<if test='waybillId != null'>" +
            "  waybill_id," +
            "</if>" +
            "<if test='content != null'>" +
            "  content," +
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
            "<if test='status != null'>" +
            "  #{status}," +
            "</if>" +
            "<if test='userId != null'>" +
            "  #{userId}," +
            "</if>" +
            "<if test='waybillId != null'>" +
            "  #{waybillId}," +
            "</if>" +
            "<if test='content != null'>" +
            "  #{content}," +
            "</if>" +
            "<if test='addTime != null'>" +
            "  #{addTime}," +
            "</if>" +
            "<if test='updateTime != null'>" +
            "  #{updateTime}," +
            "</if>" +

            "</trim>" +
            "</script>")
        int add(WaybillLog route);



    @Update("<script>" +
            "UPDATE tb_waybill_log  " +
            "    <set>" +
            "<if test='content != null'>" +
            "  content = #{content}," +
            "</if>" +
            "<if test='addTime != null'>" +
            "  add_time = #{addTime}," +
            "</if>" +
            "<if test='updateTime != null'>" +
            "  update_time = #{updateTime}," +
            "</if>" +
            "<if test='status != null '>" +
            "  status = #{status}," +
            "</if>" +
            "    </set>" +
            "       WHERE waybill_id = #{waybillId} " +
            "</script>")
    int update(WaybillLog waybillLog);



}
