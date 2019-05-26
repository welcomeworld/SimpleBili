package com.github.welcomeworld.simplebili.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.DownloadInfoBean;

public class DownloadViewPager extends PagerAdapter {
    SparseArray<DownloadInfoBean> data;
    SparseArray<DownloadInfoBean> completedData;
    public DownloadViewPager(SparseArray<DownloadInfoBean> completedData, SparseArray<DownloadInfoBean> data) {
        this.data = data;
        this.completedData = completedData;
    }

    RecyclerView downloadingRecyclerView;
    RecyclerView completedRecyclerView;

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view;
        if(position == 0){
           view = LayoutInflater.from(container.getContext()).inflate(R.layout.download_viewpager_downloading,container,false);
            downloadingRecyclerView = view.findViewById(R.id.download_downloading_recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            DownloadingRecyclerViewAdapter recyclerViewAdapter = new DownloadingRecyclerViewAdapter(data);
            recyclerViewAdapter.setHasStableIds(true);
            downloadingRecyclerView.setAdapter(recyclerViewAdapter);
            downloadingRecyclerView.setLayoutManager(linearLayoutManager);
        }else {
            view = LayoutInflater.from(container.getContext()).inflate(R.layout.download_viewpager_downloaded,container,false);
            completedRecyclerView = view.findViewById(R.id.download_downloaded_recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            completedRecyclerView.setAdapter(new DownloadedRecyclerViewAdapter(completedData));
            completedRecyclerView.setLayoutManager(linearLayoutManager);
        }
        container.addView(view);
        return view;
    }

    public void notifyItemInsert(int position){
        if(downloadingRecyclerView!=null&&downloadingRecyclerView.getAdapter()!=null){
            downloadingRecyclerView.getAdapter().notifyItemInserted(position);
        }
    }

    public void notifyItemChange(int position){
        if(downloadingRecyclerView!=null&&downloadingRecyclerView.getAdapter()!=null){
            downloadingRecyclerView.getAdapter().notifyItemChanged(position);
        }
    }

    public void notifyItemCompleted(int position,int completedPosition){
        if(position != -1&&downloadingRecyclerView!=null&&downloadingRecyclerView.getAdapter()!=null){
            downloadingRecyclerView.getAdapter().notifyDataSetChanged();
        }
        if(completedPosition!=-1&&completedRecyclerView!=null&&completedRecyclerView.getAdapter()!=null){
            completedRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

}
