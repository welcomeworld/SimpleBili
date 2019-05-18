package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IndexDynamicBean {

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
        @SerializedName("new_num")
        private int newNum;
        @SerializedName("exist_gap")
        private int existGap;
        @SerializedName("update_num")
        private int updateNum;
        @SerializedName("open_rcmd")
        private int openRcmd;
        @SerializedName("extra_flag")
        private ExtraFlagBean extraFlag;
        @SerializedName("attentions")
        private AttentionsBean attentions;
        @SerializedName("_gt_")
        private int gt;
        @SerializedName("cards")
        private List<CardsBean> cards;

        public int getNewNum() {
            return newNum;
        }

        public void setNewNum(int newNum) {
            this.newNum = newNum;
        }

        public int getExistGap() {
            return existGap;
        }

        public void setExistGap(int existGap) {
            this.existGap = existGap;
        }

        public int getUpdateNum() {
            return updateNum;
        }

        public void setUpdateNum(int updateNum) {
            this.updateNum = updateNum;
        }

        public int getOpenRcmd() {
            return openRcmd;
        }

        public void setOpenRcmd(int openRcmd) {
            this.openRcmd = openRcmd;
        }

        public ExtraFlagBean getExtraFlag() {
            return extraFlag;
        }

        public void setExtraFlag(ExtraFlagBean extraFlag) {
            this.extraFlag = extraFlag;
        }

        public AttentionsBean getAttentions() {
            return attentions;
        }

        public void setAttentions(AttentionsBean attentions) {
            this.attentions = attentions;
        }

        public int getGt() {
            return gt;
        }

        public void setGt(int gt) {
            this.gt = gt;
        }

        public List<CardsBean> getCards() {
            return cards;
        }

        public void setCards(List<CardsBean> cards) {
            this.cards = cards;
        }

        public static class ExtraFlagBean {
            @SerializedName("great_dynamic")
            private int greatDynamic;

            public int getGreatDynamic() {
                return greatDynamic;
            }

            public void setGreatDynamic(int greatDynamic) {
                this.greatDynamic = greatDynamic;
            }
        }

        public static class AttentionsBean {
            @SerializedName("uids")
            private List<Integer> uids;
            @SerializedName("bangumis")
            private List<BangumisBean> bangumis;

            public List<Integer> getUids() {
                return uids;
            }

            public void setUids(List<Integer> uids) {
                this.uids = uids;
            }

            public List<BangumisBean> getBangumis() {
                return bangumis;
            }

            public void setBangumis(List<BangumisBean> bangumis) {
                this.bangumis = bangumis;
            }

            public static class BangumisBean {
                @SerializedName("season_id")
                private int seasonId;
                @SerializedName("type")
                private int type;

                public int getSeasonId() {
                    return seasonId;
                }

                public void setSeasonId(int seasonId) {
                    this.seasonId = seasonId;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }
        }

        public static class CardsBean {
            @SerializedName("desc")
            private DescBean desc;
            @SerializedName("card")
            private String card;
            @SerializedName("extend_json")
            private String extendJson;

            public DescBean getDesc() {
                return desc;
            }

            public void setDesc(DescBean desc) {
                this.desc = desc;
            }

            public String getCard() {
                return card;
            }

            public void setCard(String card) {
                this.card = card;
            }

            public String getExtendJson() {
                return extendJson;
            }

            public void setExtendJson(String extendJson) {
                this.extendJson = extendJson;
            }

            public static class DescBean {
                @SerializedName("uid")
                private int uid;
                @SerializedName("type")
                private int type;
                @SerializedName("rid")
                private int rid;
                @SerializedName("acl")
                private int acl;
                @SerializedName("view")
                private int view;
                @SerializedName("repost")
                private int repost;
                @SerializedName("like")
                private int like;
                @SerializedName("is_liked")
                private int isLiked;
                @SerializedName("dynamic_id")
                private long dynamicId;
                @SerializedName("timestamp")
                private int timestamp;
                @SerializedName("pre_dy_id")
                private int preDyId;
                @SerializedName("orig_dy_id")
                private int origDyId;
                @SerializedName("orig_type")
                private int origType;
                @SerializedName("user_profile")
                private UserProfileBean userProfile;
                @SerializedName("stype")
                private int stype;
                @SerializedName("r_type")
                private int rType;
                @SerializedName("inner_id")
                private int innerId;
                @SerializedName("status")
                private int status;
                @SerializedName("dynamic_id_str")
                private String dynamicIdStr;

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getRid() {
                    return rid;
                }

                public void setRid(int rid) {
                    this.rid = rid;
                }

                public int getAcl() {
                    return acl;
                }

                public void setAcl(int acl) {
                    this.acl = acl;
                }

                public int getView() {
                    return view;
                }

                public void setView(int view) {
                    this.view = view;
                }

                public int getRepost() {
                    return repost;
                }

                public void setRepost(int repost) {
                    this.repost = repost;
                }

                public int getLike() {
                    return like;
                }

                public void setLike(int like) {
                    this.like = like;
                }

                public int getIsLiked() {
                    return isLiked;
                }

                public void setIsLiked(int isLiked) {
                    this.isLiked = isLiked;
                }

                public long getDynamicId() {
                    return dynamicId;
                }

                public void setDynamicId(long dynamicId) {
                    this.dynamicId = dynamicId;
                }

                public int getTimestamp() {
                    return timestamp;
                }

                public void setTimestamp(int timestamp) {
                    this.timestamp = timestamp;
                }

                public int getPreDyId() {
                    return preDyId;
                }

                public void setPreDyId(int preDyId) {
                    this.preDyId = preDyId;
                }

                public int getOrigDyId() {
                    return origDyId;
                }

                public void setOrigDyId(int origDyId) {
                    this.origDyId = origDyId;
                }

                public int getOrigType() {
                    return origType;
                }

                public void setOrigType(int origType) {
                    this.origType = origType;
                }

                public UserProfileBean getUserProfile() {
                    return userProfile;
                }

                public void setUserProfile(UserProfileBean userProfile) {
                    this.userProfile = userProfile;
                }

                public int getStype() {
                    return stype;
                }

                public void setStype(int stype) {
                    this.stype = stype;
                }

                public int getRType() {
                    return rType;
                }

                public void setRType(int rType) {
                    this.rType = rType;
                }

                public int getInnerId() {
                    return innerId;
                }

                public void setInnerId(int innerId) {
                    this.innerId = innerId;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getDynamicIdStr() {
                    return dynamicIdStr;
                }

                public void setDynamicIdStr(String dynamicIdStr) {
                    this.dynamicIdStr = dynamicIdStr;
                }

                public static class UserProfileBean {
                    @SerializedName("info")
                    private InfoBean info;
                    @SerializedName("card")
                    private CardBean card;
                    @SerializedName("vip")
                    private VipBean vip;
                    @SerializedName("pendant")
                    private PendantBean pendant;
                    @SerializedName("rank")
                    private String rank;
                    @SerializedName("sign")
                    private String sign;
                    @SerializedName("level_info")
                    private LevelInfoBean levelInfo;

                    public InfoBean getInfo() {
                        return info;
                    }

                    public void setInfo(InfoBean info) {
                        this.info = info;
                    }

                    public CardBean getCard() {
                        return card;
                    }

                    public void setCard(CardBean card) {
                        this.card = card;
                    }

                    public VipBean getVip() {
                        return vip;
                    }

                    public void setVip(VipBean vip) {
                        this.vip = vip;
                    }

                    public PendantBean getPendant() {
                        return pendant;
                    }

                    public void setPendant(PendantBean pendant) {
                        this.pendant = pendant;
                    }

                    public String getRank() {
                        return rank;
                    }

                    public void setRank(String rank) {
                        this.rank = rank;
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

                    public static class InfoBean {
                        @SerializedName("uid")
                        private int uid;
                        @SerializedName("uname")
                        private String uname;
                        @SerializedName("face")
                        private String face;

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
                    }

                    public static class CardBean {
                        @SerializedName("official_verify")
                        private OfficialVerifyBean officialVerify;

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

                    public static class LevelInfoBean {
                        @SerializedName("current_level")
                        private int currentLevel;
                        @SerializedName("current_min")
                        private int currentMin;
                        @SerializedName("current_exp")
                        private int currentExp;
                        @SerializedName("next_exp")
                        private String nextExp;

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

                        public String getNextExp() {
                            return nextExp;
                        }

                        public void setNextExp(String nextExp) {
                            this.nextExp = nextExp;
                        }
                    }
                }
            }
        }
    }
}
