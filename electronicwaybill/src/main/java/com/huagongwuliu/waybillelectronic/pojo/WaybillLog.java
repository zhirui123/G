package com.huagongwuliu.waybillelectronic.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "tb_waybill_log")
@Data
public class WaybillLog {

        @Id
        private String id;
        private Integer status;
        private String userId;
        private String waybillId;
        private String content;
        private Integer addTime;
        private Integer updateTime;




    }

