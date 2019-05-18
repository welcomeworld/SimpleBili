package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

public class IndexDynamicBangumiCardBean {

    @SerializedName("aid")
    private int aid;
    @SerializedName("apiSeasonInfo")
    private ApiSeasonInfoBean apiSeasonInfo;
    @SerializedName("bullet_count")
    private int bulletCount;
    @SerializedName("cover")
    private String cover;
    @SerializedName("episode_id")
    private int episodeId;
    @SerializedName("index")
    private String index;
    @SerializedName("index_title")
    private String indexTitle;
    @SerializedName("new_desc")
    private String newDesc;
    @SerializedName("online_finish")
    private int onlineFinish;
    @SerializedName("play_count")
    private int playCount;
    @SerializedName("reply_count")
    private int replyCount;
    @SerializedName("url")
    private String url;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public ApiSeasonInfoBean getApiSeasonInfo() {
        return apiSeasonInfo;
    }

    public void setApiSeasonInfo(ApiSeasonInfoBean apiSeasonInfo) {
        this.apiSeasonInfo = apiSeasonInfo;
    }

    public int getBulletCount() {
        return bulletCount;
    }

    public void setBulletCount(int bulletCount) {
        this.bulletCount = bulletCount;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndexTitle() {
        return indexTitle;
    }

    public void setIndexTitle(String indexTitle) {
        this.indexTitle = indexTitle;
    }

    public String getNewDesc() {
        return newDesc;
    }

    public void setNewDesc(String newDesc) {
        this.newDesc = newDesc;
    }

    public int getOnlineFinish() {
        return onlineFinish;
    }

    public void setOnlineFinish(int onlineFinish) {
        this.onlineFinish = onlineFinish;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class ApiSeasonInfoBean {
        @SerializedName("bgm_type")
        private int bgmType;
        @SerializedName("cover")
        private String cover;
        @SerializedName("is_finish")
        private int isFinish;
        @SerializedName("season_id")
        private int seasonId;
        @SerializedName("title")
        private String title;
        @SerializedName("total_count")
        private int totalCount;
        @SerializedName("ts")
        private int ts;

        public int getBgmType() {
            return bgmType;
        }

        public void setBgmType(int bgmType) {
            this.bgmType = bgmType;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(int isFinish) {
            this.isFinish = isFinish;
        }

        public int getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(int seasonId) {
            this.seasonId = seasonId;
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

        public int getTs() {
            return ts;
        }

        public void setTs(int ts) {
            this.ts = ts;
        }
    }
}
