package com.huagongwuliu.waybillelectronic.service;


import com.huagongwuliu.waybillelectronic.mapper.WaybillLogMapper;
import com.huagongwuliu.waybillelectronic.pojo.WaybillLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WaybillLogService {


    @Resource
    private WaybillLogMapper waybillLogMapper;



    public List<WaybillLog> findByWaybillId(String waybillId) throws Exception {
        return this.waybillLogMapper.findByWaybillId(waybillId);
    }



    public int updateWaubillLog(WaybillLog waybillLog)  {
        return this.waybillLogMapper.update(waybillLog);
    }

    public Integer insertWaubillLog(WaybillLog waybillLog)  {

        return this.waybillLogMapper.add(waybillLog);

    }





}
