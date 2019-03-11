package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

public class RoomStatusBean {

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
        @SerializedName("room_id")
        private int roomId;
        @SerializedName("short_id")
        private int shortId;
        @SerializedName("uid")
        private int uid;
        @SerializedName("need_p2p")
        private int needP2p;
        @SerializedName("is_hidden")
        private boolean isHidden;
        @SerializedName("is_locked")
        private boolean isLocked;
        @SerializedName("is_portrait")
        private boolean isPortrait;
        @SerializedName("live_status")
        private int liveStatus;
        @SerializedName("hidden_till")
        private int hiddenTill;
        @SerializedName("lock_till")
        private int lockTill;
        @SerializedName("encrypted")
        private boolean encrypted;
        @SerializedName("pwd_verified")
        private boolean pwdVerified;
        @SerializedName("live_time")
        private long liveTime;
        @SerializedName("room_shield")
        private int roomShield;
        @SerializedName("is_sp")
        private int isSp;
        @SerializedName("special_type")
        private int specialType;

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getShortId() {
            return shortId;
        }

        public void setShortId(int shortId) {
            this.shortId = shortId;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getNeedP2p() {
            return needP2p;
        }

        public void setNeedP2p(int needP2p) {
            this.needP2p = needP2p;
        }

        public boolean isIsHidden() {
            return isHidden;
        }

        public void setIsHidden(boolean isHidden) {
            this.isHidden = isHidden;
        }

        public boolean isIsLocked() {
            return isLocked;
        }

        public void setIsLocked(boolean isLocked) {
            this.isLocked = isLocked;
        }

        public boolean isIsPortrait() {
            return isPortrait;
        }

        public void setIsPortrait(boolean isPortrait) {
            this.isPortrait = isPortrait;
        }

        public int getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(int liveStatus) {
            this.liveStatus = liveStatus;
        }

        public int getHiddenTill() {
            return hiddenTill;
        }

        public void setHiddenTill(int hiddenTill) {
            this.hiddenTill = hiddenTill;
        }

        public int getLockTill() {
            return lockTill;
        }

        public void setLockTill(int lockTill) {
            this.lockTill = lockTill;
        }

        public boolean isEncrypted() {
            return encrypted;
        }

        public void setEncrypted(boolean encrypted) {
            this.encrypted = encrypted;
        }

        public boolean isPwdVerified() {
            return pwdVerified;
        }

        public void setPwdVerified(boolean pwdVerified) {
            this.pwdVerified = pwdVerified;
        }

        public long getLiveTime() {
            return liveTime;
        }

        public void setLiveTime(long liveTime) {
            this.liveTime = liveTime;
        }

        public int getRoomShield() {
            return roomShield;
        }

        public void setRoomShield(int roomShield) {
            this.roomShield = roomShield;
        }

        public int getIsSp() {
            return isSp;
        }

        public void setIsSp(int isSp) {
            this.isSp = isSp;
        }

        public int getSpecialType() {
            return specialType;
        }

        public void setSpecialType(int specialType) {
            this.specialType = specialType;
        }
    }
}
