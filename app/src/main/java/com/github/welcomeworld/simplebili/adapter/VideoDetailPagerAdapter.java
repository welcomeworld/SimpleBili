package com.github.welcomeworld.simplebili.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.ReplyCursorBean;
import com.github.welcomeworld.simplebili.bean.VideoDetailPageBean;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;

public class VideoDetailPagerAdapter extends PagerAdapter {

    private boolean descChanged=false;
    private boolean replyChanged=false;

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    private View.OnClickListener listener;
    private VideoDetailReplyRecyclerViewAdapter replyRecyclerViewAdapter;

    public int getMaxId() {
        return maxId;
    }

    private int maxId=0;



    public void setLoadListener(SwiperefreshContainer.OnLoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public void setRefreshListener(SwiperefreshContainer.OnRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    public void setRefreshing(boolean refreshing){
        if(replyView!=null){
            replyView.setRefreshing(refreshing);
        }
    }

    public void setLoading(boolean loading){
        if(replyView!=null){
            replyView.setLoading(loading);
        }
    }


    private SwiperefreshContainer.OnLoadListener loadListener;
    private SwiperefreshContainer.OnRefreshListener refreshListener;
    private RecyclerView descView;
    private SwiperefreshContainer replyView;


    public void setDescData(VideoDetailPageBean.DataBean descData) {
        this.descData = descData;
        descChanged=true;
        notifyDataSetChanged();
    }

    public void setReplyData(ReplyCursorBean.DataBean replyData) {
        this.replyData = replyData;
        if(replyData!=null){
            if(replyData.getCursor().getMin_id()>1){
                maxId=replyData.getCursor().getMin_id()-1;
            }else{
                maxId=0;
            }
            replyChanged=true;
            notifyDataSetChanged();
        }
    }

    public void addReplyData(ReplyCursorBean.DataBean replyData){
        if(this.replyData==null){
            setReplyData(replyData);
            return;
        }
        if(replyData!=null){
            int index=replyRecyclerViewAdapter.getItemCount();
            this.replyData.getReplies().addAll(replyData.getReplies());
            if(replyData.getCursor().getMin_id()>1){
                maxId=replyData.getCursor().getMin_id()-1;
            }else{
                maxId=0;
            }
            replyRecyclerViewAdapter.notifyItemRangeInserted(index,replyData.getCursor().getSize());
        }
    }

    private VideoDetailPageBean.DataBean descData;
    private ReplyCursorBean.DataBean replyData;
    public VideoDetailPagerAdapter(VideoDetailPageBean.DataBean descData,ReplyCursorBean.DataBean replyData) {
        this.descData=descData;
        this.replyData=replyData;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if(object==descView){
            return descChanged?POSITION_NONE:POSITION_UNCHANGED;
        }else{
            return replyChanged?POSITION_NONE:POSITION_UNCHANGED;
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if(position==0){
            descView= (RecyclerView)LayoutInflater.from(container.getContext()).inflate(R.layout.video_detail_desc,container,false);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(container.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            descView.setLayoutManager(linearLayoutManager);
            VideoDetailDescRecyclerViewAdapter recyclerViewAdapter = new VideoDetailDescRecyclerViewAdapter(descData);
            recyclerViewAdapter.setListener(listener);
            descView.setAdapter(recyclerViewAdapter);
            container.addView(descView);
            return descView;
        }else{
            replyView= (SwiperefreshContainer) LayoutInflater.from(container.getContext()).inflate(R.layout.video_detail_reply,container,false);
            replyView.setOnLoadListener(loadListener);
            replyView.setOnRefreshListener(refreshListener);
            replyView.setColorSchemeColors(container.getContext().getResources().getColor(R.color.colorPrimary));
            RecyclerView recyclerView=replyView.findViewById(R.id.video_detail_reply_recyclerview);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(container.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(),DividerItemDecoration.VERTICAL));
            recyclerView.setLayoutManager(linearLayoutManager);
            replyRecyclerViewAdapter=new VideoDetailReplyRecyclerViewAdapter(replyData);
            recyclerView.setAdapter(replyRecyclerViewAdapter);
            container.addView(replyView);
            return replyView;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


}
