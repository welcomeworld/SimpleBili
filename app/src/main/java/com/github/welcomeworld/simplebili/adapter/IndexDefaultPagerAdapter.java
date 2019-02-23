package com.github.welcomeworld.simplebili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.welcomeworld.simplebili.fragment.IndexDefaultBangumiFragment;
import com.github.welcomeworld.simplebili.fragment.IndexDefaultPopularFragment;
import com.github.welcomeworld.simplebili.fragment.IndexDefaultLiveFragment;
import com.github.welcomeworld.simplebili.fragment.IndexDefaultRecommendFragment;

public class IndexDefaultPagerAdapter extends FragmentPagerAdapter {
    public IndexDefaultPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment=new IndexDefaultLiveFragment();
                break;
            case 1:
                fragment=new IndexDefaultRecommendFragment();
                break;
            case 2:
                fragment=new IndexDefaultPopularFragment();
                break;
            case 3:
                fragment=new IndexDefaultBangumiFragment();
                break;
            default:fragment=new IndexDefaultLiveFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
