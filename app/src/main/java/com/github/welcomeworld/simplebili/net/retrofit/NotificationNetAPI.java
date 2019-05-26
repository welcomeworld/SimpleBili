package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.NotificationReplyBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NotificationNetAPI {

    @GET("api/notify/query.replyme.list.do")
    Call<NotificationReplyBean> getReplyMe();

    @GET("api/notify/query.atme.list.do")
    Call<NotificationReplyBean> getAtMe();

    @GET("api/notify/query.praiseme.list.do")
    Call<NotificationReplyBean> getPraiseMe();

}
