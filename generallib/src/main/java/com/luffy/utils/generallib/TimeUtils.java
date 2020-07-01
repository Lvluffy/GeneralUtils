package com.luffy.utils.generallib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 时间-辅助工具
 */
public class TimeUtils {
    public static final SimpleDateFormat DATE_FORMAT_Y = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat DATE_FORMAT_M = new SimpleDateFormat("MM");
    public static final SimpleDateFormat DATE_FORMAT_D = new SimpleDateFormat("dd");
    public static final SimpleDateFormat DATE_FORMAT_HMS = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_HM = new SimpleDateFormat("HH:mm");

    public static final SimpleDateFormat DATE_FORMAT_YMD_HMS_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HMS_2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HMS_3 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HMS_4 = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HMS_5 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public static final SimpleDateFormat DATE_FORMAT_YMD_HM_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HM_2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HM_3 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HM_4 = new SimpleDateFormat("yyyyMMddHHmm");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HM_5 = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    public static final SimpleDateFormat DATE_FORMAT_YMD_1 = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_YMD_2 = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat DATE_FORMAT_YMD_3 = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat DATE_FORMAT_YMD_4 = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat DATE_FORMAT_YMD_5 = new SimpleDateFormat("yyyy.MM.dd");

    public static final SimpleDateFormat DATE_FORMAT_YM_1 = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat DATE_FORMAT_YM_2 = new SimpleDateFormat("yyyy/MM");
    public static final SimpleDateFormat DATE_FORMAT_YM_3 = new SimpleDateFormat("yyyy年MM月");
    public static final SimpleDateFormat DATE_FORMAT_YM_4 = new SimpleDateFormat("yyyyMM");
    public static final SimpleDateFormat DATE_FORMAT_YM_5 = new SimpleDateFormat("yyyy.MM");

