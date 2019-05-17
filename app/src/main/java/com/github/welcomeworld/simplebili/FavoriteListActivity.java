package com.github.welcomeworld.simplebili;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.welcomeworld.simplebili.adapter.FavoriteListRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.bean.FavoriteListBean;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.FavoriteNetAPI;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;

import java.util.HashMap;
import java.util.List;
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

public class FavoriteListActivity extends SimpleBaseActivity{
    @BindView(R.id.favorite_list_toolbar)
    Toolbar toolbar;
    @BindView(R.id.favorite_list_swiperefresh)
    SwiperefreshContainer swiperefreshContainer;
    @BindView(R.id.favorite_list_recyclerview)
    RecyclerView recyclerView;

    List<FavoriteListBean.DataBean.ItemsBean> data;
    public int fid;
    public int mid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        ButterKnife.bind(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle(getIntent().getStringExtra("name"));
        mid = getIntent().getIntExtra("mid",-1);
        fid = getIntent().getIntExtra("fid",-1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        swiperefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(true);
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new FavoriteListRecyclerViewAdapter(data));
        refresh(false);
    }
    public void refresh(boolean force){
        if(!force&&data!=null||swiperefreshContainer==null){
            return;
        }
        swiperefreshContainer.setRefreshing(true);
        Map<String,String> parameters=new HashMap<>();
        parameters.put("pn","1");
        parameters.put("ps","20");
        parameters.put("fid",fid+"");
        parameters.put("vmid",mid+"");
        parameters.put("order","ftime");
        parameters.put("itd","0");
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
        retrofit.create(FavoriteNetAPI.class).getFavoriteVideoList().enqueue(new Callback<FavoriteListBean>() {
            @Override
            public void onResponse(Call<FavoriteListBean> call, Response<FavoriteListBean> response) {
                swiperefreshContainer.setRefreshing(false);
                if(response.body().getCode() == 0){
                    data = response.body().getData().getItems();
                    recyclerView.setAdapter(new FavoriteListRecyclerViewAdapter(data));
                }
            }

            @Override
            public void onFailure(Call<FavoriteListBean> call, Throwable t) {
                swiperefreshContainer.setRefreshing(false);
            }
        });
    }
}
