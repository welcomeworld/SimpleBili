package com.github.welcomeworld.simplebili.fragment;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.welcomeworld.simplebili.MApplication;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.adapter.DownloadViewPager;
import com.github.welcomeworld.simplebili.bean.DownloadInfoBean;
import com.github.welcomeworld.simplebili.utils.DownloadManager;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DownloadFragment extends Fragment {
    @BindView(R.id.navigation_toolbar)
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    Activity activity;
    @BindView(R.id.download_viewpager)
    ViewPager viewPager;
    @BindView(R.id.download_tablaout)
    TabLayout tabLayout;

    SparseArray<DownloadInfoBean> data = new SparseArray<>();
    SparseArray<DownloadInfoBean> completedData = new SparseArray<>();

    DownloadViewPager viewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download,container,false);
        ButterKnife.bind(this,view);
        toolbar.setTitle(R.string.off_line_cache);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout!=null){
                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPagerAdapter = new DownloadViewPager(completedData,data);
        viewPager.setAdapter(viewPagerAdapter);
        DownloadManager.getInstance().addDownloadListener(listener);
        updateView();
        return view;
    }

    public void updateView(){
        Disposable observable = Observable.create(new ObservableOnSubscribe<DownloadInfoBean>() {
            @Override
            public void subscribe(ObservableEmitter<DownloadInfoBean> emitter) throws Exception {
                List<DownloadInfoBean> downloadInfoBeanList = ((MApplication)getActivity().getApplication()).getDatabase().getDao().getDownload();
                for(DownloadInfoBean info:downloadInfoBeanList){
                    updateInfo(info);
                    emitter.onNext(info);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DownloadInfoBean>() {
            @Override
            public void accept(DownloadInfoBean info) throws Exception {
                if(info.getDownloadState() == DownloadInfoBean.COMPLETE){
                    if(completedData.get(info.getDownloadId())==null){
                        completedData.append(info.getDownloadId(),info);
                        viewPagerAdapter.notifyItemCompleted(-1,completedData.indexOfKey(info.getDownloadId()));
                    }
                }else{
                    if(data.get(info.getDownloadId())==null){
                        data.append(info.getDownloadId(),info);
                        viewPagerAdapter.notifyItemInsert(data.indexOfKey(info.getDownloadId()));
                    }
                    if(PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(getString(R.string.preference_key_download_auto),true)){
                        DownloadManager.getInstance().preparedDownload(info);
                    }
                }
            }
        });
    }

    public void updateInfo(DownloadInfoBean info){
        File file = new File(info.getLocalPath());
        if(!file.exists()){
            DownloadManager.getInstance().pauseDownload(info);
        }else {
            if(file.length() == info.getContentLength()){
                info.setDownloadState(DownloadInfoBean.COMPLETE);
            }else {
                DownloadManager.getInstance().pauseDownload(info);
            }
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (Activity) context;
        Bundle bundle=getArguments();
        if(bundle!=null){
            int drawerLayoutId=bundle.getInt("drawerLayoutId",-1);
            if(drawerLayoutId!=-1){
                drawerLayout=activity.findViewById(drawerLayoutId);
            }
        }
    }

    public DownloadManager.DownloadListener listener = new DownloadManager.DownloadListener(){
        @Override
        public void onPrepared(DownloadInfoBean info) {
            super.onPrepared(info);
            if(data.get(info.getDownloadId())==null){
                data.append(info.getDownloadId(),info);
                viewPagerAdapter.notifyItemInsert(data.indexOfKey(info.getDownloadId()));
            }
        }

        @Override
        public void onDownloading(DownloadInfoBean info) {
            super.onDownloading(info);
            if(data.get(info.getDownloadId())==null){
                data.append(info.getDownloadId(),info);
                viewPagerAdapter.notifyItemInsert(data.indexOfKey(info.getDownloadId()));
            }else {
                viewPagerAdapter.notifyItemChange(data.indexOfKey(info.getDownloadId()));
            }
        }

        @Override
        public void onCompleted(DownloadInfoBean info) {
            super.onCompleted(info);
            int position =-1;
            int completedPostion =-1;
            if(data.get(info.getDownloadId())!=null){
                position = data.indexOfKey(info.getDownloadId());
                data.remove(info.getDownloadId());
            }
            if(completedData.get(info.getDownloadId())==null){
                completedData.append(info.getDownloadId(),info);
                completedPostion = completedData.indexOfKey(info.getDownloadId());
            }
            viewPagerAdapter.notifyItemCompleted(position,completedPostion);
        }

        @Override
        public void onStart(DownloadInfoBean info) {
            super.onStart(info);
            if(data.get(info.getDownloadId())==null){
                data.append(info.getDownloadId(),info);
                viewPagerAdapter.notifyItemInsert(data.indexOfKey(info.getDownloadId()));
            }else {
                viewPagerAdapter.notifyItemChange(data.indexOfKey(info.getDownloadId()));
            }
        }

        @Override
        public void onPause(DownloadInfoBean info) {
            super.onPause(info);
            if(data.get(info.getDownloadId())==null){
                data.append(info.getDownloadId(),info);
                viewPagerAdapter.notifyItemInsert(data.indexOfKey(info.getDownloadId()));
            }else {
                viewPagerAdapter.notifyItemChange(data.indexOfKey(info.getDownloadId()));
            }
        }

        @Override
        public void onError(DownloadInfoBean info) {
            super.onError(info);
            if(data.get(info.getDownloadId())==null){
                data.append(info.getDownloadId(),info);
                viewPagerAdapter.notifyItemInsert(data.indexOfKey(info.getDownloadId()));
            }else {
                viewPagerAdapter.notifyItemChange(data.indexOfKey(info.getDownloadId()));
            }
        }

    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        DownloadManager.getInstance().removeDownloadListener(listener);
    }
}
