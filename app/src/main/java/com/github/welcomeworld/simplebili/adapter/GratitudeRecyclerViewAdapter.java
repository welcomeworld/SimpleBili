package com.github.welcomeworld.simplebili.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.welcomeworld.simplebili.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GratitudeRecyclerViewAdapter extends RecyclerView.Adapter<GratitudeRecyclerViewAdapter.MyInnerViewHolder> {
    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
