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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.FavoriteBangumiBean;
import com.github.welcomeworld.simplebili.bean.FavoriteCinemaBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteCinemaRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteCinemaRecyclerViewAdapter.MyInnerViewHolder> {

    List<FavoriteCinemaBean.ResultBean> data;

    public FavoriteCinemaRecyclerViewAdapter(List<FavoriteCinemaBean.ResultBean> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_favorite_cinema,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        FavoriteCinemaBean.ResultBean currentData = data.get(position);
        holder.titleView.setText(currentData.getTitle());
        holder.descView.setText(holder.descView.getContext().getResources().getString(R.string.favorite_cinema_desc,currentData.getSeasonTypeName(),currentData.getAreas().get(0).getName()));
        holder.progressView.setText(currentData.getProgress());
        Glide.with(holder.coverView).load(currentData.getCover()).apply(new RequestOptions().transform(new CenterCrop(),new RoundedCorners(10))).into(holder.coverView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bangumiIntent = new Intent("com.github.welcomeworld.simplebili.action.BANGUMIDETAIL");
                bangumiIntent.setData(Uri.parse("https://www.bilibili.com/bangumi/play/ss"+currentData.getSeasonId()));
                v.getContext().startActivity(bangumiIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.favorite_cinema_recyclerview_progress)
        TextView progressView;

        @BindView(R.id.favorite_cinema_recyclerview_cover)
        ImageView coverView;

        @BindView(R.id.favorite_cinema_recyclerview_title)
        TextView titleView;

        @BindView(R.id.favorite_cinema_recyclerview_desc)
        TextView descView;



        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
