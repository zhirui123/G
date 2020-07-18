package com.huagongwuliu.waybillelectronic.utils;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Li Fuxiang
 * @Description: 时间字段工具类
 * @date 2016/10/9
 */
public class DateUtil {

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_REGULAR = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";

    public static final String CRAWLER_FORMAT = "yyyy/MM/dd HH:mm:ss";

    public static final String SOLR_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATEFORMAT = "yyyyMMdd";

    public static final String DATEFORMAT1 = "yyMMdd";

    public static final String START_FORMAT = "yyyy-MM-dd 00:00:00";

    public static final String DATE_NIAN_YU_RI_FORMART = "yyyy年MM月dd日";

    public static final String NO_FORMAT = "yyyyMMddHHmmss";


    /**
     * 获取当前时间 年月日
     * @return String
     */
    public static String getNowDate() {
        return new DateTime().toString("yyyy-MM-dd");
    }
    /**
     * 获取当前时间 n年后的时间 年月日
     * @return String
     */
    public static String getNowDateAddN(int i) {
        return new DateTime().plusYears(i).toString("yyyy-MM-dd");
    }




    /**
     * 解析时间
     *
     * @param srcDate
     * @return
     * @throws ParseException
     */
    public static Date parse(String srcDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        Date date = null;
        try {
            date= sdf.parse(srcDate);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return date;
    }

    public static Date parse(String srcDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date= sdf.parse(srcDate);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return date;
    }
    /**
     * 解析时间
     *
     * @param srcDate
     * @return
     * @throws ParseException
     */
    public static String parse(Date srcDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        String date = null;
        try {
            date= sdf.format(srcDate);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 解析时间  yyyy-MM-dd 格式
     *
     * @param srcDate
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String srcDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
        Date date = null;
        try {
            date= sdf.parse(srcDate);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 将solr返回时间转换为正常时间
     *
     * @param srcDate
     * @return
     */
    /*public static String parseSolrDate(String srcDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(SOLR_FORMAT);
		Date solrDate = null;
		try {
			solrDate = sdf.parse(srcDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		Date realDate = new DateTime(solrDate).plusHours(8).toDate();
		return format(realDate, DEFAULT_FORMAT);
	}*/

    /**
     * 格式化时间
     *
     * @param srcDate
     * @param srcFormat
     * @param destFormat
     * @return
     */
    public static String format(String srcDate, String srcFormat, String destFormat) {
        SimpleDateFormat src = new SimpleDateFormat(srcFormat);
        Date date = null;
        try {
            date = src.parse(srcDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return format(date, destFormat);
    }

    /**
     * 格式化时间
     *
     * @param srcDate
     * @param destFormat
     * @return
     */
	/*public static String format(Date srcDate, String destFormat) {
		return new DateTime(srcDate).toString(destFormat);
	}*/

    /**
     * 格式化时间
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, DEFAULT_FORMAT);
    } /**

     * 格式化时间 日
     *
     * @param date
     * @return
     */
    public static String formatDate1(DateTime date) {
        return format1(date, DATE_FORMAT);
    }

    /**
     * 将CST格式转换为默认时间格式
     *
     * @param cstDate
     * @return
     * @throws ParseException
     */
    public static String cstToDefault(String cstDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        try {
            return format(sdf.parse(cstDate), DEFAULT_FORMAT);
        } catch (ParseException e) {
            throw new RuntimeException("输入时间'" + cstDate + "'格式不正确，时间格式应为EEE MMM dd HH:mm:ss zzz yyyy", e);
        }
    }

    /**
     * 转换默认格式为指定时间格式
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Object date, String format) {

        if (date == null)
            return null;
        if ("".equals(date))
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        return sdf.format(date);
    }

    public static String format1(Object date, String format) {

        if (date == null)
            return null;
        if ("".equals(date))
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 格式化开始时间
     *
     * @param srcDate
     * @return
     */
    public static String formatStart(Object srcDate) {
        return completeDate(srcDate, true);
    }

    /**
     * 格式化结束时间
     *
     * @param srcDate
     * @return
     */
    public static String formatEnd(Object srcDate) {
        return completeDate(srcDate, false);
    }

    /**
     * 补全时间
     *
     * @param srcDate
     * @param isStart
     * @return
     */
    public static String completeDate(Object srcDate, boolean isStart) {
        if (srcDate == null)
            return null;
        if ("".equals(srcDate))
            return "";

        String tmpDate = srcDate.toString();
        if (tmpDate.length() != 10)
            return tmpDate;

        if (isStart) {
            return srcDate + " 00:00:00";
        } else {
            return srcDate + " 23:59:59";
        }
    }

    /**
     * 获取上年时间
     *
     * @param srcDate
     * @return
     */
    public static String getLastYear(String srcDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        Calendar theCa = Calendar.getInstance();
        try {
            theCa.setTime(sdf.parse(srcDate));
        } catch (ParseException e) {

        }
        theCa.add(theCa.YEAR, -1);
        return sdf.format(theCa.getTime());
    }


    /**
     * 获取上一小时时间
     *
     * @param srcDate
     * @return
     *//*
    public static String getLastHour(String srcDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);

        Calendar theCa = Calendar.getInstance();
        try {
            theCa.setTime(sdf.parse(srcDate));
        } catch (ParseException e) {
            new Result(ErrorCode.E_10001);
        }
        theCa.add(theCa.HOUR, -1);
        return sdf.format(theCa.getTime());
    }*/

    /**
     * 转化 z时间格式转换 TUC
     * @param time
     * @return
     */
    public static String getDateFormat(String time){
        time = time.replace("Z", " UTC");//UTC是本地时间
        SimpleDateFormat format =new SimpleDateFormat(SOLR_FORMAT);
        Date d = null;
        try {
            d = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String date = sf.format(d);
        return date;
    }

    public static String formatDate(Date date) {
        return format(date, DATE_FORMAT);
    }


    //获取本月的开始时间
    public static Date getBeginDayOfMonth() {
         Calendar calendar = Calendar.getInstance();
         calendar.set(getNowYear(), getNowMonth() - 1, 1);
       return getDayStartTime(calendar.getTime());
    }

    //获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
       Calendar calendar = Calendar.getInstance();
       if(null != d) calendar.setTime(d);
       calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
       calendar.set(Calendar.MILLISECOND, 0);
       return new Timestamp(calendar.getTimeInMillis());
    }

    //获取今年是哪一年
    public static Integer getNowYear() {
       Date date = new Date();
       GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
       gc.setTime(date);
       return Integer.valueOf(gc.get(1));
    }

    //获取本月是哪一月
    public static int getNowMonth() {
       Date date = new Date();
       GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
       gc.setTime(date);
       return gc.get(2) + 1;
    }

    /**
     * string 转 datetime
     * @param date
     * @return
     */
    public static DateTime dateTimeFormat(String date){
        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(date);
    }

    /**
     * 获取 当前时间的时间戳 11位
     * @return
     */
    public static int getNowTimestamp(){
        return (int)(DateTime.now().getMillis() / 1000);
    }

    /**
     * 获取 当前时间的时间戳 提前 n秒 11位
     * @return
     */
    public static int getNowAfterTimestamp(int n){
        return (int)(DateTime.now().plusSeconds(n).getMillis() / 1000);
    }

    /**
     * 获取前 n天的时间戳
     * @param num
     * @return
     */
    public static int getNowBeforeDayTimestamp(int num){
        return (int)(DateTime.now().minusDays(num).getMillis() / 1000);
    }

    public static int getNowAfterHourTimestamp(int num){
        return (int)(DateTime.now().plusHours(num).getMillis() / 1000);
    }

    /**
     * 获取多少分钟时间的时间戳
     * @param num
     * @return
     */
    public static int getNowAfterMinuteTimestamp(int num){
        return (int)(DateTime.now().plusMinutes(num).getMillis() / 1000);
    }

    /**
     * 获取 当前时间一个月后的时间戳  11位
     * @return
     */
    public static int getLastMonthTimestamp(){
        return (int)(DateTime.now().minusMonths(1).getMillis() / 1000);
    }


    /**
     * 获取指定年月的第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH,firstDay);

        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定年月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);

        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取当天0点时间戳
     * @return
     */
    public static long getDayStartTimestamp(){
        Long  time = System.currentTimeMillis();  //当前时间的时间戳
        long zero = time/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset();
        return zero/1000;
    }

    /**
     * 获取当天23点59分59秒的时间戳
     * @return
     */
    public static long getDayEndTimestamp(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),23,59,59);
        long tt = calendar.getTime().getTime()/1000;
        return tt;
    }

    /**
     * 返回当前：*月*日*点*分
     * @return
     */
    public static String getSmsPayTime(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        StringBuilder builder = new StringBuilder();
        builder.append(month).append("月").append(day).append("日").append(hour).append("点")
                .append(minute).append("分");
        return builder.toString();
    }

    public static void main(String [] args) {
        System.out.println(getSmsPayTime());
        System.out.println(getNowAfterMinuteTimestamp(30));

    }
}
