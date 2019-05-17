package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavoriteBangumiBean {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private List<ResultBean> result;
    @SerializedName("vip_tip")
    private List<?> vipTip;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public List<?> getVipTip() {
        return vipTip;
    }

    public void setVipTip(List<?> vipTip) {
        this.vipTip = vipTip;
    }

    public static class ResultBean {
        @SerializedName("badge")
        private String badge;
        @SerializedName("badge_type")
        private int badgeType;
        @SerializedName("cover")
        private String cover;
        @SerializedName("new_ep")
        private NewEpBean newEp;
        @SerializedName("progress")
        private ProgressBean progress;
        @SerializedName("season_id")
        private int seasonId;
        @SerializedName("season_type")
        private int seasonType;
        @SerializedName("season_type_name")
        private String seasonTypeName;
        @SerializedName("title")
        private String title;
        @SerializedName("url")
        private String url;

        public String getBadge() {
            return badge;
        }

        public void setBadge(String badge) {
            this.badge = badge;
        }

        public int getBadgeType() {
            return badgeType;
        }

        public void setBadgeType(int badgeType) {
            this.badgeType = badgeType;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public NewEpBean getNewEp() {
            return newEp;
        }

        public void setNewEp(NewEpBean newEp) {
            this.newEp = newEp;
        }

        public ProgressBean getProgress() {
            return progress;
        }

        public void setProgress(ProgressBean progress) {
            this.progress = progress;
        }

        public int getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(int seasonId) {
            this.seasonId = seasonId;
        }

        public int getSeasonType() {
            return seasonType;
        }

        public void setSeasonType(int seasonType) {
            this.seasonType = seasonType;
        }

        public String getSeasonTypeName() {
            return seasonTypeName;
        }

        public void setSeasonTypeName(String seasonTypeName) {
            this.seasonTypeName = seasonTypeName;
        }

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

        public static class NewEpBean {
            @SerializedName("id")
            private int id;
            @SerializedName("index_show")
            private String indexShow;
            @SerializedName("is_new")
            private int isNew;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIndexShow() {
                return indexShow;
            }

            public void setIndexShow(String indexShow) {
                this.indexShow = indexShow;
            }

            public int getIsNew() {
                return isNew;
            }

            public void setIsNew(int isNew) {
                this.isNew = isNew;
            }
        }

        public static class ProgressBean {
            @SerializedName("index_show")
            private String indexShow;
            @SerializedName("last_ep_id")
            private int lastEpId;
            @SerializedName("last_time")
            private int lastTime;

            public String getIndexShow() {
                return indexShow;
            }

            public void setIndexShow(String indexShow) {
                this.indexShow = indexShow;
            }

            public int getLastEpId() {
                return lastEpId;
            }

            public void setLastEpId(int lastEpId) {
                this.lastEpId = lastEpId;
            }

            public int getLastTime() {
                return lastTime;
            }

            public void setLastTime(int lastTime) {
                this.lastTime = lastTime;
            }
        }
    }
}
