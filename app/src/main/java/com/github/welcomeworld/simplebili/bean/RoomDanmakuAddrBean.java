package com.github.welcomeworld.simplebili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomDanmakuAddrBean {

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
        @SerializedName("refresh_row_factor")
        private double refreshRowFactor;
        @SerializedName("refresh_rate")
        private int refreshRate;
        @SerializedName("max_delay")
        private int maxDelay;
        @SerializedName("port")
        private int port;
        @SerializedName("host")
        private String host;
        @SerializedName("host_server_list")
        private List<HostServerListBean> hostServerList;
        @SerializedName("server_list")
        private List<ServerListBean> serverList;

        public double getRefreshRowFactor() {
            return refreshRowFactor;
        }

        public void setRefreshRowFactor(double refreshRowFactor) {
            this.refreshRowFactor = refreshRowFactor;
        }

        public int getRefreshRate() {
            return refreshRate;
        }

        public void setRefreshRate(int refreshRate) {
            this.refreshRate = refreshRate;
        }

        public int getMaxDelay() {
            return maxDelay;
        }

        public void setMaxDelay(int maxDelay) {
            this.maxDelay = maxDelay;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public List<HostServerListBean> getHostServerList() {
            return hostServerList;
        }

        public void setHostServerList(List<HostServerListBean> hostServerList) {
            this.hostServerList = hostServerList;
        }

        public List<ServerListBean> getServerList() {
            return serverList;
        }

        public void setServerList(List<ServerListBean> serverList) {
            this.serverList = serverList;
        }

        public static class HostServerListBean {
            @SerializedName("host")
            private String host;
            @SerializedName("port")
            private int port;
            @SerializedName("wss_port")
            private int wssPort;
            @SerializedName("ws_port")
            private int wsPort;

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public int getWssPort() {
                return wssPort;
            }

            public void setWssPort(int wssPort) {
                this.wssPort = wssPort;
            }

            public int getWsPort() {
                return wsPort;
            }

            public void setWsPort(int wsPort) {
                this.wsPort = wsPort;
            }
        }

        public static class ServerListBean {
            @SerializedName("host")
            private String host;
            @SerializedName("port")
            private int port;

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }
        }
    }
}
