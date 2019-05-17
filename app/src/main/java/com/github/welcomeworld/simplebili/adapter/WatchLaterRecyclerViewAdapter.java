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
import com.github.welcomeworld.simplebili.bean.FavoriteListBean;
import com.github.welcomeworld.simplebili.bean.WatchLaterBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WatchLaterRecyclerViewAdapter extends RecyclerView.Adapter<WatchLaterRecyclerViewAdapter.MyInnerViewHolder> {
    List<WatchLaterBean.DataBean.ListBean> data;

    public WatchLaterRecyclerViewAdapter(List<WatchLaterBean.DataBean.ListBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_watch_later,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        WatchLaterBean.DataBean.ListBean currentData=data.get(position);
        Glide.with(holder.coverView).load(currentData.getPic()).apply(new RequestOptions().transform(new FitCenter(),new RoundedCorners(10))).into(holder.coverView);
        holder.titleView.setText(currentData.getTitle());
        if(currentData.getOwner()!=null){
            holder.upperView.setText(currentData.getOwner().getName());
        }else {
            holder.upperView.setText("空白");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent=new Intent("com.github.welcomeworld.simplebili.action.VIDEODETAIL");
                playIntent.setData(Uri.parse("bilibili://video/"+currentData.getAid()));
                v.getContext().startActivity(playIntent);
            }
        });
        holder.playView.setText(currentData.getStat().getView()+"");
        holder.danmakuView.setText(currentData.getStat().getDanmaku()+"");
    }

    @Override
    public int getItemCount() {
        return data ==null?0:data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerview_watch_later_cover)
        ImageView coverView;
        @BindView(R.id.recyclerview_watch_later_title)
        TextView titleView;
        @BindView(R.id.recyclerview_watch_later_upper)
        TextView upperView;
        @BindView(R.id.recyclerview_watch_later_play_num)
        TextView playView;
        @BindView(R.id.recyclerview_watch_later_danmaku_num)
        TextView danmakuView;


        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
