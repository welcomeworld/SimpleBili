package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.IndexRecommendBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IndexNetAPI {
    @GET("x/feed/index")
    Call<IndexRecommendBean> getIndexRecommend();


}
