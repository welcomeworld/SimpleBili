package com.github.welcomeworld.simplebili;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.bean.UserInfoMineBean;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.fragment.DownloadFragment;
import com.github.welcomeworld.simplebili.fragment.FavoriteFragment;
import com.github.welcomeworld.simplebili.fragment.HistoryFragment;
import com.github.welcomeworld.simplebili.fragment.IndexFragment;
import com.github.welcomeworld.simplebili.fragment.WatchLaterFragment;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.UserNetAPI;
import com.github.welcomeworld.simplebili.utils.FirebaseUtils;

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

public class MainActivity extends SimpleBaseActivity implements View.OnClickListener {

    @BindView(R.id.main_drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_navigation)
    NavigationView navigationView;
    ImageView headerProfile;
    ImageView heaserBg;
    TextView headerName;
    Fragment currentFragment;

    private int currentCheckItem = R.id.item_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FirebaseUtils.getInstance(this).logEvent("进入主页","进入主页");
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                if(BiliLocalStatus.isLogin()){
                    if(BiliLocalStatus.getCover()!=null){
                        headerName.setText(BiliLocalStatus.getName());
                        Glide.with(headerProfile).load(BiliLocalStatus.getCover()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(headerProfile.getDrawable())).into(headerProfile);
                    }else {
                        getMineInfo();
                    }
                    heaserBg.setImageResource(R.mipmap.drawerbg_logined);
                }else {
                    headerProfile.setImageResource(R.mipmap.ic_default_avatar);
                    headerName.setText(R.string.login_tips);
                    heaserBg.setImageResource(R.mipmap.drawerbg_not_logined);
                }
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                navigationView.setCheckedItem(currentCheckItem);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_index:
                        switchFragment("index");
                        break;
                    case R.id.item_history:
                        switchFragment("history");
                        break;
                    case R.id.item_cache:
                        switchFragment("cache");
                        break;
                    case R.id.item_favorite:
                        if(BiliLocalStatus.isLogin()){
                            switchFragment("favorite");
                        }else{
                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.item_later:
                        if(BiliLocalStatus.isLogin()){
                            switchFragment("later");
                        }else{
                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.item_myvip:
                        if(BiliLocalStatus.isLogin()){
                            Intent vipIntent = new Intent(MainActivity.this,BrowserActivity.class);
                            vipIntent.setData(Uri.parse("https://big.bilibili.com/mobile/home"));
                            startActivity(vipIntent);
                        }else{
                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.item_customerservice:
                        Intent intent=new Intent(MainActivity.this,BrowserActivity.class);
                        intent.setData(Uri.parse("https://www.bilibili.com/h5/faq"));
                        startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
        headerProfile=navigationView.getHeaderView(0).findViewById(R.id.main_header_profile);
        headerName = navigationView.getHeaderView(0).findViewById(R.id.main_header_name);
        heaserBg = navigationView.getHeaderView(0).findViewById(R.id.main_header_bg);
        headerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!BiliLocalStatus.isLogin()){
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(MainActivity.this,UserInfoActivity.class);
                    startActivity(intent);
                }

            }
        });
        initFragment("index");
        navigationView.findViewById(R.id.navigation_settings_text).setOnClickListener(this::onClick);
        navigationView.findViewById(R.id.navigation_settings_image).setOnClickListener(this::onClick);
        navigationView.findViewById(R.id.navigation_theme_text).setOnClickListener(this::onClick);
        navigationView.findViewById(R.id.navigation_theme_image).setOnClickListener(this::onClick);
        navigationView.findViewById(R.id.navigation_night_text).setOnClickListener(this::onClick);
        navigationView.findViewById(R.id.navigation_night_image).setOnClickListener(this::onClick);
        if(BiliLocalStatus.isLogin()){
            getMineInfo();
        }
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
                headerName.setText(BiliLocalStatus.getName());
                Glide.with(headerProfile).load(BiliLocalStatus.getCover()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(headerProfile.getDrawable())).into(headerProfile);

            }

            @Override
            public void onFailure(Call<UserInfoMineBean> call, Throwable t) {
            }
        });
    }

    private void initFragment(String tag) {
        Bundle bundle=new Bundle();
        bundle.putInt("drawerLayoutId",R.id.main_drawerLayout);
        Fragment fragment;
        switch (tag){
            case "index":
                fragment=new IndexFragment();
                currentCheckItem = R.id.item_index;
                break;
            case "history":
                fragment=new HistoryFragment();
                currentCheckItem = R.id.item_history;
                break;
            case "favorite":
                fragment=new FavoriteFragment();
                currentCheckItem = R.id.item_favorite;
                break;
            case "cache":
                fragment=new DownloadFragment();
                currentCheckItem = R.id.item_cache;
                break;
            case "later":
                fragment=new WatchLaterFragment();
                currentCheckItem = R.id.item_later;
                break;
            default:
                return;
        }
        fragment.setArguments(bundle);
        FragmentManager fragmentManager=getSupportFragmentManager();
        if(currentFragment!=null){
            fragmentManager.beginTransaction().add(R.id.main_container,fragment,tag).hide(currentFragment).commit();
        }else{
            fragmentManager.beginTransaction().add(R.id.main_container,fragment,tag).commit();
        }
        currentFragment=fragment;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void switchFragment(String tag){
        if(currentFragment.getTag()!=null&&currentFragment.getTag().equalsIgnoreCase(tag)){
            return;
        }
        Fragment fragment=getSupportFragmentManager().findFragmentByTag(tag);
        if(fragment==null){
            initFragment(tag);
        }else{
            getSupportFragmentManager().beginTransaction().hide(currentFragment).show(fragment).commit();
            currentFragment=fragment;
        }
        if(navigationView!=null&&currentFragment.getTag()!=null){
            switch (currentFragment.getTag()){
                case "index":
                    navigationView.setCheckedItem(R.id.item_index);
                    currentCheckItem = R.id.item_index;
                    break;
                case "history":
                    navigationView.setCheckedItem(R.id.item_history);
                    currentCheckItem = R.id.item_history;
                    break;
                case "favorite":
                    navigationView.setCheckedItem(R.id.item_favorite);
                    currentCheckItem = R.id.item_favorite;
                    break;
                case "cache":
                    navigationView.setCheckedItem(R.id.item_cache);
                    currentCheckItem = R.id.item_cache;
                    break;
                case "later":
                    navigationView.setCheckedItem(R.id.item_later);
                    currentCheckItem = R.id.item_later;
                    break;
            }
        }
    }

    @Override
    public void updateSelf(String command) {
        if(command.equalsIgnoreCase("search")){
            startActivity(new Intent(MainActivity.this,SearchActivity.class));
        }else if(command.equalsIgnoreCase("cache")){
            switchFragment("cache");
        }else if(command.equalsIgnoreCase("gameCenter")){
            Intent gameIntent=new Intent();
            gameIntent.setData(Uri.parse("https://mobilegame-1.biligame.com/?statusBarHeight=48"));
            gameIntent.setPackage(getPackageName());
            startActivity(gameIntent);
        }
        else {
            Log.e(TAG,"unknown command:"+command);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if(currentFragment.getTag()!=null&&currentFragment.getTag().equalsIgnoreCase("index")){
            super.onBackPressed();
        }else{
            switchFragment("index");
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.navigation_settings_image:
            case R.id.navigation_settings_text:
                startActivity(new Intent(this,SettingsActivity.class));
                break;
            case R.id.navigation_theme_image:
            case R.id.navigation_theme_text:
                startActivity(new Intent(this,ThemeActivity.class));
                break;
            case R.id.navigation_night_image:
            case R.id.navigation_night_text:

                break;
        }
    }
}
