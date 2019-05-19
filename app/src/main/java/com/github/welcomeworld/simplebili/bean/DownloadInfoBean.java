package com.github.welcomeworld.simplebili.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "download")
public class DownloadInfoBean {
    @Ignore
    public static final int PAUSE =0;
    @Ignore
    public static final int COMPLETE = 1;
    @Ignore
    public static final int DOWNLOADING = 2;
    @Ignore
    public static final int PREPARED = 3;
    @Ignore
    public static final int ERROR = 4;
    @Ignore
    public static final int BANGUMI_TYPE = 0;
    @Ignore
    public static final int VIDEO_TYPE = 1;
    @Ignore
    public static final int UNKNOWN_TYPE =2;

    @PrimaryKey
    private int downloadId =-1;

    private int cid =-1;

    private String title;

    private String sourceUrl;

    private int type =-1;

    private int downloadState =-1; //1 complete ,2 downloading ,3 pause

    private String localPath;

    private long contentLength =-1;

    private String cover;

    private String originalUrl;

    public int getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(int downloadId) {
        this.downloadId = downloadId;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDownloadState() {
        return downloadState;
    }

    public void setDownloadState(int downloadState) {
        this.downloadState = downloadState;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
