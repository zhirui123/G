package com.huagongwuliu.waybillelectronic.pojo;

import lombok.Data;

import javax.persistence.Table;

@Table(name="wh_huowu")
@Data
public class Goods {
    private Long id;
    private  String goodNum;
    private  String goodName;
    private  String goodType;
    private  String goodXb;
    private  String packCategory;
    private  String goodDj;
    private  String goodUnit;
    private  String packSpecs;
    private  String pisAller;

    private  String addtime;
    private  String edittime;
    private  String edituid;


}
