package com.huagongwuliu.waybillelectronic.service;

import com.huagongwuliu.waybillelectronic.mapper.GoodsMapper;
import com.huagongwuliu.waybillelectronic.mapper.WaybillLogMapper;
import com.huagongwuliu.waybillelectronic.mapper.WaybillMapper;
import com.huagongwuliu.waybillelectronic.mapper.WaybillTagMapper;
import com.huagongwuliu.waybillelectronic.pojo.Goods;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    WaybillTagMapper mapper;

    public List<Goods> queryByGoodsName(String goodsName,String userId) throws Exception {
        List<Goods> list = goodsMapper.queryByGoodsName(goodsName);
        List<Waybill> list1 = mapper.queryByGoodsNameAndUserId(goodsName,userId);
        if (list1.size()>0) {
            for (Waybill waybill : list1) {
                Goods goods = new Goods();
                goods.setGoodsName(waybill.getGoodsName());
                goods.setUnNum(waybill.getUnNum());
                goods.setGoodsType(waybill.getGoodsType());
                goods.setGoodsPackingNorms(waybill.getGoodsPackingNorms());
                goods.setGoodsPackingType(waybill.getGoodsPackingType());
                goods.setGoodsNum(waybill.getGoodsNum());
                goods.setGoodsCompany(waybill.getGoodsCompany());
                goods.setUserId(waybill.getUserId());
                list.add(0,goods);
            }
        }

        return list;
    }


    public Goods addGoodsData(@Param("goods") Goods goods) throws Exception {


        List<Goods> goodsList = this.goodsMapper.queryByGoodsName2(goods.getGoodsName());

        if (goodsList.size() > 0) {
            Goods g = goodsList.get(0);
            goods.setId(g.getId());


            System.out.println("更新数据了开始 " + goods);
            this.goodsMapper.changeGoodsData(goods);

        } else {
            this.goodsMapper.addGoodsData(goods);
        }

        return goods;

    }

    public int changeGoodsData(@Param("goods") Goods goods) throws Exception {
        return this.goodsMapper.changeGoodsData(goods);
    }

    public int deleteGoodsById(Long id) throws Exception {
        return this.goodsMapper.deleteGoodsById(id);
    }


    public List<Goods> queryByUserId(String userId) throws Exception {
        return this.goodsMapper.queryByUserId(userId);
    }


    public Goods queryById(Long id) throws Exception {
        return this.goodsMapper.queryById(id);
    }


//    Goods goods = new Goods();
//        goods.setGoodsName(waybill.getGoodsName());
//        goods.setUnNum(waybill.getUnNum());
//        goods.setGoodsType(waybill.getGoodsType());
//        goods.setGoodsPackingNorms(waybill.getGoodsPackingNorms());
//        goods.setGoodsPackingType(waybill.getGoodsPackingType());
//        goods.setGoodsNum(waybill.getGoodsNum());
//        goods.setGoodsCompany(waybill.getGoodsCompany());
//        goods.setUserId(waybill.getUserId());
//
//    Goods goods1  =   this.goodsService.addGoodsData(goods);
//
//        if (goods1.getId() != 0){
//        waybill.setGoodsId(goods1.getId());
//        waybill.setVehicleId(goods1.getId());
//        return this.waybillMapper.insert(waybill);
//    }else{
//        return 0;
//    }

}
