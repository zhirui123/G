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
    public static synchronized String getCode(Integer serialNum, String carriageLicensekey)
    {

        StringBuffer sBuffer = new StringBuffer();

        sBuffer.append(carriageLicensekey.substring(0,4)); //区域代码
        sBuffer.append(carriageLicensekey.substring(carriageLicensekey.length() - 6,carriageLicensekey.length())); //企业编码
        sBuffer.append(getDateTime()); //生成时间
        if (serialNum <10){
            sBuffer.append("000" + serialNum);
        }else if (serialNum < 100){
            sBuffer.append("00" + serialNum);
        }else  if (serialNum < 1000){
            sBuffer.append("0" + serialNum);
        }else  if (serialNum < 10000){
            sBuffer.append(serialNum);
        }else  if (serialNum >= 10000){
            sBuffer.append((char)(65 + serialNum / 1000  -10));
            int i =  serialNum % 1000;
            if (i >= 100){
                sBuffer.append(i);
            }else if (i >= 10){
                sBuffer.append("0");
                sBuffer.append(i);
            }else  if (i > 0){
                sBuffer.append("00");
                sBuffer.append(i);
            }else{
                sBuffer.append("0000");
            }
        }else{
            sBuffer.append(0000);
        }   //顺序号

        sBuffer.append(getRandom(3)); //3位随机数

        byte[] sb = sBuffer.toString().getBytes();


        return sBuffer.toString();
    }



    public static String creatYDOrderNum(String licenseKey,int sortNum){
        String sortNum2 = null;
        if(0 < sortNum && sortNum <=9999){
            int i = (4 - (String.valueOf(sortNum)).length()) > 0 ? 4 - (String.valueOf(sortNum)).length() : 0;
            String buLing = null;
            for(int j = 0;j <= i; j++){
                buLing +="0";
            }
            sortNum2 = buLing+sortNum;
        }else{
            String buLing = null;
            String letter = String.valueOf(65 + (sortNum / 1000) - 10);
            int i =  sortNum % 1000;
            if (i >= 100){
                buLing = String.valueOf(i);
            }else if (i >= 10){
                buLing = "0"+ String.valueOf(i);
            }else  if (i > 0){
                buLing = "00"+ String.valueOf(i);
            }
            sortNum2 = letter + buLing;

        }
        String s = licenseKey.substring(0, 4) + licenseKey.substring(licenseKey.length() - 6) + DateUtil.format1(new Date(),DateUtil.DATEFORMAT) + sortNum2 + getRandom(3);
        byte[] bytes = s.getBytes();
        byte temp = bytes[0];
        for(int i=1;i<bytes.length;i++){
            temp^=bytes[i];
        }
        int y = temp % 10;
        return s + String.valueOf(y);
    }






    public static void main(String[] args) {

          creatYDOrderNum("1234567890",11);


        System.out.println(DateUtil.format1(new Date(),DateUtil.DATE_FORMAT));


    }



}
