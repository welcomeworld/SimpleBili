package com.github.welcomeworld.simplebili.common;


public class BiliLocalStatus {
    private static String accessKey = "";
    private static boolean isLogin = false;
    private static int mid =-1;
    private static String cover;
    private static String name="";



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

    public static String getCover() {
        return cover;
    }

    public static void setCover(String cover) {
        BiliLocalStatus.cover = cover;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        BiliLocalStatus.name = name;
    }
}
