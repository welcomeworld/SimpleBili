package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.VideoDetailPageBean;
import com.github.welcomeworld.simplebili.bean.VideoUrlBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface VideoDetailNetAPI {
    @GET("x/v2/view")
    public Call<VideoDetailPageBean> getVideoDetailPageInfo();

    @Streaming
    @GET("x/playurl")
    public Call<VideoUrlBean> getVideoUrl();
}
