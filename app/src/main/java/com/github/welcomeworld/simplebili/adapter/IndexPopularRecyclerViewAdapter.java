package com.github.welcomeworld.simplebili.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
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
import com.github.welcomeworld.simplebili.bean.IndexLiveBean;
import com.github.welcomeworld.simplebili.bean.IndexPopularBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.netty.util.Recycler;

public class IndexPopularRecyclerViewAdapter  extends RecyclerView.Adapter<IndexPopularRecyclerViewAdapter.MyInnerViewHolder>{

    IndexPopularBean data;
    Context context;

    public IndexPopularRecyclerViewAdapter(IndexPopularBean data){
        this.data=data;
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_popular,parent,false);
        context=parent.getContext();
        return new IndexPopularRecyclerViewAdapter.MyInnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        IndexPopularBean.DataBean currentData=data.getData().get(position);
        Glide.with(context).load(currentData.getCover()+"@320w_200h_1e_1c.webp").apply(new RequestOptions().transforms(new FitCenter(),new RoundedCorners(10))).into(holder.coverView);
        holder.titleView.setText(currentData.getTitle());
        holder.upperView.setText(currentData.getRight_desc_1());
        holder.descView.setText(currentData.getRight_desc_2());
        holder.recommendView.setText(currentData.getRcmd_reason_style()==null?"":currentData.getRcmd_reason_style().getText());
        holder.coverView.setOnClickListener(new View.OnClickListener() {
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
        return data==null?0:data.getData().size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_video_cover)
        ImageView coverView;
        @BindView(R.id.card_video_title)
        TextView titleView;
        @BindView(R.id.card_upper)
        TextView upperView;
        @BindView(R.id.card_recommend)
        TextView recommendView;
        @BindView(R.id.card_desc)
        TextView descView;

        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
