package com.github.welcomeworld.simplebili.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.welcomeworld.simplebili.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PingRecyclerViewAdapter extends RecyclerView.Adapter<PingRecyclerViewAdapter.MyInnerViewHolder> {
    List<String> data;
    String[] hosts = {"www.bilibili.com","interface.bilibili.com","comment.bilibili.com","api.bilibili.com",
            "app.bilibili.com","passport.bilibili.com","account.bilibili.com","bangumi.bilibili.com",
            "live.bilibili.com","message.bilibili.com","elec.bilibili.com","pay.bilibili.com",
            "secure.bilibili.com","s.search.bilibili.com","chat.bilibili.com","api.biligame.com",
            "apigame.bilibili.com","www.im9.com","acg.tv","static.hdslb.com",
            "i0.hdslb.com","i1.hdslb.com","i2.hdslb.com"
    };

    public PingRecyclerViewAdapter(List<String> data){
        this.data = data;
    }


    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_ping,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        holder.hostView.setText(hosts[position]);
        holder.timeView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size()>hosts.length?hosts.length:data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ping_recyclerView_host)
        TextView hostView;
        @BindView(R.id.ping_recyclerView_time)
        TextView timeView;
        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
