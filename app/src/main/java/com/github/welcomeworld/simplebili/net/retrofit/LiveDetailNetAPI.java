package com.github.welcomeworld.simplebili.net.retrofit;

import com.github.welcomeworld.simplebili.bean.LiveMasterUserInfoBean;
import com.github.welcomeworld.simplebili.bean.LiveRelatedBean;
import com.github.welcomeworld.simplebili.bean.RoomHistoryDanmakuBean;
import com.github.welcomeworld.simplebili.bean.RoomInfoBean;
import com.github.welcomeworld.simplebili.bean.RoomStatusBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LiveDetailNetAPI {

    @GET("room/v1/RoomRecommend/getRelativeList")
    public Call<LiveRelatedBean> getRoomRelated();


    @GET("live_user/v1/Master/info")
    public Call<LiveMasterUserInfoBean> getRoomMasterUserInfo();


    @GET("room/v1/Room/mobileRoomInit")
    public Call<RoomStatusBean> getRoomInitStatus();

    @GET("room/v1/Room/get_info")
    public Call<RoomInfoBean> getRoomInfo();

    @GET("AppRoom/msg")
    public Call<RoomHistoryDanmakuBean> getRoomHisttoryDanmaku();

}
