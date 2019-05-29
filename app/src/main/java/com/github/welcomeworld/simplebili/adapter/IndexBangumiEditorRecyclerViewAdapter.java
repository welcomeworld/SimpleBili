package com.github.welcomeworld.simplebili.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.github.welcomeworld.simplebili.bean.IndexBangumiBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexBangumiEditorRecyclerViewAdapter extends RecyclerView.Adapter<IndexBangumiEditorRecyclerViewAdapter.MyInnerViewHolder>{

    IndexBangumiBean.ResultBean.ModulesBean data;
    Context context;

    public IndexBangumiEditorRecyclerViewAdapter(IndexBangumiBean.ResultBean.ModulesBean data){
        this.data=data;
    }
    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_bangumi_editor,parent,false);
        context=parent.getContext();
        return new MyInnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        IndexBangumiBean.ResultBean.ModulesBean.ItemsBean currentData=data.getItems().get(position);
        Glide.with(context).load(currentData.getCover()+"@680w_200h_1e_1c.webp ").apply(new RequestOptions().transform(new FitCenter(),new RoundedCorners(10))).into(holder.coverView);
        holder.titleView.setText(currentData.getTitle());
        if(currentData.getDesc()==null){
            holder.descView.setVisibility(View.GONE);
        }else{
            holder.descView.setText(currentData.getDesc());
        }
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.getItems().size();
    }

    class MyInnerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.editor_cover)
        ImageView coverView;
        @BindView(R.id.editor_title)
        TextView titleView;
        @BindView(R.id.editor_desc)
        TextView descView;
        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
