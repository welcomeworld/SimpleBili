package com.github.welcomeworld.simplebili.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class BiliViewPager extends ViewPager {

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

    boolean canScroll=false;

    public BiliViewPager(@NonNull Context context) {
        super(context);
    }

    public BiliViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return canScroll&&super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return canScroll&&super.onInterceptTouchEvent(ev);
    }
}
