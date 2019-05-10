package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BangumiDetailRecommendBean {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        @SerializedName("card")
        private List<?> card;
        @SerializedName("relates")
        private List<?> relates;
        @SerializedName("season")
        private List<SeasonBean> season;

        public List<?> getCard() {
            return card;
        }

        public void setCard(List<?> card) {
            this.card = card;
        }

        public List<?> getRelates() {
            return relates;
        }

        public void setRelates(List<?> relates) {
            this.relates = relates;
        }

        public List<SeasonBean> getSeason() {
            return season;
        }

        public void setSeason(List<SeasonBean> season) {
            this.season = season;
        }

        public static class SeasonBean {
            @SerializedName("badge")
            private String badge;
            @SerializedName("badge_type")
            private int badgeType;
            @SerializedName("cover")
            private String cover;
            @SerializedName("from")
            private int from;
            @SerializedName("new_ep")
            private NewEpBean newEp;
            @SerializedName("rating")
            private RatingBean rating;
            @SerializedName("season_id")
            private int seasonId;
            @SerializedName("season_type")
            private int seasonType;
            @SerializedName("stat")
            private StatBean stat;
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

            public int getFrom() {
                return from;
            }

            public void setFrom(int from) {
                this.from = from;
            }

            public NewEpBean getNewEp() {
                return newEp;
            }

            public void setNewEp(NewEpBean newEp) {
                this.newEp = newEp;
            }

            public RatingBean getRating() {
                return rating;
            }

            public void setRating(RatingBean rating) {
                this.rating = rating;
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

            public StatBean getStat() {
                return stat;
            }

            public void setStat(StatBean stat) {
                this.stat = stat;
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
                @SerializedName("cover")
                private String cover;
                @SerializedName("index_show")
                private String indexShow;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getIndexShow() {
                    return indexShow;
                }

                public void setIndexShow(String indexShow) {
                    this.indexShow = indexShow;
                }
            }

            public static class RatingBean {
                @SerializedName("count")
                private int count;
                @SerializedName("score")
                private double score;

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }
            }

            public static class StatBean {
                @SerializedName("danmaku")
                private int danmaku;
                @SerializedName("follow")
                private int follow;
                @SerializedName("view")
                private int view;

                public int getDanmaku() {
                    return danmaku;
                }

                public void setDanmaku(int danmaku) {
                    this.danmaku = danmaku;
                }

                public int getFollow() {
                    return follow;
                }

                public void setFollow(int follow) {
                    this.follow = follow;
                }

                public int getView() {
                    return view;
                }

                public void setView(int view) {
                    this.view = view;
                }
            }
        }
    }
}
