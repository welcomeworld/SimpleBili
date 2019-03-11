package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveMasterUserInfoBean {

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
        @SerializedName("info")
        private InfoBean info;
        @SerializedName("exp")
        private ExpBean exp;
        @SerializedName("follower_num")
        private int followerNum;
        @SerializedName("room_id")
        private int roomId;
        @SerializedName("medal_name")
        private String medalName;
        @SerializedName("glory_count")
        private int gloryCount;
        @SerializedName("pendant")
        private String pendant;
        @SerializedName("link_group_num")
        private int linkGroupNum;
        @SerializedName("room_news")
        private RoomNewsBean roomNews;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public ExpBean getExp() {
            return exp;
        }

        public void setExp(ExpBean exp) {
            this.exp = exp;
        }

        public int getFollowerNum() {
            return followerNum;
        }

        public void setFollowerNum(int followerNum) {
            this.followerNum = followerNum;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public String getMedalName() {
            return medalName;
        }

        public void setMedalName(String medalName) {
            this.medalName = medalName;
        }

        public int getGloryCount() {
            return gloryCount;
        }

        public void setGloryCount(int gloryCount) {
            this.gloryCount = gloryCount;
        }

        public String getPendant() {
            return pendant;
        }

        public void setPendant(String pendant) {
            this.pendant = pendant;
        }

        public int getLinkGroupNum() {
            return linkGroupNum;
        }

        public void setLinkGroupNum(int linkGroupNum) {
            this.linkGroupNum = linkGroupNum;
        }

        public RoomNewsBean getRoomNews() {
            return roomNews;
        }

        public void setRoomNews(RoomNewsBean roomNews) {
            this.roomNews = roomNews;
        }

        public static class InfoBean {
            @SerializedName("uid")
            private int uid;
            @SerializedName("uname")
            private String uname;
            @SerializedName("face")
            private String face;
            @SerializedName("official_verify")
            private OfficialVerifyBean officialVerify;
            @SerializedName("gender")
            private int gender;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public OfficialVerifyBean getOfficialVerify() {
                return officialVerify;
            }

            public void setOfficialVerify(OfficialVerifyBean officialVerify) {
                this.officialVerify = officialVerify;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public static class OfficialVerifyBean {
                @SerializedName("type")
                private int type;
                @SerializedName("desc")
                private String desc;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }
        }

        public static class ExpBean {
            @SerializedName("master_level")
            private MasterLevelBean masterLevel;

            public MasterLevelBean getMasterLevel() {
                return masterLevel;
            }

            public void setMasterLevel(MasterLevelBean masterLevel) {
                this.masterLevel = masterLevel;
            }

            public static class MasterLevelBean {
                @SerializedName("level")
                private int level;
                @SerializedName("color")
                private int color;
                @SerializedName("current")
                private List<Integer> current;
                @SerializedName("next")
                private List<Integer> next;

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getColor() {
                    return color;
                }

                public void setColor(int color) {
                    this.color = color;
                }

                public List<Integer> getCurrent() {
                    return current;
                }

                public void setCurrent(List<Integer> current) {
                    this.current = current;
                }

                public List<Integer> getNext() {
                    return next;
                }

                public void setNext(List<Integer> next) {
                    this.next = next;
                }
            }
        }

        public static class RoomNewsBean {
            @SerializedName("content")
            private String content;
            @SerializedName("ctime")
            private String ctime;
            @SerializedName("ctime_text")
            private String ctimeText;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getCtimeText() {
                return ctimeText;
            }

            public void setCtimeText(String ctimeText) {
                this.ctimeText = ctimeText;
            }
        }
    }
}
