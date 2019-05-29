package com.github.welcomeworld.simplebili.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.welcomeworld.simplebili.MApplication;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.adapter.HistoryRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends Fragment {
    @BindView(R.id.navigation_toolbar)
    Toolbar toolbar;
    @BindView(R.id.history_recyclerView)
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    Activity activity;
    @BindView(R.id.history_swipeRefresh)
    SwiperefreshContainer swiperefreshContainer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history,container,false);
        ButterKnife.bind(this,view);
        toolbar.setTitle(R.string.history);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout!=null){
                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new HistoryRecyclerViewAdapter(((MApplication)getActivity().getApplication()).getDatabase().getDao().getHistorys(BiliLocalStatus.getMid())));
        swiperefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiperefreshContainer.setRefreshing(false);
            }
        });
        return view;
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
}
