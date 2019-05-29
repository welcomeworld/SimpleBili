package com.github.welcomeworld.simplebili.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.BangumiDetailActivity;
import com.github.welcomeworld.simplebili.PlayActivity;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.VideoDetailActivity;
import com.github.welcomeworld.simplebili.bean.DownloadInfoBean;
import com.github.welcomeworld.simplebili.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadedRecyclerViewAdapter extends RecyclerView.Adapter<DownloadedRecyclerViewAdapter.MyInnerViewHolder> {
    SparseArray<DownloadInfoBean> completedData;
    public DownloadedRecyclerViewAdapter(SparseArray<DownloadInfoBean> completedData) {
        this.completedData = completedData;
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_downloaded,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        completedData.keyAt(position);
        DownloadInfoBean currentData = completedData.get(completedData.keyAt(position));
        holder.titleView.setText(currentData.getTitle());
        Glide.with(holder.coverView).load(currentData.getCover()+"@320w_200h_1e_1c.webp").apply(new RequestOptions().transform(new CenterCrop(),new RoundedCorners(10))).into(holder.coverView);
        holder.descView.setText(StringUtils.formatFlow(currentData.getContentLength()));
        holder.detailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentData.getOriginalUrl()==null||currentData.getOriginalUrl().isEmpty()){
                    return;
                }
                if(currentData.getType() == DownloadInfoBean.BANGUMI_TYPE){
                    Intent bangumiIntent = new Intent(v.getContext(), BangumiDetailActivity.class);
                    bangumiIntent.setData(Uri.parse(currentData.getOriginalUrl()));
                    v.getContext().startActivity(bangumiIntent);
                }else if(currentData.getType() == DownloadInfoBean.VIDEO_TYPE){
                    Intent videoIntent = new Intent(v.getContext(), VideoDetailActivity.class);
                    videoIntent.setData(Uri.parse(currentData.getOriginalUrl()));
                    v.getContext().startActivity(videoIntent);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(v.getContext(), PlayActivity.class);
                playIntent.putExtra("real_path",true);
                playIntent.setData(Uri.parse(currentData.getLocalPath()));
                v.getContext().startActivity(playIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return completedData == null?0:completedData.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recyclerview_downloaded_title)
        TextView titleView;
        @BindView(R.id.recyclerview_downloaded_cover)
        ImageView coverView;
        @BindView(R.id.recyclerview_downloaded_desc)
        TextView descView;
        @BindView(R.id.recyclerview_downloaded_detail)
        TextView detailView;
        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
