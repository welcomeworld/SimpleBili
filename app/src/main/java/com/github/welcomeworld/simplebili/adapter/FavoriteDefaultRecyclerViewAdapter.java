package com.github.welcomeworld.simplebili.adapter;

import android.content.Intent;
import android.os.Bundle;
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
import com.github.welcomeworld.simplebili.bean.FavoriteDefaultBean;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteDefaultRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteDefaultRecyclerViewAdapter.MyInnerViewHolder> {
    FavoriteDefaultBean.DataBean.FavoriteBean data;

    private static final int FIRSTTYPE = 0x233;
    private static final int SECONDTYPE = 0x234;
    private static final int THREETYPE = 0x235;

    public FavoriteDefaultRecyclerViewAdapter(FavoriteDefaultBean.DataBean.FavoriteBean data){
        this.data = data;
    }

    private boolean open = true;


    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == FIRSTTYPE){
           return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_favorite_default_first,parent,false));
        }else if(viewType == SECONDTYPE){
            return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_favorite_default_second,parent,false));
        }else if(viewType == THREETYPE){
            return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_favorite_default_three,parent,false));
        }
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_favorite_default_item,parent,false));
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return FIRSTTYPE;
        }else if(position == getItemCount()-2){
            return SECONDTYPE;
        }else if(position == getItemCount() -1){
            return THREETYPE;
        }
        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        if(getItemViewType(position) == THREETYPE){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return;
        }
        if(getItemViewType(position) == FIRSTTYPE){
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 open = !open;
                 holder.headerView.setEnabled(open);
                 notifyDataSetChanged();
             }
         });
         return;
        }
        if(getItemViewType(position) == SECONDTYPE){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return;
        }
        if(open&&holder.coverView!=null){
            FavoriteDefaultBean.DataBean.FavoriteBean.ItemsBean currentData = data.getItems().get(position-1);
            holder.titleView.setText(currentData.getName());
            holder.descView.setText(holder.descView.getContext().getString(R.string.favorite_list_desc,currentData.getCurCount(),currentData.getState() == 3?"私密":"公开"));
            Glide.with(holder.coverView).load(currentData.getCover().get(0).getPic()+"@320w_200h_1e_1c.webp").apply(new RequestOptions().transform(new CenterCrop(),new RoundedCorners(10))).into(holder.coverView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent listIntent = new Intent("com.github.welcomeworld.simplebili.action.FAVORITELIST");
                    listIntent.putExtra("name",currentData.getName());
                    listIntent.putExtra("fid",currentData.getFid());
                    listIntent.putExtra("mid",currentData.getMid());
                    v.getContext().startActivity(listIntent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data!=null&&open?data.getCount()+3:3;
    }


    class MyInnerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.recyclerview_favorite_default_first_header)
        @Nullable
        ImageView headerView;

        @BindView(R.id.recyclerview_favorite_default_item_cover)
        @Nullable
        ImageView coverView;

        @BindView(R.id.recyclerview_favorite_default_item_title)
        @Nullable
        TextView titleView;

        @BindView(R.id.recyclerview_favorite_default_item_desc)
        @Nullable
        TextView descView;



        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
