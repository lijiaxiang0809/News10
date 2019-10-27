package com.teach.news10.local_utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


public class TimeUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     * 
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     * 
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time in milliseconds
     * 
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     * 
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     * 
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType, Locale.getDefault()).format(data);
    }

    public static String stringToString(String time, String formatType) throws ParseException {
        return dateToString(stringToDate(time, formatType), formatType);
    }

    public static String stringToStringUTC(String time, String formatType) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("utc"));
        String time2;
        try {
            Date date = format.parse(time);
            System.out.println(date);

            Calendar calendar = new GregorianCalendar();
            TimeZone timeZone = calendar.getTimeZone();
            format.setTimeZone(timeZone);

            time2 = format.format(date);
            if (time2 != null && time2.split(" ").length > 0)
                return time2.split(" ")[0];

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String longToString(long currentTime, String formatType) throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        return dateToString(date, formatType);
    }

    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        return stringToDate(sDateTime, formatType);
    }

    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType, Locale.getDefault());
        Date date;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate2() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return formatter.format(currentTime);
    }

    public static long getLongDate(String date) {
        if (TextUtils.isEmpty(date)) return 0L;
        String[] arr = date.split(":");
        if (arr.length == 0) return 0L;
        if (arr.length == 1) {
            return Integer.parseInt(arr[0]) * 1000;
        } else if (arr.length == 2) {
            return Integer.parseInt(arr[0]) * 60 * 1000 + Integer.parseInt(arr[1]) * 1000;
        } else if (arr.length == 3) {
            return Integer.parseInt(arr[0]) * 60 * 60 * 1000
                    + Integer.parseInt(arr[1]) * 60 * 1000 + Integer.parseInt(arr[2]) * 1000;
        }
        return 0L;
    }

    public static long compareCurrent(String time) throws ParseException {
        SimpleDateFormat utcformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        utcformat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date mGmsDate = utcformat.parse(time);
        return mGmsDate.getTime() - System.currentTimeMillis();
    }

    /*
     * 判断本地时间是否小于服务器时间(时间戳)
     */
    public static boolean datePk(long localDate, long serverDate) {
        return localDate < serverDate;
    }

    public static String[] getSchoolYear(int year, int month) {
        if (month < 7) {
            year -= 1;
        }
        String[] schoolYear = new String[]{
                (year - 1) + "-" + year, year + "-" + (year + 1), (year + 1) + "-" + (year + 2)
        };
        return schoolYear;
    }

    public static Date getLocalDate(String time) {
        SimpleDateFormat localFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        SimpleDateFormat utcFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date gpsUTCDate = utcFormater.parse(time);
            long localTime = gpsUTCDate.getTime();
            String localGmsDate = localFormater.format(localTime);
            return localFormater.parse(localGmsDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean shoudShowTime(long timestamp, long target) {
        //TODO:修改上拉不显示时间BUG
        return Math.abs(timestamp - target) >= 3 * 60 * 1000;
    }


    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.getDefault());
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 将UTC时间转为北京时间
     *
     * @param utcTime
     * @return
     */
    public static String utc2Local(String utcTime) {
        String localTime = "";
        try {
            SimpleDateFormat utcFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date gpsUTCDate = utcFormater.parse(utcTime);
            SimpleDateFormat localFormater = new SimpleDateFormat("HH:mm", Locale.getDefault());
            localFormater.setTimeZone(TimeZone.getDefault());
            localTime = localFormater.format(gpsUTCDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localTime;
    }

    /**
     * 秒转分钟
     */
    public static String secondToMinute(String second) {
        if (Lang.isEmpty(second)) return "";
        int minute = Lang.toInt(second) / 60;
        int second2 = Lang.toInt(second) % 60;
        String text = minute < 10 ? "0" + minute : String.valueOf(minute);
        String text2 = second2 < 10 ? "0" + second2 : String.valueOf(second2);
        return text + ":" + text2;
    }
}
