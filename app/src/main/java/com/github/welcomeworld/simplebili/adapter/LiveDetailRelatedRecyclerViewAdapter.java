package com.github.welcomeworld.simplebili.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.LiveRelatedBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveDetailRelatedRecyclerViewAdapter extends RecyclerView.Adapter<LiveDetailRelatedRecyclerViewAdapter.MyInnerViewHolder>{

    List<LiveRelatedBean.DataBean> data;

    Context context;

    public LiveDetailRelatedRecyclerViewAdapter(List<LiveRelatedBean.DataBean> data){
        this.data=data;
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View contentView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_live_related,parent,false);
        return new MyInnerViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent liveIntent = new Intent("com.github.welcomeworld.simplebili.action.LIVEPLAY");
                Bundle bundle = new Bundle();
                bundle.putString("url",data.get(position).getPlayUrl());
                bundle.putString("title",data.get(position).getTitle());
                liveIntent.putExtras(bundle);
                liveIntent.setData(Uri.parse("bilibili://live"+data.get(position).getLink()));
                context.startActivity(liveIntent);
            }
        });
        holder.titleView.setText(data.get(position).getTitle());
        holder.upnameView.setText(data.get(position).getUname());
        holder.countView.setText(""+data.get(position).getOnline());
        holder.labelView.setText(data.get(position).getAreaV2Name());
        Glide.with(context).load(data.get(position).getCover()+"@320w_180h_1e_1c.webp").apply(new RequestOptions().transforms(new FitCenter(),new RoundedCorners(10))).into(holder.coverView);
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recyclerview_live_related_title)
        TextView titleView;
        @BindView(R.id.recyclerview_live_related_cover)
        ImageView coverView;
        @BindView(R.id.recyclerview_live_related_upname)
        TextView upnameView;
        @BindView(R.id.recyclerview_live_related_count)
        TextView countView;
        @BindView(R.id.recyclerview_live_related_label)
        TextView labelView;
        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
