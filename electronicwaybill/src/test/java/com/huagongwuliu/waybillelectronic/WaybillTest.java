package com.huagongwuliu.waybillelectronic;

import com.huagongwuliu.waybillelectronic.mapper.WaybillMapper;
import com.huagongwuliu.waybillelectronic.pojo.Waybill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WaybillTest {



    @Autowired
    WaybillMapper waybillMapper;

    @Test
    public void findAll() {
        List<Waybill> list = this.waybillMapper.findAll();
//        System.out.println("数据库汇总的运单" + list);
        System.out.println("数据库中共" +  list.size() + "条运单");
        List<Waybill> list2 = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getUserId() + ";"))), ArrayList::new));//o代表属性值，根据此属性值去重
        System.out.println("数据库汇总共" + list2.size() + "个用户");
    }


}
