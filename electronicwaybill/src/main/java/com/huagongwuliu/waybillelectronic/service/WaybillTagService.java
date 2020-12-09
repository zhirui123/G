package com.huagongwuliu.waybillelectronic.service;


import com.huagongwuliu.waybillelectronic.mapper.WaybillTagMapper;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaybillTagService {


    @Autowired
    WaybillTagMapper waybillTagMapper;


    public List<Waybill> getList(Waybill waybill) throws Exception {
        return this.waybillTagMapper.list(waybill);
    }
    public  Waybill get(String id){
        return  this.waybillTagMapper.get(id);
    }
    public int updateWaubillTag(Waybill waybill)  {
        return this.waybillTagMapper.update(waybill);
    }

    public Integer insertWaubillTag(Waybill waybill)  {
        return this.waybillTagMapper.add(waybill);
    }



    public int remove(List<String> ids) {

        return  waybillTagMapper.delete(ids);
    }


}
