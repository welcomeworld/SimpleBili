package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

public class LoginKeyBean {

    @SerializedName("ts")
    private int ts;
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private DataBean data;

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("hash")
        private String hash;
        @SerializedName("key")
        private String key;

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
