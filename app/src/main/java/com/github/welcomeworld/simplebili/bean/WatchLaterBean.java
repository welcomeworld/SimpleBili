package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WatchLaterBean {

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
        @SerializedName("count")
        private int count;
        @SerializedName("list")
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            @SerializedName("aid")
            private int aid;
            @SerializedName("videos")
            private int videos;
            @SerializedName("tid")
            private int tid;
            @SerializedName("tname")
            private String tname;
            @SerializedName("copyright")
            private int copyright;
            @SerializedName("pic")
            private String pic;
            @SerializedName("title")
            private String title;
            @SerializedName("pubdate")
            private int pubdate;
            @SerializedName("ctime")
            private int ctime;
            @SerializedName("desc")
            private String desc;
            @SerializedName("state")
            private int state;
            @SerializedName("attribute")
            private int attribute;
            @SerializedName("duration")
            private int duration;
            @SerializedName("order_id")
            private int orderId;
            @SerializedName("rights")
            private RightsBean rights;
            @SerializedName("owner")
            private OwnerBean owner;
            @SerializedName("stat")
            private StatBean stat;
            @SerializedName("dynamic")
            private String dynamic;
            @SerializedName("dimension")
            private DimensionBean dimension;
            @SerializedName("count")
            private int count;
            @SerializedName("cid")
            private int cid;
            @SerializedName("progress")
            private int progress;
            @SerializedName("add_at")
            private int addAt;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public int getVideos() {
                return videos;
            }

            public void setVideos(int videos) {
                this.videos = videos;
            }

            public int getTid() {
                return tid;
            }

            public void setTid(int tid) {
                this.tid = tid;
            }

            public String getTname() {
                return tname;
            }

            public void setTname(String tname) {
                this.tname = tname;
            }

            public int getCopyright() {
                return copyright;
            }

            public void setCopyright(int copyright) {
                this.copyright = copyright;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getPubdate() {
                return pubdate;
            }

            public void setPubdate(int pubdate) {
                this.pubdate = pubdate;
            }

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getAttribute() {
                return attribute;
            }

            public void setAttribute(int attribute) {
                this.attribute = attribute;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public RightsBean getRights() {
                return rights;
            }

            public void setRights(RightsBean rights) {
                this.rights = rights;
            }

            public OwnerBean getOwner() {
                return owner;
            }

            public void setOwner(OwnerBean owner) {
                this.owner = owner;
            }

            public StatBean getStat() {
                return stat;
            }

            public void setStat(StatBean stat) {
                this.stat = stat;
            }

            public String getDynamic() {
                return dynamic;
            }

            public void setDynamic(String dynamic) {
                this.dynamic = dynamic;
            }

            public DimensionBean getDimension() {
                return dimension;
            }

            public void setDimension(DimensionBean dimension) {
                this.dimension = dimension;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getProgress() {
                return progress;
            }

            public void setProgress(int progress) {
                this.progress = progress;
            }

            public int getAddAt() {
                return addAt;
            }

            public void setAddAt(int addAt) {
                this.addAt = addAt;
            }

            public static class RightsBean {
                @SerializedName("bp")
                private int bp;
                @SerializedName("elec")
                private int elec;
                @SerializedName("download")
                private int download;
                @SerializedName("movie")
                private int movie;
                @SerializedName("pay")
                private int pay;
                @SerializedName("hd5")
                private int hd5;
                @SerializedName("no_reprint")
                private int noReprint;
                @SerializedName("autoplay")
                private int autoplay;
                @SerializedName("ugc_pay")
                private int ugcPay;
                @SerializedName("is_cooperation")
                private int isCooperation;
                @SerializedName("ugc_pay_preview")
                private int ugcPayPreview;

                public int getBp() {
                    return bp;
                }

                public void setBp(int bp) {
                    this.bp = bp;
                }

                public int getElec() {
                    return elec;
                }

                public void setElec(int elec) {
                    this.elec = elec;
                }

                public int getDownload() {
                    return download;
                }

                public void setDownload(int download) {
                    this.download = download;
                }

                public int getMovie() {
                    return movie;
                }

                public void setMovie(int movie) {
                    this.movie = movie;
                }

                public int getPay() {
                    return pay;
                }

                public void setPay(int pay) {
                    this.pay = pay;
                }

                public int getHd5() {
                    return hd5;
                }

                public void setHd5(int hd5) {
                    this.hd5 = hd5;
                }

                public int getNoReprint() {
                    return noReprint;
                }

                public void setNoReprint(int noReprint) {
                    this.noReprint = noReprint;
                }

                public int getAutoplay() {
                    return autoplay;
                }

                public void setAutoplay(int autoplay) {
                    this.autoplay = autoplay;
                }

                public int getUgcPay() {
                    return ugcPay;
                }

                public void setUgcPay(int ugcPay) {
                    this.ugcPay = ugcPay;
                }

                public int getIsCooperation() {
                    return isCooperation;
                }

                public void setIsCooperation(int isCooperation) {
                    this.isCooperation = isCooperation;
                }

                public int getUgcPayPreview() {
                    return ugcPayPreview;
                }

                public void setUgcPayPreview(int ugcPayPreview) {
                    this.ugcPayPreview = ugcPayPreview;
                }
            }

            public static class OwnerBean {
                @SerializedName("mid")
                private int mid;
                @SerializedName("name")
                private String name;
                @SerializedName("face")
                private String face;

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
            }

            public static class StatBean {
                @SerializedName("aid")
                private int aid;
                @SerializedName("view")
                private int view;
                @SerializedName("danmaku")
                private int danmaku;
                @SerializedName("reply")
                private int reply;
                @SerializedName("favorite")
                private int favorite;
                @SerializedName("coin")
                private int coin;
                @SerializedName("share")
                private int share;
                @SerializedName("now_rank")
                private int nowRank;
                @SerializedName("his_rank")
                private int hisRank;
                @SerializedName("like")
                private int like;
                @SerializedName("dislike")
                private int dislike;

                public int getAid() {
                    return aid;
                }

                public void setAid(int aid) {
                    this.aid = aid;
                }

                public int getView() {
                    return view;
                }

                public void setView(int view) {
                    this.view = view;
                }

                public int getDanmaku() {
                    return danmaku;
                }

                public void setDanmaku(int danmaku) {
                    this.danmaku = danmaku;
                }

                public int getReply() {
                    return reply;
                }

                public void setReply(int reply) {
                    this.reply = reply;
                }

                public int getFavorite() {
                    return favorite;
                }

                public void setFavorite(int favorite) {
                    this.favorite = favorite;
                }

                public int getCoin() {
                    return coin;
                }

                public void setCoin(int coin) {
                    this.coin = coin;
                }

                public int getShare() {
                    return share;
                }

                public void setShare(int share) {
                    this.share = share;
                }

                public int getNowRank() {
                    return nowRank;
                }

                public void setNowRank(int nowRank) {
                    this.nowRank = nowRank;
                }

                public int getHisRank() {
                    return hisRank;
                }

                public void setHisRank(int hisRank) {
                    this.hisRank = hisRank;
                }

                public int getLike() {
                    return like;
                }

                public void setLike(int like) {
                    this.like = like;
                }

                public int getDislike() {
                    return dislike;
                }

                public void setDislike(int dislike) {
                    this.dislike = dislike;
                }
            }

            public static class DimensionBean {
                @SerializedName("width")
                private int width;
                @SerializedName("height")
                private int height;
                @SerializedName("rotate")
                private int rotate;

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getRotate() {
                    return rotate;
                }

                public void setRotate(int rotate) {
                    this.rotate = rotate;
                }
            }
        }
    }
}
