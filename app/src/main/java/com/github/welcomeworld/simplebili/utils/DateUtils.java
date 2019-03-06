package com.github.welcomeworld.simplebili.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {
    public static String getDay(long date){
        Calendar calendar=new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_YEAR,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        if(calendar.getTimeInMillis()<date){
            return new SimpleDateFormat("MM-dd", Locale.CHINA).format(new Date(date));
        }
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date(date));
    }
}
