package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.UpdateBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UpdateNetAPI {
    @GET("welcomeworld/SimpleBili/master/app/release/output.json")
    public Call<UpdateBean> checkUpdate();
}
