package com.github.welcomeworld.simplebili.bean;

import java.util.List;

public class MyBangumiBean {

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
        private int follow;
        private int follows_type;
        private int update;
        private List<?> delay;
        private List<FollowsBean> follows;

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getFollows_type() {
            return follows_type;
        }

        public void setFollows_type(int follows_type) {
            this.follows_type = follows_type;
        }

        public int getUpdate() {
            return update;
        }

        public void setUpdate(int update) {
            this.update = update;
        }

        public List<?> getDelay() {
            return delay;
        }

        public void setDelay(List<?> delay) {
            this.delay = delay;
        }

        public List<FollowsBean> getFollows() {
            return follows;
        }

        public void setFollows(List<FollowsBean> follows) {
            this.follows = follows;
        }

        public static class FollowsBean {
            private String badge;
            private int badge_type;
            private String cover;
            private int is_finish;
            private int is_started;
            private NewEpBean new_ep;
            private ProgressBean progress;
            private int season_id;
            private String title;
            private int total_count;
            private String url;

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

            public int getIs_finish() {
                return is_finish;
            }

            public void setIs_finish(int is_finish) {
                this.is_finish = is_finish;
            }

            public int getIs_started() {
                return is_started;
            }

            public void setIs_started(int is_started) {
                this.is_started = is_started;
            }

            public NewEpBean getNew_ep() {
                return new_ep;
            }

            public void setNew_ep(NewEpBean new_ep) {
                this.new_ep = new_ep;
            }

            public ProgressBean getProgress() {
                return progress;
            }

            public void setProgress(ProgressBean progress) {
                this.progress = progress;
            }

            public int getSeason_id() {
                return season_id;
            }

            public void setSeason_id(int season_id) {
                this.season_id = season_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getTotal_count() {
                return total_count;
            }

            public void setTotal_count(int total_count) {
                this.total_count = total_count;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public static class NewEpBean {
                private String cover;
                private int id;
                private String index_show;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIndex_show() {
                    return index_show;
                }

                public void setIndex_show(String index_show) {
                    this.index_show = index_show;
                }
            }

            public static class ProgressBean {
                private String last_ep_desc;
                private int last_ep_id;
                private String last_ep_index;
                private int last_time;

                public String getLast_ep_desc() {
                    return last_ep_desc;
                }

                public void setLast_ep_desc(String last_ep_desc) {
                    this.last_ep_desc = last_ep_desc;
                }

                public int getLast_ep_id() {
                    return last_ep_id;
                }

                public void setLast_ep_id(int last_ep_id) {
                    this.last_ep_id = last_ep_id;
                }

                public String getLast_ep_index() {
                    return last_ep_index;
                }

                public void setLast_ep_index(String last_ep_index) {
                    this.last_ep_index = last_ep_index;
                }

                public int getLast_time() {
                    return last_time;
                }

                public void setLast_time(int last_time) {
                    this.last_time = last_time;
                }
            }
        }
    }
}
