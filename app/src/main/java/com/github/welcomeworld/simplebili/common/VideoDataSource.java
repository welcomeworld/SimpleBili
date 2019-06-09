package com.github.welcomeworld.simplebili.common;

import java.util.List;

public class VideoDataSource {

    private long cid;
    private String title;
    private List<String> descriptions;
    private List<String> videoSources;
    private boolean isDash=false;
    private List<String> audioSources;
    private String danmakuSource;

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public List<String> getVideoSources() {
        return videoSources;
    }

    public void setVideoSources(List<String> videoSources) {
        this.videoSources = videoSources;
    }

    public boolean isDash() {
        return isDash;
    }

    public void setDash(boolean dash) {
        isDash = dash;
    }

    public List<String> getAudioSources() {
        return audioSources;
    }

    public void setAudioSources(List<String> audioSources) {
        this.audioSources = audioSources;
    }

    public String getDanmakuSource() {
        return danmakuSource;
    }

    public void setDanmakuSource(String danmakuSource) {
        this.danmakuSource = danmakuSource;
    }
}
