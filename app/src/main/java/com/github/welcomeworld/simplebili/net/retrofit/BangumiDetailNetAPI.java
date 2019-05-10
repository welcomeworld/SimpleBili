package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.BangumiDetailPageBean;
import com.github.welcomeworld.simplebili.bean.BangumiDetailRecommendBean;
import com.github.welcomeworld.simplebili.bean.BangumiUrlBean;
import com.github.welcomeworld.simplebili.bean.ReplyCursorBean;
import com.github.welcomeworld.simplebili.bean.VideoDetailPageBean;
import com.github.welcomeworld.simplebili.bean.VideoUrlBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface BangumiDetailNetAPI {
    @GET("pgc/view/app/season")
    public Call<BangumiDetailPageBean> getBangumiDetailPageInfo();

    @Streaming
    @GET("pgc/player/api/playurl")
    public Call<BangumiUrlBean> getBangumiUrl();

    @GET("x/v2/reply/cursor")
    public Call<ReplyCursorBean> getVideoReply();

    @GET("pgc/season/app/related/recommend")
    public Call<BangumiDetailRecommendBean> getBangumiRecommend();
}
