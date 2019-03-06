package com.github.welcomeworld.simplebili.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.adapter.IndexRecommendRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.bean.IndexRecommendBean;
import com.github.welcomeworld.simplebili.bean.IndexRecommendDataBean;
import com.github.welcomeworld.simplebili.common.IndexGridItemDecoration;
import com.github.welcomeworld.simplebili.net.retrofit.IndexNetAPI;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;

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

public class IndexDefaultRecommendFragment extends Fragment {

    @BindView(R.id.index_default_recommend_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.index_default_recommend_swipeRefresh)
    SwiperefreshContainer swipeRefreshLayout;
    List<IndexRecommendDataBean> data=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO: 10/22/2018
        View view=inflater.inflate(R.layout.fragment_index_default_recommend,container,false);
        ButterKnife.bind(this,view);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(recyclerView.getContext(),2,LinearLayoutManager.VERTICAL,false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new IndexGridItemDecoration(recyclerView.getContext(),5,2));
        recyclerView.setAdapter(new IndexRecommendRecyclerViewAdapter(data));
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Map<String,String> parameters=new HashMap<>();
                parameters.put("ad_extra","6BCCA2213B3B094292DFF9454EB02128A5CD624F226D40F0B86CA8263CE1D50C927F2F8DF2FD134C361A06FAA537402E66E53155D1C1F218BE42D2AFE4A6A701C496E5196401D81A1E390498A1CA24C20C25C97600C552962682D90C9D9DF8B9EDAD866490BE972EA37F92AA7A1040F2BEA5122D039B942437307F298336AAF27EFB5AF87961F6F852401DD8BBD0BFB92309D3B60C12E307ECD02D9BCBB19725E2964F77CDE07BFAC45A34884CE0167EEDBB0EBC8926A3CC9CB9B27536BF9C0DF87AB6DABAE86D1E6D4E714BC140A1D500E27446265DC85C226B381E10AF2299D961E06FA60A84EE34DFCB65E1253339112FD0D5ECE9C9C58C084D028DD7E26A70DC806C36C46E9D5C08169A2571B8BAC2BC0AE91AE8D36F3CBDCD2768950CD1CE9C3A3F53B5FE145A20B020435E79CA");
                parameters.put("banner_hash","5370060068233862947");
                parameters.put("idx","1547815464");
                parameters.put("login_event","0");
                parameters.put("build","591204");
                parameters.put("network","wifi");
                parameters.put("pull","true");
                parameters.put("style","2");
                parameters.put("ts",""+System.currentTimeMillis());
                parameters.put("open_event","");
                OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                        .addInterceptor(new FixedHeaderInterceptor())
                        .addInterceptor(new DynamicHeaderInterceptor(null))
                        .addInterceptor(new FixedParameterInterceptor())
                        .addInterceptor(new DynamicParameterInterceptor(parameters))
                        .addInterceptor(new SortAndSignInterceptor())
                        .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(BaseUrl.APPURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClientBuilder.build())
                        .build();
                IndexNetAPI indexNetAPI=retrofit.create(IndexNetAPI.class);
                Call<IndexRecommendBean> indexBeanCall=indexNetAPI.getIndexRecommend();
                indexBeanCall.enqueue(new Callback<IndexRecommendBean>() {
                    @Override
                    public void onResponse(Call<IndexRecommendBean> call, Response<IndexRecommendBean> response) {
                        swipeRefreshLayout.setRefreshing(false);
                        if(response.body().getData()==null){
                            Toast.makeText(getContext(),"没有更新",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        data=response.body().getData();
                        for(int i=0;i<data.size();i++){
                            if(!data.get(i).getGotoX().equalsIgnoreCase("av")){
                                data.remove(i);
                                i--;
                            }
                        }
                        recyclerView.setAdapter(new IndexRecommendRecyclerViewAdapter(data));
                    }

                    @Override
                    public void onFailure(Call<IndexRecommendBean> call, Throwable t) {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(),"电波无法到达",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        swipeRefreshLayout.setOnLoadListener(new SwiperefreshContainer.OnLoadListener() {
            @Override
            public void onLoad() {
                Map<String,String> parameters=new HashMap<>();
                parameters.put("ad_extra","6BCCA2213B3B094292DFF9454EB02128A5CD624F226D40F0B86CA8263CE1D50C927F2F8DF2FD134C361A06FAA537402E66E53155D1C1F218BE42D2AFE4A6A701C496E5196401D81A1E390498A1CA24C20C25C97600C552962682D90C9D9DF8B9EDAD866490BE972EA37F92AA7A1040F2BEA5122D039B942437307F298336AAF27EFB5AF87961F6F852401DD8BBD0BFB92309D3B60C12E307ECD02D9BCBB19725E2964F77CDE07BFAC45A34884CE0167EEDBB0EBC8926A3CC9CB9B27536BF9C0DF87AB6DABAE86D1E6D4E714BC140A1D500E27446265DC85C226B381E10AF2299D961E06FA60A84EE34DFCB65E1253339112FD0D5ECE9C9C58C084D028DD7E26A70DC806C36C46E9D5C08169A2571B8BAC2BC0AE91AE8D36F3CBDCD2768950CD1CE9C3A3F53B5FE145A20B020435E79CA");
                parameters.put("banner_hash","5370060068233862947");
                parameters.put("idx","1547815474");
                parameters.put("login_event","0");
                parameters.put("build","591204");
                parameters.put("network","wifi");
                parameters.put("pull","false");
                parameters.put("style","2");
                parameters.put("ts",""+System.currentTimeMillis());
                parameters.put("open_event","");
                OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                        .addInterceptor(new FixedHeaderInterceptor())
                        .addInterceptor(new DynamicHeaderInterceptor(null))
                        .addInterceptor(new FixedParameterInterceptor())
                        .addInterceptor(new DynamicParameterInterceptor(parameters))
                        .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(BaseUrl.APPURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClientBuilder.build())
                        .build();
                IndexNetAPI indexNetAPI=retrofit.create(IndexNetAPI.class);
                Call<IndexRecommendBean> indexBeanCall=indexNetAPI.getIndexRecommend();
                indexBeanCall.enqueue(new Callback<IndexRecommendBean>() {
                    @Override
                    public void onResponse(Call<IndexRecommendBean> call, Response<IndexRecommendBean> response) {
                        swipeRefreshLayout.setLoading(false);
                        if(response.body().getData()==null){
                            Toast.makeText(getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        List<IndexRecommendDataBean> moreData;
                        moreData=response.body().getData();
                        for(int i=0;i<moreData.size();i++){
                            if(moreData.get(i).getGotoX().equalsIgnoreCase("login")||moreData.get(i).getGotoX().equalsIgnoreCase("banner")||moreData.get(i).getGotoX().equalsIgnoreCase("ad_web_s")){
                                moreData.remove(i);
                                i--;
                            }
                        }
                        data.addAll(moreData);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<IndexRecommendBean> call, Throwable t) {
                        swipeRefreshLayout.setLoading(false);
                        Toast.makeText(getContext(),"电波无法到达",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }
}
