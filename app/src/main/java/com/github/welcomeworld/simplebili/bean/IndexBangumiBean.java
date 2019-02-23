package com.github.welcomeworld.simplebili.bean;

import java.util.List;

public class IndexBangumiBean {

    private int code;
    private String message;
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
        private List<ModulesBean> modules;
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
            private AttrBean attr;
            private int module_id;
            private int size;
            private String style;
            private String title;
            private List<HeadersBean> headers;
            private List<ItemsBean> items;
            private List<Integer> wid;

            public AttrBean getAttr() {
                return attr;
            }

            public void setAttr(AttrBean attr) {
                this.attr = attr;
            }

            public int getModule_id() {
                return module_id;
            }

            public void setModule_id(int module_id) {
                this.module_id = module_id;
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
                private int follow;
                private int header;
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
                private String icon;
                private String title;
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
                private String badge;
                private int badge_type;
                private String cover;
                private String desc;
                private int is_auto;
                private String link;
                private int season_id;
                private int season_type;
                private StatBean stat;
                private StatusBean status;
                private String title;
                private int wid;

                public String getBadge() {
                    return badge;
                }

                public void setBadge(String badge) {
                    this.badge = badge;
                }

                public int getBadge_type() {
                    return badge_type;
                }

                public void setBadge_type(int badge_type) {
                    this.badge_type = badge_type;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getIs_auto() {
                    return is_auto;
                }

                public void setIs_auto(int is_auto) {
                    this.is_auto = is_auto;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public int getSeason_id() {
                    return season_id;
                }

                public void setSeason_id(int season_id) {
                    this.season_id = season_id;
                }

                public int getSeason_type() {
                    return season_type;
                }

                public void setSeason_type(int season_type) {
                    this.season_type = season_type;
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
                    private int danmaku;
                    private int follow;
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
            private String icon;
            private String title;
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
