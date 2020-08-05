package com.huagongwuliu.waybillelectronic.mapper;

import com.huagongwuliu.waybillelectronic.pojo.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


@org.apache.ibatis.annotations.Mapper
public interface UserMapper  extends Mapper<User> {


    @Select("select * from app_user_info where id = #{userId}")
    User queryByUserId(String userId);
}
