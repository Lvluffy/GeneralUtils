package com.luffy.generalutilslib.utils;

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
    public static final SimpleDateFormat DATE_FORMAT_YMD_HMS_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HMS_2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HMS_3 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HMS_4 = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HM_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HM_2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HM_3 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_YMD_HM_4 = new SimpleDateFormat("yyyyMMddHHmm");
    public static final SimpleDateFormat DATE_FORMAT_YMD_1 = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_YMD_2 = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat DATE_FORMAT_YMD_3 = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat DATE_FORMAT_YMD_4 = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat DATE_FORMAT_YM_1 = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat DATE_FORMAT_YM_2 = new SimpleDateFormat("yyyy/MM");
    public static final SimpleDateFormat DATE_FORMAT_YM_3 = new SimpleDateFormat("yyyy年MM月");
    public static final SimpleDateFormat DATE_FORMAT_YM_4 = new SimpleDateFormat("yyyyMM");
    public static final SimpleDateFormat DATE_FORMAT_MD_1 = new SimpleDateFormat("MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_MD_2 = new SimpleDateFormat("MM/dd");
    public static final SimpleDateFormat DATE_FORMAT_MD_3 = new SimpleDateFormat("MM月dd日");
    public static final SimpleDateFormat DATE_FORMAT_MD_4 = new SimpleDateFormat("yyyydd");
    public static final SimpleDateFormat DATE_FORMAT_Y = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat DATE_FORMAT_M = new SimpleDateFormat("MM");
    public static final SimpleDateFormat DATE_FORMAT_D = new SimpleDateFormat("dd");
    public static final SimpleDateFormat DATE_FORMAT_HMS = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_HM = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_MD_HMS_1 = new SimpleDateFormat("MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_MD_HMS_2 = new SimpleDateFormat("MM/dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_MD_HMS_3 = new SimpleDateFormat("MM月dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_MD_HMS_4 = new SimpleDateFormat("MM月dd日 HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_MD_HM_1 = new SimpleDateFormat("MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_MD_HM_2 = new SimpleDateFormat("MM/dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_MD_HM_3 = new SimpleDateFormat("MM月dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_MD_HM_4 = new SimpleDateFormat("MM月dd日 HH:mm");

    private TimeUtils() {
    }

    public static TimeUtils getInstance() {
        return TimeUtilsHelper.mTimeUtils;
    }

    private static class TimeUtilsHelper {
        private static TimeUtils mTimeUtils;

        static {
            mTimeUtils = new TimeUtils();
        }
    }

    /**
     * 获取格式化时间
     *
     * @param time
     * @param dateFormat
     * @return
     */
    public String getTime(long time, SimpleDateFormat dateFormat) {
        if (time == 0) {
            return "";
        }
        return dateFormat.format(new Date(time));
    }

    /**
     * 获取格式化时间
     *
     * @param time
     * @param dateFormat
     * @return
     */
    public String getTimeUnix(long time, SimpleDateFormat dateFormat) {
        if (time == 0) {
            return "";
        }
        return dateFormat.format(new Date(time * 1000));
    }

    /**
     * 获取格式化时间
     *
     * @param time
     * @param dateFormat
     * @return
     */
    public String getTime(String time, SimpleDateFormat dateFormat) {
        if (time != null && time.length() > 0 && !"".equals(time)) {
            long tm = Long.parseLong(time);
            return dateFormat.format(new Date(tm));
        } else {
            return "";
        }
    }

    /**
     * 获取格式化时间
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public String getTime(Date date, SimpleDateFormat dateFormat) {
        if (!ValidUtils.getInstance().isValid(date)) {
            return "";
        }
        return dateFormat.format(date);
    }

    /**
     * 获取时间
     *
     * @param time
     * @return
     */
    public String getTime(long time) {
        if (time == 0) {
            return "";
        }
        return getTime(time, DATE_FORMAT_YMD_HMS_1);
    }

    /**
     * 获取时间
     *
     * @param date
     * @return
     */
    public long getTimeLong(Date date) {
        long timeLong = 0;
        if (date != null) {
            timeLong = date.getTime();
        }
        return timeLong;
    }

    /**
     * 获取时间
     *
     * @param time
     * @return
     */
    public long getTimeLong(String time, SimpleDateFormat simpleDateFormat) {
        long timeLong = 0;
        if (time != null && !"".equals(time) && time.length() > 0) {
            try {
                Date date = simpleDateFormat.parse(time);
                if (date != null) {
                    timeLong = date.getTime();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return timeLong;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public long getCurrentTimeLong() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public String getCurrentTimeString() {
        return String.valueOf(getCurrentTimeLong());
    }

    /**
     * 获取格式化当前时间
     *
     * @param dateFormat
     * @return
     */
    public String getCurrentTimeString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeLong(), dateFormat);
    }

    /**
     * String转Date
     *
     * @param strTime
     * @param simpleDateFormat
     * @return
     * @throws ParseException
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
     * @param time
     * @return
     */
    public boolean isToday(long time) {
        return isThisTime(time, "yyyy-MM-dd");
    }

    /**
     * 判断选择的日期是否是本周
     *
     * @param time
     * @return
     */
    public boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }

    /**
     * 判断选择的日期是否是本月
     *
     * @param time
     * @return
     */
    public boolean isThisMonth(long time) {
        return isThisTime(time, "yyyy-MM");
    }

    private boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
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
        if (endTime > now) {
            return true;
        } else {
            return false;
        }
    }

    /*----------将时间转化成几分钟前、几天前等字样----------*/
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 1月
    private final static long year = 12 * month;// 1年

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public String getTimeFormatText(long date) {
        long diff = new Date().getTime() - date;
        long r = 0;
        if (diff > day) {
            return getTime(date, TimeUtils.DATE_FORMAT_YMD_1);
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }
}