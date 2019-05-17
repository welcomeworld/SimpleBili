package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IndexBangumiBean {

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
        @SerializedName("modules")
        private List<ModulesBean> modules;
        @SerializedName("regions")
        private List<RegionsBean> regions;

        public List<ModulesBean> getModules() {
            return modules;
        }

        public void setModules(List<ModulesBean> modules) {
            this.modules = modules;
        }

        public List<RegionsBean> getRegions() {
            return regions;
        }

        public void setRegions(List<RegionsBean> regions) {
            this.regions = regions;
        }

        public static class ModulesBean {
            @SerializedName("attr")
            private AttrBean attr;
            @SerializedName("module_id")
            private int moduleId;
            @SerializedName("size")
            private int size;
            @SerializedName("style")
            private String style;
            @SerializedName("title")
            private String title;
            @SerializedName("type")
            private int type;
            @SerializedName("headers")
            private List<HeadersBean> headers;
            @SerializedName("items")
            private List<ItemsBean> items;
            @SerializedName("wid")
            private List<Integer> wid;

            public AttrBean getAttr() {
                return attr;
            }

            public void setAttr(AttrBean attr) {
                this.attr = attr;
            }

            public int getModuleId() {
                return moduleId;
            }

            public void setModuleId(int moduleId) {
                this.moduleId = moduleId;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<HeadersBean> getHeaders() {
                return headers;
            }

            public void setHeaders(List<HeadersBean> headers) {
                this.headers = headers;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public List<Integer> getWid() {
                return wid;
            }

            public void setWid(List<Integer> wid) {
                this.wid = wid;
            }

            public static class AttrBean {
                @SerializedName("follow")
                private int follow;
                @SerializedName("header")
                private int header;
                @SerializedName("random")
                private int random;

                public int getFollow() {
                    return follow;
                }

                public void setFollow(int follow) {
                    this.follow = follow;
                }

                public int getHeader() {
                    return header;
                }

                public void setHeader(int header) {
                    this.header = header;
                }

                public int getRandom() {
                    return random;
                }

                public void setRandom(int random) {
                    this.random = random;
                }
            }

            public static class HeadersBean {
                @SerializedName("icon")
                private String icon;
                @SerializedName("title")
                private String title;
                @SerializedName("url")
                private String url;

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
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
            }

            public static class ItemsBean {
                @SerializedName("badge")
                private String badge;
                @SerializedName("badge_type")
                private int badgeType;
                @SerializedName("cover")
                private String cover;
                @SerializedName("cursor")
                private String cursor;
                @SerializedName("desc")
                private String desc;
                @SerializedName("is_auto")
                private int isAuto;
                @SerializedName("is_new")
                private int isNew;
                @SerializedName("link")
                private String link;
                @SerializedName("season_id")
                private int seasonId;
                @SerializedName("season_type")
                private int seasonType;
                @SerializedName("stat")
                private StatBean stat;
                @SerializedName("status")
                private StatusBean status;
                @SerializedName("title")
                private String title;
                @SerializedName("wid")
                private int wid;

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

                public String getCursor() {
                    return cursor;
                }

                public void setCursor(String cursor) {
                    this.cursor = cursor;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getIsAuto() {
                    return isAuto;
                }

                public void setIsAuto(int isAuto) {
                    this.isAuto = isAuto;
                }

                public int getIsNew() {
                    return isNew;
                }

                public void setIsNew(int isNew) {
                    this.isNew = isNew;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
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

                public StatusBean getStatus() {
                    return status;
                }

                public void setStatus(StatusBean status) {
                    this.status = status;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getWid() {
                    return wid;
                }

                public void setWid(int wid) {
                    this.wid = wid;
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

                public static class StatusBean {
                    @SerializedName("follow")
                    private int follow;

                    public int getFollow() {
                        return follow;
                    }

                    public void setFollow(int follow) {
                        this.follow = follow;
                    }
                }
            }
        }

        public static class RegionsBean {
            @SerializedName("icon")
            private String icon;
            @SerializedName("title")
            private String title;
            @SerializedName("url")
            private String url;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
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
        }
    }
}
