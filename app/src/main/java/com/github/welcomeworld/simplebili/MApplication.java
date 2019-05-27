package com.github.welcomeworld.simplebili;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import com.crashlytics.android.Crashlytics;
import com.github.welcomeworld.simplebili.bean.LoginResultBean;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.dao.SimpleDatabase;
import com.github.welcomeworld.simplebili.utils.DownloadManager;

import java.util.List;

import io.fabric.sdk.android.Fabric;

public class MApplication extends Application {
    private SimpleDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this,new Crashlytics());
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        database = Room.databaseBuilder(this, SimpleDatabase.class,"simple_bili.db" ).allowMainThreadQueries().build();
        List<LoginResultBean.TokenInfoBean> tokens = database.getDao().getToken();
        if(tokens!=null&&tokens.size()>0){
            BiliLocalStatus.setLogin(true);
            BiliLocalStatus.setAccessKey(tokens.get(0).getAccessToken());
            BiliLocalStatus.setMid(tokens.get(0).getMid());
        }
        strictMode();
        DownloadManager.getInstance().setApplication(this);
    }

    public void strictMode(){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    public SimpleDatabase getDatabase(){
        return database;
    }
}
