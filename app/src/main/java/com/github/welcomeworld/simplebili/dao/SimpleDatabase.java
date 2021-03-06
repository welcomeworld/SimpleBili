package com.github.welcomeworld.simplebili.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.github.welcomeworld.simplebili.bean.DownloadInfoBean;


@Database(version = 1,entities = {com.github.welcomeworld.simplebili.bean.LoginResultBean.TokenInfoBean.class,com.github.welcomeworld.simplebili.bean.LocalHistoryBean.class, com.github.welcomeworld.simplebili.bean.DownloadInfoBean.class},exportSchema = false)
public abstract class SimpleDatabase extends RoomDatabase {

    public abstract SimpleDao getDao();
}
