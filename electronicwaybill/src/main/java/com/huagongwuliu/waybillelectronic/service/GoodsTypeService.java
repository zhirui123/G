package com.huagongwuliu.waybillelectronic.service;


import com.huagongwuliu.waybillelectronic.mapper.GoodsTypeMapper;
import com.huagongwuliu.waybillelectronic.pojo.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeService {
    @Autowired
    private GoodsTypeMapper mapper;

    public List<GoodsType> findAll(){
        return mapper.findALl();
    }

}
