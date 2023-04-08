package com.moh.hamadpulse.constants;

import android.util.Log;

import com.moh.hamadpulse.models.MyDateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ToolApp {

    static final int SECOND = 1000;
    static final int MINUTE = 60 * SECOND;
    static final int HOUR = 60 * MINUTE;
    static final int DAY = 24 * HOUR;
    public static String pattern_date = "yyyy-MM-dd HH:mm:ss";
    public static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(pattern_date, Locale.ENGLISH);

    public static String getDateNow()
    {
        return mSimpleDateFormat.format(new Date());
    }

    public static String convertFormatDate(String mDate,SimpleDateFormat pSimpleDateFormat) {

        //SimpleDateFormat spf = new SimpleDateFormat("dd-MMM-yy");
        Date newDate = null;
        try {
            if (pSimpleDateFormat != null)
                newDate = pSimpleDateFormat.parse(mDate);
            else
                newDate = mSimpleDateFormat.parse(mDate);
            return mSimpleDateFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("ERROR",e.getMessage());
            return "";
        }
    }

    public static MyDateTime getDiffDate(String endDate, String StartDate) {
        Date d1 = null;
        Date d2 = null;
        long difference;
        MyDateTime mMyDateTime = null;

        try {

            mMyDateTime = new MyDateTime();
            d1 = mSimpleDateFormat.parse(endDate);
            d2 = mSimpleDateFormat.parse(StartDate);
            difference = d1.getTime() - d2.getTime();


            long calDays = difference / DAY;
            mMyDateTime.setDays(calDays + "");
            difference %= DAY;
            long calHour = difference / HOUR;
            mMyDateTime.setHours(calHour < 10 ? 0 + "" + calHour : calHour + "");
            difference %= HOUR;
            long calMinute = difference / MINUTE;
            mMyDateTime.setMinutes(calMinute < 10 ? 0 + "" + calMinute : calMinute + "");


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mMyDateTime;
    }
}
