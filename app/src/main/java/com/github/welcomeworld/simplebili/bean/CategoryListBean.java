package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryListBean {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("ttl")
    private int ttl;
    @SerializedName("data")
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("title")
        private String title;
        @SerializedName("cover")
        private String cover;
        @SerializedName("uri")
        private String uri;
        @SerializedName("param")
        private String param;
        @SerializedName("goto")
        private String gotoX;
        @SerializedName("name")
        private String name;
        @SerializedName("face")
        private String face;
        @SerializedName("play")
        private int play;
        @SerializedName("danmaku")
        private int danmaku;
        @SerializedName("reply")
        private int reply;
        @SerializedName("favourite")
        private int favourite;
        @SerializedName("pubdate")
        private int pubdate;
        @SerializedName("duration")
        private int duration;
        @SerializedName("rid")
        private int rid;
        @SerializedName("rname")
        private String rname;
        @SerializedName("like")
        private int like;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getGotoX() {
            return gotoX;
        }

        public void setGotoX(String gotoX) {
            this.gotoX = gotoX;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public int getPlay() {
            return play;
        }

        public void setPlay(int play) {
            this.play = play;
        }

        public int getDanmaku() {
            return danmaku;
        }

        public void setDanmaku(int danmaku) {
            this.danmaku = danmaku;
        }

        public int getReply() {
            return reply;
        }

        public void setReply(int reply) {
            this.reply = reply;
        }

        public int getFavourite() {
            return favourite;
        }

        public void setFavourite(int favourite) {
            this.favourite = favourite;
        }

        public int getPubdate() {
            return pubdate;
        }

        public void setPubdate(int pubdate) {
            this.pubdate = pubdate;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getRname() {
            return rname;
        }

        public void setRname(String rname) {
            this.rname = rname;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }
    }
}
