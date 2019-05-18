package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.CategoryListBean;
import com.github.welcomeworld.simplebili.bean.IndexBangumiBean;
import com.github.welcomeworld.simplebili.bean.IndexDynamicBean;
import com.github.welcomeworld.simplebili.bean.IndexLiveBean;
import com.github.welcomeworld.simplebili.bean.IndexPopularBean;
import com.github.welcomeworld.simplebili.bean.IndexRecommendBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface IndexNetAPI {
    @GET("x/feed/index")
    Call<IndexRecommendBean> getIndexRecommend();

    @GET("xlive/app-interface/v2/index/getAllList")
    Call<IndexLiveBean> getIndexLive();

    @GET("x/v2/show/popular/index")
    Call<IndexPopularBean> getIndexPopular();

    @GET("pgc/app/page/bangumi")
    Call<IndexBangumiBean> getIndexBangumi();

    @GET("x/v2/region/show/dynamic")
    Call<CategoryListBean> getCategoryList();

    @GET("dynamic_svr/v1/dynamic_svr/dynamic_new")
    Call<IndexDynamicBean> getIndexDynamic();

}
