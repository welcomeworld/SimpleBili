package com.github.welcomeworld.simplebili.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.adapter.IndexDynamicRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.bean.IndexDynamicBean;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.IndexNetAPI;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DynamicFragment extends Fragment {
    @BindView(R.id.index_toolbar)
    Toolbar toolbar;
    @BindView(R.id.index_toolbar_title)
    TextView titleView;
    DrawerLayout drawerLayout;
    Activity activity;

    @BindView(R.id.dynamic_login_guide)
    ImageView loginGuide;

    @BindView(R.id.index_dynamic_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.index_dynamic_swiprefresh)
    SwiperefreshContainer swiperefreshContainer;

    List<IndexDynamicBean.DataBean.CardsBean> data;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (Activity) context;
        Bundle bundle=getArguments();
        if(bundle!=null){
            int drawerLayoutId=bundle.getInt("drawerLayoutId",-1);
            if(drawerLayoutId!=-1){
                drawerLayout=activity.findViewById(drawerLayoutId);
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_index_dynamic,container,false);
        ButterKnife.bind(this,view);
        toolbar.inflateMenu(R.menu.index_rank);
        toolbar.inflateMenu(R.menu.index_post);
        titleView.setText(R.string.dynamic);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        swiperefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(true);
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new IndexDynamicRecyclerViewAdapter(data));
        return view;
    }

    @OnClick({R.id.index_toolbar_drawer,R.id.index_toolbar_avatar})
    public void openDrawer(){
        if(drawerLayout!=null){
            drawerLayout.openDrawer(Gravity.START);
        }
    }

    @OnClick(R.id.dynamic_login_guide)
    public void login(){
        Intent loginIntent=new Intent("com.github.welcomeworld.simplebili.action.LOGIN");
        loginIntent.setPackage(getContext().getPackageName());
        startActivity(loginIntent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser&&toolbar!=null){
            refresh(false);
            ImageView avator = toolbar.findViewById(R.id.index_toolbar_avatar);
            if(BiliLocalStatus.getCover()!=null){
                Glide.with(avator).load(BiliLocalStatus.getCover()).apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop())).into(avator);
            }else {
                avator.setImageResource(R.mipmap.ic_default_avatar);
            }
            if(BiliLocalStatus.isLogin()){
                loginGuide.setVisibility(View.GONE);
                swiperefreshContainer.setVisibility(View.VISIBLE);
            }else {
                loginGuide.setVisibility(View.VISIBLE);
                swiperefreshContainer.setVisibility(View.GONE);
            }
        }
    }

    public void refresh(boolean force){
        if(!force&&data!=null||swiperefreshContainer==null){
            return;
        }
        swiperefreshContainer.setRefreshing(true);
        Map<String,String> parameters=new HashMap<>();
        parameters.put("pn","1");
        parameters.put("ps","20");
        parameters.put("qn","32");
        parameters.put("dislike_ts","0");
        parameters.put("uid",""+BiliLocalStatus.getMid());
        parameters.put("type_list","8,512,4097,4098,4099,4100,4101");
        parameters.put("update_num_dy_id","2");
        parameters.put("rsp_type","2");
        parameters.put("version","5.32.2.5322000");
        parameters.put("trace_id","20190516205800053");
        parameters.put("src","bili");
        parameters.put("ts",""+System.currentTimeMillis());
        OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                .addInterceptor(new FixedHeaderInterceptor())
                .addInterceptor(new DynamicHeaderInterceptor(null))
                .addInterceptor(new FixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters))
                .addInterceptor(new SortAndSignInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.APIVCURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
        retrofit.create(IndexNetAPI.class).getIndexDynamic().enqueue(new Callback<IndexDynamicBean>() {
            @Override
            public void onResponse(Call<IndexDynamicBean> call, Response<IndexDynamicBean> response) {
                swiperefreshContainer.setRefreshing(false);
                if(response.body().getCode() == 0){
                    data = response.body().getData().getCards();
                    recyclerView.setAdapter(new IndexDynamicRecyclerViewAdapter(data));
                }
            }

            @Override
            public void onFailure(Call<IndexDynamicBean> call, Throwable t) {
                swiperefreshContainer.setRefreshing(false);
            }
        });
    }
}
