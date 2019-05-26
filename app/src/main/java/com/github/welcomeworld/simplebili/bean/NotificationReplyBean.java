package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationReplyBean {

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("id")
        private int id;
        @SerializedName("cursor")
        private long cursor;
        @SerializedName("publisher")
        private PublisherBean publisher;
        @SerializedName("type")
        private int type;
        @SerializedName("title")
        private String title;
        @SerializedName("content")
        private String content;
        @SerializedName("source")
        private SourceBean source;
        @SerializedName("ext_info")
        private ExtInfoBean extInfo;
        @SerializedName("time_at")
        private String timeAt;
        @SerializedName("card_type")
        private int cardType;
        @SerializedName("card_brief")
        private String cardBrief;
        @SerializedName("card_msg_brief")
        private String cardMsgBrief;
        @SerializedName("card_cover")
        private String cardCover;
        @SerializedName("card_story_title")
        private String cardStoryTitle;
        @SerializedName("card_link")
        private String cardLink;
        @SerializedName("mc")
        private String mc;
        @SerializedName("is_station")
        private int isStation;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCursor() {
            return cursor;
        }

        public void setCursor(long cursor) {
            this.cursor = cursor;
        }

        public PublisherBean getPublisher() {
            return publisher;
        }

        public void setPublisher(PublisherBean publisher) {
            this.publisher = publisher;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public SourceBean getSource() {
            return source;
        }

        public void setSource(SourceBean source) {
            this.source = source;
        }

        public ExtInfoBean getExtInfo() {
            return extInfo;
        }

        public void setExtInfo(ExtInfoBean extInfo) {
            this.extInfo = extInfo;
        }

        public String getTimeAt() {
            return timeAt;
        }

        public void setTimeAt(String timeAt) {
            this.timeAt = timeAt;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }

        public String getCardBrief() {
            return cardBrief;
        }

        public void setCardBrief(String cardBrief) {
            this.cardBrief = cardBrief;
        }

        public String getCardMsgBrief() {
            return cardMsgBrief;
        }

        public void setCardMsgBrief(String cardMsgBrief) {
            this.cardMsgBrief = cardMsgBrief;
        }

        public String getCardCover() {
            return cardCover;
        }

        public void setCardCover(String cardCover) {
            this.cardCover = cardCover;
        }

        public String getCardStoryTitle() {
            return cardStoryTitle;
        }

        public void setCardStoryTitle(String cardStoryTitle) {
            this.cardStoryTitle = cardStoryTitle;
        }

        public String getCardLink() {
            return cardLink;
        }

        public void setCardLink(String cardLink) {
            this.cardLink = cardLink;
        }

        public String getMc() {
            return mc;
        }

        public void setMc(String mc) {
            this.mc = mc;
        }

        public int getIsStation() {
            return isStation;
        }

        public void setIsStation(int isStation) {
            this.isStation = isStation;
        }

        public static class PublisherBean {
            @SerializedName("name")
            private String name;
            @SerializedName("mid")
            private int mid;
            @SerializedName("face")
            private String face;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }
        }

        public static class SourceBean {
            @SerializedName("name")
            private String name;
            @SerializedName("logo")
            private String logo;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }
        }

        public static class ExtInfoBean {
            @SerializedName("cm_new_url")
            private CmNewUrlBean cmNewUrl;

            public CmNewUrlBean getCmNewUrl() {
                return cmNewUrl;
            }

            public void setCmNewUrl(CmNewUrlBean cmNewUrl) {
                this.cmNewUrl = cmNewUrl;
            }

            public static class CmNewUrlBean {
                @SerializedName("title")
                private String title;
                @SerializedName("content")
                private String content;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }
        }
    }
}
