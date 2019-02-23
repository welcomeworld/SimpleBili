package com.github.welcomeworld.simplebili.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.adapter.IndexBangumiBannerPagerAdapter;
import com.github.welcomeworld.simplebili.adapter.IndexBangumiEditorRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.adapter.IndexDefaultRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.bean.IndexBangumiBean;
import com.github.welcomeworld.simplebili.common.IndexGridItemDecoration;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.IndexNetAPI;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndexDefaultBangumiFragment extends Fragment {

    @BindView(R.id.editorrecommend_recyclerView)
    RecyclerView editorRecyclerView;
    @BindView(R.id.mine_recyclerview)
    RecyclerView mineRecyclerView;
    @BindView(R.id.banner_viewpager)
    ViewPager bannerViewPager;
    @BindView(R.id.index_default_star_swipeRefresh)
    SwiperefreshContainer swipeRefreshLayout;
    @BindView(R.id.bangumi_cover_1)
    ImageView bangumiCover1;
    @BindView(R.id.bangumi_cover_2)
    ImageView bangumiCover2;
    @BindView(R.id.bangumi_cover_3)
    ImageView bangumiCover3;
    @BindView(R.id.bangumi_title_1)
    TextView bangumiTitle1;
    @BindView(R.id.bangumi_title_2)
    TextView bangumiTitle2;
    @BindView(R.id.bangumi_title_3)
    TextView bangumiTitle3;
    @BindView(R.id.bangumi_desc_1)
    TextView bangumiDesc1;
    @BindView(R.id.bangumi_desc_2)
    TextView bangumiDesc2;
    @BindView(R.id.bangumi_desc_3)
    TextView bangumiDesc3;
    @BindView(R.id.hope_cover_1)
    ImageView hopeCover1;
    @BindView(R.id.hope_cover_2)
    ImageView hopeCover2;
    @BindView(R.id.hope_cover_3)
    ImageView hopeCover3;
    @BindView(R.id.hope_title_1)
    TextView hopeTitle1;
    @BindView(R.id.hope_title_2)
    TextView hopeTitle2;
    @BindView(R.id.hope_title_3)
    TextView hopeTitle3;
    @BindView(R.id.hope_desc_1)
    TextView hopeDesc1;
    @BindView(R.id.hope_desc_2)
    TextView hopeDesc2;
    @BindView(R.id.hope_desc_3)
    TextView hopeDesc3;
    @BindView(R.id.mine_group)
    Group mineGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_index_default_bangumi,container,false);
        ButterKnife.bind(this,view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        editorRecyclerView.setLayoutManager(linearLayoutManager);
        IndexBangumiBean.ResultBean.ModulesBean data=null;
        editorRecyclerView.setAdapter(new IndexBangumiEditorRecyclerViewAdapter(data));
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Map<String,String> parameters=new HashMap<>();
                parameters.put("ts",""+System.currentTimeMillis());
                OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                        .addInterceptor(new FixedHeaderInterceptor())
                        .addInterceptor(new DynamicHeaderInterceptor(null))
                        .addInterceptor(new FixedParameterInterceptor())
                        .addInterceptor(new DynamicParameterInterceptor(parameters))
                        .addInterceptor(new SortAndSignInterceptor())
                        .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(BaseUrl.APIURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClientBuilder.build())
                        .build();
                IndexNetAPI indexNetAPI=retrofit.create(IndexNetAPI.class);
                Call<IndexBangumiBean> indexBangumiBeanCall=indexNetAPI.getIndexBangumi();
                indexBangumiBeanCall.enqueue(new Callback<IndexBangumiBean>() {
                    @Override
                    public void onResponse(Call<IndexBangumiBean> call, Response<IndexBangumiBean> response) {
                        swipeRefreshLayout.setRefreshing(false);
                        if(response.body()==null||response.body().getResult()==null){
                            return;
                        }
                        //BANGUMI
                        Glide.with(getContext()).load(response.body().getResult().getModules().get(1).getItems().get(0).getCover()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(bangumiCover1);
                        Glide.with(getContext()).load(response.body().getResult().getModules().get(1).getItems().get(1).getCover()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(bangumiCover2);
                        Glide.with(getContext()).load(response.body().getResult().getModules().get(1).getItems().get(2).getCover()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(bangumiCover3);
                        bangumiTitle1.setText(response.body().getResult().getModules().get(1).getItems().get(0).getTitle());
                        bangumiTitle2.setText(response.body().getResult().getModules().get(1).getItems().get(1).getTitle());
                        bangumiTitle3.setText(response.body().getResult().getModules().get(1).getItems().get(2).getTitle());
                        bangumiDesc1.setText(response.body().getResult().getModules().get(1).getItems().get(0).getDesc());
                        bangumiDesc2.setText(response.body().getResult().getModules().get(1).getItems().get(1).getDesc());
                        bangumiDesc3.setText(response.body().getResult().getModules().get(1).getItems().get(2).getDesc());

                        //HOPE
                        Glide.with(getContext()).load(response.body().getResult().getModules().get(3).getItems().get(0).getCover()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(hopeCover1);
                        Glide.with(getContext()).load(response.body().getResult().getModules().get(3).getItems().get(1).getCover()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(hopeCover2);
                        Glide.with(getContext()).load(response.body().getResult().getModules().get(3).getItems().get(2).getCover()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(hopeCover3);
                        hopeTitle1.setText(response.body().getResult().getModules().get(3).getItems().get(0).getTitle());
                        hopeTitle2.setText(response.body().getResult().getModules().get(3).getItems().get(1).getTitle());
                        hopeTitle3.setText(response.body().getResult().getModules().get(3).getItems().get(2).getTitle());
                        hopeDesc1.setText(response.body().getResult().getModules().get(3).getItems().get(0).getDesc());
                        hopeDesc2.setText(response.body().getResult().getModules().get(3).getItems().get(1).getDesc());
                        hopeDesc3.setText(response.body().getResult().getModules().get(3).getItems().get(2).getDesc());
                        IndexBangumiBannerPagerAdapter pagerAdapter=new IndexBangumiBannerPagerAdapter(bannerViewPager,response.body().getResult().getModules().get(0));
                        bannerViewPager.setAdapter(pagerAdapter);
                        bannerViewPager.addOnPageChangeListener(pagerAdapter);
                        bannerViewPager.setCurrentItem(1);
                        editorRecyclerView.setAdapter(new IndexBangumiEditorRecyclerViewAdapter(response.body().getResult().getModules().get(response.body().getResult().getModules().size()-1)));

                    }

                    @Override
                    public void onFailure(Call<IndexBangumiBean> call, Throwable t) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
        swipeRefreshLayout.setOnLoadListener(new SwiperefreshContainer.OnLoadListener() {
            @Override
            public void onLoad() {

            }
        });
        return view;
    }

}
