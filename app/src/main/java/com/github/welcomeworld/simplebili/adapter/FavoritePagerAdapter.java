package com.github.welcomeworld.simplebili.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.welcomeworld.simplebili.bean.FavoriteDefaultBean;
import com.github.welcomeworld.simplebili.fragment.FavoriteBangumiFragment;
import com.github.welcomeworld.simplebili.fragment.FavoriteCinemaFragment;
import com.github.welcomeworld.simplebili.fragment.FavoriteDefaultFragment;
import com.github.welcomeworld.simplebili.fragment.FavoriteTopicFragment;

import java.util.ArrayList;
import java.util.List;

public class FavoritePagerAdapter extends FragmentPagerAdapter {

    FavoriteDefaultBean.DataBean data;

    List<String> tabs = new ArrayList<>();

    public FavoritePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public FavoritePagerAdapter(FragmentManager fm, FavoriteDefaultBean.DataBean data) {
        super(fm);
        this.data = data;
        if(data.getTab().isFavorite()){
            tabs.add("追番");
        }
        if(data.getTab().isCinema()){
            tabs.add("追剧");
        }
        if(data.getTab().isTopic()){
            tabs.add("话题");
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FavoriteDefaultFragment favoriteDefaultFragment = new FavoriteDefaultFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",data);
                favoriteDefaultFragment.setArguments(bundle);
                return favoriteDefaultFragment;
            case 1:return new FavoriteBangumiFragment();
            case 2:return new FavoriteCinemaFragment();
            case 3:return new FavoriteTopicFragment();
            default:FavoriteDefaultFragment favoriteDefaultFragment1 = new FavoriteDefaultFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("data",data);
                favoriteDefaultFragment1.setArguments(bundle1);
                return favoriteDefaultFragment1;
        }
    }

    @Override
    public int getCount() {
        return tabs.size()+1;
    }
}
