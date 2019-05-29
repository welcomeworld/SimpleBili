package com.github.welcomeworld.simplebili;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.welcomeworld.simplebili.adapter.BangumiDetailPagerAdapter;
import com.github.welcomeworld.simplebili.bean.BangumiDetailPageBean;
import com.github.welcomeworld.simplebili.bean.BangumiUrlBean;
import com.github.welcomeworld.simplebili.bean.DownloadInfoBean;
import com.github.welcomeworld.simplebili.bean.ReplyCursorBean;
import com.github.welcomeworld.simplebili.common.VideoDataSource;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BangumiDetailNetAPI;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.utils.DownloadManager;
import com.github.welcomeworld.simplebili.widget.IjkMediaView;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;
import java.util.ArrayList;
import java.util.HashMap;
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
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BangumiDetailActivity extends SimpleBaseActivity implements View.OnClickListener{

    @BindView(R.id.bangumi_detail_ijkVideoView)
    IjkMediaView ijkMediaView;
    @BindView(R.id.bangumi_detail_group)
    Group group;
    @BindView(R.id.bangumi_detail_tab)
    TabLayout tabLayout;
    @BindView(R.id.bangumi_detail_viewpager)
    ViewPager viewPager;
    Uri currentUri;
    int currentAid=-1;
    int currentCid =-1;
    String currentTitle ="";
    String currentUrl = "";
    String currentCover = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bangumi_detail);
        ButterKnife.bind(this);
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        currentUri=getIntent().getData();
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        BangumiDetailPagerAdapter bangumiDetailPagerAdapter=new BangumiDetailPagerAdapter(null,null);
        bangumiDetailPagerAdapter.setOnClickListener(this);
        bangumiDetailPagerAdapter.setLoadListener(new SwiperefreshContainer.OnLoadListener() {
            @Override
            public void onLoad() {
                if(currentAid==-1){
                    bangumiDetailPagerAdapter.setLoading(false);
                    return;
                }
                Map<String,String> replyParameters=new HashMap<>();
                if(bangumiDetailPagerAdapter.getMaxId()!=0){
                    replyParameters.put("max_id",bangumiDetailPagerAdapter.getMaxId()+"");
                }
                replyParameters.put("oid",currentAid+"");
                replyParameters.put("plat","2");
                replyParameters.put("sort","0");
                replyParameters.put("size","20");
                replyParameters.put("ts",""+System.currentTimeMillis());
                replyParameters.put("type","1");
                OkHttpClient.Builder replyOkHttpClientBuilder=new OkHttpClient.Builder()
                        .addInterceptor(new FixedHeaderInterceptor())
                        .addInterceptor(new DynamicHeaderInterceptor(null))
                        .addInterceptor(new FixedParameterInterceptor())
                        .addInterceptor(new DynamicParameterInterceptor(replyParameters))
                        .addInterceptor(new SortAndSignInterceptor())
                        .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
                Retrofit replyRetrofit=new Retrofit.Builder()
                        .baseUrl(BaseUrl.APIURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(replyOkHttpClientBuilder.build())
                        .build();
                BangumiDetailNetAPI replyVideoDetailNetAPI=replyRetrofit.create(BangumiDetailNetAPI.class);
                replyVideoDetailNetAPI.getVideoReply().enqueue(new Callback<ReplyCursorBean>() {
                    @Override
                    public void onResponse(Call<ReplyCursorBean> call, Response<ReplyCursorBean> response) {
                        bangumiDetailPagerAdapter.setLoading(false);
                        ((BangumiDetailPagerAdapter)viewPager.getAdapter()).addReplyData(response.body().getData());
                    }

                    @Override
                    public void onFailure(Call<ReplyCursorBean> call, Throwable t) {
                        bangumiDetailPagerAdapter.setLoading(false);
                        Toast.makeText(getApplicationContext(),"电波无法到达",Toast.LENGTH_SHORT).show();
                        Log.e("ReplyError",t.getMessage()+"/n");
                        t.printStackTrace();
                    }
                });
            }
        });
        bangumiDetailPagerAdapter.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(currentAid==-1){
                    bangumiDetailPagerAdapter.setRefreshing(false);
                    return;
                }
                Map<String,String> replyParameters=new HashMap<>();
                replyParameters.put("oid",""+currentAid);
                replyParameters.put("plat","2");
                replyParameters.put("sort","0");
                replyParameters.put("size","20");
                replyParameters.put("ts",""+System.currentTimeMillis());
                replyParameters.put("type","1");
                OkHttpClient.Builder replyOkHttpClientBuilder=new OkHttpClient.Builder()
                        .addInterceptor(new FixedHeaderInterceptor())
                        .addInterceptor(new DynamicHeaderInterceptor(null))
                        .addInterceptor(new FixedParameterInterceptor())
                        .addInterceptor(new DynamicParameterInterceptor(replyParameters))
                        .addInterceptor(new SortAndSignInterceptor())
                        .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
                Retrofit replyRetrofit=new Retrofit.Builder()
                        .baseUrl(BaseUrl.APIURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(replyOkHttpClientBuilder.build())
                        .build();
                BangumiDetailNetAPI replyVideoDetailNetAPI=replyRetrofit.create(BangumiDetailNetAPI.class);
                replyVideoDetailNetAPI.getVideoReply().enqueue(new Callback<ReplyCursorBean>() {
                    @Override
                    public void onResponse(Call<ReplyCursorBean> call, Response<ReplyCursorBean> response) {
                        bangumiDetailPagerAdapter.setRefreshing(false);
                        ((BangumiDetailPagerAdapter)viewPager.getAdapter()).setReplyData(response.body().getData());
                    }

                    @Override
                    public void onFailure(Call<ReplyCursorBean> call, Throwable t) {
                        bangumiDetailPagerAdapter.setRefreshing(false);
                        Toast.makeText(getApplicationContext(),"电波无法到达",Toast.LENGTH_SHORT).show();
                        Log.e("ReplyError",t.getMessage()+"/n");
                        t.printStackTrace();
                    }
                });
            }
        });
        viewPager.setAdapter(bangumiDetailPagerAdapter);
        if(currentUri!=null){
            Map<String,String> parameters=new HashMap<>();
            if(currentUri.getPath()!=null&&currentUri.getPath().contains("ss")){
                parameters.put("season_id",currentUri.getPath().substring(currentUri.getPath().lastIndexOf('/')+3));
            }else if(currentUri.getPath()!=null&&currentUri.getPath().contains("ep")){
                parameters.put("ep_id",currentUri.getPath().substring(currentUri.getPath().lastIndexOf('/')+3));
            }
            parameters.put("ts",""+System.currentTimeMillis());
            parameters.put("qn","32");
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
            BangumiDetailNetAPI gangumiDetailNetAPI=retrofit.create(BangumiDetailNetAPI.class);
            gangumiDetailNetAPI.getBangumiDetailPageInfo().enqueue(new Callback<BangumiDetailPageBean>() {
                @Override
                public void onResponse(Call<BangumiDetailPageBean> call, Response<BangumiDetailPageBean> response) {
                    if(response.body()==null||response.body().getCode()!=0){
                        return;
                    }
                    ((BangumiDetailPagerAdapter)viewPager.getAdapter()).setDescData(response.body().getResult());
                    if(currentUri.getPath().substring(currentUri.getPath().lastIndexOf('/')+1).startsWith("ss")){
                        currentUri = Uri.parse(response.body().getResult().getEpisodes().get(0).getShareUrl());
                        bangumiDetailPagerAdapter.setMaxId(0);
                    }
                    for(int i=0;i<response.body().getResult().getEpisodes().size();i++){
                        if(currentUri.getPath().equals(Uri.parse(response.body().getResult().getEpisodes().get(i).getShareUrl()).getPath())){
                            currentAid = response.body().getResult().getEpisodes().get(i).getAid();
                            currentCid = response.body().getResult().getEpisodes().get(i).getCid();
                            currentTitle = response.body().getResult().getEpisodes().get(i).getLongTitle();
                            currentCover = response.body().getResult().getCover();
                            bangumiDetailPagerAdapter.setRefreshing(true);
                        }else {
                            Log.e("ok","not match");
                        }
                        VideoDataSource videoDataSource=new VideoDataSource();
                        videoDataSource.setDanmakuSource("http://comment.bilibili.com/"+response.body().getResult().getEpisodes().get(i).getCid()+".xml");
                        videoDataSource.setTitle(response.body().getResult().getEpisodes().get(i).getLongTitle());
                        int cid = response.body().getResult().getEpisodes().get(i).getCid();
                        Map<String,String> parameters=new HashMap<>();
                        parameters.put("aid",response.body().getResult().getEpisodes().get(i).getAid()+"");
                        parameters.put("expire","0");
                        parameters.put("fnval","0");
                        parameters.put("fnver","0");
                        parameters.put("otype","json");
                        parameters.put("force_host","0");
                        parameters.put("cid",response.body().getResult().getEpisodes().get(i).getCid()+"");
                        parameters.put("npcybs","0");
                        parameters.put("ts",""+System.currentTimeMillis());
                        parameters.put("qn","32");
                        parameters.put("module","bangumi");
                        parameters.put("buvid","Yh4vH3pDckBzQQExADZRZlYqGytOd0Z0R3VCinfoc");
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
                        BangumiDetailNetAPI videoDetailNetAPI=retrofit.create(BangumiDetailNetAPI.class);
                        videoDetailNetAPI.getBangumiUrl().enqueue(new Callback<BangumiUrlBean>() {
                            @Override
                            public void onResponse(Call<BangumiUrlBean> call, Response<BangumiUrlBean> response) {
                                if(response.body().getDash()!=null){
                                    videoDataSource.setDash(true);
                                    if(cid == currentCid){
                                        currentUrl = response.body().getDash().getVideo().get(0).getBaseUrl();
                                    }
                                    ArrayList<String> videoSources=new ArrayList<>();
                                    ArrayList<String> descriptions=new ArrayList<>();
                                    ArrayList<String> audioSources=new ArrayList<>();
                                    for(int j=0;j<response.body().getDash().getVideo().size();j++){
                                        videoSources.add(response.body().getDash().getVideo().get(j).getBaseUrl());
                                        String description;
                                        switch (response.body().getDash().getVideo().get(j).getId()){
                                            case 16:
                                                description="360P";
                                                break;
                                            case 32:
                                                description="480P";
                                                break;
                                            case 64:
                                                description="720P";
                                                break;
                                            default:
                                                description="1080P";
                                                break;
                                        }
                                        descriptions.add(description);
                                        audioSources.add(response.body().getDash().getAudio().get(0).getBaseUrl());
                                    }
                                    videoDataSource.setVideoSources(videoSources);
                                    videoDataSource.setDescriptions(descriptions);
                                    videoDataSource.setAudioSources(audioSources);
                                }else {
                                    Toast.makeText(BangumiDetailActivity.this,"dash is null",Toast.LENGTH_LONG).show();
                                }
                                ijkMediaView.addVideoDataSource(videoDataSource);
                            }

                            @Override
                            public void onFailure(Call<BangumiUrlBean> call, Throwable t) {

                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<BangumiDetailPageBean> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"电波无法到达",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this,"Uri 不能为空！",Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onBackPressed() {
        if(ijkMediaView.isSystemBack()){
            ijkMediaView.release();
            super.onBackPressed();
        }else{
            ijkMediaView.playBack();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        ijkMediaView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkMediaView.release();
        IjkMediaPlayer.native_profileEnd();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bangumi_detail_desc_download:
                DownloadInfoBean info = new DownloadInfoBean();
                info.setType(DownloadInfoBean.BANGUMI_TYPE);
                info.setCid(currentCid);
                info.setTitle(currentTitle);
                info.setSourceUrl(currentUrl);
                info.setCover(currentCover);
                info.setOriginalUrl(currentUri.toString());
                DownloadManager.getInstance().preparedDownload(info);
                Toast.makeText(this,"开始下载",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
