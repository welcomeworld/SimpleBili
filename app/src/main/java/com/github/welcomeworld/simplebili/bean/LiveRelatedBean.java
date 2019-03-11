package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveRelatedBean {

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("roomid")
        private int roomid;
        @SerializedName("title")
        private String title;
        @SerializedName("uname")
        private String uname;
        @SerializedName("online")
        private int online;
        @SerializedName("cover")
        private String cover;
        @SerializedName("link")
        private String link;
        @SerializedName("face")
        private String face;
        @SerializedName("area_v2_parent_id")
        private int areaV2ParentId;
        @SerializedName("area_v2_parent_name")
        private String areaV2ParentName;
        @SerializedName("area_v2_id")
        private int areaV2Id;
        @SerializedName("area_v2_name")
        private String areaV2Name;
        @SerializedName("play_url")
        private String playUrl;
        @SerializedName("current_quality")
        private int currentQuality;
        @SerializedName("broadcast_type")
        private int broadcastType;
        @SerializedName("pendent_ru")
        private String pendentRu;
        @SerializedName("pendent_ru_color")
        private String pendentRuColor;
        @SerializedName("pendent_ru_pic")
        private String pendentRuPic;
        @SerializedName("session_id")
        private String sessionId;
        @SerializedName("group_id")
        private int groupId;
        @SerializedName("accept_quality")
        private List<Integer> acceptQuality;

        public int getRoomid() {
            return roomid;
        }

        public void setRoomid(int roomid) {
            this.roomid = roomid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public int getAreaV2ParentId() {
            return areaV2ParentId;
        }

        public void setAreaV2ParentId(int areaV2ParentId) {
            this.areaV2ParentId = areaV2ParentId;
        }

        public String getAreaV2ParentName() {
            return areaV2ParentName;
        }

        public void setAreaV2ParentName(String areaV2ParentName) {
            this.areaV2ParentName = areaV2ParentName;
        }

        public int getAreaV2Id() {
            return areaV2Id;
        }

        public void setAreaV2Id(int areaV2Id) {
            this.areaV2Id = areaV2Id;
        }

        public String getAreaV2Name() {
            return areaV2Name;
        }

        public void setAreaV2Name(String areaV2Name) {
            this.areaV2Name = areaV2Name;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public int getCurrentQuality() {
            return currentQuality;
        }

        public void setCurrentQuality(int currentQuality) {
            this.currentQuality = currentQuality;
        }

        public int getBroadcastType() {
            return broadcastType;
        }

        public void setBroadcastType(int broadcastType) {
            this.broadcastType = broadcastType;
        }

        public String getPendentRu() {
            return pendentRu;
        }

        public void setPendentRu(String pendentRu) {
            this.pendentRu = pendentRu;
        }

        public String getPendentRuColor() {
            return pendentRuColor;
        }

        public void setPendentRuColor(String pendentRuColor) {
            this.pendentRuColor = pendentRuColor;
        }

        public String getPendentRuPic() {
            return pendentRuPic;
        }

        public void setPendentRuPic(String pendentRuPic) {
            this.pendentRuPic = pendentRuPic;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public List<Integer> getAcceptQuality() {
            return acceptQuality;
        }

        public void setAcceptQuality(List<Integer> acceptQuality) {
            this.acceptQuality = acceptQuality;
        }
    }
}
