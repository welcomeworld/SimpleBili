package com.github.welcomeworld.simplebili.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.adapter.IndexPopularRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.bean.IndexPopularBean;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.IndexNetAPI;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;
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

public class IndexDefaultPopularFragment extends Fragment {

    @BindView(R.id.index_default_film_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.index_default_film_swipeRefresh)
    SwiperefreshContainer swipeRefreshLayout;

    List<IndexPopularBean.DataBean> data;

    private int loadCount=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_index_default_popular,container,false);
        ButterKnife.bind(this,view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(true);
            }
        });
        swipeRefreshLayout.setOnLoadListener(new SwiperefreshContainer.OnLoadListener() {
            @Override
            public void onLoad() {
                load();
            }
        });
        return view;

    }

    public void refresh(boolean force){
        if((!force&&data!=null)||swipeRefreshLayout ==null){
            return;
        }
        if(swipeRefreshLayout.isRefreshing()&&!force){
            return;
        }
        swipeRefreshLayout.setRefreshing(true);
        loadCount = 0;
        Map<String,String> parameters=new HashMap<>();
        parameters.put("fnval","16");
        parameters.put("fnver","0");
        parameters.put("ver","1549982418");
        parameters.put("idx",loadCount+"");
        parameters.put("last_param","");
        parameters.put("login_event","0");
        parameters.put("qn","112");
        parameters.put("ts",""+System.currentTimeMillis());
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
        Call<IndexPopularBean> popularBeanCall=indexNetAPI.getIndexPopular();
        popularBeanCall.enqueue(new Callback<IndexPopularBean>() {
            @Override
            public void onResponse(Call<IndexPopularBean> call, Response<IndexPopularBean> response) {
                swipeRefreshLayout.setRefreshing(false);
                if(response.body()==null||response.body().getCode()!=0){
                    return;
                }
                data = response.body().getData();
                if(data!=null){
                    loadCount = data.size();
                    for(int i=0;i<data.size();i++){
                        if(!data.get(i).getGotoX().equalsIgnoreCase("av")){
                            data.remove(i);
                            i--;
                        }
                    }
                }
                recyclerView.setAdapter(new IndexPopularRecyclerViewAdapter(data));
            }

            @Override
            public void onFailure(Call<IndexPopularBean> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void load(){
        Map<String,String> parameters=new HashMap<>();
        parameters.put("fnval","16");
        parameters.put("fnver","0");
        parameters.put("ver","1549982418");
        parameters.put("idx",loadCount+"");
        parameters.put("last_param","");
        parameters.put("login_event","0");
        parameters.put("qn","112");
        parameters.put("ts",""+System.currentTimeMillis());
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
        Call<IndexPopularBean> popularBeanCall=indexNetAPI.getIndexPopular();
        popularBeanCall.enqueue(new Callback<IndexPopularBean>() {
            @Override
            public void onResponse(Call<IndexPopularBean> call, Response<IndexPopularBean> response) {
                swipeRefreshLayout.setLoading(false);
                if(response.body()==null||response.body().getCode()!=0){
                    return;
                }
                List<IndexPopularBean.DataBean> data = response.body().getData();
                if(data!=null){
                    loadCount = loadCount+data.size();
                    for(int i=0;i<data.size();i++){
                        if(!data.get(i).getGotoX().equalsIgnoreCase("av")){
                            data.remove(i);
                            i--;
                        }
                    }
                }
                int index = IndexDefaultPopularFragment.this.data.size();
                IndexDefaultPopularFragment.this.data.addAll(data);
                recyclerView.getAdapter().notifyItemRangeInserted(index,data.size());
            }

            @Override
            public void onFailure(Call<IndexPopularBean> call, Throwable t) {
                swipeRefreshLayout.setLoading(false);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            refresh(false);
        }
    }
}
