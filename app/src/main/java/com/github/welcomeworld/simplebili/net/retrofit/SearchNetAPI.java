package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.SearchResultBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SearchNetAPI {
    @GET("x/v2/search")
    public Call<SearchResultBean> getSearchResult();
}
