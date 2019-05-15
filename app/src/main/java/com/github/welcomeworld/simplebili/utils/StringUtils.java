package com.github.welcomeworld.simplebili.utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StringUtils {
    public static final int MILLISECOND=0;
    public static final int SECOND=1;
    public static String formatTime(long time){
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int totalMinutes=totalSeconds / 60;
        int minutes = totalMinutes %60;
        int hours=totalMinutes/60;
        if(hours>0){
            return String.format(Locale.getDefault(),"%02d:%02d:%02d",hours,minutes,seconds);
        }else if(minutes>0){
            return String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        }else if(seconds>=0){
            return String.format(Locale.getDefault(),"00:%02d",seconds);
        }else{
         return "--:--";
        }
    }
    public static String formatNumber(long number){
        String result;
        double handleNumber;
        if(number>100000000){
            handleNumber=(double)number/100000000;
            result=String.format(Locale.CHINA,"%.1f亿",handleNumber);
        }else if(number>10000){
            handleNumber=(double)number/10000;
            result=String.format(Locale.CHINA,"%.1f万",handleNumber);
        }else {
            result=number+"";
        }
        return result;
    }
    public static String formatTime(long time,int unit){
        int totalSeconds;
        if(unit==MILLISECOND){
            totalSeconds = (int) (time / 1000);
        }else {
            totalSeconds=(int)time;
        }
        int seconds = totalSeconds % 60;
        int totalMinutes=totalSeconds / 60;
        int minutes = totalMinutes %60;
        int hours=totalMinutes/60;
        if(hours>0){
            return String.format(Locale.getDefault(),"%02d:%02d:%02d",hours,minutes,seconds);
        }else if(minutes>0){
            return String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        }else if(seconds>=0){
            return String.format(Locale.getDefault(),"00:%02d",seconds);
        }else{
            return "--:--";
        }
    }

    public static String formatDate(long time){
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance();
        Calendar formatTime = Calendar.getInstance();
        formatTime.setTime(date);
        if(calendar.get(Calendar.YEAR)>formatTime.get(Calendar.YEAR)){
            return formatTime.get(Calendar.YEAR)+" "+formatTime.get(Calendar.MONTH)+"-"+formatTime.get(Calendar.DAY_OF_MONTH);
        }else if(calendar.get(Calendar.MONTH)>formatTime.get(Calendar.MONTH)){
            return formatTime.get(Calendar.MONTH)+"-"+formatTime.get(Calendar.DAY_OF_MONTH)+" "+formatTime.get(Calendar.HOUR_OF_DAY)+":"+formatTime.get(Calendar.MINUTE);
        }else if(calendar.get(Calendar.DAY_OF_MONTH)>formatTime.get(Calendar.DAY_OF_MONTH)){
            return formatTime.get(Calendar.MONTH)+"-"+formatTime.get(Calendar.DAY_OF_MONTH)+" "+formatTime.get(Calendar.HOUR_OF_DAY)+":"+formatTime.get(Calendar.MINUTE);
        }else {
            return formatTime.get(Calendar.HOUR_OF_DAY)+":"+formatTime.get(Calendar.MINUTE);
        }
    }
}
