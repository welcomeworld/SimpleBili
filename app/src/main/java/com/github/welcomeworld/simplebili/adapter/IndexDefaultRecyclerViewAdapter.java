package com.github.welcomeworld.simplebili.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.welcomeworld.simplebili.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexDefaultRecyclerViewAdapter extends RecyclerView.Adapter<IndexDefaultRecyclerViewAdapter.MyInnerViewHolder> {

    List<String> data;

    public IndexDefaultRecyclerViewAdapter(List<String> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_recommend,parent,false);
        return new MyInnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        //holder.titleView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.card_video_label)
        TextView titleView;
        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
