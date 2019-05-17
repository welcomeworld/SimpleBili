package com.github.welcomeworld.simplebili;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.fragment.DownloadFragment;
import com.github.welcomeworld.simplebili.fragment.FavoriteFragment;
import com.github.welcomeworld.simplebili.fragment.HistoryFragment;
import com.github.welcomeworld.simplebili.fragment.IndexFragment;
import com.github.welcomeworld.simplebili.fragment.WatchLaterFragment;
import com.github.welcomeworld.simplebili.utils.FirebaseUtils;
import com.github.welcomeworld.simplebili.utils.NavigationViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends SimpleBaseActivity implements View.OnClickListener {

    @BindView(R.id.main_drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_navigation)
    NavigationView navigationView;
    ImageView headerProfile;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FirebaseUtils.getInstance(this).logEvent("进入主页","进入主页");
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
        navigationView.setItemIconTintList(getResources().getColorStateList(R.color.gary2white_color));
        navigationView.findViewById(R.id.navigation_settings_text).setOnClickListener(this::onClick);
        navigationView.findViewById(R.id.navigation_settings_image).setOnClickListener(this::onClick);
        //NavigationViewUtils.addFooterView(navigationView,R.layout.navigation_footer_main);
    }

    private void initFragment(String tag) {
        Bundle bundle=new Bundle();
        bundle.putInt("drawerLayoutId",R.id.main_drawerLayout);
        Fragment fragment;
        switch (tag){
            case "index":
                fragment=new IndexFragment();
                break;
            case "history":
                fragment=new HistoryFragment();
                break;
            case "favorite":
                fragment=new FavoriteFragment();
                break;
            case "cache":
                fragment=new DownloadFragment();
                break;
            case "later":
                fragment=new WatchLaterFragment();
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
        }
    }
}
