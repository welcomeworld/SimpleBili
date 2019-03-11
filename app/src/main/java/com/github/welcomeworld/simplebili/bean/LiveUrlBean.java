package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveUrlBean {

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("current_quality")
        private int currentQuality;
        @SerializedName("accept_quality")
        private List<String> acceptQuality;
        @SerializedName("durl")
        private List<DurlBean> durl;

        public int getCurrentQuality() {
            return currentQuality;
        }

        public void setCurrentQuality(int currentQuality) {
            this.currentQuality = currentQuality;
        }

        public List<String> getAcceptQuality() {
            return acceptQuality;
        }

        public void setAcceptQuality(List<String> acceptQuality) {
            this.acceptQuality = acceptQuality;
        }

        public List<DurlBean> getDurl() {
            return durl;
        }

        public void setDurl(List<DurlBean> durl) {
            this.durl = durl;
        }

        public static class DurlBean {
            @SerializedName("url")
            private String url;
            @SerializedName("length")
            private int length;
            @SerializedName("order")
            private int order;
            @SerializedName("stream_type")
            private int streamType;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getStreamType() {
                return streamType;
            }

            public void setStreamType(int streamType) {
                this.streamType = streamType;
            }
        }
    }
}
