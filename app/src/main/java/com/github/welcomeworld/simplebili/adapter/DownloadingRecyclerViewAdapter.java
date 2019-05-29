package com.github.welcomeworld.simplebili.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;;
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
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.DownloadInfoBean;
import com.github.welcomeworld.simplebili.utils.DownloadManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadingRecyclerViewAdapter extends RecyclerView.Adapter<DownloadingRecyclerViewAdapter.MyInnerViewHolder> {
    SparseArray<DownloadInfoBean> data;
    public DownloadingRecyclerViewAdapter(SparseArray<DownloadInfoBean> data) {
        this.data = data;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_downloading,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        data.keyAt(position);
        DownloadInfoBean currentData = data.get(data.keyAt(position));
        holder.titleView.setText(currentData.getTitle());
        Glide.with(holder.coverView).load(currentData.getCover()+"@320w_200h_1e_1c.webp").apply(new RequestOptions().transform(new CenterCrop(),new RoundedCorners(10))).into(holder.coverView);
        if(currentData.getDownloadState() == DownloadInfoBean.DOWNLOADING){
            holder.descView.setText(R.string.downloading);
        }else if(currentData.getDownloadState() == DownloadInfoBean.PREPARED){
            holder.descView.setText("正在准备");
        }else if(currentData.getDownloadState() == DownloadInfoBean.COMPLETE){
            holder.descView.setText("下载完成");
        }else if(currentData.getDownloadState() == DownloadInfoBean.ERROR){
            holder.descView.setText("发生错误");
        }else if(currentData.getDownloadState() == DownloadInfoBean.PAUSE){
            holder.descView.setText(R.string.pause);
        }
        holder.detailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentData.getOriginalUrl()==null||currentData.getOriginalUrl().isEmpty()){
                    return;
                }
                if(currentData.getType() == DownloadInfoBean.BANGUMI_TYPE){
                    Intent bangumiIntent = new Intent("com.github.welcomeworld.simplebili.action.BANGUMIDETAIL");
                    bangumiIntent.setData(Uri.parse(currentData.getOriginalUrl()));
                    v.getContext().startActivity(bangumiIntent);
                }else if(currentData.getType() == DownloadInfoBean.VIDEO_TYPE){
                    Intent videoIntent = new Intent("com.github.welcomeworld.simplebili.action.VIDEODETAIL");
                    videoIntent.setData(Uri.parse(currentData.getOriginalUrl()));
                    v.getContext().startActivity(videoIntent);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentData.getDownloadState() == DownloadInfoBean.DOWNLOADING||currentData.getDownloadState() == DownloadInfoBean.PREPARED){
                    DownloadManager.getInstance().pauseDownload(currentData);
                }else {
                    DownloadManager.getInstance().preparedDownload(currentData);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recyclerview_downloading_title)
        TextView titleView;
        @BindView(R.id.recyclerview_downloading_cover)
        ImageView coverView;
        @BindView(R.id.recyclerview_downloading_desc)
        TextView descView;
        @BindView(R.id.recyclerview_downloading_detail)
        TextView detailView;

        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
