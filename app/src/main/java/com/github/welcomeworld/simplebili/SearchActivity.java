package com.github.welcomeworld.simplebili;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.github.welcomeworld.simplebili.adapter.FavoriteListRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.adapter.SearchResultRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.bean.FavoriteListBean;
import com.github.welcomeworld.simplebili.bean.SearchResultBean;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.FavoriteNetAPI;
import com.github.welcomeworld.simplebili.net.retrofit.SearchNetAPI;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends SimpleBaseActivity {
    @BindView(R.id.search_input)
    EditText searchInputText;
    @BindView(R.id.search_result_swipecontainer)
    SwiperefreshContainer swiperefreshContainer;
    @BindView(R.id.search_result_recyclerview)
    RecyclerView resultRecyclerView;

    List<SearchResultBean.DataBean.ItemBean> data;

    private String searchText = "";
    private int pn=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                searchText = searchInputText.getText().toString();
                if(searchText.startsWith("av")){
                    Intent videoIntent = new Intent(SearchActivity.this,VideoDetailActivity.class);
                    videoIntent.setData(Uri.parse("bilibili://video/"+searchText.substring(2)));
                    startActivity(videoIntent);
                }else if(searchText.startsWith("bangumi")){
                    Intent bangumiIntent = new Intent(SearchActivity.this,BangumiDetailActivity.class);
                    bangumiIntent.setData(Uri.parse("https://m.bilibili.com/bangumi/play/ss"+searchText.substring(7)));
                    startActivity(bangumiIntent);
                }else {
                    refresh(true);
                }
                return false;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(resultRecyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        resultRecyclerView.setLayoutManager(linearLayoutManager);
        swiperefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(true);
            }
        });
        swiperefreshContainer.setOnLoadListener(new SwiperefreshContainer.OnLoadListener() {
            @Override
            public void onLoad() {
                if(data==null){
                    return;
                }
                load();
            }
        });
    }

    public void refresh(boolean force){
        if(!force&&data!=null||swiperefreshContainer==null||searchText==null||searchText.isEmpty()){
            return;
        }
        swiperefreshContainer.setRefreshing(true);
        pn = 1;
        Map<String,String> parameters=new HashMap<>();
        parameters.put("duration","0");
        parameters.put("channel","bili");
        parameters.put("from_source","app_search");
        parameters.put("highlight","1");
        parameters.put("is_org_query","0");
        parameters.put("pn",pn+"");
        parameters.put("keyword",searchText);
        parameters.put("ps","20");
        parameters.put("recommend","1");
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
        retrofit.create(SearchNetAPI.class).getSearchResult().enqueue(new Callback<SearchResultBean>() {
            @Override
            public void onResponse(Call<SearchResultBean> call, Response<SearchResultBean> response) {
                swiperefreshContainer.setRefreshing(false);
                if(response.body()!=null&&response.body().getCode() == 0){
                    data = response.body().getData().getItem();
                    for(int i=0;i<data.size();i++){
                        if(!data.get(i).getGotoX().equalsIgnoreCase("av")){
                            data.remove(i);
                            i--;
                        }
                    }
                    resultRecyclerView.setAdapter(new SearchResultRecyclerViewAdapter(data));
                }
            }

            @Override
            public void onFailure(Call<SearchResultBean> call, Throwable t) {
                swiperefreshContainer.setRefreshing(false);
            }
        });
    }

    public void load(){
        Map<String,String> parameters=new HashMap<>();
        parameters.put("duration","0");
        parameters.put("channel","bili");
        parameters.put("from_source","app_search");
        parameters.put("highlight","1");
        parameters.put("is_org_query","0");
        parameters.put("pn",pn+1+"");
        parameters.put("keyword",searchText);
        parameters.put("ps","20");
        parameters.put("recommend","1");
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
        retrofit.create(SearchNetAPI.class).getSearchResult().enqueue(new Callback<SearchResultBean>() {
            @Override
            public void onResponse(Call<SearchResultBean> call, Response<SearchResultBean> response) {
                swiperefreshContainer.setLoading(false);
                if(response.body()!=null&&response.body().getCode() == 0){
                    pn++;
                    List<SearchResultBean.DataBean.ItemBean> moredata = response.body().getData().getItem();
                    for(int i=0;i<moredata.size();i++){
                        if(!moredata.get(i).getGotoX().equalsIgnoreCase("av")){
                            moredata.remove(i);
                            i--;
                        }
                    }
                    int index = data.size();
                    data.addAll(moredata);
                    resultRecyclerView.getAdapter().notifyItemRangeInserted(index,moredata.size());
                }
            }

            @Override
            public void onFailure(Call<SearchResultBean> call, Throwable t) {
                swiperefreshContainer.setLoading(false);
            }
        });
    }


    @OnClick(R.id.search_cancel)
    public void cancel(){
        onBackPressed();
    }
}
