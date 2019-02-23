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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.fragment.DownloadFragment;
import com.github.welcomeworld.simplebili.fragment.FavoriteFragment;
import com.github.welcomeworld.simplebili.fragment.HistoryFragment;
import com.github.welcomeworld.simplebili.fragment.IndexFragment;
import com.github.welcomeworld.simplebili.fragment.WatchLaterFragment;
import com.github.welcomeworld.simplebili.utils.NavigationViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(navigationView);
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
                        if(BiliLocalStatus.isLogin(getApplicationContext())){
                            switchFragment("favorite");
                        }else{
                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.item_later:
                        if(BiliLocalStatus.isLogin(getApplicationContext())){
                            switchFragment("later");
                        }else{
                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.item_follow:
                        if(BiliLocalStatus.isLogin(getApplicationContext())){
                            switchFragment("follow");
                        }else{
                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.item_uppercenter:
                        if(BiliLocalStatus.isLogin(getApplicationContext())){
                            switchFragment("uppercenter");
                        }else{
                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.item_live:
                        if(BiliLocalStatus.isLogin(getApplicationContext())){
                            switchFragment("live");
                        }else{
                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.item_myvip:
                        if(BiliLocalStatus.isLogin(getApplicationContext())){
                            switchFragment("myvip");
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
                return true;
            }
        });
        headerProfile=navigationView.getHeaderView(0).findViewById(R.id.main_header_profile);
        headerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        initFragment("index");
        navigationView.setItemIconTintList(getResources().getColorStateList(R.color.gary2white_color));
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
}
