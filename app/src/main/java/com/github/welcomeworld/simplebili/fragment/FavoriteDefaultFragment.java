package com.github.welcomeworld.simplebili.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.adapter.FavoriteDefaultRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.bean.FavoriteDefaultBean;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.FavoriteNetAPI;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;

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

public class FavoriteDefaultFragment extends Fragment {
    @BindView(R.id.favorite_default_swiperefresh)
    SwiperefreshContainer swiperefreshContainer;
    @BindView(R.id.favorite_default_recyclerview)
    RecyclerView recyclerView;

    FavoriteDefaultBean.DataBean data;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        data = (FavoriteDefaultBean.DataBean) getArguments().getSerializable("data");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_default,container,false);
        ButterKnife.bind(this,view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        if(data!=null){
            recyclerView.setAdapter(new FavoriteDefaultRecyclerViewAdapter(data.getFavorite()));
        }else {
            recyclerView.setAdapter(new FavoriteDefaultRecyclerViewAdapter(null));
        }
        recyclerView.getItemAnimator().setChangeDuration(3000);
        recyclerView.getItemAnimator().setMoveDuration(3000);
        swiperefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(true);
            }
        });
        refresh(false);
        return view;
    }

    public void refresh(boolean force){
        if(!force&&data!=null&&data.getFavorite()!=null||swiperefreshContainer==null){
            return;
        }
        swiperefreshContainer.setRefreshing(true);
        Map<String,String> parameters=new HashMap<>();
        parameters.put("ps","20");
        parameters.put("pn","1");
        parameters.put("aid","0");
        if(BiliLocalStatus.isLogin()){
            parameters.put("vmid", BiliLocalStatus.getMid()+"");
        }
        parameters.put("ts",""+System.currentTimeMillis());
        OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                .addInterceptor(new FixedHeaderInterceptor())
                .addInterceptor(new DynamicHeaderInterceptor(null))
                .addInterceptor(new FixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters))
                .addInterceptor(new SortAndSignInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.APPURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
        retrofit.create(FavoriteNetAPI.class).getFavoriteData().enqueue(new Callback<FavoriteDefaultBean>() {
            @Override
            public void onResponse(Call<FavoriteDefaultBean> call, Response<FavoriteDefaultBean> response) {
                swiperefreshContainer.setRefreshing(false);
                if(response.body().getCode() == 0){
                    data = response.body().getData();
                    recyclerView.setAdapter(new FavoriteDefaultRecyclerViewAdapter(data.getFavorite()));
                }
            }

            @Override
            public void onFailure(Call<FavoriteDefaultBean> call, Throwable t) {
                swiperefreshContainer.setRefreshing(false);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
            refresh(false);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
}
