package com.github.welcomeworld.simplebili.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.adapter.IndexLiveRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.adapter.IndexRecommendRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.adapter.LiveDetailRelatedRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.bean.IndexLiveBean;
import com.github.welcomeworld.simplebili.bean.LiveRelatedBean;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.IndexNetAPI;
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

public class LiveRelatedFragment extends Fragment {

    @BindView(R.id.live_related_recyclerview)
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.fragment_live_related,container,false);
        ButterKnife.bind(this,contentView);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(recyclerView.getContext(),2,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new LiveDetailRelatedRecyclerViewAdapter(null));

        Map<String,String> parameters=new HashMap<>();
        parameters.put("actionKey","appkey");
        parameters.put("area_v2_id",""+getArguments().getInt("areaId"));
        parameters.put("channel","bili");
        parameters.put("page","1");
        parameters.put("page_size","20");
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
        Call<LiveRelatedBean> liveRelatedBeanCall=liveDetailNetAPI.getRoomRelated();
        liveRelatedBeanCall.enqueue(new Callback<LiveRelatedBean>() {
            @Override
            public void onResponse(Call<LiveRelatedBean> call, Response<LiveRelatedBean> response) {
                recyclerView.setAdapter(new LiveDetailRelatedRecyclerViewAdapter(response.body().getData()));
            }

            @Override
            public void onFailure(Call<LiveRelatedBean> call, Throwable t) {
                Log.e("lvieRelate",""+t.getMessage());
            }
        });
        return contentView;
    }

}
