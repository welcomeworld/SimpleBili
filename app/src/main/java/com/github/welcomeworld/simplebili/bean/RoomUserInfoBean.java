package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomUserInfoBean {

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
        @SerializedName("level")
        private LevelBean level;
        @SerializedName("san")
        private int san;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public LevelBean getLevel() {
            return level;
        }

        public void setLevel(LevelBean level) {
            this.level = level;
        }

        public int getSan() {
            return san;
        }

        public void setSan(int san) {
            this.san = san;
        }

        public static class InfoBean {
            @SerializedName("uid")
            private int uid;
            @SerializedName("uname")
            private String uname;
            @SerializedName("face")
            private String face;
            @SerializedName("rank")
            private String rank;
            @SerializedName("identification")
            private int identification;
            @SerializedName("mobile_verify")
            private int mobileVerify;
            @SerializedName("platform_user_level")
            private int platformUserLevel;
            @SerializedName("vip_type")
            private int vipType;
            @SerializedName("gender")
            private int gender;
            @SerializedName("official_verify")
            private OfficialVerifyBean officialVerify;

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

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public int getIdentification() {
                return identification;
            }

            public void setIdentification(int identification) {
                this.identification = identification;
            }

            public int getMobileVerify() {
                return mobileVerify;
            }

            public void setMobileVerify(int mobileVerify) {
                this.mobileVerify = mobileVerify;
            }

            public int getPlatformUserLevel() {
                return platformUserLevel;
            }

            public void setPlatformUserLevel(int platformUserLevel) {
                this.platformUserLevel = platformUserLevel;
            }

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public OfficialVerifyBean getOfficialVerify() {
                return officialVerify;
            }

            public void setOfficialVerify(OfficialVerifyBean officialVerify) {
                this.officialVerify = officialVerify;
            }

            public static class OfficialVerifyBean {
                @SerializedName("type")
                private int type;
                @SerializedName("desc")
                private String desc;
                @SerializedName("role")
                private int role;

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

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }
            }
        }

        public static class LevelBean {
            @SerializedName("uid")
            private int uid;
            @SerializedName("cost")
            private int cost;
            @SerializedName("rcost")
            private int rcost;
            @SerializedName("user_score")
            private String userScore;
            @SerializedName("vip")
            private int vip;
            @SerializedName("vip_time")
            private String vipTime;
            @SerializedName("svip")
            private int svip;
            @SerializedName("svip_time")
            private String svipTime;
            @SerializedName("update_time")
            private String updateTime;
            @SerializedName("master_level")
            private MasterLevelBean masterLevel;
            @SerializedName("user_level")
            private int userLevel;
            @SerializedName("color")
            private int color;
            @SerializedName("anchor_score")
            private int anchorScore;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getCost() {
                return cost;
            }

            public void setCost(int cost) {
                this.cost = cost;
            }

            public int getRcost() {
                return rcost;
            }

            public void setRcost(int rcost) {
                this.rcost = rcost;
            }

            public String getUserScore() {
                return userScore;
            }

            public void setUserScore(String userScore) {
                this.userScore = userScore;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            public String getVipTime() {
                return vipTime;
            }

            public void setVipTime(String vipTime) {
                this.vipTime = vipTime;
            }

            public int getSvip() {
                return svip;
            }

            public void setSvip(int svip) {
                this.svip = svip;
            }

            public String getSvipTime() {
                return svipTime;
            }

            public void setSvipTime(String svipTime) {
                this.svipTime = svipTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public MasterLevelBean getMasterLevel() {
                return masterLevel;
            }

            public void setMasterLevel(MasterLevelBean masterLevel) {
                this.masterLevel = masterLevel;
            }

            public int getUserLevel() {
                return userLevel;
            }

            public void setUserLevel(int userLevel) {
                this.userLevel = userLevel;
            }

            public int getColor() {
                return color;
            }

            public void setColor(int color) {
                this.color = color;
            }

            public int getAnchorScore() {
                return anchorScore;
            }

            public void setAnchorScore(int anchorScore) {
                this.anchorScore = anchorScore;
            }

            public static class MasterLevelBean {
                @SerializedName("level")
                private int level;
                @SerializedName("color")
                private int color;
                @SerializedName("anchor_score")
                private int anchorScore;
                @SerializedName("upgrade_score")
                private int upgradeScore;
                @SerializedName("master_level_color")
                private int masterLevelColor;
                @SerializedName("sort")
                private int sort;
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

                public int getAnchorScore() {
                    return anchorScore;
                }

                public void setAnchorScore(int anchorScore) {
                    this.anchorScore = anchorScore;
                }

                public int getUpgradeScore() {
                    return upgradeScore;
                }

                public void setUpgradeScore(int upgradeScore) {
                    this.upgradeScore = upgradeScore;
                }

                public int getMasterLevelColor() {
                    return masterLevelColor;
                }

                public void setMasterLevelColor(int masterLevelColor) {
                    this.masterLevelColor = masterLevelColor;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
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
    }
}
