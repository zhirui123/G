package com.huagongwuliu.waybillelectronic;

import com.huagongwuliu.waybillelectronic.mapper.WaybillMapper;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import com.huagongwuliu.waybillelectronic.service.WaybillTagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WaybillTest {



    @Resource
    WaybillMapper waybillMapper;


    @Autowired
    private WaybillTagService waybillTagService;
    @Test
    public void findAll() {
        List<Waybill> list = this.waybillMapper.findAll();
//        System.out.println("数据库汇总的运单" + list);
        System.out.println("数据库中共" +  list.size() + "条运单");
        List<Waybill> list2 = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getUserId() + ";"))), ArrayList::new));//o代表属性值，根据此属性值去重
        System.out.println("数据库汇总共" + list2.size() + "个用户");
    }






    @Test
    public  void insertWaybillTag(){

        List<Waybill> list = this.waybillMapper.findAll();

//        list.size()

        for (int i = 10000 ;i < 11000;i++){

            Waybill wa = list.get(i);

            System.out.println( "当前处理到的位置是：" + i  +  "总工需要处理的数据量是：" + list.size() + "还有" + (list.size() - i)
                    +"条数据需要处理");

            this.waybillTagService.addWaybillTagByWaybill(wa);
        }

//        for (Waybill wa: list) {
////            Waybill wa = list.get(0);
//            this.waybillTagService.addWaybillTagByWaybill(wa);
//
//        }




    }






}
