package com.github.welcomeworld.simplebili.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.IndexRecommendDataBean;
import com.github.welcomeworld.simplebili.utils.StringUtils;
import com.google.gson.Gson;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexRecommendRecyclerViewAdapter extends RecyclerView.Adapter<IndexRecommendRecyclerViewAdapter.MyInnerViewHolder>{

    List<IndexRecommendDataBean> data;
    Context context;

    public IndexRecommendRecyclerViewAdapter(List<IndexRecommendDataBean> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public IndexRecommendRecyclerViewAdapter.MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_recommend,parent,false);
        context=parent.getContext();
        return new IndexRecommendRecyclerViewAdapter.MyInnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexRecommendRecyclerViewAdapter.MyInnerViewHolder holder, int position) {
        IndexRecommendDataBean currentData=data.get(position);
        if(currentData.getTag()==null){
            Gson gson=new Gson();
            Log.e("api",gson.toJson(currentData));
            return;
        }
        holder.titleView.setText(currentData.getTitle());
        holder.tagView.setText(String.format(Locale.CHINA,"%s·%s",currentData.getTname(),currentData.getTag().getTag_name()));
        Glide.with(context).load(currentData.getCover()+"@320w_200h_1e_1c.webp").into(holder.coverView);
        holder.danmakuView.setText(StringUtils.formatNumber(currentData.getDanmaku()));
        holder.playView.setText(StringUtils.formatNumber(currentData.getPlay()));
        holder.durationView.setText(StringUtils.formatTime(currentData.getDuration(),StringUtils.SECOND));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent=new Intent("com.github.welcomeworld.simplebili.action.VIDEODETAIL");
                playIntent.setData(Uri.parse(currentData.getUri()));
                context.startActivity(playIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.card_video_title)
        TextView titleView;
        @BindView(R.id.card_video_label)
        TextView tagView;
        @BindView(R.id.card_video_cover)
        ImageView coverView;
        @BindView(R.id.card_video_danmaku_num)
        TextView danmakuView;
        @BindView(R.id.card_video_play_num)
        TextView playView;
        @BindView(R.id.card_video_duration)
        TextView durationView;
        @BindView(R.id.recommend_card)
        CardView cardView;
        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
