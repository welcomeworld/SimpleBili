package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomHistoryDanmakuBean {

    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("message")
    private String message;
    @SerializedName("msg")
    private String msg;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        @SerializedName("admin")
        private List<AdminBean> admin;
        @SerializedName("room")
        private List<RoomBean> room;

        public List<AdminBean> getAdmin() {
            return admin;
        }

        public void setAdmin(List<AdminBean> admin) {
            this.admin = admin;
        }

        public List<RoomBean> getRoom() {
            return room;
        }

        public void setRoom(List<RoomBean> room) {
            this.room = room;
        }

        public static class AdminBean {
            @SerializedName("text")
            private String text;
            @SerializedName("nickname")
            private String nickname;
            @SerializedName("uid")
            private int uid;
            @SerializedName("uname_color")
            private String unameColor;
            @SerializedName("timeline")
            private String timeline;
            @SerializedName("isadmin")
            private int isadmin;
            @SerializedName("vip")
            private int vip;
            @SerializedName("svip")
            private int svip;
            @SerializedName("rank")
            private int rank;
            @SerializedName("teamid")
            private int teamid;
            @SerializedName("rnd")
            private String rnd;
            @SerializedName("user_title")
            private String userTitle;
            @SerializedName("guard_level")
            private int guardLevel;
            @SerializedName("bubble")
            private int bubble;
            @SerializedName("check_info")
            private CheckInfoBean checkInfo;
            @SerializedName("medal")
            private List<?> medal;
            @SerializedName("title")
            private List<String> title;
            @SerializedName("user_level")
            private List<?> userLevel;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getUnameColor() {
                return unameColor;
            }

            public void setUnameColor(String unameColor) {
                this.unameColor = unameColor;
            }

            public String getTimeline() {
                return timeline;
            }

            public void setTimeline(String timeline) {
                this.timeline = timeline;
            }

            public int getIsadmin() {
                return isadmin;
            }

            public void setIsadmin(int isadmin) {
                this.isadmin = isadmin;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            public int getSvip() {
                return svip;
            }

            public void setSvip(int svip) {
                this.svip = svip;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public int getTeamid() {
                return teamid;
            }

            public void setTeamid(int teamid) {
                this.teamid = teamid;
            }

            public String getRnd() {
                return rnd;
            }

            public void setRnd(String rnd) {
                this.rnd = rnd;
            }

            public String getUserTitle() {
                return userTitle;
            }

            public void setUserTitle(String userTitle) {
                this.userTitle = userTitle;
            }

            public int getGuardLevel() {
                return guardLevel;
            }

            public void setGuardLevel(int guardLevel) {
                this.guardLevel = guardLevel;
            }

            public int getBubble() {
                return bubble;
            }

            public void setBubble(int bubble) {
                this.bubble = bubble;
            }

            public CheckInfoBean getCheckInfo() {
                return checkInfo;
            }

            public void setCheckInfo(CheckInfoBean checkInfo) {
                this.checkInfo = checkInfo;
            }

            public List<?> getMedal() {
                return medal;
            }

            public void setMedal(List<?> medal) {
                this.medal = medal;
            }

            public List<String> getTitle() {
                return title;
            }

            public void setTitle(List<String> title) {
                this.title = title;
            }

            public List<?> getUserLevel() {
                return userLevel;
            }

            public void setUserLevel(List<?> userLevel) {
                this.userLevel = userLevel;
            }

            public static class CheckInfoBean {
                @SerializedName("ts")
                private int ts;
                @SerializedName("ct")
                private String ct;

                public int getTs() {
                    return ts;
                }

                public void setTs(int ts) {
                    this.ts = ts;
                }

                public String getCt() {
                    return ct;
                }

                public void setCt(String ct) {
                    this.ct = ct;
                }
            }
        }

        public static class RoomBean {
            @SerializedName("text")
            private String text;
            @SerializedName("nickname")
            private String nickname;
            @SerializedName("uid")
            private int uid;
            @SerializedName("uname_color")
            private String unameColor;
            @SerializedName("timeline")
            private String timeline;
            @SerializedName("isadmin")
            private int isadmin;
            @SerializedName("vip")
            private int vip;
            @SerializedName("svip")
            private int svip;
            @SerializedName("rank")
            private int rank;
            @SerializedName("teamid")
            private int teamid;
            @SerializedName("rnd")
            private String rnd;
            @SerializedName("user_title")
            private String userTitle;
            @SerializedName("guard_level")
            private int guardLevel;
            @SerializedName("bubble")
            private int bubble;
            @SerializedName("check_info")
            private CheckInfoBeanX checkInfo;
            @SerializedName("medal")
            private List<?> medal;
            @SerializedName("title")
            private List<String> title;
            @SerializedName("user_level")
            private List<?> userLevel;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getUnameColor() {
                return unameColor;
            }

            public void setUnameColor(String unameColor) {
                this.unameColor = unameColor;
            }

            public String getTimeline() {
                return timeline;
            }

            public void setTimeline(String timeline) {
                this.timeline = timeline;
            }

            public int getIsadmin() {
                return isadmin;
            }

            public void setIsadmin(int isadmin) {
                this.isadmin = isadmin;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            public int getSvip() {
                return svip;
            }

            public void setSvip(int svip) {
                this.svip = svip;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public int getTeamid() {
                return teamid;
            }

            public void setTeamid(int teamid) {
                this.teamid = teamid;
            }

            public String getRnd() {
                return rnd;
            }

            public void setRnd(String rnd) {
                this.rnd = rnd;
            }

            public String getUserTitle() {
                return userTitle;
            }

            public void setUserTitle(String userTitle) {
                this.userTitle = userTitle;
            }

            public int getGuardLevel() {
                return guardLevel;
            }

            public void setGuardLevel(int guardLevel) {
                this.guardLevel = guardLevel;
            }

            public int getBubble() {
                return bubble;
            }

            public void setBubble(int bubble) {
                this.bubble = bubble;
            }

            public CheckInfoBeanX getCheckInfo() {
                return checkInfo;
            }

            public void setCheckInfo(CheckInfoBeanX checkInfo) {
                this.checkInfo = checkInfo;
            }

            public List<?> getMedal() {
                return medal;
            }

            public void setMedal(List<?> medal) {
                this.medal = medal;
            }

            public List<String> getTitle() {
                return title;
            }

            public void setTitle(List<String> title) {
                this.title = title;
            }

            public List<?> getUserLevel() {
                return userLevel;
            }

            public void setUserLevel(List<?> userLevel) {
                this.userLevel = userLevel;
            }

            public static class CheckInfoBeanX {
                @SerializedName("ts")
                private int ts;
                @SerializedName("ct")
                private String ct;

                public int getTs() {
                    return ts;
                }

                public void setTs(int ts) {
                    this.ts = ts;
                }

                public String getCt() {
                    return ct;
                }

                public void setCt(String ct) {
                    this.ct = ct;
                }
            }
        }
    }
}
