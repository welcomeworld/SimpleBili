package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavoriteTopicBean {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("ttl")
    private int ttl;
    @SerializedName("data")
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("count")
        private int count;
        @SerializedName("items")
        private List<ItemsBean> items;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            @SerializedName("id")
            private int id;
            @SerializedName("mid")
            private int mid;
            @SerializedName("name")
            private String name;
            @SerializedName("pc_cover")
            private String pcCover;
            @SerializedName("h5_cover")
            private String h5Cover;
            @SerializedName("fav_at")
            private int favAt;
            @SerializedName("pc_url")
            private String pcUrl;
            @SerializedName("h5_url")
            private String h5Url;
            @SerializedName("desc")
            private String desc;
            @SerializedName("goto")
            private String gotoX;
            @SerializedName("param")
            private String param;
            @SerializedName("uri")
            private String uri;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPcCover() {
                return pcCover;
            }

            public void setPcCover(String pcCover) {
                this.pcCover = pcCover;
            }

            public String getH5Cover() {
                return h5Cover;
            }

            public void setH5Cover(String h5Cover) {
                this.h5Cover = h5Cover;
            }

            public int getFavAt() {
                return favAt;
            }

            public void setFavAt(int favAt) {
                this.favAt = favAt;
            }

            public String getPcUrl() {
                return pcUrl;
            }

            public void setPcUrl(String pcUrl) {
                this.pcUrl = pcUrl;
            }

            public String getH5Url() {
                return h5Url;
            }

            public void setH5Url(String h5Url) {
                this.h5Url = h5Url;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getGotoX() {
                return gotoX;
            }

            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }
        }
    }
}
