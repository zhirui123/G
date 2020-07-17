package com.huagongwuliu.waybillelectronic.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 托运方
 */
@Table(name = "yd_shipper")
@Data
public class Shipper {

    @Id
    private Long id;
    private  String shipperName;//托运方
    private  String shipperContact;//托运方联系人
    private  String shipperPhone;//托运方 联系电话
    private  String userId;
    private  String ydId;


}
