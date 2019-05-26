package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserSpaceBean {

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
        @SerializedName("relation")
        private int relation;
        @SerializedName("setting")
        private SettingBean setting;
        @SerializedName("tab")
        private TabBean tab;
        @SerializedName("card")
        private CardBean card;
        @SerializedName("images")
        private ImagesBean images;
        @SerializedName("live")
        private LiveBean live;
        @SerializedName("elec")
        private ElecBean elec;
        @SerializedName("archive")
        private ArchiveBean archive;
        @SerializedName("article")
        private ArticleBean article;
        @SerializedName("clip")
        private ClipBean clip;
        @SerializedName("album")
        private AlbumBean album;
        @SerializedName("season")
        private SeasonBean season;
        @SerializedName("coin_archive")
        private CoinArchiveBean coinArchive;
        @SerializedName("like_archive")
        private LikeArchiveBean likeArchive;
        @SerializedName("audios")
        private AudiosBean audios;
        @SerializedName("favourite2")
        private Favourite2Bean favourite2;

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public SettingBean getSetting() {
            return setting;
        }

        public void setSetting(SettingBean setting) {
            this.setting = setting;
        }

        public TabBean getTab() {
            return tab;
        }

        public void setTab(TabBean tab) {
            this.tab = tab;
        }

        public CardBean getCard() {
            return card;
        }

        public void setCard(CardBean card) {
            this.card = card;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public LiveBean getLive() {
            return live;
        }

        public void setLive(LiveBean live) {
            this.live = live;
        }

        public ElecBean getElec() {
            return elec;
        }

        public void setElec(ElecBean elec) {
            this.elec = elec;
        }

        public ArchiveBean getArchive() {
            return archive;
        }

        public void setArchive(ArchiveBean archive) {
            this.archive = archive;
        }

        public ArticleBean getArticle() {
            return article;
        }

        public void setArticle(ArticleBean article) {
            this.article = article;
        }

        public ClipBean getClip() {
            return clip;
        }

        public void setClip(ClipBean clip) {
            this.clip = clip;
        }

        public AlbumBean getAlbum() {
            return album;
        }

        public void setAlbum(AlbumBean album) {
            this.album = album;
        }

        public SeasonBean getSeason() {
            return season;
        }

        public void setSeason(SeasonBean season) {
            this.season = season;
        }

        public CoinArchiveBean getCoinArchive() {
            return coinArchive;
        }

        public void setCoinArchive(CoinArchiveBean coinArchive) {
            this.coinArchive = coinArchive;
        }

        public LikeArchiveBean getLikeArchive() {
            return likeArchive;
        }

        public void setLikeArchive(LikeArchiveBean likeArchive) {
            this.likeArchive = likeArchive;
        }

        public AudiosBean getAudios() {
            return audios;
        }

        public void setAudios(AudiosBean audios) {
            this.audios = audios;
        }

        public Favourite2Bean getFavourite2() {
            return favourite2;
        }

        public void setFavourite2(Favourite2Bean favourite2) {
            this.favourite2 = favourite2;
        }

        public static class SettingBean {
            @SerializedName("channel")
            private int channel;
            @SerializedName("fav_video")
            private int favVideo;
            @SerializedName("coins_video")
            private int coinsVideo;
            @SerializedName("likes_video")
            private int likesVideo;
            @SerializedName("bangumi")
            private int bangumi;
            @SerializedName("played_game")
            private int playedGame;
            @SerializedName("groups")
            private int groups;

            public int getChannel() {
                return channel;
            }

            public void setChannel(int channel) {
                this.channel = channel;
            }

            public int getFavVideo() {
                return favVideo;
            }

            public void setFavVideo(int favVideo) {
                this.favVideo = favVideo;
            }

            public int getCoinsVideo() {
                return coinsVideo;
            }

            public void setCoinsVideo(int coinsVideo) {
                this.coinsVideo = coinsVideo;
            }

            public int getLikesVideo() {
                return likesVideo;
            }

            public void setLikesVideo(int likesVideo) {
                this.likesVideo = likesVideo;
            }

            public int getBangumi() {
                return bangumi;
            }

            public void setBangumi(int bangumi) {
                this.bangumi = bangumi;
            }

            public int getPlayedGame() {
                return playedGame;
            }

            public void setPlayedGame(int playedGame) {
                this.playedGame = playedGame;
            }

            public int getGroups() {
                return groups;
            }

            public void setGroups(int groups) {
                this.groups = groups;
            }
        }

        public static class TabBean {
            @SerializedName("archive")
            private boolean archive;
            @SerializedName("article")
            private boolean article;
            @SerializedName("clip")
            private boolean clip;
            @SerializedName("album")
            private boolean album;
            @SerializedName("favorite")
            private boolean favorite;
            @SerializedName("bangumi")
            private boolean bangumi;
            @SerializedName("coin")
            private boolean coin;
            @SerializedName("like")
            private boolean like;
            @SerializedName("community")
            private boolean community;
            @SerializedName("dynamic")
            private boolean dynamic;
            @SerializedName("audios")
            private boolean audios;
            @SerializedName("shop")
            private boolean shop;
            @SerializedName("mall")
            private boolean mall;

            public boolean isArchive() {
                return archive;
            }

            public void setArchive(boolean archive) {
                this.archive = archive;
            }

            public boolean isArticle() {
                return article;
            }

            public void setArticle(boolean article) {
                this.article = article;
            }

            public boolean isClip() {
                return clip;
            }

            public void setClip(boolean clip) {
                this.clip = clip;
            }

            public boolean isAlbum() {
                return album;
            }

            public void setAlbum(boolean album) {
                this.album = album;
            }

            public boolean isFavorite() {
                return favorite;
            }

            public void setFavorite(boolean favorite) {
                this.favorite = favorite;
            }

            public boolean isBangumi() {
                return bangumi;
            }

            public void setBangumi(boolean bangumi) {
                this.bangumi = bangumi;
            }

            public boolean isCoin() {
                return coin;
            }

            public void setCoin(boolean coin) {
                this.coin = coin;
            }

            public boolean isLike() {
                return like;
            }

            public void setLike(boolean like) {
                this.like = like;
            }

            public boolean isCommunity() {
                return community;
            }

            public void setCommunity(boolean community) {
                this.community = community;
            }

            public boolean isDynamic() {
                return dynamic;
            }

            public void setDynamic(boolean dynamic) {
                this.dynamic = dynamic;
            }

            public boolean isAudios() {
                return audios;
            }

            public void setAudios(boolean audios) {
                this.audios = audios;
            }

            public boolean isShop() {
                return shop;
            }

            public void setShop(boolean shop) {
                this.shop = shop;
            }

            public boolean isMall() {
                return mall;
            }

            public void setMall(boolean mall) {
                this.mall = mall;
            }
        }

        public static class CardBean {
            @SerializedName("mid")
            private String mid;
            @SerializedName("name")
            private String name;
            @SerializedName("approve")
            private boolean approve;
            @SerializedName("sex")
            private String sex;
            @SerializedName("rank")
            private String rank;
            @SerializedName("face")
            private String face;
            @SerializedName("DisplayRank")
            private String DisplayRank;
            @SerializedName("regtime")
            private int regtime;
            @SerializedName("spacesta")
            private int spacesta;
            @SerializedName("birthday")
            private String birthday;
            @SerializedName("place")
            private String place;
            @SerializedName("description")
            private String description;
            @SerializedName("article")
            private int article;
            @SerializedName("attentions")
            private Object attentions;
            @SerializedName("fans")
            private int fans;
            @SerializedName("friend")
            private int friend;
            @SerializedName("attention")
            private int attention;
            @SerializedName("sign")
            private String sign;
            @SerializedName("level_info")
            private LevelInfoBean levelInfo;
            @SerializedName("pendant")
            private PendantBean pendant;
            @SerializedName("nameplate")
            private NameplateBean nameplate;
            @SerializedName("official_verify")
            private OfficialVerifyBean officialVerify;
            @SerializedName("vip")
            private VipBean vip;
            @SerializedName("silence")
            private int silence;
            @SerializedName("end_time")
            private int endTime;
            @SerializedName("silence_url")
            private String silenceUrl;

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isApprove() {
                return approve;
            }

            public void setApprove(boolean approve) {
                this.approve = approve;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getDisplayRank() {
                return DisplayRank;
            }

            public void setDisplayRank(String DisplayRank) {
                this.DisplayRank = DisplayRank;
            }

            public int getRegtime() {
                return regtime;
            }

            public void setRegtime(int regtime) {
                this.regtime = regtime;
            }

            public int getSpacesta() {
                return spacesta;
            }

            public void setSpacesta(int spacesta) {
                this.spacesta = spacesta;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getArticle() {
                return article;
            }

            public void setArticle(int article) {
                this.article = article;
            }

            public Object getAttentions() {
                return attentions;
            }

            public void setAttentions(Object attentions) {
                this.attentions = attentions;
            }

            public int getFans() {
                return fans;
            }

            public void setFans(int fans) {
                this.fans = fans;
            }

            public int getFriend() {
                return friend;
            }

            public void setFriend(int friend) {
                this.friend = friend;
            }

            public int getAttention() {
                return attention;
            }

            public void setAttention(int attention) {
                this.attention = attention;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public LevelInfoBean getLevelInfo() {
                return levelInfo;
            }

            public void setLevelInfo(LevelInfoBean levelInfo) {
                this.levelInfo = levelInfo;
            }

            public PendantBean getPendant() {
                return pendant;
            }

            public void setPendant(PendantBean pendant) {
                this.pendant = pendant;
            }

            public NameplateBean getNameplate() {
                return nameplate;
            }

            public void setNameplate(NameplateBean nameplate) {
                this.nameplate = nameplate;
            }

            public OfficialVerifyBean getOfficialVerify() {
                return officialVerify;
            }

            public void setOfficialVerify(OfficialVerifyBean officialVerify) {
                this.officialVerify = officialVerify;
            }

            public VipBean getVip() {
                return vip;
            }

            public void setVip(VipBean vip) {
                this.vip = vip;
            }

            public int getSilence() {
                return silence;
            }

            public void setSilence(int silence) {
                this.silence = silence;
            }

            public int getEndTime() {
                return endTime;
            }

            public void setEndTime(int endTime) {
                this.endTime = endTime;
            }

            public String getSilenceUrl() {
                return silenceUrl;
            }

            public void setSilenceUrl(String silenceUrl) {
                this.silenceUrl = silenceUrl;
            }

            public static class LevelInfoBean {
                @SerializedName("current_level")
                private int currentLevel;
                @SerializedName("current_min")
                private int currentMin;
                @SerializedName("current_exp")
                private int currentExp;
                @SerializedName("next_exp")
                private int nextExp;

                public int getCurrentLevel() {
                    return currentLevel;
                }

                public void setCurrentLevel(int currentLevel) {
                    this.currentLevel = currentLevel;
                }

                public int getCurrentMin() {
                    return currentMin;
                }

                public void setCurrentMin(int currentMin) {
                    this.currentMin = currentMin;
                }

                public int getCurrentExp() {
                    return currentExp;
                }

                public void setCurrentExp(int currentExp) {
                    this.currentExp = currentExp;
                }

                public int getNextExp() {
                    return nextExp;
                }

                public void setNextExp(int nextExp) {
                    this.nextExp = nextExp;
                }
            }

            public static class PendantBean {
                @SerializedName("pid")
                private int pid;
                @SerializedName("name")
                private String name;
                @SerializedName("image")
                private String image;
                @SerializedName("expire")
                private int expire;

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public int getExpire() {
                    return expire;
                }

                public void setExpire(int expire) {
                    this.expire = expire;
                }
            }

            public static class NameplateBean {
                @SerializedName("nid")
                private int nid;
                @SerializedName("name")
                private String name;
                @SerializedName("image")
                private String image;
                @SerializedName("image_small")
                private String imageSmall;
                @SerializedName("level")
                private String level;
                @SerializedName("condition")
                private String condition;

                public int getNid() {
                    return nid;
                }

                public void setNid(int nid) {
                    this.nid = nid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getImageSmall() {
                    return imageSmall;
                }

                public void setImageSmall(String imageSmall) {
                    this.imageSmall = imageSmall;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getCondition() {
                    return condition;
                }

                public void setCondition(String condition) {
                    this.condition = condition;
                }
            }

            public static class OfficialVerifyBean {
                @SerializedName("type")
                private int type;
                @SerializedName("desc")
                private String desc;
                @SerializedName("role")
                private int role;
                @SerializedName("title")
                private String title;

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

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class VipBean {
                @SerializedName("vipType")
                private int vipType;
                @SerializedName("vipDueDate")
                private long vipDueDate;
                @SerializedName("dueRemark")
                private String dueRemark;
                @SerializedName("accessStatus")
                private int accessStatus;
                @SerializedName("vipStatus")
                private int vipStatus;
                @SerializedName("vipStatusWarn")
                private String vipStatusWarn;
                @SerializedName("themeType")
                private int themeType;

                public int getVipType() {
                    return vipType;
                }

                public void setVipType(int vipType) {
                    this.vipType = vipType;
                }

                public long getVipDueDate() {
                    return vipDueDate;
                }

                public void setVipDueDate(long vipDueDate) {
                    this.vipDueDate = vipDueDate;
                }

                public String getDueRemark() {
                    return dueRemark;
                }

                public void setDueRemark(String dueRemark) {
                    this.dueRemark = dueRemark;
                }

                public int getAccessStatus() {
                    return accessStatus;
                }

                public void setAccessStatus(int accessStatus) {
                    this.accessStatus = accessStatus;
                }

                public int getVipStatus() {
                    return vipStatus;
                }

                public void setVipStatus(int vipStatus) {
                    this.vipStatus = vipStatus;
                }

                public String getVipStatusWarn() {
                    return vipStatusWarn;
                }

                public void setVipStatusWarn(String vipStatusWarn) {
                    this.vipStatusWarn = vipStatusWarn;
                }

                public int getThemeType() {
                    return themeType;
                }

                public void setThemeType(int themeType) {
                    this.themeType = themeType;
                }
            }
        }

        public static class ImagesBean {
            @SerializedName("imgUrl")
            private String imgUrl;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }

        public static class LiveBean {
            @SerializedName("roomStatus")
            private int roomStatus;
            @SerializedName("roundStatus")
            private int roundStatus;
            @SerializedName("liveStatus")
            private int liveStatus;
            @SerializedName("url")
            private String url;
            @SerializedName("title")
            private String title;
            @SerializedName("cover")
            private String cover;
            @SerializedName("online")
            private int online;
            @SerializedName("roomid")
            private int roomid;
            @SerializedName("broadcast_type")
            private int broadcastType;

            public int getRoomStatus() {
                return roomStatus;
            }

            public void setRoomStatus(int roomStatus) {
                this.roomStatus = roomStatus;
            }

            public int getRoundStatus() {
                return roundStatus;
            }

            public void setRoundStatus(int roundStatus) {
                this.roundStatus = roundStatus;
            }

            public int getLiveStatus() {
                return liveStatus;
            }

            public void setLiveStatus(int liveStatus) {
                this.liveStatus = liveStatus;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public int getOnline() {
                return online;
            }

            public void setOnline(int online) {
                this.online = online;
            }

            public int getRoomid() {
                return roomid;
            }

            public void setRoomid(int roomid) {
                this.roomid = roomid;
            }

            public int getBroadcastType() {
                return broadcastType;
            }

            public void setBroadcastType(int broadcastType) {
                this.broadcastType = broadcastType;
            }
        }

        public static class ElecBean {
            @SerializedName("show")
            private boolean show;
            @SerializedName("total")
            private int total;
            @SerializedName("count")
            private int count;
            @SerializedName("elec_num")
            private int elecNum;
            @SerializedName("elec_set")
            private ElecSetBean elecSet;
            @SerializedName("list")
            private List<ListBean> list;

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getElecNum() {
                return elecNum;
            }

            public void setElecNum(int elecNum) {
                this.elecNum = elecNum;
            }

            public ElecSetBean getElecSet() {
                return elecSet;
            }

            public void setElecSet(ElecSetBean elecSet) {
                this.elecSet = elecSet;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ElecSetBean {
                @SerializedName("elec_theme")
                private int elecTheme;
                @SerializedName("rmb_rate")
                private int rmbRate;
                @SerializedName("integrity_rate")
                private int integrityRate;
                @SerializedName("round_mode")
                private int roundMode;
                @SerializedName("elec_list")
                private List<ElecListBean> elecList;

                public int getElecTheme() {
                    return elecTheme;
                }

                public void setElecTheme(int elecTheme) {
                    this.elecTheme = elecTheme;
                }

                public int getRmbRate() {
                    return rmbRate;
                }

                public void setRmbRate(int rmbRate) {
                    this.rmbRate = rmbRate;
                }

                public int getIntegrityRate() {
                    return integrityRate;
                }

                public void setIntegrityRate(int integrityRate) {
                    this.integrityRate = integrityRate;
                }

                public int getRoundMode() {
                    return roundMode;
                }

                public void setRoundMode(int roundMode) {
                    this.roundMode = roundMode;
                }

                public List<ElecListBean> getElecList() {
                    return elecList;
                }

                public void setElecList(List<ElecListBean> elecList) {
                    this.elecList = elecList;
                }

                public static class ElecListBean {
                    @SerializedName("title")
                    private String title;
                    @SerializedName("elec_num")
                    private int elecNum;
                    @SerializedName("is_customize")
                    private int isCustomize;
                    @SerializedName("min_elec")
                    private int minElec;
                    @SerializedName("max_elec")
                    private int maxElec;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public int getElecNum() {
                        return elecNum;
                    }

                    public void setElecNum(int elecNum) {
                        this.elecNum = elecNum;
                    }

                    public int getIsCustomize() {
                        return isCustomize;
                    }

                    public void setIsCustomize(int isCustomize) {
                        this.isCustomize = isCustomize;
                    }

                    public int getMinElec() {
                        return minElec;
                    }

                    public void setMinElec(int minElec) {
                        this.minElec = minElec;
                    }

                    public int getMaxElec() {
                        return maxElec;
                    }

                    public void setMaxElec(int maxElec) {
                        this.maxElec = maxElec;
                    }
                }
            }

            public static class ListBean {
                @SerializedName("mid")
                private int mid;
                @SerializedName("pay_mid")
                private int payMid;
                @SerializedName("rank")
                private int rank;
                @SerializedName("uname")
                private String uname;
                @SerializedName("avatar")
                private String avatar;
                @SerializedName("message")
                private String message;
                @SerializedName("msg_deleted")
                private int msgDeleted;
                @SerializedName("vip_info")
                private VipInfoBean vipInfo;
                @SerializedName("trend_type")
                private int trendType;

                public int getMid() {
                    return mid;
                }

                public void setMid(int mid) {
                    this.mid = mid;
                }

                public int getPayMid() {
                    return payMid;
                }

                public void setPayMid(int payMid) {
                    this.payMid = payMid;
                }

                public int getRank() {
                    return rank;
                }

                public void setRank(int rank) {
                    this.rank = rank;
                }

                public String getUname() {
                    return uname;
                }

                public void setUname(String uname) {
                    this.uname = uname;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public int getMsgDeleted() {
                    return msgDeleted;
                }

                public void setMsgDeleted(int msgDeleted) {
                    this.msgDeleted = msgDeleted;
                }

                public VipInfoBean getVipInfo() {
                    return vipInfo;
                }

                public void setVipInfo(VipInfoBean vipInfo) {
                    this.vipInfo = vipInfo;
                }

                public int getTrendType() {
                    return trendType;
                }

                public void setTrendType(int trendType) {
                    this.trendType = trendType;
                }

                public static class VipInfoBean {
                    @SerializedName("vipType")
                    private int vipType;
                    @SerializedName("vipDueMsec")
                    private int vipDueMsec;
                    @SerializedName("vipStatus")
                    private int vipStatus;

                    public int getVipType() {
                        return vipType;
                    }

                    public void setVipType(int vipType) {
                        this.vipType = vipType;
                    }

                    public int getVipDueMsec() {
                        return vipDueMsec;
                    }

                    public void setVipDueMsec(int vipDueMsec) {
                        this.vipDueMsec = vipDueMsec;
                    }

                    public int getVipStatus() {
                        return vipStatus;
                    }

                    public void setVipStatus(int vipStatus) {
                        this.vipStatus = vipStatus;
                    }
                }
            }
        }

        public static class ArchiveBean {
            @SerializedName("count")
            private int count;
            @SerializedName("item")
            private List<ItemBean> item;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ItemBean> getItem() {
                return item;
            }

            public void setItem(List<ItemBean> item) {
                this.item = item;
            }

            public static class ItemBean {
                @SerializedName("title")
                private String title;
                @SerializedName("tname")
                private String tname;
                @SerializedName("cover")
                private String cover;
                @SerializedName("uri")
                private String uri;
                @SerializedName("param")
                private String param;
                @SerializedName("goto")
                private String gotoX;
                @SerializedName("length")
                private String length;
                @SerializedName("duration")
                private int duration;
                @SerializedName("play")
                private int play;
                @SerializedName("danmaku")
                private int danmaku;
                @SerializedName("ctime")
                private int ctime;
                @SerializedName("ugc_pay")
                private int ugcPay;
                @SerializedName("author")
                private String author;

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

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getUri() {
                    return uri;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public String getParam() {
                    return param;
                }

                public void setParam(String param) {
                    this.param = param;
                }

                public String getGotoX() {
                    return gotoX;
                }

                public void setGotoX(String gotoX) {
                    this.gotoX = gotoX;
                }

                public String getLength() {
                    return length;
                }

                public void setLength(String length) {
                    this.length = length;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public int getPlay() {
                    return play;
                }

                public void setPlay(int play) {
                    this.play = play;
                }

                public int getDanmaku() {
                    return danmaku;
                }

                public void setDanmaku(int danmaku) {
                    this.danmaku = danmaku;
                }

                public int getCtime() {
                    return ctime;
                }

                public void setCtime(int ctime) {
                    this.ctime = ctime;
                }

                public int getUgcPay() {
                    return ugcPay;
                }

                public void setUgcPay(int ugcPay) {
                    this.ugcPay = ugcPay;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }
            }
        }

        public static class ArticleBean {
            @SerializedName("count")
            private int count;
            @SerializedName("lists_count")
            private int listsCount;
            @SerializedName("item")
            private List<?> item;
            @SerializedName("lists")
            private List<?> lists;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getListsCount() {
                return listsCount;
            }

            public void setListsCount(int listsCount) {
                this.listsCount = listsCount;
            }

            public List<?> getItem() {
                return item;
            }

            public void setItem(List<?> item) {
                this.item = item;
            }

            public List<?> getLists() {
                return lists;
            }

            public void setLists(List<?> lists) {
                this.lists = lists;
            }
        }

        public static class ClipBean {
            @SerializedName("count")
            private int count;
            @SerializedName("has_more")
            private int hasMore;
            @SerializedName("next_offset")
            private int nextOffset;
            @SerializedName("item")
            private List<?> item;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getHasMore() {
                return hasMore;
            }

            public void setHasMore(int hasMore) {
                this.hasMore = hasMore;
            }

            public int getNextOffset() {
                return nextOffset;
            }

            public void setNextOffset(int nextOffset) {
                this.nextOffset = nextOffset;
            }

            public List<?> getItem() {
                return item;
            }

            public void setItem(List<?> item) {
                this.item = item;
            }
        }

        public static class AlbumBean {
            @SerializedName("count")
            private int count;
            @SerializedName("has_more")
            private int hasMore;
            @SerializedName("next_offset")
            private int nextOffset;
            @SerializedName("item")
            private List<ItemBeanX> item;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getHasMore() {
                return hasMore;
            }

            public void setHasMore(int hasMore) {
                this.hasMore = hasMore;
            }

            public int getNextOffset() {
                return nextOffset;
            }

            public void setNextOffset(int nextOffset) {
                this.nextOffset = nextOffset;
            }

            public List<ItemBeanX> getItem() {
                return item;
            }

            public void setItem(List<ItemBeanX> item) {
                this.item = item;
            }

            public static class ItemBeanX {
                @SerializedName("id")
                private int id;
                @SerializedName("description")
                private String description;
                @SerializedName("uri")
                private String uri;
                @SerializedName("param")
                private String param;
                @SerializedName("goto")
                private String gotoX;
                @SerializedName("play")
                private int play;
                @SerializedName("count")
                private int count;
                @SerializedName("ctime")
                private int ctime;
                @SerializedName("pictures")
                private List<PicturesBean> pictures;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getUri() {
                    return uri;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public String getParam() {
                    return param;
                }

                public void setParam(String param) {
                    this.param = param;
                }

                public String getGotoX() {
                    return gotoX;
                }

                public void setGotoX(String gotoX) {
                    this.gotoX = gotoX;
                }

                public int getPlay() {
                    return play;
                }

                public void setPlay(int play) {
                    this.play = play;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public int getCtime() {
                    return ctime;
                }

                public void setCtime(int ctime) {
                    this.ctime = ctime;
                }

                public List<PicturesBean> getPictures() {
                    return pictures;
                }

                public void setPictures(List<PicturesBean> pictures) {
                    this.pictures = pictures;
                }

                public static class PicturesBean {
                    @SerializedName("img_src")
                    private String imgSrc;
                    @SerializedName("img_width")
                    private int imgWidth;
                    @SerializedName("img_height")
                    private int imgHeight;
                    @SerializedName("img_size")
                    private int imgSize;

                    public String getImgSrc() {
                        return imgSrc;
                    }

                    public void setImgSrc(String imgSrc) {
                        this.imgSrc = imgSrc;
                    }

                    public int getImgWidth() {
                        return imgWidth;
                    }

                    public void setImgWidth(int imgWidth) {
                        this.imgWidth = imgWidth;
                    }

                    public int getImgHeight() {
                        return imgHeight;
                    }

                    public void setImgHeight(int imgHeight) {
                        this.imgHeight = imgHeight;
                    }

                    public int getImgSize() {
                        return imgSize;
                    }

                    public void setImgSize(int imgSize) {
                        this.imgSize = imgSize;
                    }
                }
            }
        }

        public static class SeasonBean {
            @SerializedName("count")
            private int count;
            @SerializedName("item")
            private List<ItemBeanXX> item;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ItemBeanXX> getItem() {
                return item;
            }

            public void setItem(List<ItemBeanXX> item) {
                this.item = item;
            }

            public static class ItemBeanXX {
                @SerializedName("title")
                private String title;
                @SerializedName("cover")
                private String cover;
                @SerializedName("uri")
                private String uri;
                @SerializedName("param")
                private String param;
                @SerializedName("goto")
                private String gotoX;
                @SerializedName("finish")
                private int finish;
                @SerializedName("index")
                private String index;
                @SerializedName("mtime")
                private int mtime;
                @SerializedName("newest_ep_index")
                private String newestEpIndex;
                @SerializedName("is_started")
                private int isStarted;
                @SerializedName("is_finish")
                private String isFinish;
                @SerializedName("newest_ep_id")
                private String newestEpId;
                @SerializedName("total_count")
                private String totalCount;
                @SerializedName("attention")
                private String attention;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getUri() {
                    return uri;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public String getParam() {
                    return param;
                }

                public void setParam(String param) {
                    this.param = param;
                }

                public String getGotoX() {
                    return gotoX;
                }

                public void setGotoX(String gotoX) {
                    this.gotoX = gotoX;
                }

                public int getFinish() {
                    return finish;
                }

                public void setFinish(int finish) {
                    this.finish = finish;
                }

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public int getMtime() {
                    return mtime;
                }

                public void setMtime(int mtime) {
                    this.mtime = mtime;
                }

                public String getNewestEpIndex() {
                    return newestEpIndex;
                }

                public void setNewestEpIndex(String newestEpIndex) {
                    this.newestEpIndex = newestEpIndex;
                }

                public int getIsStarted() {
                    return isStarted;
                }

                public void setIsStarted(int isStarted) {
                    this.isStarted = isStarted;
                }

                public String getIsFinish() {
                    return isFinish;
                }

                public void setIsFinish(String isFinish) {
                    this.isFinish = isFinish;
                }

                public String getNewestEpId() {
                    return newestEpId;
                }

                public void setNewestEpId(String newestEpId) {
                    this.newestEpId = newestEpId;
                }

                public String getTotalCount() {
                    return totalCount;
                }

                public void setTotalCount(String totalCount) {
                    this.totalCount = totalCount;
                }

                public String getAttention() {
                    return attention;
                }

                public void setAttention(String attention) {
                    this.attention = attention;
                }
            }
        }

        public static class CoinArchiveBean {
            @SerializedName("count")
            private int count;
            @SerializedName("item")
            private List<ItemBeanXXX> item;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ItemBeanXXX> getItem() {
                return item;
            }

            public void setItem(List<ItemBeanXXX> item) {
                this.item = item;
            }

            public static class ItemBeanXXX {
                @SerializedName("title")
                private String title;
                @SerializedName("tname")
                private String tname;
                @SerializedName("cover")
                private String cover;
                @SerializedName("uri")
                private String uri;
                @SerializedName("param")
                private String param;
                @SerializedName("goto")
                private String gotoX;
                @SerializedName("length")
                private String length;
                @SerializedName("duration")
                private int duration;
                @SerializedName("play")
                private int play;
                @SerializedName("danmaku")
                private int danmaku;
                @SerializedName("ctime")
                private int ctime;
                @SerializedName("ugc_pay")
                private int ugcPay;

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

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getUri() {
                    return uri;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public String getParam() {
                    return param;
                }

                public void setParam(String param) {
                    this.param = param;
                }

                public String getGotoX() {
                    return gotoX;
                }

                public void setGotoX(String gotoX) {
                    this.gotoX = gotoX;
                }

                public String getLength() {
                    return length;
                }

                public void setLength(String length) {
                    this.length = length;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public int getPlay() {
                    return play;
                }

                public void setPlay(int play) {
                    this.play = play;
                }

                public int getDanmaku() {
                    return danmaku;
                }

                public void setDanmaku(int danmaku) {
                    this.danmaku = danmaku;
                }

                public int getCtime() {
                    return ctime;
                }

                public void setCtime(int ctime) {
                    this.ctime = ctime;
                }

                public int getUgcPay() {
                    return ugcPay;
                }

                public void setUgcPay(int ugcPay) {
                    this.ugcPay = ugcPay;
                }
            }
        }

        public static class LikeArchiveBean {
            @SerializedName("count")
            private int count;
            @SerializedName("item")
            private List<ItemBeanXXXX> item;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ItemBeanXXXX> getItem() {
                return item;
            }

            public void setItem(List<ItemBeanXXXX> item) {
                this.item = item;
            }

            public static class ItemBeanXXXX {
                @SerializedName("title")
                private String title;
                @SerializedName("tname")
                private String tname;
                @SerializedName("cover")
                private String cover;
                @SerializedName("uri")
                private String uri;
                @SerializedName("param")
                private String param;
                @SerializedName("goto")
                private String gotoX;
                @SerializedName("length")
                private String length;
                @SerializedName("duration")
                private int duration;
                @SerializedName("play")
                private int play;
                @SerializedName("danmaku")
                private int danmaku;
                @SerializedName("ctime")
                private int ctime;
                @SerializedName("ugc_pay")
                private int ugcPay;

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

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getUri() {
                    return uri;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public String getParam() {
                    return param;
                }

                public void setParam(String param) {
                    this.param = param;
                }

                public String getGotoX() {
                    return gotoX;
                }

                public void setGotoX(String gotoX) {
                    this.gotoX = gotoX;
                }

                public String getLength() {
                    return length;
                }

                public void setLength(String length) {
                    this.length = length;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public int getPlay() {
                    return play;
                }

                public void setPlay(int play) {
                    this.play = play;
                }

                public int getDanmaku() {
                    return danmaku;
                }

                public void setDanmaku(int danmaku) {
                    this.danmaku = danmaku;
                }

                public int getCtime() {
                    return ctime;
                }

                public void setCtime(int ctime) {
                    this.ctime = ctime;
                }

                public int getUgcPay() {
                    return ugcPay;
                }

                public void setUgcPay(int ugcPay) {
                    this.ugcPay = ugcPay;
                }
            }
        }

        public static class AudiosBean {
            @SerializedName("count")
            private int count;
            @SerializedName("item")
            private List<?> item;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<?> getItem() {
                return item;
            }

            public void setItem(List<?> item) {
                this.item = item;
            }
        }

        public static class Favourite2Bean {
            @SerializedName("count")
            private int count;
            @SerializedName("item")
            private List<ItemBeanXXXXX> item;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ItemBeanXXXXX> getItem() {
                return item;
            }

            public void setItem(List<ItemBeanXXXXX> item) {
                this.item = item;
            }

            public static class ItemBeanXXXXX {
                @SerializedName("media_id")
                private int mediaId;
                @SerializedName("id")
                private int id;
                @SerializedName("mid")
                private int mid;
                @SerializedName("title")
                private String title;
                @SerializedName("cover")
                private String cover;
                @SerializedName("count")
                private int count;
                @SerializedName("type")
                private int type;
                @SerializedName("is_public")
                private int isPublic;
                @SerializedName("ctime")
                private int ctime;
                @SerializedName("mtime")
                private int mtime;

                public int getMediaId() {
                    return mediaId;
                }

                public void setMediaId(int mediaId) {
                    this.mediaId = mediaId;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getMid() {
                    return mid;
                }

                public void setMid(int mid) {
                    this.mid = mid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getIsPublic() {
                    return isPublic;
                }

                public void setIsPublic(int isPublic) {
                    this.isPublic = isPublic;
                }

                public int getCtime() {
                    return ctime;
                }

                public void setCtime(int ctime) {
                    this.ctime = ctime;
                }

                public int getMtime() {
                    return mtime;
                }

                public void setMtime(int mtime) {
                    this.mtime = mtime;
                }
            }
        }
    }
}
