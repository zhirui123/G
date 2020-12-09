package com.huagongwuliu.waybillelectronic.service;


import com.huagongwuliu.waybillelectronic.mapper.WaybillTagMapper;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import com.huagongwuliu.waybillelectronic.utils.DateUtil;
import com.huagongwuliu.waybillelectronic.utils.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WaybillTagService {


    @Resource
    WaybillTagMapper waybillTagMapper;


    public List<Waybill> getList(Waybill waybill){
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




    public  void  addWaybillTagByWaybill(Waybill waybill){

         addWaybillTagShipperByWaybill(waybill);






    }


    /**
     * 添加到托运方标签
     * @param waybill
     */
    public  void  addWaybillTagShipperByWaybill(Waybill waybill){

        Waybill waybillTag = new Waybill();
        waybillTag.setShipperName(waybill.getShipperName());
        waybillTag.setShipperPhone(waybill.getShipperPhone());
        waybillTag.setShipperContact(waybill.getShipperContact());
        waybillTag.setUserId(waybill.getUserId());
        waybillTag.setShipperStatus("0");

        List<Waybill> list = this.getList(waybillTag);

        if (list.size() > 0){

        }else{

            waybillTag.setId(UuidUtil.getUUID());
            waybillTag.setAddTime(DateUtil.getNowTimestamp());
            waybillTag.setUpdateTime(DateUtil.getNowTimestamp());
            insertWaubillTag(waybillTag);
        }


    }
















}
