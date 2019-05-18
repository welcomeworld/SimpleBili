package com.github.welcomeworld.simplebili.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.adapter.IndexPagerAdapter;
import com.github.welcomeworld.simplebili.utils.NavigationViewUtils;
import com.github.welcomeworld.simplebili.widget.BiliViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexFragment extends Fragment {

    @BindView(R.id.index_bottomNavigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.index_viewpager)
    BiliViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView=inflater.inflate(R.layout.fragment_index,container,false);
        ButterKnife.bind(this,contentView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.index_bottom_home:
                        viewPager.setCurrentItem(0,false);
                        break;
                    case R.id.index_bottom_category:
                        viewPager.setCurrentItem(1,false);
                        break;
                    case R.id.index_bottom_dynamic:
                        viewPager.setCurrentItem(2,false);
                        break;
                    case R.id.index_bottom_message:
                        viewPager.setCurrentItem(3,false);
                        break;
                }
                return true;
            }
        });
        NavigationViewUtils.disableShiftMode(bottomNavigationView);
        viewPager.setOffscreenPageLimit(3);
        IndexPagerAdapter pagerAdapter=new IndexPagerAdapter(getChildFragmentManager(),getArguments());
        viewPager.setAdapter(pagerAdapter);
        return contentView;
    }
}
