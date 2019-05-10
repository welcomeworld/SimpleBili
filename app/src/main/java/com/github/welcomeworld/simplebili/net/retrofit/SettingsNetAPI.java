package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.PingIpBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SettingsNetAPI {
    @GET("x/v2/display/zone")
    public Call<PingIpBean> getIpInfo();
}
