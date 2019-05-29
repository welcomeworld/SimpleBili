package com.github.welcomeworld.simplebili.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.VideoDetailPageBean;
import com.github.welcomeworld.simplebili.utils.DateUtils;
import com.github.welcomeworld.simplebili.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoDetailDescRecyclerViewAdapter extends RecyclerView.Adapter<VideoDetailDescRecyclerViewAdapter.MyInnerViewHolder>{
    private VideoDetailPageBean.DataBean data;
    Context context;

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    private View.OnClickListener listener;

    private static final int HEADERVIEWTYPE=0x233;

    public VideoDetailDescRecyclerViewAdapter(VideoDetailPageBean.DataBean data){
        this.data=data;
    }

    @NonNull
    @Override
    public VideoDetailDescRecyclerViewAdapter.MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==HEADERVIEWTYPE){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_desc_first,parent,false);
        }else{
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_desc_related,parent,false);
        }
        context=parent.getContext();
        return new VideoDetailDescRecyclerViewAdapter.MyInnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoDetailDescRecyclerViewAdapter.MyInnerViewHolder holder, int position) {
        if(getItemViewType(position)==HEADERVIEWTYPE){
            holder.titleView.setText(data.getTitle());
            holder.uppernameView.setText(data.getOwner().getName());
            Glide.with(context).load(data.getOwner().getFace()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new FitCenter(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(holder.avatorView.getDrawable())).into(holder.avatorView);
            holder.followNumView.setText(StringUtils.formatNumber(data.getOwner_ext().getFans())+"粉丝");
            holder.playNumView.setText(data.getStat().getView()+"");
            holder.danmakuNumView.setText(data.getStat().getDanmaku()+"");
            holder.dateView.setText(DateUtils.getDay((long)data.getPubdate()*1000));
            holder. aidView.setText("av"+data.getAid());
            holder.descDescView.setText(data.getDesc());
            holder.likeView.setText(StringUtils.formatNumber(data.getStat().getLike()));
            holder.coinView.setText(StringUtils.formatNumber(data.getStat().getCoin()));
            holder.favoriteView.setText(StringUtils.formatNumber(data.getStat().getFavorite()));
            holder.downloadView.setOnClickListener(listener);
        }else{
            VideoDetailPageBean.DataBean.RelatesBean currentData=data.getRelates().get(position-1);
            Glide.with(context).load(currentData.getPic()+"@320w_200h_1e_1c.webp").apply(new RequestOptions().transforms(new FitCenter(),new RoundedCorners(10))).into(holder.coverView);
            holder.relatedTitleView.setText(currentData.getTitle());
            if(currentData.getOwner()==null){
                holder.upperView.setText("用户信息为空？");
            }else{
                holder.upperView.setText(currentData.getOwner().getName());
            }
            holder.relatedLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent playIntent=new Intent("com.github.welcomeworld.simplebili.action.VIDEODETAIL");
                    playIntent.setData(Uri.parse(currentData.getUri()));
                    context.startActivity(playIntent);
                }
            });
            holder.playView.setText(currentData.getStat().getView()+"");
            holder.danmakuView.setText(currentData.getStat().getDanmaku()+"");
        }
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.getRelates().size()+1;
    }


    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return HEADERVIEWTYPE;
        }
        return super.getItemViewType(position);
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.related_cover)
        ImageView coverView;
        @Nullable
        @BindView(R.id.related_title)
        TextView relatedTitleView;
        @Nullable
        @BindView(R.id.related_upper)
        TextView upperView;
        @Nullable
        @BindView(R.id.related_play_num)
        TextView playView;
        @Nullable
        @BindView(R.id.related_danmaku_num)
        TextView danmakuView;
        @Nullable
        @BindView(R.id.related_layout)
        ConstraintLayout relatedLayout;


        @Nullable
        @BindView(R.id.video_desc_title)
        TextView titleView;
        @Nullable
        @BindView(R.id.video_desc_upname)
        TextView uppernameView;
        @Nullable
        @BindView(R.id.video_desc_avator)
        ImageView avatorView;
        @Nullable
        @BindView(R.id.video_desc_follow_num)
        TextView followNumView;
        @Nullable
        @BindView(R.id.video_desc_play_num)
        TextView playNumView;
        @Nullable
        @BindView(R.id.video_desc_danmaku_num)
        TextView danmakuNumView;
        @Nullable
        @BindView(R.id.video_desc_date)
        TextView dateView;
        @Nullable
        @BindView(R.id.video_desc_aid)
        TextView aidView;
        @Nullable
        @BindView(R.id.video_desc_desc)
        TextView descDescView;
        @Nullable
        @BindView(R.id.video_desc_like)
        TextView likeView;
        @Nullable
        @BindView(R.id.video_desc_dislike)
        TextView dislikeView;
        @Nullable
        @BindView(R.id.video_desc_coin)
        TextView coinView;
        @Nullable
        @BindView(R.id.video_desc_favorite)
        TextView favoriteView;
        @Nullable
        @BindView(R.id.video_desc_download)
        TextView downloadView;

        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
