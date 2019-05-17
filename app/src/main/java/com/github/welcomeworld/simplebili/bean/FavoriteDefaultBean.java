package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FavoriteDefaultBean {

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

    public static class DataBean implements Serializable{
        @SerializedName("tab")
        private TabBean tab;
        @SerializedName("favorite")
        private FavoriteBean favorite;

        public TabBean getTab() {
            return tab;
        }

        public void setTab(TabBean tab) {
            this.tab = tab;
        }

        public FavoriteBean getFavorite() {
            return favorite;
        }

        public void setFavorite(FavoriteBean favorite) {
            this.favorite = favorite;
        }

        public static class TabBean implements Serializable{
            @SerializedName("favorite")
            private boolean favorite;
            @SerializedName("topic")
            private boolean topic;
            @SerializedName("article")
            private boolean article;
            @SerializedName("clips")
            private boolean clips;
            @SerializedName("albums")
            private boolean albums;
            @SerializedName("specil")
            private boolean specil;
            @SerializedName("cinema")
            private boolean cinema;
            @SerializedName("audios")
            private boolean audios;
            @SerializedName("menu")
            private boolean menu;
            @SerializedName("pgc_menu")
            private boolean pgcMenu;
            @SerializedName("ticket")
            private boolean ticket;
            @SerializedName("product")
            private boolean product;

            public boolean isFavorite() {
                return favorite;
            }

            public void setFavorite(boolean favorite) {
                this.favorite = favorite;
            }

            public boolean isTopic() {
                return topic;
            }

            public void setTopic(boolean topic) {
                this.topic = topic;
            }

            public boolean isArticle() {
                return article;
            }

            public void setArticle(boolean article) {
                this.article = article;
            }

            public boolean isClips() {
                return clips;
            }

            public void setClips(boolean clips) {
                this.clips = clips;
            }

            public boolean isAlbums() {
                return albums;
            }

            public void setAlbums(boolean albums) {
                this.albums = albums;
            }

            public boolean isSpecil() {
                return specil;
            }

            public void setSpecil(boolean specil) {
                this.specil = specil;
            }

            public boolean isCinema() {
                return cinema;
            }

            public void setCinema(boolean cinema) {
                this.cinema = cinema;
            }

            public boolean isAudios() {
                return audios;
            }

            public void setAudios(boolean audios) {
                this.audios = audios;
            }

            public boolean isMenu() {
                return menu;
            }

            public void setMenu(boolean menu) {
                this.menu = menu;
            }

            public boolean isPgcMenu() {
                return pgcMenu;
            }

            public void setPgcMenu(boolean pgcMenu) {
                this.pgcMenu = pgcMenu;
            }

            public boolean isTicket() {
                return ticket;
            }

            public void setTicket(boolean ticket) {
                this.ticket = ticket;
            }

            public boolean isProduct() {
                return product;
            }

            public void setProduct(boolean product) {
                this.product = product;
            }
        }

        public static class FavoriteBean implements Serializable{
            @SerializedName("count")
            private int count;
            @SerializedName("items")
            private List<ItemsBean> items;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                @SerializedName("media_id")
                private int mediaId;
                @SerializedName("fid")
                private int fid;
                @SerializedName("mid")
                private int mid;
                @SerializedName("name")
                private String name;
                @SerializedName("cur_count")
                private int curCount;
                @SerializedName("state")
                private int state;
                @SerializedName("cover")
                private List<CoverBean> cover;

                public int getMediaId() {
                    return mediaId;
                }

                public void setMediaId(int mediaId) {
                    this.mediaId = mediaId;
                }

                public int getFid() {
                    return fid;
                }

                public void setFid(int fid) {
                    this.fid = fid;
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

                public int getCurCount() {
                    return curCount;
                }

                public void setCurCount(int curCount) {
                    this.curCount = curCount;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public List<CoverBean> getCover() {
                    return cover;
                }

                public void setCover(List<CoverBean> cover) {
                    this.cover = cover;
                }

                public static class CoverBean {
                    @SerializedName("aid")
                    private int aid;
                    @SerializedName("pic")
                    private String pic;
                    @SerializedName("type")
                    private int type;

                    public int getAid() {
                        return aid;
                    }

                    public void setAid(int aid) {
                        this.aid = aid;
                    }

                    public String getPic() {
                        return pic;
                    }

                    public void setPic(String pic) {
                        this.pic = pic;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }
                }
            }
        }
    }
}