    public static final SimpleDateFormat DATE_FORMAT_MD_1 = new SimpleDateFormat("MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_MD_2 = new SimpleDateFormat("MM/dd");
    public static final SimpleDateFormat DATE_FORMAT_MD_3 = new SimpleDateFormat("MM月dd日");
    public static final SimpleDateFormat DATE_FORMAT_MD_4 = new SimpleDateFormat("yyyydd");
    public static final SimpleDateFormat DATE_FORMAT_MD_5 = new SimpleDateFormat("yyyy.dd");

    public static final SimpleDateFormat DATE_FORMAT_MD_HMS_1 = new SimpleDateFormat("MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_MD_HMS_2 = new SimpleDateFormat("MM/dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_MD_HMS_3 = new SimpleDateFormat("MM月dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_MD_HMS_4 = new SimpleDateFormat("MM月dd日 HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_MD_HMS_5 = new SimpleDateFormat("MM.dd HH:mm:ss");

    public static final SimpleDateFormat DATE_FORMAT_MD_HM_1 = new SimpleDateFormat("MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_MD_HM_2 = new SimpleDateFormat("MM/dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_MD_HM_3 = new SimpleDateFormat("MM月dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_MD_HM_4 = new SimpleDateFormat("MM月dd日 HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_MD_HM_5 = new SimpleDateFormat("MM.dd HH:mm");

    private TimeUtils() {
    }

    public static TimeUtils getInstance() {
        return TimeUtilsHelper.mTimeUtils;
    }

    private static class TimeUtilsHelper {
        private static final TimeUtils mTimeUtils = new TimeUtils();
    }

    /**
     * 获取格式化时间
     *
     * @param date       日期
     * @param dateFormat 时间格式
     * @return 格式化时间
     */
    public String getTime(Date date, SimpleDateFormat dateFormat) {
        if (ValidUtils.getInstance().isValid(date)) {
            return dateFormat.format(date);
        }
        return null;
    }

    /**
     * 获取格式化时间
     *
     * @param time       时间戳
     * @param dateFormat 时间格式
     * @return 格式化时间
     */
    public String getTime(long time, SimpleDateFormat dateFormat) {
        if (time > 0) {
            return this.getTime(new Date(time), dateFormat);
        }
        return null;
    }

    /**
     * 获取格式化时间
     *
     * @param time       时间戳
     * @param dateFormat 时间格式
     * @return 格式化时间
     */
    public String getTime(String time, SimpleDateFormat dateFormat) {
        try {
            return this.getTime(Long.parseLong(time), dateFormat);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取时间
     *
     * @param time 时间戳
     * @return 默认格式化时间
     */
    public String getTime(long time) {
        if (time > 0) {
            return getTime(time, DATE_FORMAT_YMD_HMS_1);
        }
        return null;
    }

    /**
     * 获取时间
     *
     * @param date 日期
     * @return 时间戳
     */
    public long getTimeLong(Date date) {
        try {
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取时间
     *
     * @param time             时间
     * @param simpleDateFormat 时间格式
     * @return 时间戳
     */
    public long getTimeLong(String time, SimpleDateFormat simpleDateFormat) {
        try {
            return this.getTimeLong(simpleDateFormat.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取当前时间
     *
     * @return 时间戳
     */
    public long getCurrentTimeLong() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public String getCurrentTimeString() {
        return String.valueOf(getCurrentTimeLong());
    }

    /**
     * 获取格式化当前时间
     *
     * @param dateFormat 时间格式
     * @return 格式化时间
     */
    public String getCurrentTimeString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeLong(), dateFormat);
    }

    /**
     * String转Date
     *
     * @param strTime          时间
     * @param simpleDateFormat 时间格式
     * @return 日期
     */
    public Date stringToDate(String strTime, SimpleDateFormat simpleDateFormat) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 判断选择的日期是否是今天
     *
     * @param time 时间戳
     * @return 是否是今天
     */
    public boolean isToday(long time) {
        return isThisTime(time, "yyyy-MM-dd");
    }

    /**
     * 判断选择的日期是否是本周
     *
     * @param time 时间戳
     * @return 是否是本周
     */
    public boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        return paramWeek == currentWeek;
    }

    /**
     * 判断选择的日期是否是本月
     *
     * @param time 时间戳
     * @return 是否是本月
     */
    public boolean isThisMonth(long time) {
        return isThisTime(time, "yyyy-MM");
    }

    private boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        return param.equals(now);
    }

    /**
     * 判断某个时间是否是在条件时间的minute分钟之内
     *
     * @param now    当前日期
     * @param time   条件时间
     * @param minute 正数表示在条件时间minute分钟之后，负数表示在条件时间minute分钟之前
     * @return true:不显示 false：显示
     */
    public boolean belongDate(long now, long time, int minute) {
        //得到日历
        Calendar calendar = Calendar.getInstance();
        //把当前时间赋给日历
        calendar.setTime(new Date(time));
        calendar.add(Calendar.MINUTE, minute);
        //得到minute分钟之后的时间
        long endTime = calendar.getTime().getTime();
        return endTime > now;
    }

    /**
     * 当前时间是星期几
     *
     * @return
     */
    public String dayForWeek() {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar.getInstance().setTime(new Date());
        int w = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w < 0 ? 0 : w];
    }

    /*----------将时间转化成几分钟前、几天前等字样----------*/
    private final static long MINUTE = 60 * 1000;// 1分钟
    private final static long HOUR = 60 * MINUTE;// 1小时
    private final static long DAY = 24 * HOUR;// 1天
    private final static long WEEK = 7 * HOUR;// 1周
    private final static long MONTH = 31 * DAY;// 1月
    private final static long YEAR = 12 * MONTH;// 1年

    /**
     * 返回文字描述的日期
     *
     * @param date 时间戳
     * @return 日期描述
     */
    public String getTimeFormatText(long date) {
        long diff = new Date().getTime() - date;
        long r = 0;
        if (diff > DAY) {
            return getTime(date, TimeUtils.DATE_FORMAT_YMD_1);
        }
        if (diff > HOUR) {
            r = (diff / HOUR);
            return r + "小时前";
        }
        if (diff > MINUTE) {
            r = (diff / MINUTE);
            return r + "分钟前";
        }
        return "刚刚";
    }
}
