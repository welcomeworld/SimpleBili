package com.github.welcomeworld.simplebili.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.NotificationReplyBean;
import com.github.welcomeworld.simplebili.widget.BiliTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<NotificationRecyclerViewAdapter.MyInnerViewHolder> {
    List<NotificationReplyBean.DataBean> data;
    public NotificationRecyclerViewAdapter(List<NotificationReplyBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_notification,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        NotificationReplyBean.DataBean currentData = data.get(position);
        holder.nameView.setText(currentData.getPublisher().getName());
        holder.descView.setText(currentData.getContent());
        Glide.with(holder.avatorView).load(currentData.getPublisher().getFace()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(holder.avatorView.getDrawable())).into(holder.avatorView);
        holder.timeView.setText(currentData.getTimeAt());
        holder.referenceView.setText(currentData.getTitle());
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.recyclerview_notification_avator)
        ImageView avatorView;

        @BindView(R.id.recyclerview_notification_name)
        TextView nameView;

        @BindView(R.id.recyclerview_notification_desc)
        BiliTextView descView;

        @BindView(R.id.recyclerview_notification_update_time)
        TextView timeView;

        @BindView(R.id.recyclerview_notification_reference)
        BiliTextView referenceView;





        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
