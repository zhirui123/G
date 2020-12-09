package com.huagongwuliu.waybillelectronic.service;


import com.huagongwuliu.waybillelectronic.mapper.WaybillTagMapper;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import com.huagongwuliu.waybillelectronic.utils.DateUtil;
import com.huagongwuliu.waybillelectronic.utils.StringUtil;
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


    /**
     * 根据电子运单内容，添加电子运单标签
     * @param waybill
     */
    public  void  addWaybillTagByWaybill(Waybill waybill){


        if (StringUtil.isNotEmpty(waybill.getUserId())){
            addWaybillTagShipperByWaybill(waybill);
            addWaybillTagShiptoByWaybill(waybill);
            addWaybillTagshipmentByWaybill(waybill);
            addWaybillTagVehicleByWaybill(waybill);
            addWaybillTagGoodsByWaybill(waybill);
        }

    }


    /**
     * 添加到托运方标签
     * @param waybill
     */
    private   void  addWaybillTagShipperByWaybill(Waybill waybill){

        if (StringUtil.isNotEmpty(waybill.getShipperName())){
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

    /**
     * 添加收货方标签
     * @param waybill
     */

    private  void  addWaybillTagShiptoByWaybill(Waybill waybill){

        if (StringUtil.isNotEmpty(waybill.getShiptoName())){
            Waybill waybillTag = new Waybill();
            waybillTag.setShiptoName(waybill.getShiptoName());
            waybillTag.setShiptoPhone(waybill.getShiptoPhone());

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


    /**
     * 添加装货人标签
     * @param waybill
     */
    public  void  addWaybillTagshipmentByWaybill(Waybill waybill){

        if (StringUtil.isNotEmpty(waybill.getShipmentName())){
            Waybill waybillTag = new Waybill();


            waybillTag.setShipmentName(waybill.getShipmentName());
            waybillTag.setShipmentPhone(waybill.getShipmentPhone());
            waybillTag.setShipmentFromAddress(waybill.getShipmentFromAddress());
            waybillTag.setShipmentFromDetails(waybill.getShipmentFromDetails());
            waybillTag.setShipmentToAddress(waybill.getShipmentToAddress());
            waybillTag.setShipmentToDetails(waybill.getShipmentToDetails());


            waybillTag.setUserId(waybill.getUserId());
            waybillTag.setShipmentStatus("0");

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


    /**
     * 添加承运人标签
     * @param waybill
     */
    public  void  addWaybillTagCarriageByWaybill(Waybill waybill){

        if (StringUtil.isNotEmpty(waybill.getCarriageName())){
            Waybill waybillTag = new Waybill();

            waybillTag.setCarriageName(waybill.getCarriageName());
            waybillTag.setCarriagePhone(waybill.getCarriagePhone());
            waybillTag.setCarriageLicensekey(waybill.getCarriageLicensekey());


            waybillTag.setUserId(waybill.getUserId());
            waybillTag.setCarriageStatus("0");

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


    /**
     * 添加车辆标签
     * @param waybill
     */
    public  void  addWaybillTagVehicleByWaybill(Waybill waybill){

        if (StringUtil.isNotEmpty(waybill.getLicensePlateNum())){
            Waybill waybillTag = new Waybill();





            waybillTag.setLicensePlateNum(waybill.getLicensePlateNum());
            waybillTag.setLicensePlateColor(waybill.getLicensePlateColor());
            waybillTag.setRoadTransportPermitNum(waybill.getRoadTransportPermitNum());
            waybillTag.setTrailerNum(waybill.getTrailerNum());
            waybillTag.setTrailerRoadRansportPermit(waybill.getTrailerRoadRansportPermit());
            waybillTag.setCanbodyNum(waybill.getCanbodyNum());
            waybillTag.setCanbodyVolume(waybill.getCanbodyVolume());

            waybillTag.setDriverName(waybill.getDriverName());
            waybillTag.setDriverPhone(waybill.getDriverPhone());
            waybillTag.setDriverCertificate(waybill.getDriverCertificate());
            waybillTag.setEscortName(waybill.getEscortName());
            waybillTag.setEscortPhone(waybill.getEscortPhone());
            waybillTag.setEscortCertificate(waybill.getEscortCertificate());




            waybillTag.setUserId(waybill.getUserId());
            waybillTag.setVehicleStatus("0");

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







    /**
     * 添加货物标签
     * @param waybill
     */
    public  void  addWaybillTagGoodsByWaybill(Waybill waybill){

        if (StringUtil.isNotEmpty(waybill.getGoodsName())){
            Waybill waybillTag = new Waybill();


            waybillTag.setGoodsName(waybill.getGoodsName());
            waybillTag.setUnNum(waybill.getUnNum());
            waybillTag.setGoodsType(waybill.getGoodsType());
            waybillTag.setGoodsPackingNorms(waybill.getGoodsPackingNorms());
            waybillTag.setGoodsPackingType(waybill.getGoodsPackingType());
//            waybillTag.setGoodsNum(waybill.getGoodsNum());
//            waybillTag.setGoodsCompany(waybill.getGoodsCompany());



            waybillTag.setUserId(waybill.getUserId());
            waybillTag.setGoodsStatus("0");

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









}
