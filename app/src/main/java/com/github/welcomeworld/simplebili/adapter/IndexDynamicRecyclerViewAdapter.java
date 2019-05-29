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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.IndexDynamicBangumiCardBean;
import com.github.welcomeworld.simplebili.bean.IndexDynamicBean;
import com.github.welcomeworld.simplebili.bean.IndexDynamicVideoCardBean;
import com.github.welcomeworld.simplebili.utils.StringUtils;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexDynamicRecyclerViewAdapter extends RecyclerView.Adapter<IndexDynamicRecyclerViewAdapter.MyInnerViewHolder> {

    List<IndexDynamicBean.DataBean.CardsBean> data;

    public IndexDynamicRecyclerViewAdapter(List<IndexDynamicBean.DataBean.CardsBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_index_dynamic,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        IndexDynamicBean.DataBean.CardsBean currentData = data.get(position);
        if(currentData.getDesc().getType() == 512 ){   //番剧
            IndexDynamicBangumiCardBean card = new Gson().fromJson(currentData.getCard(),IndexDynamicBangumiCardBean.class);
            Glide.with(holder.avatorView).load(card.getApiSeasonInfo().getCover()+"@200w_200h_1e_1c.webp ").apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(holder.avatorView.getDrawable())).into(holder.avatorView);
            holder.nameView.setText(card.getApiSeasonInfo().getTitle());
            holder.updateView.setText(holder.updateView.getContext().getResources().getString(R.string.dynamic_bangumi_update,StringUtils.formatDate(currentData.getDesc().getTimestamp(),StringUtils.SECOND)));
            holder.replyView.setText(""+card.getReplyCount());
            holder.shareView.setText(""+currentData.getDesc().getRepost());
            holder.likeView.setText(""+currentData.getDesc().getLike());
            holder.titleView.setText(card.getIndexTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bangumiIntent = new Intent("com.github.welcomeworld.simplebili.action.BANGUMIDETAIL");
                    bangumiIntent.setData(Uri.parse(card.getUrl()));
                    v.getContext().startActivity(bangumiIntent);
                }
            });
            Glide.with(holder.coverView).load(card.getCover()+"@720w_405h_1e_1c.webp ").apply(new RequestOptions().transform(new CenterCrop(),new RoundedCorners(10))).into(holder.coverView);
        }else if(currentData.getDesc().getType() == 8){  //视频
            IndexDynamicVideoCardBean card = new Gson().fromJson(currentData.getCard(), IndexDynamicVideoCardBean.class);
            Glide.with(holder.avatorView).load(currentData.getDesc().getUserProfile().getInfo().getFace()+"@200w_200h_1e_1c.webp ").apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(holder.avatorView.getDrawable())).into(holder.avatorView);
            holder.nameView.setText(currentData.getDesc().getUserProfile().getInfo().getUname());
            holder.descView.setText(card.getDynamic());
            holder.updateView.setText(holder.updateView.getContext().getResources().getString(R.string.dynamic_video_update,StringUtils.formatDate(currentData.getDesc().getTimestamp(),StringUtils.SECOND)));
            holder.shareView.setText(""+currentData.getDesc().getRepost());
            holder.replyView.setText(""+card.getStat().getReply());
            holder.likeView.setText(""+card.getStat().getLike());
            holder.titleView.setText(card.getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent videoIntent = new Intent("com.github.welcomeworld.simplebili.action.VIDEODETAIL");
                    videoIntent.setData(Uri.parse(card.getJumpUrl()));
                    v.getContext().startActivity(videoIntent);
                }
            });
            Glide.with(holder.coverView).load(card.getPic()+"@720w_405h_1e_1c.webp ").apply(new RequestOptions().transform(new CenterCrop(),new RoundedCorners(10))).into(holder.coverView);
        }
    }

    @Override
    public int getItemCount() {
        return data ==null?0:data.size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.recyclerview_index_dynamic_cover)
        ImageView coverView;
        @BindView(R.id.recyclerview_index_dynamic_title)
        TextView titleView;
        @BindView(R.id.recyclerview_index_dynamic_name)
        TextView nameView;
        @BindView(R.id.recyclerview_index_dynamic_update_time)
        TextView updateView;
        @BindView(R.id.recyclerview_index_dynamic_desc)
        TextView descView;
        @BindView(R.id.recyclerview_index_dynamic_avator)
        ImageView avatorView;
        @BindView(R.id.recyclerview_index_dynamic_share_num)
        TextView shareView;
        @BindView(R.id.recyclerview_index_dynamic_reply_num)
        TextView replyView;
        @BindView(R.id.recyclerview_index_dynamic_like_num)
        TextView likeView;


        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
