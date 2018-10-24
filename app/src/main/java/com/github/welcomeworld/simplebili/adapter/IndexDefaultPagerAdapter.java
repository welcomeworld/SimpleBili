package com.github.welcomeworld.simplebili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.welcomeworld.simplebili.fragment.IndexDefaultFilmFragment;
import com.github.welcomeworld.simplebili.fragment.IndexDefaultFollowFragment;
import com.github.welcomeworld.simplebili.fragment.IndexDefaultLiveFragment;
import com.github.welcomeworld.simplebili.fragment.IndexDefaultRecommendFragment;
import com.github.welcomeworld.simplebili.fragment.IndexDefaultSpecialColumnFragment;

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
                fragment=new IndexDefaultFollowFragment();
                break;
            case 3:
                fragment=new IndexDefaultFilmFragment();
                break;
            case 4:
                fragment=new IndexDefaultSpecialColumnFragment();
            default:fragment=new IndexDefaultLiveFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
