package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.IndexBangumiBean;
import com.github.welcomeworld.simplebili.bean.IndexLiveBean;
import com.github.welcomeworld.simplebili.bean.IndexPopularBean;
import com.github.welcomeworld.simplebili.bean.IndexRecommendBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IndexNetAPI {
    @GET("x/feed/index")
    Call<IndexRecommendBean> getIndexRecommend();

    @GET("xlive/app-interface/v2/index/getAllList")
    Call<IndexLiveBean> getIndexLive();

    @GET("x/v2/show/popular/index")
    Call<IndexPopularBean> getIndexPopular();

    @GET("pgc/app/page/bangumi")
    Call<IndexBangumiBean> getIndexBangumi();
}
