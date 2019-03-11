package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomInfoBean {

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
        @SerializedName("uid")
        private int uid;
        @SerializedName("room_id")
        private int roomId;
        @SerializedName("short_id")
        private int shortId;
        @SerializedName("attention")
        private int attention;
        @SerializedName("online")
        private int online;
        @SerializedName("is_portrait")
        private boolean isPortrait;
        @SerializedName("description")
        private String description;
        @SerializedName("live_status")
        private int liveStatus;
        @SerializedName("area_id")
        private int areaId;
        @SerializedName("parent_area_id")
        private int parentAreaId;
        @SerializedName("parent_area_name")
        private String parentAreaName;
        @SerializedName("old_area_id")
        private int oldAreaId;
        @SerializedName("background")
        private String background;
        @SerializedName("title")
        private String title;
        @SerializedName("user_cover")
        private String userCover;
        @SerializedName("keyframe")
        private String keyframe;
        @SerializedName("is_strict_room")
        private boolean isStrictRoom;
        @SerializedName("live_time")
        private String liveTime;
        @SerializedName("tags")
        private String tags;
        @SerializedName("is_anchor")
        private int isAnchor;
        @SerializedName("room_silent_type")
        private String roomSilentType;
        @SerializedName("room_silent_level")
        private int roomSilentLevel;
        @SerializedName("room_silent_second")
        private int roomSilentSecond;
        @SerializedName("area_name")
        private String areaName;
        @SerializedName("pendants")
        private String pendants;
        @SerializedName("area_pendants")
        private String areaPendants;
        @SerializedName("hot_words_status")
        private int hotWordsStatus;
        @SerializedName("verify")
        private String verify;
        @SerializedName("new_pendants")
        private NewPendantsBean newPendants;
        @SerializedName("up_session")
        private String upSession;
        @SerializedName("pk_status")
        private int pkStatus;
        @SerializedName("pk_id")
        private int pkId;
        @SerializedName("allow_change_area_time")
        private int allowChangeAreaTime;
        @SerializedName("allow_upload_cover_time")
        private int allowUploadCoverTime;
        @SerializedName("hot_words")
        private List<String> hotWords;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

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

        public int getAttention() {
            return attention;
        }

        public void setAttention(int attention) {
            this.attention = attention;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public boolean isIsPortrait() {
            return isPortrait;
        }

        public void setIsPortrait(boolean isPortrait) {
            this.isPortrait = isPortrait;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(int liveStatus) {
            this.liveStatus = liveStatus;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public int getParentAreaId() {
            return parentAreaId;
        }

        public void setParentAreaId(int parentAreaId) {
            this.parentAreaId = parentAreaId;
        }

        public String getParentAreaName() {
            return parentAreaName;
        }

        public void setParentAreaName(String parentAreaName) {
            this.parentAreaName = parentAreaName;
        }

        public int getOldAreaId() {
            return oldAreaId;
        }

        public void setOldAreaId(int oldAreaId) {
            this.oldAreaId = oldAreaId;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUserCover() {
            return userCover;
        }

        public void setUserCover(String userCover) {
            this.userCover = userCover;
        }

        public String getKeyframe() {
            return keyframe;
        }

        public void setKeyframe(String keyframe) {
            this.keyframe = keyframe;
        }

        public boolean isIsStrictRoom() {
            return isStrictRoom;
        }

        public void setIsStrictRoom(boolean isStrictRoom) {
            this.isStrictRoom = isStrictRoom;
        }

        public String getLiveTime() {
            return liveTime;
        }

        public void setLiveTime(String liveTime) {
            this.liveTime = liveTime;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getIsAnchor() {
            return isAnchor;
        }

        public void setIsAnchor(int isAnchor) {
            this.isAnchor = isAnchor;
        }

        public String getRoomSilentType() {
            return roomSilentType;
        }

        public void setRoomSilentType(String roomSilentType) {
            this.roomSilentType = roomSilentType;
        }

        public int getRoomSilentLevel() {
            return roomSilentLevel;
        }

        public void setRoomSilentLevel(int roomSilentLevel) {
            this.roomSilentLevel = roomSilentLevel;
        }

        public int getRoomSilentSecond() {
            return roomSilentSecond;
        }

        public void setRoomSilentSecond(int roomSilentSecond) {
            this.roomSilentSecond = roomSilentSecond;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getPendants() {
            return pendants;
        }

        public void setPendants(String pendants) {
            this.pendants = pendants;
        }

        public String getAreaPendants() {
            return areaPendants;
        }

        public void setAreaPendants(String areaPendants) {
            this.areaPendants = areaPendants;
        }

        public int getHotWordsStatus() {
            return hotWordsStatus;
        }

        public void setHotWordsStatus(int hotWordsStatus) {
            this.hotWordsStatus = hotWordsStatus;
        }

        public String getVerify() {
            return verify;
        }

        public void setVerify(String verify) {
            this.verify = verify;
        }

        public NewPendantsBean getNewPendants() {
            return newPendants;
        }

        public void setNewPendants(NewPendantsBean newPendants) {
            this.newPendants = newPendants;
        }

        public String getUpSession() {
            return upSession;
        }

        public void setUpSession(String upSession) {
            this.upSession = upSession;
        }

        public int getPkStatus() {
            return pkStatus;
        }

        public void setPkStatus(int pkStatus) {
            this.pkStatus = pkStatus;
        }

        public int getPkId() {
            return pkId;
        }

        public void setPkId(int pkId) {
            this.pkId = pkId;
        }

        public int getAllowChangeAreaTime() {
            return allowChangeAreaTime;
        }

        public void setAllowChangeAreaTime(int allowChangeAreaTime) {
            this.allowChangeAreaTime = allowChangeAreaTime;
        }

        public int getAllowUploadCoverTime() {
            return allowUploadCoverTime;
        }

        public void setAllowUploadCoverTime(int allowUploadCoverTime) {
            this.allowUploadCoverTime = allowUploadCoverTime;
        }

        public List<String> getHotWords() {
            return hotWords;
        }

        public void setHotWords(List<String> hotWords) {
            this.hotWords = hotWords;
        }

        public static class NewPendantsBean {
            @SerializedName("frame")
            private Object frame;
            @SerializedName("badge")
            private BadgeBean badge;
            @SerializedName("mobile_frame")
            private Object mobileFrame;
            @SerializedName("mobile_badge")
            private Object mobileBadge;

            public Object getFrame() {
                return frame;
            }

            public void setFrame(Object frame) {
                this.frame = frame;
            }

            public BadgeBean getBadge() {
                return badge;
            }

            public void setBadge(BadgeBean badge) {
                this.badge = badge;
            }

            public Object getMobileFrame() {
                return mobileFrame;
            }

            public void setMobileFrame(Object mobileFrame) {
                this.mobileFrame = mobileFrame;
            }

            public Object getMobileBadge() {
                return mobileBadge;
            }

            public void setMobileBadge(Object mobileBadge) {
                this.mobileBadge = mobileBadge;
            }

            public static class BadgeBean {
                @SerializedName("name")
                private String name;
                @SerializedName("position")
                private int position;
                @SerializedName("value")
                private String value;
                @SerializedName("desc")
                private String desc;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getPosition() {
                    return position;
                }

                public void setPosition(int position) {
                    this.position = position;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }
        }
    }
}
