package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.FavoriteBangumiBean;
import com.github.welcomeworld.simplebili.bean.FavoriteCinemaBean;
import com.github.welcomeworld.simplebili.bean.FavoriteDefaultBean;
import com.github.welcomeworld.simplebili.bean.FavoriteListBean;
import com.github.welcomeworld.simplebili.bean.FavoriteTopicBean;
import com.github.welcomeworld.simplebili.bean.WatchLaterBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FavoriteNetAPI {
    @GET("x/v2/favorite")
    public Call<FavoriteDefaultBean> getFavoriteData();

    @GET("pgc/app/follow/bangumi")
    public Call<FavoriteBangumiBean> getFollowBangumi();

    @GET("follow/api/list/mine")
    public Call<FavoriteCinemaBean> getFollowCinema();

    @GET("x/v2/favorite/topic")
    public Call<FavoriteTopicBean> getFollowTopic();

    @GET("x/v2/favorite/video")
    public Call<FavoriteListBean> getFavoriteVideoList();

    @GET("x/v2/history/toview")
    public Call<WatchLaterBean> getWatchLaterList();
}
