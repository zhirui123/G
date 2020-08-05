package com.huagongwuliu.waybillelectronic.service;

import com.huagongwuliu.waybillelectronic.mapper.UserMapper;
import com.huagongwuliu.waybillelectronic.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    User queryByUserId(String userId) throws  Exception{

        return this.userMapper.queryByUserId(userId);

    }
}
