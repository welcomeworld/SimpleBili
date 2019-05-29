package com.github.welcomeworld.simplebili.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import com.github.welcomeworld.simplebili.bean.LocalHistoryBean;
import com.github.welcomeworld.simplebili.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.MyInnerViewHolder> {

    List<LocalHistoryBean> data;

    public HistoryRecyclerViewAdapter(List<LocalHistoryBean> data){
        this.data = data;
    }


    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_history,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        LocalHistoryBean currentData = data.get(position);
        holder.titleView.setText(currentData.getTitle());
        holder.viewTimeView.setText(StringUtils.formatDate(currentData.getViewTime()));
        String url="";
        Glide.with(holder.coverView).load(currentData.getCover()+"@320w_200h_1e_1c.webp").apply(new RequestOptions().transform(new FitCenter(),new RoundedCorners(10))).into(holder.coverView);
        if(currentData.getType() == LocalHistoryBean.LIVE){
            holder.durationView.setVisibility(View.GONE);
            holder.viewProgressView.setVisibility(View.GONE);
            holder.backslashView.setVisibility(View.GONE);
            holder.upperView.setVisibility(View.VISIBLE);
            holder.subTitleView.setVisibility(View.GONE);
            holder.upperView.setText(currentData.getUpName());
            holder.typeView.setText(R.string.live);
            holder.typeView.setVisibility(View.VISIBLE);
            url = "bilibili://live/"+currentData.getAid();
        }else {
            holder.typeView.setVisibility(View.GONE);
            holder.durationView.setVisibility(View.VISIBLE);
            holder.viewProgressView.setVisibility(View.VISIBLE);
            holder.backslashView.setVisibility(View.VISIBLE);
            if(currentData.getType() == LocalHistoryBean.VIDEO){
                holder.upperView.setVisibility(View.VISIBLE);
                holder.subTitleView.setVisibility(View.GONE);
                holder.upperView.setText(currentData.getUpName());
                url = "bilibili://video/"+currentData.getAid();
            }else if(currentData.getType() == LocalHistoryBean.BANGUMI){
                holder.upperView.setVisibility(View.GONE);
                holder.subTitleView.setVisibility(View.VISIBLE);
                holder.subTitleView.setText(currentData.getSubTitle());
                url = "https://www.bilibili.com/bangumi/ss"+currentData.getAid();
            }
        }
        String finalUrl = url;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setData(Uri.parse(finalUrl));
                intent.setPackage(v.getContext().getPackageName());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.history_recyclerView_title)
        TextView titleView;
        @BindView(R.id.history_recyclerView_upper)
        TextView upperView;
        @BindView(R.id.history_recyclerView_viewProgress)
        TextView viewProgressView;
        @BindView(R.id.history_recyclerView_viewTime)
        TextView viewTimeView;
        @BindView(R.id.history_recyclerView_duration)
        TextView durationView;
        @BindView(R.id.history_recyclerView_type)
        TextView typeView;
        @BindView(R.id.history_recyclerView_cover)
        ImageView coverView;
        @BindView(R.id.history_recyclerView_backslash)
        TextView backslashView;
        @BindView(R.id.history_recyclerView_subtitle)
        TextView subTitleView;

        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
