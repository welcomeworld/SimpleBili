package com.github.welcomeworld.simplebili.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.DanmakuBean;
import com.github.welcomeworld.simplebili.utils.SpanUtils;
import com.github.welcomeworld.simplebili.widget.BiliSimpleClickSpan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveDetailDanmakuRecyclerViewAdapter extends RecyclerView.Adapter<LiveDetailDanmakuRecyclerViewAdapter.MyInnerViewHolder>{

    List<DanmakuBean> data;


    public LiveDetailDanmakuRecyclerViewAdapter(List<DanmakuBean> data){
        this.data=data;
    }

    public void addHistory(@NonNull List<DanmakuBean> data){
        this.data.addAll(0,data);
        notifyItemRangeInserted(0,data.size());
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_live_danmaku,parent,false);
        return new MyInnerViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        holder.itemView.setMovementMethod(LinkMovementMethod.getInstance());
        holder.itemView.setText(data.get(position).getUserName()+":"+data.get(position).getText());
        SpanUtils.addClickSpan(holder.itemView,new BiliSimpleClickSpan("https://space.bilibili.com/"+data.get(position).getUserId()),0,data.get(position).getUserName().length());
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.recyclerview_live_danmaku)
        TextView itemView;

        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
