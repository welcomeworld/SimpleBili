package com.github.welcomeworld.simplebili.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DownloadInfoBean {
    @PrimaryKey
    private int cid;

    private String title;

    private String sourceUrl;

    private int type;

    private int downloadState; //1 complete ,2 downloading ,3 pause ,4



    private String localPath;

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
