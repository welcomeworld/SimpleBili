package com.github.welcomeworld.simplebili.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.LiveMasterUserInfoBean;
import com.github.welcomeworld.simplebili.bean.LiveRelatedBean;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.LiveDetailNetAPI;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveUpInfoFragment extends Fragment {

    @BindView(R.id.live_upinfo_avator)
    ImageView avatorView;
    @BindView(R.id.live_upinfo_upname)
    TextView upnameView;
    @BindView(R.id.live_upinfo_roomnum)
    TextView roomnumView;
    @BindView(R.id.live_upinfo_follownum)
    TextView follownumView;
    @BindView(R.id.live_upinfo_glory)
    TextView gloryView;
    @BindView(R.id.live_upinfo_medal)
    TextView medalView;
    @BindView(R.id.live_upinfo_desc)
    TextView descView;
    @BindView(R.id.live_upinfo_notice)
    TextView noticeView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.fragment_live_upinfo,container,false);
        ButterKnife.bind(this,contentView);
        Map<String,String> parameters=new HashMap<>();
        parameters.put("actionKey","appkey");
        parameters.put("uid",""+getArguments().getInt("upId"));
        parameters.put("channel","bili");
        parameters.put("ts",""+System.currentTimeMillis());
        OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                .addInterceptor(new FixedHeaderInterceptor())
                .addInterceptor(new DynamicHeaderInterceptor(null))
                .addInterceptor(new FixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters))
                .addInterceptor(new SortAndSignInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.APILIVEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
        LiveDetailNetAPI liveDetailNetAPI=retrofit.create(LiveDetailNetAPI.class);
        Call<LiveMasterUserInfoBean> masterUserInfoBeanCall=liveDetailNetAPI.getRoomMasterUserInfo();
        masterUserInfoBeanCall.enqueue(new Callback<LiveMasterUserInfoBean>() {
            @Override
            public void onResponse(Call<LiveMasterUserInfoBean> call, Response<LiveMasterUserInfoBean> response) {
                LiveMasterUserInfoBean data=response.body();
                if(data!=null){
                    Glide.with(getContext()).load(data.getData().getInfo().getFace()+"@200w_200h_1e_1c.webp").apply(RequestOptions.circleCropTransform()).into(avatorView);
                    upnameView.setText(data.getData().getInfo().getUname());
                    roomnumView.setText("房间号： "+data.getData().getRoomId());
                    follownumView.setText("粉丝： "+data.getData().getFollowerNum());
                    if(data.getData().getGloryCount()>0){
                        gloryView.setText(data.getData().getGloryCount()+" 主播荣耀");
                    }
                    if(data.getData().getMedalName()!=null){
                        medalView.setText("已开通粉丝勋章:"+data.getData().getMedalName());
                    }
                    if(data.getData().getInfo().getOfficialVerify()!=null){
                        descView.setText(data.getData().getInfo().getOfficialVerify().getDesc());
                    }
                    noticeView.setText(""+data.getData().getRoomNews().getContent());
            }
            }

            @Override
            public void onFailure(Call<LiveMasterUserInfoBean> call, Throwable t) {

            }
        });
        return contentView;
    }
}
