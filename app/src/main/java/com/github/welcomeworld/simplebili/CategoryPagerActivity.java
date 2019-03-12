package com.github.welcomeworld.simplebili;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.welcomeworld.simplebili.adapter.CategoryPagerRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.adapter.IndexRecommendRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.bean.CategoryListBean;
import com.github.welcomeworld.simplebili.common.IndexGridItemDecoration;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.IndexNetAPI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryPagerActivity extends AppCompatActivity {
    public final static String TAG="CategoryPagerActivity";

    @BindView(R.id.category_pager_recyclerview)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_pager);
        ButterKnife.bind(this);
        if(getIntent().getIntExtra("rid",-2)==-2){
            finish();
            Log.e(TAG,"rid is null");
            return;
        }
        Map<String,String> parameters=new HashMap<>();
        parameters.put("pn",""+1);
        parameters.put("channel","bili");
        parameters.put("ps",""+10);
        parameters.put("rid",""+getIntent().getIntExtra("rid",1));
        parameters.put("ts",""+System.currentTimeMillis());
        OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                .addInterceptor(new FixedHeaderInterceptor())
                .addInterceptor(new DynamicHeaderInterceptor(null))
                .addInterceptor(new FixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters))
                .addInterceptor(new SortAndSignInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.APPAPIURL)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IndexNetAPI liveDetailNetAPI=retrofit.create(IndexNetAPI.class);
        liveDetailNetAPI.getCategoryList().enqueue(new Callback<CategoryListBean>() {
            @Override
            public void onResponse(Call<CategoryListBean> call, Response<CategoryListBean> response) {
                if(response.body()!=null){
                    GridLayoutManager gridLayoutManager=new GridLayoutManager(recyclerView.getContext(),2, LinearLayoutManager.VERTICAL,false);
                    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            return 1;
                        }
                    });
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.addItemDecoration(new IndexGridItemDecoration(recyclerView.getContext(),5,2));
                    recyclerView.setAdapter(new CategoryPagerRecyclerViewAdapter(response.body().getData()));
                }
            }

            @Override
            public void onFailure(Call<CategoryListBean> call, Throwable t) {

            }
        });
    }
}
