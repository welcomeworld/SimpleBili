package com.github.welcomeworld.simplebili.adapter;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.welcomeworld.simplebili.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemeRecyclerViewAdapter extends RecyclerView.Adapter<ThemeRecyclerViewAdapter.MyInnerViewHolder> {

    String[] themes = {"少女粉","姨妈红","咸蛋黄","早苗绿",
            "胖次蓝","基佬紫"
    };
    int[] colors ={R.color.colorPrimary,R.color.red_theme,R.color.yellow_theme,R.color.green_theme,R.color.blue_theme,R.color.violet_theme};
    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_theme,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        holder.titleView.setText(themes[position]);
        holder.headerView.setBackgroundResource(colors[position]);
        if(PreferenceManager.getDefaultSharedPreferences(holder.actionView.getContext()).getInt("theme",0) == position){
            holder.actionView.setVisibility(View.VISIBLE);
        }else {
            holder.actionView.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(PreferenceManager.getDefaultSharedPreferences(holder.actionView.getContext()).getInt("theme",0));
                holder.actionView.setVisibility(View.VISIBLE);
                PreferenceManager.getDefaultSharedPreferences(v.getContext()).edit().putInt("theme",position).apply();
                Toast.makeText(v.getContext(),"设置主题成功，重启生效^_^",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.recyclerview_theme_title)
        TextView titleView;
        @BindView(R.id.recyclerview_theme_action)
        ImageView actionView;
        @BindView(R.id.recyclerview_theme_header)
        ImageView headerView;
        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
