package com.huagongwuliu.waybillelectronic.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class WaybillUtils {

    /**
     * 生成时间戳
     */
    private static String getDateTime() {
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * 生成固定长度随机码
     *
     * @param n 长度
     */

    private static long getRandom(long n) {
        long min = 1, max = 9;
        for (int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min;
        return rangeLong;
    }

    /**
     *
     * @param serialNum 顺序号
     * @param carriageLicensekey 承运人许可证号
     * @return
     */
    public static synchronized String getCode(String serialNum, String carriageLicensekey)
    {

        String  areaCode; //地区标号
        String  enterpriseCode;// 企业标号
        String  timeCode =  getDateTime(); //时间戳

//        String serialNumber = "123"; //顺序号
        Long randomNumber =   getRandom(3);


        areaCode = carriageLicensekey.substring(0,4);
        enterpriseCode = carriageLicensekey.substring(carriageLicensekey.length() - 6,carriageLicensekey.length());

        System.out.println(areaCode + "-------------" + enterpriseCode);


        return getDateTime() + getRandom(6);
    }





    public static void main(String[] args) {
        System.out.println(getCode("123","12345678901222"));
    }



}
