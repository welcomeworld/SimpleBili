package com.github.welcomeworld.simplebili;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.welcomeworld.simplebili.adapter.FavoriteBangumiRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.adapter.NotificationRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.bean.FavoriteBangumiBean;
import com.github.welcomeworld.simplebili.bean.NotificationReplyBean;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.FavoriteNetAPI;
import com.github.welcomeworld.simplebili.net.retrofit.NotificationNetAPI;
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

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.notification_swiperefresh)
    SwiperefreshContainer swiperefreshContainer;
    @BindView(R.id.notification_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.notification_toolbar)
    Toolbar toolbar;

    private int type = 0;

    List<NotificationReplyBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type",0);
        switch (type){
            case 0:
                toolbar.setTitle(R.string.notification_reply_me);
                break;
            case 1:
                toolbar.setTitle(R.string.notification_at_me);
                break;
            case 2:
                toolbar.setTitle(R.string.notification_praise_me);
                break;
                default:toolbar.setTitle(R.string.notification_reply_me);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        swiperefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(true);
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new NotificationRecyclerViewAdapter(data));
    }

    public void refresh(boolean force){
        if(!force&&data!=null&&data.size()>0||swiperefreshContainer==null){
            return;
        }
        swiperefreshContainer.setRefreshing(true);
        Map<String,String> parameters=new HashMap<>();
        parameters.put("actionKey","appKey");
        parameters.put("data_type","1");
        parameters.put("page_size","40");
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
                .baseUrl(BaseUrl.MESSAGEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
        Call<NotificationReplyBean> call;
        switch (type){
            case 0:
                call = retrofit.create(NotificationNetAPI.class).getReplyMe();
                break;
            case 1:
                call = retrofit.create(NotificationNetAPI.class).getAtMe();
                break;
            case 2:
                call = retrofit.create(NotificationNetAPI.class).getPraiseMe();
                break;
            default:call = retrofit.create(NotificationNetAPI.class).getReplyMe();
        }
        call.enqueue(new Callback<NotificationReplyBean>() {
            @Override
            public void onResponse(Call<NotificationReplyBean> call, Response<NotificationReplyBean> response) {
                swiperefreshContainer.setRefreshing(false);
                if(response.body() !=null&&response.body().getCode() == 0){
                    data = response.body().getData();
                    recyclerView.setAdapter(new NotificationRecyclerViewAdapter(data));
                }
            }

            @Override
            public void onFailure(Call<NotificationReplyBean> call, Throwable t) {
                swiperefreshContainer.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh(false);
    }
}
