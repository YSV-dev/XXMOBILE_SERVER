package com.swlibs.common.types;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Класс для работы с датой и временем.
 */
public class DateTime {
    //YYYY-MM-DD HH24:MI:SS

    /**
     * Date ISO 8601.
     */
    final private static String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    //final static String patternText = "dd.MM.yyyy HH:mm:ss";
    //final static String patternSql = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date ISO 8601 + milliseconds.
     */
    public final static String FORMAT_ISO8601_MILLS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    final private static String patternFileName = "yyyyMMdd_HHmmss";


    private Date mDate;
    private static SimpleDateFormat sFormatUTC = null;
    //private SimpleDateFormat mFormatSql;

    final private static long sDayOfMills = 86400000L;

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String getPatternFileName() {
        return patternFileName;
    }

    private static void initFormat() {
        if(sFormatUTC == null) {
            sFormatUTC = new SimpleDateFormat(pattern, Locale.getDefault());
            sFormatUTC.setTimeZone(TimeZone.getTimeZone("GMT"));  //TODO: UTC?
            /*mFormatSql = new SimpleDateFormat(patternSql);
            mFormatSql.setTimeZone(TimeZone.getTimeZone("Asia/Yekaterinburg"));*/
        }
    }

    public static Date now() {
        return new Date();
    }

    public static Date fromLong(long timestamp) {
        return new Date(timestamp);
    }

    public DateTime() {
        initFormat();
        mDate = new Date();
    }

    public DateTime(long timestamp) {
        initFormat();
        mDate = new Date(timestamp);
    }

    public DateTime(String date) {
        initFormat();
        try {
            mDate = sFormatUTC.parse(date);
        } catch (ParseException e) {
            mDate = new Date();
        }
    }

    public DateTime(Date date) {
        initFormat();
        mDate = (Date)date.clone();
    }

    public DateTime fromDate(Date date) {
        if(date == null) {
            return null;
        } else {
            return new DateTime(date);
        }
    }

    @Deprecated
    public static String toDiffString(Date date, String defaultValue) {
        return toString(date, true, defaultValue);
    }

    public static Date truncate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT"));
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date addDays(Date date, int days) {
        return new Date(date.getTime() + (sDayOfMills * days));
    }

    public static String toString(Date date, boolean trimDate, String defaultValue) {
        if(date == null) {
            return defaultValue;
        } else {
            if (trimDate && DateTime.isToday(date)) {
                return DateTime.toFormat(date, "HH:mm");
            } else {
                return DateTime.toFormat(date, "dd.MM.yyyy HH:mm");
            }
        }
    }

    public static String toString(Date date, String isnull) {
        if(date == null) {
            return isnull;
        } else {
            DateTime dt = new DateTime(date);
            return dt.toFormat("dd.MM.yyyy HH:mm");
        }
    }

    public static Long toDiffDates(Date dateStart, Date dateEnd) {
        if(dateStart == null) {
            return null;
        }
        if(dateEnd == null) {
            return null;
        }
        return dateEnd.getTime() - dateStart.getTime();
    }

    @Deprecated
    public Boolean isToday() {
        return isToday(mDate);
    }

    public static Boolean isToday(Date date) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long todayInMillis = c.getTimeInMillis();

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        long inMillis = c2.getTimeInMillis();

        return todayInMillis == inMillis;
    }

    /*public DateTime(String date, Boolean utc){
        initFormat();
        try {
            if(utc){//UTC
                mDate = mFormatUTC.parse(date);
            }else{//Yekaterinburg
                mDate = mFormatSql.parse(date);
            }
        } catch (ParseException e) {
            Log.e("parse", e);
            mDate = new Date();
        }
    }*/

    public Date getSystemType() {
        return mDate;
    }

    public long getTime() {
        return mDate.getTime();
    }
    
    /*@Depricated
    public Date asDate() {
        return (Date)mDate.clone();
    }*/

    public Date toDate() {
        return (Date)mDate.clone();
    }

    public String toString() {
        //return String.format("%1$tY-%1$tm-%1$tdT%1$tT", mDate);
        return sFormatUTC.format(mDate);
    }

    public static String asString(Date date) {
        initFormat();
        return sFormatUTC.format(date);
    }

    @Deprecated
    public Long getDifference(DateTime date) {
        return date.getTime() - this.mDate.getTime();
    }

    public static Long getDifference(Date firstDate, Date secondDate) {
        if(firstDate == null) {
            return null;
        }
        if(secondDate == null) {
            return null;
        }
        return firstDate.getTime() - secondDate.getTime();
    }

    @Deprecated
    public String toFormat(String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(mDate);
    }

    public static String toFormat(Date date, String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(date);
    }

    public static String toFormatUTC(Date date, String format) {
        SimpleDateFormat d = new SimpleDateFormat(format, Locale.getDefault());
        d.setTimeZone(TimeZone.getTimeZone("UTC"));
        return d.format(date);
    }

    public static Date parseDate(String date) {
        initFormat();
        if(date == null) {
            return null;
        } else if("".equals(date)) {
            return null;
        }
        try {
            return sFormatUTC.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
