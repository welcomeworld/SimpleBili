package com.github.welcomeworld.simplebili;

import android.app.Application;
import android.preference.PreferenceManager;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this,new Crashlytics());
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
    }
}
