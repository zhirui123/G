package com.huagongwuliu.waybillelectronic.pojo;

import lombok.Data;

import javax.persistence.Table;

@Table(name="tb_goods")
@Data
public class Goods {
    private Long id;
    private  String goodsName; //危险货物名称
    private  String unNum; //UN编号
    private  String goodsType; //货物类别
    private  String goodsPackingNorms; //包装规格
    private  String goodsPackingType; //包装分类
    private  String goodsNum; //货物数量
    private  String goodsCompany; //货物单位
    private  String userId; //用户id




}
