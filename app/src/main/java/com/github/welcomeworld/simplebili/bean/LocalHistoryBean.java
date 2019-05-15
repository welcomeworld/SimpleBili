package com.github.welcomeworld.simplebili.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "history",primaryKeys = {"mid","aid"})
public class LocalHistoryBean {
    @Ignore
    public static final int LIVE =1;
    @Ignore
    public static final int VIDEO = 2;
    @Ignore
    public static final int BANGUMI = 3;

    private int mid;
    private String cover;
    private long duration;
    private int aid;
    private String title;
    private int type;
    private long viewTime;
    private String upName;
    private long viewProgress;
    private String subTitle;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getViewTime() {
        return viewTime;
    }

    public void setViewTime(long viewTime) {
        this.viewTime = viewTime;
    }

    public String getUpName() {
        return upName;
    }

    public void setUpName(String upName) {
        this.upName = upName;
    }

    public long getViewProgress() {
        return viewProgress;
    }

    public void setViewProgress(long viewProgress) {
        this.viewProgress = viewProgress;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
