package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IndexDynamicVideoCardBean {

    @SerializedName("aid")
    private int aid;
    @SerializedName("attribute")
    private int attribute;
    @SerializedName("cid")
    private int cid;
    @SerializedName("copyright")
    private int copyright;
    @SerializedName("ctime")
    private int ctime;
    @SerializedName("desc")
    private String desc;
    @SerializedName("dimension")
    private DimensionBean dimension;
    @SerializedName("duration")
    private int duration;
    @SerializedName("dynamic")
    private String dynamic;
    @SerializedName("jump_url")
    private String jumpUrl;
    @SerializedName("owner")
    private OwnerBean owner;
    @SerializedName("pic")
    private String pic;
    @SerializedName("player_info")
    private PlayerInfoBean playerInfo;
    @SerializedName("pubdate")
    private int pubdate;
    @SerializedName("rights")
    private RightsBean rights;
    @SerializedName("stat")
    private StatBean stat;
    @SerializedName("state")
    private int state;
    @SerializedName("tid")
    private int tid;
    @SerializedName("title")
    private String title;
    @SerializedName("tname")
    private String tname;
    @SerializedName("videos")
    private int videos;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCopyright() {
        return copyright;
    }

    public void setCopyright(int copyright) {
        this.copyright = copyright;
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

    public DimensionBean getDimension() {
        return dimension;
    }

    public void setDimension(DimensionBean dimension) {
        this.dimension = dimension;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public OwnerBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerBean owner) {
        this.owner = owner;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public PlayerInfoBean getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfoBean playerInfo) {
        this.playerInfo = playerInfo;
    }

    public int getPubdate() {
        return pubdate;
    }

    public void setPubdate(int pubdate) {
        this.pubdate = pubdate;
    }

    public RightsBean getRights() {
        return rights;
    }

    public void setRights(RightsBean rights) {
        this.rights = rights;
    }

    public StatBean getStat() {
        return stat;
    }

    public void setStat(StatBean stat) {
        this.stat = stat;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getVideos() {
        return videos;
    }

    public void setVideos(int videos) {
        this.videos = videos;
    }

    public static class DimensionBean {
        @SerializedName("height")
        private int height;
        @SerializedName("rotate")
        private int rotate;
        @SerializedName("width")
        private int width;

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

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

    public static class OwnerBean {
        @SerializedName("face")
        private String face;
        @SerializedName("mid")
        private int mid;
        @SerializedName("name")
        private String name;

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

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
    }

    public static class PlayerInfoBean {
        @SerializedName("cid")
        private int cid;
        @SerializedName("expire_time")
        private int expireTime;
        @SerializedName("file_info")
        private FileInfoBean fileInfo;
        @SerializedName("fnval")
        private int fnval;
        @SerializedName("fnver")
        private int fnver;
        @SerializedName("quality")
        private int quality;
        @SerializedName("url")
        private String url;
        @SerializedName("video_codecid")
        private int videoCodecid;
        @SerializedName("video_project")
        private boolean videoProject;
        @SerializedName("support_description")
        private List<String> supportDescription;
        @SerializedName("support_formats")
        private List<String> supportFormats;
        @SerializedName("support_quality")
        private List<Integer> supportQuality;

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(int expireTime) {
            this.expireTime = expireTime;
        }

        public FileInfoBean getFileInfo() {
            return fileInfo;
        }

        public void setFileInfo(FileInfoBean fileInfo) {
            this.fileInfo = fileInfo;
        }

        public int getFnval() {
            return fnval;
        }

        public void setFnval(int fnval) {
            this.fnval = fnval;
        }

        public int getFnver() {
            return fnver;
        }

        public void setFnver(int fnver) {
            this.fnver = fnver;
        }

        public int getQuality() {
            return quality;
        }

        public void setQuality(int quality) {
            this.quality = quality;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVideoCodecid() {
            return videoCodecid;
        }

        public void setVideoCodecid(int videoCodecid) {
            this.videoCodecid = videoCodecid;
        }

        public boolean isVideoProject() {
            return videoProject;
        }

        public void setVideoProject(boolean videoProject) {
            this.videoProject = videoProject;
        }

        public List<String> getSupportDescription() {
            return supportDescription;
        }

        public void setSupportDescription(List<String> supportDescription) {
            this.supportDescription = supportDescription;
        }

        public List<String> getSupportFormats() {
            return supportFormats;
        }

        public void setSupportFormats(List<String> supportFormats) {
            this.supportFormats = supportFormats;
        }

        public List<Integer> getSupportQuality() {
            return supportQuality;
        }

        public void setSupportQuality(List<Integer> supportQuality) {
            this.supportQuality = supportQuality;
        }

        public static class FileInfoBean {
            @SerializedName("16")
            private List<_$16Bean> $16;
            @SerializedName("32")
            private List<_$32Bean> $32;
            @SerializedName("64")
            private List<_$64Bean> $64;
            @SerializedName("80")
            private List<_$80Bean> $80;

            public List<_$16Bean> get$16() {
                return $16;
            }

            public void set$16(List<_$16Bean> $16) {
                this.$16 = $16;
            }

            public List<_$32Bean> get$32() {
                return $32;
            }

            public void set$32(List<_$32Bean> $32) {
                this.$32 = $32;
            }

            public List<_$64Bean> get$64() {
                return $64;
            }

            public void set$64(List<_$64Bean> $64) {
                this.$64 = $64;
            }

            public List<_$80Bean> get$80() {
                return $80;
            }

            public void set$80(List<_$80Bean> $80) {
                this.$80 = $80;
            }

            public static class _$16Bean {
                @SerializedName("filesize")
                private int filesize;
                @SerializedName("timelength")
                private int timelength;

                public int getFilesize() {
                    return filesize;
                }

                public void setFilesize(int filesize) {
                    this.filesize = filesize;
                }

                public int getTimelength() {
                    return timelength;
                }

                public void setTimelength(int timelength) {
                    this.timelength = timelength;
                }
            }

            public static class _$32Bean {
                @SerializedName("filesize")
                private int filesize;
                @SerializedName("timelength")
                private int timelength;

                public int getFilesize() {
                    return filesize;
                }

                public void setFilesize(int filesize) {
                    this.filesize = filesize;
                }

                public int getTimelength() {
                    return timelength;
                }

                public void setTimelength(int timelength) {
                    this.timelength = timelength;
                }
            }

            public static class _$64Bean {
                @SerializedName("filesize")
                private int filesize;
                @SerializedName("timelength")
                private int timelength;

                public int getFilesize() {
                    return filesize;
                }

                public void setFilesize(int filesize) {
                    this.filesize = filesize;
                }

                public int getTimelength() {
                    return timelength;
                }

                public void setTimelength(int timelength) {
                    this.timelength = timelength;
                }
            }

            public static class _$80Bean {
                @SerializedName("filesize")
                private int filesize;
                @SerializedName("timelength")
                private int timelength;

                public int getFilesize() {
                    return filesize;
                }

                public void setFilesize(int filesize) {
                    this.filesize = filesize;
                }

                public int getTimelength() {
                    return timelength;
                }

                public void setTimelength(int timelength) {
                    this.timelength = timelength;
                }
            }
        }
    }

    public static class RightsBean {
        @SerializedName("autoplay")
        private int autoplay;
        @SerializedName("bp")
        private int bp;
        @SerializedName("download")
        private int download;
        @SerializedName("elec")
        private int elec;
        @SerializedName("hd5")
        private int hd5;
        @SerializedName("is_cooperation")
        private int isCooperation;
        @SerializedName("movie")
        private int movie;
        @SerializedName("no_reprint")
        private int noReprint;
        @SerializedName("pay")
        private int pay;
        @SerializedName("ugc_pay")
        private int ugcPay;
        @SerializedName("ugc_pay_preview")
        private int ugcPayPreview;

        public int getAutoplay() {
            return autoplay;
        }

        public void setAutoplay(int autoplay) {
            this.autoplay = autoplay;
        }

        public int getBp() {
            return bp;
        }

        public void setBp(int bp) {
            this.bp = bp;
        }

        public int getDownload() {
            return download;
        }

        public void setDownload(int download) {
            this.download = download;
        }

        public int getElec() {
            return elec;
        }

        public void setElec(int elec) {
            this.elec = elec;
        }

        public int getHd5() {
            return hd5;
        }

        public void setHd5(int hd5) {
            this.hd5 = hd5;
        }

        public int getIsCooperation() {
            return isCooperation;
        }

        public void setIsCooperation(int isCooperation) {
            this.isCooperation = isCooperation;
        }

        public int getMovie() {
            return movie;
        }

        public void setMovie(int movie) {
            this.movie = movie;
        }

        public int getNoReprint() {
            return noReprint;
        }

        public void setNoReprint(int noReprint) {
            this.noReprint = noReprint;
        }

        public int getPay() {
            return pay;
        }

        public void setPay(int pay) {
            this.pay = pay;
        }

        public int getUgcPay() {
            return ugcPay;
        }

        public void setUgcPay(int ugcPay) {
            this.ugcPay = ugcPay;
        }

        public int getUgcPayPreview() {
            return ugcPayPreview;
        }

        public void setUgcPayPreview(int ugcPayPreview) {
            this.ugcPayPreview = ugcPayPreview;
        }
    }

    public static class StatBean {
        @SerializedName("aid")
        private int aid;
        @SerializedName("coin")
        private int coin;
        @SerializedName("danmaku")
        private int danmaku;
        @SerializedName("dislike")
        private int dislike;
        @SerializedName("favorite")
        private int favorite;
        @SerializedName("his_rank")
        private int hisRank;
        @SerializedName("like")
        private int like;
        @SerializedName("now_rank")
        private int nowRank;
        @SerializedName("reply")
        private int reply;
        @SerializedName("share")
        private int share;
        @SerializedName("view")
        private int view;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getCoin() {
            return coin;
        }

        public void setCoin(int coin) {
            this.coin = coin;
        }

        public int getDanmaku() {
            return danmaku;
        }

        public void setDanmaku(int danmaku) {
            this.danmaku = danmaku;
        }

        public int getDislike() {
            return dislike;
        }

        public void setDislike(int dislike) {
            this.dislike = dislike;
        }

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
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

        public int getNowRank() {
            return nowRank;
        }

        public void setNowRank(int nowRank) {
            this.nowRank = nowRank;
        }

        public int getReply() {
            return reply;
        }

        public void setReply(int reply) {
            this.reply = reply;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public int getView() {
            return view;
        }

        public void setView(int view) {
            this.view = view;
        }
    }
}
