package com.github.welcomeworld.simplebili.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.BrowserActivity;
import com.github.welcomeworld.simplebili.NotificationActivity;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageFragment extends Fragment {
    @BindView(R.id.index_toolbar)
    Toolbar toolbar;
    @BindView(R.id.index_toolbar_title)
    TextView titleView;
    DrawerLayout drawerLayout;
    Activity activity;
    @BindView(R.id.login_group)
    Group loginGuide;
    @BindView(R.id.message_group)
    Group messageGroup;

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
        View view=inflater.inflate(R.layout.fragment_index_messages,container,false);
        ButterKnife.bind(this,view);
        //toolbar.inflateMenu(R.menu.index_contacts);
        //toolbar.inflateMenu(R.menu.index_message_more);
        titleView.setText(R.string.message);
        return view;
    }

    @OnClick({R.id.index_toolbar_drawer,R.id.index_toolbar_avatar})
    public void openDrawer(){
        if(drawerLayout!=null){
            drawerLayout.openDrawer(Gravity.START);
        }
    }

    @OnClick(R.id.message_login_guide)
    public void login(){
        Intent loginIntent=new Intent("com.github.welcomeworld.simplebili.action.LOGIN");
        loginIntent.setPackage(getContext().getPackageName());
        startActivity(loginIntent);
    }

    @OnClick(R.id.message_notification)
    public void openWebNotification(){
        Intent notificationIntent = new Intent(getActivity(), BrowserActivity.class);
        notificationIntent.setData(Uri.parse("https://message.bilibili.com/h5/app/system-message"));
        startActivity(notificationIntent);
    }

    @OnClick({R.id.message_reply_me,R.id.message_at_me,R.id.message_praise_me})
    public void openNotification(View v){
        Intent notificationIntent = new Intent(getActivity(), NotificationActivity.class);
        switch (v.getId()){
            case R.id.message_reply_me:
                notificationIntent.putExtra("type",0);
                break;
            case R.id.message_at_me:
                notificationIntent.putExtra("type",1);
                break;
            case R.id.message_praise_me:
                notificationIntent.putExtra("type",2);
                break;
                default:
                    notificationIntent.putExtra("type",0);
        }
        startActivity(notificationIntent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser&&toolbar!=null){
            ImageView avator = toolbar.findViewById(R.id.index_toolbar_avatar);
            if(BiliLocalStatus.getCover()!=null){
                Glide.with(avator).load(BiliLocalStatus.getCover()).apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop())).into(avator);
            }else {
                avator.setImageResource(R.mipmap.ic_default_avatar);
            }
            if(BiliLocalStatus.isLogin()){
                loginGuide.setVisibility(View.GONE);
                messageGroup.setVisibility(View.VISIBLE);
            }else {
                loginGuide.setVisibility(View.VISIBLE);
                messageGroup.setVisibility(View.GONE);
            }
        }
    }
}
