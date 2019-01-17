package com.github.welcomeworld.simplebili.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class SwiperefreshContainer extends SwipeRefreshLayout {
    private boolean isLoading;
    private OnLoadListener onLoadListener;
    private int mScaledTouchSlop;
    private float mDownY,mCurrentY;
    private RecyclerView recyclerView;


    public boolean isLoading(){
        return isLoading;
    }

    public void setLoading(boolean isLoading){
        if(isLoading&&!this.isLoading){
            this.isLoading=true;
            if(onLoadListener!=null){
                onLoadListener.onLoad();
            }
        }else{
            this.isLoading=isLoading;
        }
    }


    public SwiperefreshContainer(@NonNull Context context) {
        super(context);
        mScaledTouchSlop=ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public SwiperefreshContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScaledTouchSlop=ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(recyclerView==null){
            if(getChildAt(1)instanceof RecyclerView) {
                recyclerView = (RecyclerView) getChildAt(1);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            mDownY=ev.getY();
        }
        if(ev.getAction()==MotionEvent.ACTION_MOVE){
            mCurrentY=ev.getY();
            if(canLoad()){
                setLoading(true);
            }
        }
        if(ev.getAction()==MotionEvent.ACTION_UP){
            mCurrentY=ev.getY();
            if(canLoad()){
                setLoading(true);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public interface OnLoadListener{
        void onLoad();
    }

    private boolean canLoad(){
        return recyclerView!=null&&mDownY-mCurrentY>=mScaledTouchSlop&&!isLoading&&!recyclerView.canScrollVertically(1);
    }

    public void setOnLoadListener(OnLoadListener onLoadListener){
        this.onLoadListener=onLoadListener;
    }
}
