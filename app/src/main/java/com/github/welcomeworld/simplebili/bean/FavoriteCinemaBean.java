package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavoriteCinemaBean {

    @SerializedName("code")
    private int code;
    @SerializedName("count")
    private String count;
    @SerializedName("message")
    private String message;
    @SerializedName("pages")
    private String pages;
    @SerializedName("result")
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        @SerializedName("badge_type")
        private int badgeType;
        @SerializedName("cover")
        private String cover;
        @SerializedName("follow_at")
        private int followAt;
        @SerializedName("is_finish")
        private int isFinish;
        @SerializedName("is_new")
        private int isNew;
        @SerializedName("is_started")
        private int isStarted;
        @SerializedName("is_watch_newest")
        private int isWatchNewest;
        @SerializedName("mode")
        private int mode;
        @SerializedName("new_ep_desc")
        private String newEpDesc;
        @SerializedName("newest_ep_index")
        private String newestEpIndex;
        @SerializedName("progress")
        private String progress;
        @SerializedName("pub_time_show")
        private String pubTimeShow;
        @SerializedName("season_id")
        private int seasonId;
        @SerializedName("season_type")
        private int seasonType;
        @SerializedName("season_type_name")
        private String seasonTypeName;
        @SerializedName("status")
        private int status;
        @SerializedName("title")
        private String title;
        @SerializedName("total_count")
        private int totalCount;
        @SerializedName("areas")
        private List<AreasBean> areas;

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

        public int getFollowAt() {
            return followAt;
        }

        public void setFollowAt(int followAt) {
            this.followAt = followAt;
        }

        public int getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(int isFinish) {
            this.isFinish = isFinish;
        }

        public int getIsNew() {
            return isNew;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
        }

        public int getIsStarted() {
            return isStarted;
        }

        public void setIsStarted(int isStarted) {
            this.isStarted = isStarted;
        }

        public int getIsWatchNewest() {
            return isWatchNewest;
        }

        public void setIsWatchNewest(int isWatchNewest) {
            this.isWatchNewest = isWatchNewest;
        }

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }

        public String getNewEpDesc() {
            return newEpDesc;
        }

        public void setNewEpDesc(String newEpDesc) {
            this.newEpDesc = newEpDesc;
        }

        public String getNewestEpIndex() {
            return newestEpIndex;
        }

        public void setNewestEpIndex(String newestEpIndex) {
            this.newestEpIndex = newestEpIndex;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getPubTimeShow() {
            return pubTimeShow;
        }

        public void setPubTimeShow(String pubTimeShow) {
            this.pubTimeShow = pubTimeShow;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<AreasBean> getAreas() {
            return areas;
        }

        public void setAreas(List<AreasBean> areas) {
            this.areas = areas;
        }

        public static class AreasBean {
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
