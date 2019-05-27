package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

public class UpdateBean {

    @SerializedName("versionName")
    private String versionName;
    @SerializedName("versionCode")
    private int versionCode;
    @SerializedName("description")
    private String description;
    @SerializedName("path")
    private String path;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
