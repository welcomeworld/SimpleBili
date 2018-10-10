package com.github.welcomeworld.simplebili.utils;

import java.util.Locale;

public class StringUtils {
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
}
