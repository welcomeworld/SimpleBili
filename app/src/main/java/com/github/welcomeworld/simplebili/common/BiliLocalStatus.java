package com.github.welcomeworld.simplebili.common;


public class BiliLocalStatus {
    private static String accessKey = "";
    private static boolean isLogin = false;
    private static int mid =-1;



    public static String getAccessKey(){
        return accessKey;
    }

    public static void setAccessKey(String key){
        accessKey = key;
    }

    public static boolean isLogin(){
        return isLogin;
    }

    public static void setLogin(boolean login){
        isLogin = login;
    }

    public static int getMid() {
        return mid;
    }

    public static void setMid(int mid) {
        BiliLocalStatus.mid = mid;
    }
}
