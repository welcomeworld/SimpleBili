package com.github.welcomeworld.simplebili.utils;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FirebaseUtils {
    private static FirebaseUtils instance;
    Context context;
    private FirebaseAnalytics firebaseAnalytics;
    private FirebaseUtils(Context context){
        this.context=context;
    }

    public static FirebaseUtils getInstance(Context context){
        if(instance==null){
            instance = new FirebaseUtils(context);
        }
        return instance;
    }

    private FirebaseAnalytics getFirebaseAnalytics(){
        if(firebaseAnalytics==null){
            firebaseAnalytics = FirebaseAnalytics.getInstance(context.getApplicationContext());
            firebaseAnalytics.setUserProperty("admin","我是管理员");
        }
        return firebaseAnalytics;
    }

    public void logEvent(String action,String key){
        Bundle bundle = new Bundle();
        bundle.putString("actions",action);
        getFirebaseAnalytics().logEvent(key,bundle);
    }

}
