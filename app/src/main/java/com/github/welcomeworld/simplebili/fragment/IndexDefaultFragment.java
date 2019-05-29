package com.github.welcomeworld.simplebili.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.SearchActivity;
import com.github.welcomeworld.simplebili.SimpleBaseActivity;
import com.github.welcomeworld.simplebili.adapter.IndexDefaultPagerAdapter;
import com.github.welcomeworld.simplebili.bean.UserInfoMineBean;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.UserNetAPI;
import com.github.welcomeworld.simplebili.widget.BiliViewPager;

import java.util.ArrayList;
import java.util.HashMap;
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

public class IndexDefaultFragment extends Fragment {

    @BindView(R.id.index_toolbar)
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    @BindView(R.id.index_default_viewpager)
    BiliViewPager viewPager;
    @BindView(R.id.index_default_tabLayout)
    TabLayout tabLayout;

    Activity activity;

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
        View view=inflater.inflate(R.layout.fragment_index_default,container,false);
        ButterKnife.bind(this,view);
        toolbar.inflateMenu(R.menu.index_game);
        toolbar.inflateMenu(R.menu.index_downlaod);
        toolbar.inflateMenu(R.menu.index_search);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_home_search:
                        ((SimpleBaseActivity)getActivity()).updateSelf("search");
                        return true;
                    case R.id.item_game:
                        ((SimpleBaseActivity)getActivity()).updateSelf("gameCenter");
                        return true;
                    case R.id.item_download:
                        ((SimpleBaseActivity)getActivity()).updateSelf("cache");
                        return true;
                        default:return false;
                }
            }
        });
        viewPager.setAdapter(new IndexDefaultPagerAdapter(getChildFragmentManager()));
        viewPager.setCanScroll(true);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(3);
        if(BiliLocalStatus.isLogin()){
            if(BiliLocalStatus.getCover()!=null){
                ImageView avator = toolbar.findViewById(R.id.index_toolbar_avatar);
                if(BiliLocalStatus.getCover()!=null){
                    Glide.with(avator).load(BiliLocalStatus.getCover()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(avator.getDrawable())).into(avator);
                }else {
                    avator.setImageResource(R.mipmap.ic_default_avatar);
                }
            }else {
                getMineInfo();
            }
        }
        return view;
    }

    public void getMineInfo(){
        Map<String,String> parameters1=new HashMap<>();
        parameters1.put("ts",""+System.currentTimeMillis());
        OkHttpClient.Builder okHttpClientBuilder1=new OkHttpClient.Builder()
                .addInterceptor(new FixedHeaderInterceptor())
                .addInterceptor(new DynamicHeaderInterceptor(null))
                .addInterceptor(new FixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters1))
                .addInterceptor(new SortAndSignInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit1=new Retrofit.Builder()
                .baseUrl(BaseUrl.APPURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder1.build())
                .build();
        retrofit1.create(UserNetAPI.class).getMine().enqueue(new Callback<UserInfoMineBean>() {
            @Override
            public void onResponse(Call<UserInfoMineBean> call, Response<UserInfoMineBean> response) {
                if(response.body()==null||response.body().getCode()!=0){
                    return;
                }
                if(BiliLocalStatus.isLogin()){
                    BiliLocalStatus.setName(response.body().getData().getName());
                    BiliLocalStatus.setCover(response.body().getData().getFace());
                }
                ImageView avator = toolbar.findViewById(R.id.index_toolbar_avatar);
                Glide.with(avator).load(BiliLocalStatus.getCover()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(avator.getDrawable())).into(avator);
            }

            @Override
            public void onFailure(Call<UserInfoMineBean> call, Throwable t) {
            }
        });
    }

   @OnClick({R.id.index_toolbar_drawer,R.id.index_toolbar_avatar})
    public void openDrawer(){
        if(drawerLayout!=null){
            drawerLayout.openDrawer(Gravity.START);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser&&toolbar!=null){
            ImageView avator = toolbar.findViewById(R.id.index_toolbar_avatar);
            if(BiliLocalStatus.getCover()!=null){
                Glide.with(avator).load(BiliLocalStatus.getCover()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(avator.getDrawable())).into(avator);
            }else {
                avator.setImageResource(R.mipmap.ic_default_avatar);
            }
        }
    }
}

