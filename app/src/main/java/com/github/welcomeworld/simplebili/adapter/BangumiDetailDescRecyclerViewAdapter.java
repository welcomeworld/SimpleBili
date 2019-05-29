package com.github.welcomeworld.simplebili.adapter;

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
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.BangumiDetailRecommendBean;
import com.github.welcomeworld.simplebili.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BangumiDetailDescRecyclerViewAdapter extends RecyclerView.Adapter<BangumiDetailDescRecyclerViewAdapter.MyInnerViewHolder> {

    private BangumiDetailRecommendBean.ResultBean data;

    BangumiDetailDescRecyclerViewAdapter(BangumiDetailRecommendBean.ResultBean data){
        this.data = data;
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_bangumi_desc_recommend,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        BangumiDetailRecommendBean.ResultBean.SeasonBean currentData = data.getSeason().get(position);
        Glide.with(holder.coverView).load(currentData.getNewEp().getCover()+"@320w_200h_1e_1c.webp").apply(new RequestOptions().transform(new FitCenter(),new RoundedCorners(10))).into(holder.coverView);
        holder.titleView.setText(currentData.getTitle());
        holder.descView.setText(currentData.getNewEp().getIndexShow());
        holder.playView.setText(StringUtils.formatNumber(currentData.getStat().getView()));
        holder.subscribeView.setText(StringUtils.formatNumber(currentData.getStat().getFollow()));
        if(currentData.getRating()==null){
            holder.evaluateNumView.setVisibility(View.INVISIBLE);
            holder.evaluateView.setVisibility(View.INVISIBLE);
            holder.evaluateSuffixView.setVisibility(View.INVISIBLE);
        }else {
            holder.evaluateNumView.setVisibility(View.VISIBLE);
            holder.evaluateView.setVisibility(View.VISIBLE);
            holder.evaluateSuffixView.setVisibility(View.VISIBLE);
            holder.evaluateView.setText(currentData.getRating().getScore()+"");
            holder.evaluateNumView.setText(StringUtils.formatNumber(currentData.getRating().getCount())+"人");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bangumiIntent = new Intent("com.github.welcomeworld.simplebili.action.BANGUMIDETAIL");
                bangumiIntent.setData(Uri.parse(currentData.getUrl()));
                v.getContext().startActivity(bangumiIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data==null?0:data.getSeason().size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bangumi_detail_desc_recommend_cover)
        ImageView coverView;

        @BindView(R.id.bangumi_detail_desc_recommend_title)
        TextView titleView;

        @BindView(R.id.bangumi_detail_desc_recommend_desc)
        TextView descView;

        @BindView(R.id.bangumi_detail_desc_recommend_play_num)
        TextView playView;

        @BindView(R.id.bangumi_detail_desc_recommend_subscribe_num)
        TextView subscribeView;

        @BindView(R.id.bangumi_detail_desc_recommend_layout)
        ConstraintLayout recommendLayout;

        @BindView(R.id.bangumi_detail_desc_recommend_evaluate_text)
        TextView evaluateView;

        @BindView(R.id.bangumi_detail_desc_recommend_evaluate_num)
        TextView evaluateNumView;

        @BindView(R.id.bangumi_detail_desc_recommend_evaluate_suffix)
        TextView evaluateSuffixView;


        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
