package com.github.welcomeworld.simplebili.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.welcomeworld.simplebili.bean.DownloadInfoBean;
import com.github.welcomeworld.simplebili.bean.LocalHistoryBean;
import com.github.welcomeworld.simplebili.bean.LoginResultBean;

import java.util.List;

@Dao
public interface SimpleDao {

    //token
    @Insert
    public void setToken(LoginResultBean.TokenInfoBean token);

    @Query("select * from token")
    public List<LoginResultBean.TokenInfoBean> getToken();


    //history
    @Insert
    public long setHistory(LocalHistoryBean history);

    @Update
    public int updateHistory(LocalHistoryBean history);

    @Query("select * from history where mid = :mid order by viewTime desc")
    public List<LocalHistoryBean> getHistorys(int mid);


    //download
    @Query("select * from download")
    public List<DownloadInfoBean> getDownload();

    @Update
    public int updateDownload(DownloadInfoBean downloadInfoBean);

    @Insert
    public long setDownload(DownloadInfoBean downloadInfoBean);


}
