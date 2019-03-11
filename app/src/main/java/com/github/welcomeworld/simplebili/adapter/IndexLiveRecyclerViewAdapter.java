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
import com.github.welcomeworld.simplebili.bean.IndexLiveBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexLiveRecyclerViewAdapter extends RecyclerView.Adapter<IndexLiveRecyclerViewAdapter.MyInnerViewHolder>{
    IndexLiveBean.DataBean data;
    Context context;

    public IndexLiveRecyclerViewAdapter(IndexLiveBean.DataBean data) {
        this.data = data;
    }


    @NonNull
    @Override
    public IndexLiveRecyclerViewAdapter.MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_live_roomlist,parent,false);
        context=parent.getContext();
        return new IndexLiveRecyclerViewAdapter.MyInnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexLiveRecyclerViewAdapter.MyInnerViewHolder holder, int position) {
        IndexLiveBean.DataBean.RoomListBean currentData=data.getRoom_list().get(position);
        holder.headerTitleView.setText(currentData.getModule_info().getTitle());
        holder.categoryView1.setText(currentData.getList().get(0).getArea_v2_name());
        holder.titleView1.setText(currentData.getList().get(0).getTitle());
        Glide.with(context).load(currentData.getList().get(0).getCover()).into(holder.coverView1);
        holder.coverView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent=new Intent("com.github.welcomeworld.simplebili.action.LIVEPLAY");
                playIntent.setData(Uri.parse("bilibili://live/"+currentData.getList().get(0).getRoomid()));
                playIntent.putExtra("url",currentData.getList().get(0).getPlay_url());
                //playIntent.setData(Uri.parse(currentData.getList().get(0).getPlay_url()));
                playIntent.putExtra("title",currentData.getList().get(0).getTitle());
                context.startActivity(playIntent);
            }
        });
        holder.categoryView2.setText(currentData.getList().get(1).getArea_v2_name());
        holder.titleView2.setText(currentData.getList().get(1).getTitle());
        Glide.with(context).load(currentData.getList().get(1).getCover()).into(holder.coverView2);
        holder.coverView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent=new Intent("com.github.welcomeworld.simplebili.action.LIVEPLAY");
                playIntent.setData(Uri.parse("bilibili://live/"+currentData.getList().get(1).getRoomid()));
                playIntent.putExtra("url",currentData.getList().get(1).getPlay_url());
                //playIntent.setData(Uri.parse(currentData.getList().get(0).getPlay_url()));
                playIntent.putExtra("title",currentData.getList().get(1).getTitle());
                context.startActivity(playIntent);
            }
        });
        holder.categoryView3.setText(currentData.getList().get(2).getArea_v2_name());
        holder.titleView3.setText(currentData.getList().get(2).getTitle());
        Glide.with(context).load(currentData.getList().get(2).getCover()).into(holder.coverView3);
        holder.coverView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent=new Intent("com.github.welcomeworld.simplebili.action.LIVEPLAY");
                playIntent.setData(Uri.parse("bilibili://live/"+currentData.getList().get(2).getRoomid()));
                playIntent.putExtra("url",currentData.getList().get(2).getPlay_url());
                //playIntent.setData(Uri.parse(currentData.getList().get(0).getPlay_url()));
                playIntent.putExtra("title",currentData.getList().get(2).getTitle());
                context.startActivity(playIntent);
            }
        });
        holder.categoryView4.setText(currentData.getList().get(3).getArea_v2_name());
        holder.titleView4.setText(currentData.getList().get(3).getTitle());
        Glide.with(context).load(currentData.getList().get(3).getCover()).into(holder.coverView4);
        holder.coverView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent=new Intent("com.github.welcomeworld.simplebili.action.LIVEPLAY");
                playIntent.setData(Uri.parse("bilibili://live/"+currentData.getList().get(3).getRoomid()));
                playIntent.putExtra("url",currentData.getList().get(3).getPlay_url());
                //playIntent.setData(Uri.parse(currentData.getList().get(0).getPlay_url()));
                playIntent.putExtra("title",currentData.getList().get(3).getTitle());
                context.startActivity(playIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(data==null){
            return 0;
        }
        return data.getRoom_list()==null?0:data.getRoom_list().size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.live_header_title)
        TextView headerTitleView;
        @BindView(R.id.live_1_category)
        TextView categoryView1;
        @BindView(R.id.live_1_cover)
        ImageView coverView1;
        @BindView(R.id.live_1_title)
        TextView titleView1;
        @BindView(R.id.live_2_category)
        TextView categoryView2;
        @BindView(R.id.live_2_cover)
        ImageView coverView2;
        @BindView(R.id.live_2_title)
        TextView titleView2;
        @BindView(R.id.live_3_category)
        TextView categoryView3;
        @BindView(R.id.live_3_cover)
        ImageView coverView3;
        @BindView(R.id.live_3_title)
        TextView titleView3;
        @BindView(R.id.live_4_category)
        TextView categoryView4;
        @BindView(R.id.live_4_cover)
        ImageView coverView4;
        @BindView(R.id.live_4_title)
        TextView titleView4;


        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
