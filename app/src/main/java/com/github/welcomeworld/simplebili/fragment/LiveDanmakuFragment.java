package com.github.welcomeworld.simplebili.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.adapter.LiveDetailDanmakuRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.adapter.LiveDetailRelatedRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.bean.DanmakuBean;
import com.github.welcomeworld.simplebili.bean.RoomHistoryDanmakuBean;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.LiveDetailNetAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveDanmakuFragment extends Fragment {

    @BindView(R.id.live_danmaku_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.live_danmaku_input)
    EditText danmakuInputTextView;
    @BindView(R.id.live_danmaku_send)
    TextView danmakuSendView;
    List<DanmakuBean> data;

    int lastVisiblePosition=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView= inflater.inflate(R.layout.fragment_live_danmaku,container,false);
        ButterKnife.bind(this,contentView);
        data=new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(container.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new LiveDetailDanmakuRecyclerViewAdapter(data));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                lastVisiblePosition=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });
        Map<String,String> parameters=new HashMap<>();
        parameters.put("room_id",""+getArguments().getInt("roomId"));
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.addInterceptor(new FixedHeaderInterceptor());
        builder.addInterceptor(new DynamicHeaderInterceptor(null));
        builder.addInterceptor(new FixedParameterInterceptor());
        builder.addInterceptor(new DynamicParameterInterceptor(parameters));
        builder.addInterceptor(new SortAndSignInterceptor());
        builder.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.APILIVEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LiveDetailNetAPI liveDetailNetAPI=retrofit.create(LiveDetailNetAPI.class);
        liveDetailNetAPI.getRoomHisttoryDanmaku().enqueue(new Callback<RoomHistoryDanmakuBean>() {
            @Override
            public void onResponse(Call<RoomHistoryDanmakuBean> call, Response<RoomHistoryDanmakuBean> response) {
                RoomHistoryDanmakuBean data=response.body();
                if(data!=null){
                    List<DanmakuBean> danmakuBeanList=new ArrayList<>();
                    for(int i=0;i<data.getData().getRoom().size();i++){
                        DanmakuBean danmakuBean=new DanmakuBean();
                        danmakuBean.setPriority((byte)0);
                        danmakuBean.setText(data.getData().getRoom().get(i).getText());
                        danmakuBean.setTextColor(00);
                        danmakuBean.setTextSize(25);
                        danmakuBean.setType(1);
                        danmakuBean.setUserId(data.getData().getRoom().get(i).getUid());
                        danmakuBean.setUserName(data.getData().getRoom().get(i).getNickname());
                        danmakuBeanList.add(danmakuBean);
                    }
                    ((LiveDetailDanmakuRecyclerViewAdapter)recyclerView.getAdapter()).addHistory(danmakuBeanList);
                    lastVisiblePosition+=danmakuBeanList.size();
                }
            }

            @Override
            public void onFailure(Call<RoomHistoryDanmakuBean> call, Throwable t) {
                Log.e("liveDanmaku","出错了"+t.getMessage());
                t.printStackTrace();
            }
        });
        return contentView;
    }

    public void addDanmaku(DanmakuBean danmakuBean){
        if(data!=null){
            data.add(danmakuBean);
            recyclerView.getAdapter().notifyItemInserted(data.size()-1);
            recyclerView.smoothScrollToPosition(lastVisiblePosition+1>data.size()-1?data.size()-1:lastVisiblePosition+1);
            lastVisiblePosition++;
        }
    }

}
