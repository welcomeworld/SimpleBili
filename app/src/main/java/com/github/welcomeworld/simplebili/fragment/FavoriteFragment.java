package com.github.welcomeworld.simplebili.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.SimpleBaseActivity;
import com.github.welcomeworld.simplebili.adapter.FavoritePagerAdapter;
import com.github.welcomeworld.simplebili.bean.FavoriteDefaultBean;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.FavoriteNetAPI;

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

public class FavoriteFragment extends Fragment {
    @BindView(R.id.navigation_toolbar)
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    SimpleBaseActivity activity;
    @BindView(R.id.favorite_tablayout)
    TabLayout tabLayout;
    @BindView(R.id.favorite_viewpager)
    ViewPager viewPager;

    FavoriteDefaultBean data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,container,false);
        ButterKnife.bind(this,view);
        toolbar.setTitle(R.string.favorites);
        toolbar.inflateMenu(R.menu.index_downlaod);
        toolbar.inflateMenu(R.menu.index_search);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout!=null){
                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_download:
                        activity.updateSelf("cache");
                        return true;
                    case R.id.item_home_search:
                        activity.updateSelf("search");
                        return true;
                    default:return false;
                }
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(new FavoritePagerAdapter(getChildFragmentManager()));
        refresh(false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (SimpleBaseActivity) context;
        Bundle bundle=getArguments();
        if(bundle!=null){
            int drawerLayoutId=bundle.getInt("drawerLayoutId",-1);
            if(drawerLayoutId!=-1){
                drawerLayout=activity.findViewById(drawerLayoutId);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
            refresh(false);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    public void refresh(boolean force){
        if(!force&&data!=null){
            return;
        }
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
                if(response.body().getCode() == 0){
                    data = response.body();
                    viewPager.setAdapter(new FavoritePagerAdapter(getChildFragmentManager(),data.getData()));
                    if(data.getData().getTab().isFavorite()){
                        tabLayout.addTab(tabLayout.newTab().setText("追番"));
                    }
                    if(data.getData().getTab().isCinema()){
                        tabLayout.addTab(tabLayout.newTab().setText("追剧"));
                    }
                    if(data.getData().getTab().isTopic()){
                        tabLayout.addTab(tabLayout.newTab().setText("话题"));
                    }
                }
            }

            @Override
            public void onFailure(Call<FavoriteDefaultBean> call, Throwable t) {

            }
        });
    }
}
