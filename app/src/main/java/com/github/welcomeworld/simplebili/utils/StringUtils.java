package com.github.welcomeworld.simplebili.utils;

import java.text.DecimalFormat;
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
}
