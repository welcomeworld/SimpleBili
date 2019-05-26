package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.LoginKeyBean;
import com.github.welcomeworld.simplebili.bean.LoginResultBean;
import com.github.welcomeworld.simplebili.bean.UserInfoMineBean;
import com.github.welcomeworld.simplebili.bean.UserSpaceBean;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserNetAPI {
    @POST("api/v3/oauth2/login")
    public Call<LoginResultBean> login();

    @POST("api/oauth2/getKey")
    public Call<LoginKeyBean> getKey();

    @GET("x/v2/account/mine")
    public Call<UserInfoMineBean> getMine();

    @GET("x/v2/space")
    public Call<UserSpaceBean> getSpace();
}
