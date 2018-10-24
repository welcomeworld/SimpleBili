package com.github.welcomeworld.simplebili.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.welcomeworld.simplebili.fragment.CategoryFragment;
import com.github.welcomeworld.simplebili.fragment.DynamicFragment;
import com.github.welcomeworld.simplebili.fragment.IndexDefaultFragment;
import com.github.welcomeworld.simplebili.fragment.MessageFragment;

import java.util.List;

public class IndexPagerAdapter extends FragmentPagerAdapter {
    Bundle data;
    public IndexPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public IndexPagerAdapter(FragmentManager fm,Bundle bundle) {
        super(fm);
        data=bundle;
    }

    public void setArguments(Bundle bundle){
        data=bundle;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment=new IndexDefaultFragment();
                break;
            case 1:
                fragment=new CategoryFragment();
                break;
            case 2:
                fragment=new DynamicFragment();
                break;
            case 3:
                fragment=new MessageFragment();
                break;
            default:fragment=new IndexDefaultFragment();
        }
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
