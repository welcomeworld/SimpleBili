package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IndexPopularBean {

    private int code;
    private ConfigBean config;
    private String message;
    private String ver;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean config) {
        this.config = config;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ConfigBean {
        private String item_title;
        private String bottom_text;
        private String bottom_text_cover;
        private String bottom_text_url;

        public String getItem_title() {
            return item_title;
        }

        public void setItem_title(String item_title) {
            this.item_title = item_title;
        }

        public String getBottom_text() {
            return bottom_text;
        }

        public void setBottom_text(String bottom_text) {
            this.bottom_text = bottom_text;
        }

        public String getBottom_text_cover() {
            return bottom_text_cover;
        }

        public void setBottom_text_cover(String bottom_text_cover) {
            this.bottom_text_cover = bottom_text_cover;
        }

        public String getBottom_text_url() {
            return bottom_text_url;
        }

        public void setBottom_text_url(String bottom_text_url) {
            this.bottom_text_url = bottom_text_url;
        }
    }

    public static class DataBean {

        private String card_type;
        private String card_goto;
        @SerializedName("goto")
        private String gotoX;
        private String param;
        private String cover;
        private String title;
        private String uri;
        private ThreePointBean three_point;
        private ArgsBean args;
        private int idx;
        private String from_type;
        private String cover_right_text_1;
        private String right_desc_1;
        private String right_desc_2;
        private RcmdReasonStyleBean rcmd_reason_style;
        private List<ThreePointV2Bean> three_point_v2;

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getCard_goto() {
            return card_goto;
        }

        public void setCard_goto(String card_goto) {
            this.card_goto = card_goto;
        }

        public String getGotoX() {
            return gotoX;
        }

        public void setGotoX(String gotoX) {
            this.gotoX = gotoX;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public ThreePointBean getThree_point() {
            return three_point;
        }

        public void setThree_point(ThreePointBean three_point) {
            this.three_point = three_point;
        }

        public ArgsBean getArgs() {
            return args;
        }

        public void setArgs(ArgsBean args) {
            this.args = args;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public String getFrom_type() {
            return from_type;
        }

        public void setFrom_type(String from_type) {
            this.from_type = from_type;
        }

        public String getCover_right_text_1() {
            return cover_right_text_1;
        }

        public void setCover_right_text_1(String cover_right_text_1) {
            this.cover_right_text_1 = cover_right_text_1;
        }

        public String getRight_desc_1() {
            return right_desc_1;
        }

        public void setRight_desc_1(String right_desc_1) {
            this.right_desc_1 = right_desc_1;
        }

        public String getRight_desc_2() {
            return right_desc_2;
        }

        public void setRight_desc_2(String right_desc_2) {
            this.right_desc_2 = right_desc_2;
        }

        public RcmdReasonStyleBean getRcmd_reason_style() {
            return rcmd_reason_style;
        }

        public void setRcmd_reason_style(RcmdReasonStyleBean rcmd_reason_style) {
            this.rcmd_reason_style = rcmd_reason_style;
        }

        public List<ThreePointV2Bean> getThree_point_v2() {
            return three_point_v2;
        }

        public void setThree_point_v2(List<ThreePointV2Bean> three_point_v2) {
            this.three_point_v2 = three_point_v2;
        }

        public static class ThreePointBean {
        }

        public static class ArgsBean {
        }

        public static class RcmdReasonStyleBean {
            private String text;
            private String text_color;
            private String bg_color;
            private String border_color;
            private int bg_style;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getText_color() {
                return text_color;
            }

            public void setText_color(String text_color) {
                this.text_color = text_color;
            }

            public String getBg_color() {
                return bg_color;
            }

            public void setBg_color(String bg_color) {
                this.bg_color = bg_color;
            }

            public String getBorder_color() {
                return border_color;
            }

            public void setBorder_color(String border_color) {
                this.border_color = border_color;
            }

            public int getBg_style() {
                return bg_style;
            }

            public void setBg_style(int bg_style) {
                this.bg_style = bg_style;
            }
        }

        public static class ThreePointV2Bean {
            private String title;
            private String type;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
