package com.github.welcomeworld.simplebili.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
import com.github.welcomeworld.simplebili.bean.SearchResultBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultRecyclerViewAdapter extends RecyclerView.Adapter<SearchResultRecyclerViewAdapter.MyInnerViewHolder> {
    List<SearchResultBean.DataBean.ItemBean> data;
    public SearchResultRecyclerViewAdapter(List<SearchResultBean.DataBean.ItemBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_search_result,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        SearchResultBean.DataBean.ItemBean currentData=data.get(position);
        Glide.with(holder.coverView).load(currentData.getCover()+"@320w_200h_1e_1c.webp").apply(new RequestOptions().transform(new FitCenter(),new RoundedCorners(10))).into(holder.coverView);
        holder.titleView.setText(Html.fromHtml(currentData.getTitle()));
        holder.upperView.setText(currentData.getAuthor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent=new Intent("com.github.welcomeworld.simplebili.action.VIDEODETAIL");
                playIntent.setData(Uri.parse(currentData.getUri()));
                v.getContext().startActivity(playIntent);
            }
        });
        holder.playView.setText(currentData.getPlay()+"");
        holder.danmakuView.setText(currentData.getDanmaku()+"");
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerview_search_result_cover)
        ImageView coverView;
        @BindView(R.id.recyclerview_search_result_title)
        TextView titleView;
        @BindView(R.id.recyclerview_search_result_upper)
        TextView upperView;
        @BindView(R.id.recyclerview_search_result_play_num)
        TextView playView;
        @BindView(R.id.recyclerview_search_result_danmaku_num)
        TextView danmakuView;


        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
