package com.huagongwuliu.waybillelectronic.service;

import com.huagongwuliu.waybillelectronic.mapper.GoodsMapper;
import com.huagongwuliu.waybillelectronic.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> queryByGoodsName(String goodsName) throws  Exception{
        return  this.goodsMapper.queryByGoodsName(goodsName);
    }



}
