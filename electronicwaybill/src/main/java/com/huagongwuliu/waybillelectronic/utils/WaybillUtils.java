package com.huagongwuliu.waybillelectronic.utils;

import com.huagongwuliu.waybillelectronic.pojo.Goods;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            String buLing = "";
            for(int j = 0;j < i; j++){
                buLing +="0";
            }
            sortNum2 = buLing+sortNum;
        }else{
            String buLing = "";
            String letter = String.valueOf((char)(65 + (sortNum / 1000) - 10));
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
        String s = licenseKey.substring(0, 4) + licenseKey.substring(licenseKey.length() - 6) + DateUtil.format1(new Date(),DateUtil.DATEFORMAT1) + sortNum2 + getRandom(3);


        byte[] bytes = s.getBytes();
        byte temp = bytes[0];
        for(int i=1;i<bytes.length;i++){
            temp^=bytes[i];
        }
        int y = temp % 10;
        System.out.println(String.valueOf(y));
        return s + y;
    }





    public static List<Goods> getAllByExcel(){
        List<Goods> list=new ArrayList<Goods>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("/Users/mac/Desktop/product.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行

            System.out.println("表的列数："+clos+" 表的行数:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String goods_name=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++

                    String un_num=rs.getCell(j++, i).getContents();
                    String goods_type=rs.getCell(j++, i).getContents();
                    String goods_packing_type=rs.getCell(j++, i).getContents();

                    System.out.println("goods_name:"+goods_name+" un_num:"+un_num+" goods_type:"+goods_type+" goods_packing_type:"+goods_packing_type);
//
                    Goods goods = new Goods();
                    goods.setGoodsName(goods_name);
                    goods.setUnNum(un_num);
                    goods.setGoodsType(goods_type);
                    goods.setGoodsPackingType(goods_packing_type);
//
//


                    list.add(goods);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }







    public static void main(String[] args) {

//          creatYDOrderNum("1234567890",11);
//
//
//        System.out.println(creatYDOrderNum("1020102010201020",11));
//
//        ; //获取当前0点的时间戳
//        DateUtil.getDayEndTimestamp();
//        DateUtil.getNowTimestamp();
//
//
//        System.out.println("0点的时间 " + DateUtil.getDayStartTimestamp());
//        System.out.println("当前时间  " + DateUtil.getNowTimestamp());
//        System.out.println("晚上时间  " + DateUtil.getDayEndTimestamp());








    }



}
