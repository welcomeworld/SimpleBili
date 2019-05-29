package com.github.welcomeworld.simplebili.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.SimpleBaseActivity;
import com.github.welcomeworld.simplebili.adapter.IndexCategoryRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryFragment extends Fragment {
    @BindView(R.id.index_toolbar)
    Toolbar toolbar;
    @BindView(R.id.index_toolbar_title)
    TextView titleView;
    DrawerLayout drawerLayout;
    Activity activity;
    @BindView(R.id.index_category_detail)
    RecyclerView recyclerView;

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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_index_category,container,false);
        ButterKnife.bind(this,view);
        toolbar.inflateMenu(R.menu.index_downlaod);
        toolbar.inflateMenu(R.menu.index_search);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_home_search:
                        ((SimpleBaseActivity)getActivity()).updateSelf("search");
                        return true;
                    case R.id.item_download:
                        ((SimpleBaseActivity)getActivity()).updateSelf("cache");
                        return true;
                    default:return false;
                }
            }
        });
        titleView.setText(R.string.category);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(recyclerView.getContext(),4,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new IndexCategoryRecyclerViewAdapter());
        return view;
    }

    @OnClick({R.id.index_toolbar_drawer,R.id.index_toolbar_avatar})
    public void openDrawer(){
        if(drawerLayout!=null){
            drawerLayout.openDrawer(Gravity.START);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser&&toolbar!=null){
            ImageView avator = toolbar.findViewById(R.id.index_toolbar_avatar);
            if(BiliLocalStatus.getCover()!=null){
                Glide.with(avator).load(BiliLocalStatus.getCover()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(avator.getDrawable())).into(avator);
            }else {
                avator.setImageResource(R.mipmap.ic_default_avatar);
            }
        }
    }
}
