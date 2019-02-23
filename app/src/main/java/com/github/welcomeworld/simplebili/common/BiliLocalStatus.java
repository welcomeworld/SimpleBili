package com.github.welcomeworld.simplebili.common;

import android.content.Context;

public class BiliLocalStatus {

    public static String getAccessKey(Context context){
        return context.getSharedPreferences("data",Context.MODE_PRIVATE).getString("accessKey","");
    }

    public static void saveAccessKey(Context context,String key){
        context.getSharedPreferences("data",Context.MODE_PRIVATE).edit().putString("accessKey",key).apply();
    }

    public static boolean isLogin(Context context){
        return !context.getSharedPreferences("data",Context.MODE_PRIVATE).getString("accessKey","").equalsIgnoreCase("");
    }
}
