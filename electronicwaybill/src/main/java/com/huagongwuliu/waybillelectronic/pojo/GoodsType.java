package com.huagongwuliu.waybillelectronic.pojo;


import lombok.Data;

import javax.persistence.Table;

@Table(name="wh_zhr")
@Data
public class GoodsType {
    private long id;
    private String companyName;
    private String companyAddress;

    private String companyContacts;
    private String companyContactPhone;
    private String upArea;
    private String upAddress;
    private String uid;
    private String addtime;
    private String edittime;
    private String edituid;
}
