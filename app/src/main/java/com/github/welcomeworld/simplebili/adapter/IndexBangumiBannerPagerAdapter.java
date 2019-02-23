package com.github.welcomeworld.simplebili.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.bean.IndexBangumiBean;

import java.util.ArrayList;
import java.util.List;

public class IndexBangumiBannerPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{

    IndexBangumiBean.ResultBean.ModulesBean data;
    ViewPager viewPager;
    public IndexBangumiBannerPagerAdapter(ViewPager viewPager,IndexBangumiBean.ResultBean.ModulesBean data){
        this.data=data;
        this.viewPager=viewPager;
        viewPager.setOffscreenPageLimit(getCount());
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView=new ImageView(container.getContext());
        if(position==0){
            Glide.with(container.getContext()).load(data.getItems().get(getCount()-3).getCover()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(imageView);
        }else if(position==getCount()-1){
            Glide.with(container.getContext()).load(data.getItems().get(0).getCover()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(imageView);
        }else{
            Glide.with(container.getContext()).load(data.getItems().get(position-1).getCover()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(imageView);
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return data==null?0:data.getItems().size()+2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 0 && positionOffset == 0) viewPager .setCurrentItem(getCount() - 2, false);
        else if (position == getCount() - 1 && positionOffset == 0) viewPager .setCurrentItem(1, false);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
