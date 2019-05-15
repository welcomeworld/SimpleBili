package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

public class UserInfoMineBean {

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
        @SerializedName("mid")
        private int mid;
        @SerializedName("name")
        private String name;
        @SerializedName("face")
        private String face;
        @SerializedName("coin")
        private double coin;
        @SerializedName("bcoin")
        private int bcoin;
        @SerializedName("sex")
        private int sex;
        @SerializedName("rank")
        private int rank;
        @SerializedName("silence")
        private int silence;
        @SerializedName("show_videoup")
        private int showVideoup;
        @SerializedName("show_creative")
        private int showCreative;
        @SerializedName("level")
        private int level;
        @SerializedName("vip_type")
        private int vipType;
        @SerializedName("audio_type")
        private int audioType;
        @SerializedName("dynamic")
        private int dynamic;
        @SerializedName("following")
        private int following;
        @SerializedName("follower")
        private int follower;
        @SerializedName("new_followers")
        private int newFollowers;
        @SerializedName("official_verify")
        private OfficialVerifyBean officialVerify;
        @SerializedName("vip_section")
        private VipSectionBean vipSection;
        @SerializedName("vip_section_v2")
        private VipSectionV2Bean vipSectionV2;
        @SerializedName("vip")
        private VipBean vip;

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

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public double getCoin() {
            return coin;
        }

        public void setCoin(double coin) {
            this.coin = coin;
        }

        public int getBcoin() {
            return bcoin;
        }

        public void setBcoin(int bcoin) {
            this.bcoin = bcoin;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getSilence() {
            return silence;
        }

        public void setSilence(int silence) {
            this.silence = silence;
        }

        public int getShowVideoup() {
            return showVideoup;
        }

        public void setShowVideoup(int showVideoup) {
            this.showVideoup = showVideoup;
        }

        public int getShowCreative() {
            return showCreative;
        }

        public void setShowCreative(int showCreative) {
            this.showCreative = showCreative;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public int getAudioType() {
            return audioType;
        }

        public void setAudioType(int audioType) {
            this.audioType = audioType;
        }

        public int getDynamic() {
            return dynamic;
        }

        public void setDynamic(int dynamic) {
            this.dynamic = dynamic;
        }

        public int getFollowing() {
            return following;
        }

        public void setFollowing(int following) {
            this.following = following;
        }

        public int getFollower() {
            return follower;
        }

        public void setFollower(int follower) {
            this.follower = follower;
        }

        public int getNewFollowers() {
            return newFollowers;
        }

        public void setNewFollowers(int newFollowers) {
            this.newFollowers = newFollowers;
        }

        public OfficialVerifyBean getOfficialVerify() {
            return officialVerify;
        }

        public void setOfficialVerify(OfficialVerifyBean officialVerify) {
            this.officialVerify = officialVerify;
        }

        public VipSectionBean getVipSection() {
            return vipSection;
        }

        public void setVipSection(VipSectionBean vipSection) {
            this.vipSection = vipSection;
        }

        public VipSectionV2Bean getVipSectionV2() {
            return vipSectionV2;
        }

        public void setVipSectionV2(VipSectionV2Bean vipSectionV2) {
            this.vipSectionV2 = vipSectionV2;
        }

        public VipBean getVip() {
            return vip;
        }

        public void setVip(VipBean vip) {
            this.vip = vip;
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

        public static class VipSectionBean {
            @SerializedName("title")
            private String title;
            @SerializedName("url")
            private String url;
            @SerializedName("start_time")
            private int startTime;
            @SerializedName("end_time")
            private int endTime;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public int getEndTime() {
                return endTime;
            }

            public void setEndTime(int endTime) {
                this.endTime = endTime;
            }
        }

        public static class VipSectionV2Bean {
            @SerializedName("title")
            private String title;
            @SerializedName("subtitle")
            private String subtitle;
            @SerializedName("desc")
            private String desc;
            @SerializedName("url")
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class VipBean {
            @SerializedName("type")
            private int type;
            @SerializedName("status")
            private int status;
            @SerializedName("due_date")
            private long dueDate;
            @SerializedName("vip_pay_type")
            private int vipPayType;
            @SerializedName("theme_type")
            private int themeType;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public long getDueDate() {
                return dueDate;
            }

            public void setDueDate(long dueDate) {
                this.dueDate = dueDate;
            }

            public int getVipPayType() {
                return vipPayType;
            }

            public void setVipPayType(int vipPayType) {
                this.vipPayType = vipPayType;
            }

            public int getThemeType() {
                return themeType;
            }

            public void setThemeType(int themeType) {
                this.themeType = themeType;
            }
        }
    }
}
