package com.github.welcomeworld.simplebili;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.constraint.Group;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.welcomeworld.simplebili.adapter.VideoDetailPagerAdapter;
import com.github.welcomeworld.simplebili.bean.LocalHistoryBean;
import com.github.welcomeworld.simplebili.bean.ReplyCursorBean;
import com.github.welcomeworld.simplebili.bean.VideoDetailPageBean;
import com.github.welcomeworld.simplebili.bean.VideoUrlBean;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.common.VideoDataSource;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.IndexNetAPI;
import com.github.welcomeworld.simplebili.net.retrofit.VideoDetailNetAPI;
import com.github.welcomeworld.simplebili.widget.IjkMediaView;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoDetailActivity extends SimpleBaseActivity {

    @BindView(R.id.ijkVideoView)
    IjkMediaView ijkMediaView;
    @BindView(R.id.video_detail_group)
    Group group;
    @BindView(R.id.video_detail_tab)
    TabLayout tabLayout;
    @BindView(R.id.video_detail_viewpager)
    ViewPager viewPager;

    private String currentAid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);
        Uri uri=getIntent().getData();
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        VideoDetailPagerAdapter videoDetailPagerAdapter=new VideoDetailPagerAdapter(null,null);
        videoDetailPagerAdapter.setLoadListener(new SwiperefreshContainer.OnLoadListener() {
            @Override
            public void onLoad() {
                Map<String,String> replyParameters=new HashMap<>();
                if(videoDetailPagerAdapter.getMaxId()!=0){
                    replyParameters.put("max_id",videoDetailPagerAdapter.getMaxId()+"");
                }
                replyParameters.put("oid",currentAid);
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
                VideoDetailNetAPI replyVideoDetailNetAPI=replyRetrofit.create(VideoDetailNetAPI.class);
                replyVideoDetailNetAPI.getVideoReply().enqueue(new Callback<ReplyCursorBean>() {
                    @Override
                    public void onResponse(Call<ReplyCursorBean> call, Response<ReplyCursorBean> response) {
                        videoDetailPagerAdapter.setLoading(false);
                        ((VideoDetailPagerAdapter)viewPager.getAdapter()).addReplyData(response.body().getData());
                    }

                    @Override
                    public void onFailure(Call<ReplyCursorBean> call, Throwable t) {
                        videoDetailPagerAdapter.setLoading(false);
                        Toast.makeText(getApplicationContext(),"电波无法到达",Toast.LENGTH_SHORT).show();
                        Log.e("ReplyError",t.getMessage()+"/n");
                        t.printStackTrace();
                    }
                });
            }
        });
        videoDetailPagerAdapter.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Map<String,String> replyParameters=new HashMap<>();
                replyParameters.put("oid",currentAid);
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
                VideoDetailNetAPI replyVideoDetailNetAPI=replyRetrofit.create(VideoDetailNetAPI.class);
                replyVideoDetailNetAPI.getVideoReply().enqueue(new Callback<ReplyCursorBean>() {
                    @Override
                    public void onResponse(Call<ReplyCursorBean> call, Response<ReplyCursorBean> response) {
                        videoDetailPagerAdapter.setRefreshing(false);
                        ((VideoDetailPagerAdapter)viewPager.getAdapter()).setReplyData(response.body().getData());
                    }

                    @Override
                    public void onFailure(Call<ReplyCursorBean> call, Throwable t) {
                        videoDetailPagerAdapter.setRefreshing(false);
                        Toast.makeText(getApplicationContext(),"电波无法到达",Toast.LENGTH_SHORT).show();
                        Log.e("ReplyError",t.getMessage()+"/n");
                        t.printStackTrace();
                    }
                });
            }
        });
        viewPager.setAdapter(videoDetailPagerAdapter);
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        if(uri!=null) {
            currentAid = uri.getPath().substring(1);
            currentAid = currentAid.substring(0,currentAid.contains("/")?currentAid.indexOf("/"):currentAid.length());
            Map<String,String> parameters=new HashMap<>();
            parameters.put("ad_extra","6BCCA2213B3B094292DFF9454EB02128A5CD624F226D40F0B86CA8263CE1D50C927F2F8DF2FD134C361A06FAA537402E66E53155D1C1F218BE42D2AFE4A6A701C496E5196401D81A1E390498A1CA24C20C25C97600C552962682D90C9D9DF8B9EDAD866490BE972EA37F92AA7A1040F2BEA5122D039B942437307F298336AAF27EFB5AF87961F6F852401DD8BBD0BFB92309D3B60C12E307ECD02D9BCBB19725E2964F77CDE07BFAC45A34884CE0167EEDBB0EBC8926A3CC9CB9B27536BF9C0DF87AB6DABAE86D1E6D4E714BC140A1D500E27446265DC85C226B381E10AF2299D961E06FA60A84EE34DFCB65E1253339112FD0D5ECE9C9C58C084D028DD7E26A70DC806C36C46E9D5C08169A2571B8BAC2BC0AE91AE8D36F3CBDCD2768950CD1CE9C3A3F53B5FE145A20B020435E79CA");
            parameters.put("aid",currentAid);
            parameters.put("autoplay","0");
            parameters.put("fnval","16");
            parameters.put("fnver","0");
            parameters.put("from","7");
            parameters.put("force_host","0");
            parameters.put("plat","0");
            parameters.put("trackid","all_16.shylf-ai-recsys-120.1548139653021.82");
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
                    .baseUrl(BaseUrl.APPURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();
            VideoDetailNetAPI videoDetailNetAPI=retrofit.create(VideoDetailNetAPI.class);
            videoDetailNetAPI.getVideoDetailPageInfo().enqueue(new Callback<VideoDetailPageBean>() {
                @Override
                public void onResponse(Call<VideoDetailPageBean> call, Response<VideoDetailPageBean> response) {
                    if(response.body() ==null||response.body().getCode() !=0){
                        return;
                    }
                    ((VideoDetailPagerAdapter)viewPager.getAdapter()).setDescData(response.body().getData());
                    LocalHistoryBean localHistoryBean = new LocalHistoryBean();
                    localHistoryBean.setType(LocalHistoryBean.VIDEO);
                    localHistoryBean.setAid(response.body().getData().getAid());
                    localHistoryBean.setCover(response.body().getData().getPic());
                    localHistoryBean.setMid(BiliLocalStatus.getMid());
                    localHistoryBean.setTitle(response.body().getData().getTitle());
                    localHistoryBean.setViewTime(System.currentTimeMillis());
                    localHistoryBean.setViewProgress(0);
                    localHistoryBean.setUpName(response.body().getData().getOwner().getName());
                    localHistoryBean.setDuration(response.body().getData().getDuration());
                    try{
                        ((MApplication)getApplication()).getDatabase().getDao().setHistory(localHistoryBean);
                    }catch (Exception e){
                        ((MApplication)getApplication()).getDatabase().getDao().updateHistory(localHistoryBean);
                    }
                    for(int i=0;i<response.body().getData().getPages().size();i++){
                        int index=i;
                        VideoDataSource videoDataSource=new VideoDataSource();
                        videoDataSource.setDanmakuSource(response.body().getData().getPages().get(i).getDmlink());
                        videoDataSource.setTitle(response.body().getData().getPages().get(i).getPart());
                        Map<String,String> parameters=new HashMap<>();
                        parameters.put("device","android");
                        parameters.put("aid",currentAid);
                        parameters.put("expire","0");
                        parameters.put("fnval","16");
                        parameters.put("fnver","0");
                        parameters.put("otype","json");
                        parameters.put("force_host","0");
                        parameters.put("cid",response.body().getData().getPages().get(i).getCid()+"");
                        parameters.put("npcybs","0");
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
                                .baseUrl(BaseUrl.APPURL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(okHttpClientBuilder.build())
                                .build();
                        VideoDetailNetAPI videoDetailNetAPI=retrofit.create(VideoDetailNetAPI.class);
                        videoDetailNetAPI.getVideoUrl().enqueue(new Callback<VideoUrlBean>() {
                            @Override
                            public void onResponse(Call<VideoUrlBean> call, Response<VideoUrlBean> response) {
                                String urlString;
                                if(response.body().getData().getDash()!=null){
                                    videoDataSource.setDash(true);
                                    ArrayList<String> videoSources=new ArrayList<>();
                                    ArrayList<String> descriptions=new ArrayList<>();
                                    ArrayList<String> audioSources=new ArrayList<>();
                                    for(int j=0;j<response.body().getData().getDash().getVideo().size();j++){
                                        videoSources.add(response.body().getData().getDash().getVideo().get(j).getBase_url());
                                        String description;
                                        switch (response.body().getData().getDash().getVideo().get(j).getId()){
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
                                        audioSources.add(response.body().getData().getDash().getAudio().get(0).getBase_url());
                                    }
                                    videoDataSource.setVideoSources(videoSources);
                                    videoDataSource.setDescriptions(descriptions);
                                    videoDataSource.setAudioSources(audioSources);
                                    urlString=response.body().getData().getDash().getVideo().get(0).getBase_url();
                                }else {
                                    videoDataSource.setDash(false);
                                    ArrayList<String> videoSources=new ArrayList<>();
                                    ArrayList<String> descriptions=new ArrayList<>();
                                    videoSources.add(response.body().getData().getDurl().get(0).getUrl());
                                    descriptions.add(response.body().getData().getFormat());
                                    videoDataSource.setVideoSources(videoSources);
                                    videoDataSource.setDescriptions(descriptions);
                                    urlString=response.body().getData().getDurl().get(0).getUrl();
                                }
                                ijkMediaView.addVideoDataSource(videoDataSource);
                            }

                            @Override
                            public void onFailure(Call<VideoUrlBean> call, Throwable t) {

                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<VideoDetailPageBean> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"电波无法到达",Toast.LENGTH_SHORT).show();
                }
            });

            Map<String,String> replyParameters=new HashMap<>();
            replyParameters.put("oid",currentAid);
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
            VideoDetailNetAPI replyVideoDetailNetAPI=replyRetrofit.create(VideoDetailNetAPI.class);
            replyVideoDetailNetAPI.getVideoReply().enqueue(new Callback<ReplyCursorBean>() {
                @Override
                public void onResponse(Call<ReplyCursorBean> call, Response<ReplyCursorBean> response) {
                    ((VideoDetailPagerAdapter)viewPager.getAdapter()).setReplyData(response.body().getData());
                }

                @Override
                public void onFailure(Call<ReplyCursorBean> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"电波无法到达",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this,R.string.uri_null_tip,Toast.LENGTH_SHORT).show();
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
        ijkMediaView.pause();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkMediaView.release();
        IjkMediaPlayer.native_profileEnd();
    }
}
