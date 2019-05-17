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
import com.github.welcomeworld.simplebili.bean.FavoriteTopicBean;
import com.github.welcomeworld.simplebili.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteTopicRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteTopicRecyclerViewAdapter.MyInnerViewHolder> {

    List<FavoriteTopicBean.DataBean.ItemsBean> data;

    public FavoriteTopicRecyclerViewAdapter(List<FavoriteTopicBean.DataBean.ItemsBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_favorite_topic,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        FavoriteTopicBean.DataBean.ItemsBean currentData = data.get(position);
        if(currentData.getH5Cover()!=null&&!currentData.getH5Cover().isEmpty()){
            Glide.with(holder.coverView).load(currentData.getH5Cover()).apply(new RequestOptions().transform(new CenterCrop(),new RoundedCorners(10))).into(holder.coverView);
        }else {
            holder.coverView.setBackgroundResource(R.drawable.background_gray_corner);
            holder.coverView.setImageResource(R.mipmap.ic_no_repost);
        }
        holder.titleView.setText(currentData.getName());
        holder.timeView.setText(holder.timeView.getContext().getResources().getString(R.string.favorite_topic_time, StringUtils.formatDate(currentData.getFavAt(),StringUtils.SECOND)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.github.welcomeworld.simplebili.action.BROWSER");
                intent.setData(Uri.parse(currentData.getH5Url()));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data ==null?0:data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.favorite_topic_recyclerview_time)
        TextView timeView;

        @BindView(R.id.favorite_topic_recyclerview_cover)
        ImageView coverView;

        @BindView(R.id.favorite_topic_recyclerview_title)
        TextView titleView;



        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
