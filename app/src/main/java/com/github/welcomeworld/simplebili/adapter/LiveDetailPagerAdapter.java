package com.github.welcomeworld.simplebili.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.github.welcomeworld.simplebili.bean.DanmakuBean;
import com.github.welcomeworld.simplebili.fragment.LiveDanmakuFragment;
import com.github.welcomeworld.simplebili.fragment.LiveRelatedFragment;
import com.github.welcomeworld.simplebili.fragment.LiveUpInfoFragment;

public class LiveDetailPagerAdapter extends FragmentPagerAdapter{

    Bundle data;

    public LiveDetailPagerAdapter(FragmentManager fm, Bundle bundle) {
        super(fm);
        data=bundle;
    }

    LiveDanmakuFragment liveDanmakuFragment;
    LiveUpInfoFragment liveUpInfoFragment;
    LiveRelatedFragment liveRelatedFragment;



    public void addDanmaku(DanmakuBean danmakuBean){
        if(liveDanmakuFragment!=null){
            liveDanmakuFragment.addDanmaku(danmakuBean);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                liveDanmakuFragment=new LiveDanmakuFragment();
                fragment=liveDanmakuFragment;
                break;
            case 1:
                liveUpInfoFragment=new LiveUpInfoFragment();
                fragment=liveUpInfoFragment;
                break;
            case 2:
                liveRelatedFragment=new LiveRelatedFragment();
                fragment=liveRelatedFragment;
                break;
            default:
                liveDanmakuFragment=new LiveDanmakuFragment();
                fragment=liveDanmakuFragment;
                break;
        }
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
