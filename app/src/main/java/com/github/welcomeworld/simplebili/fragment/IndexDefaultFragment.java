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

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.SearchActivity;
import com.github.welcomeworld.simplebili.SimpleBaseActivity;
import com.github.welcomeworld.simplebili.adapter.IndexDefaultPagerAdapter;
import com.github.welcomeworld.simplebili.widget.BiliViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        return view;
    }

   @OnClick({R.id.index_toolbar_drawer,R.id.index_toolbar_avatar})
    public void openDrawer(){
        if(drawerLayout!=null){
            drawerLayout.openDrawer(Gravity.START);
        }
    }
}

