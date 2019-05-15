package com.github.welcomeworld.simplebili;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.github.welcomeworld.simplebili.adapter.LiveDetailPagerAdapter;
import com.github.welcomeworld.simplebili.bean.DanmakuBean;
import com.github.welcomeworld.simplebili.bean.EnterRoomDataBean;
import com.github.welcomeworld.simplebili.bean.LivePackageBean;
import com.github.welcomeworld.simplebili.bean.RoomHistoryDanmakuBean;
import com.github.welcomeworld.simplebili.bean.RoomInfoBean;
import com.github.welcomeworld.simplebili.bean.RoomStatusBean;
import com.github.welcomeworld.simplebili.common.VideoDataSource;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.LiveDetailNetAPI;
import com.github.welcomeworld.simplebili.widget.LiveMediaView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
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
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LivePlayActivity extends SimpleBaseActivity {

    public static final String TAG="LivePlayActivity";

    @BindView(R.id.liveMediaView)
    LiveMediaView ijkMediaView;
    @BindView(R.id.live_viewpager)
    ViewPager viewPager;
    @BindView(R.id.live_tab)
    TabLayout tabLayout;

    SocketChannel liveSocketChannel;
    Timer timer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_live_play);
        ButterKnife.bind(this);
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        Uri uri=getIntent().getData();
        if(uri!=null){
            if(getIntent().getStringExtra("url")!=null){
                VideoDataSource videoDataSource=new VideoDataSource();
                videoDataSource.setTitle(getIntent().getExtras().getString("title"));
                List<String> videos=new ArrayList<>();
                videos.add(getIntent().getStringExtra("url"));
                videoDataSource.setVideoSources(videos);
                ijkMediaView.addVideoDataSource(videoDataSource);
            }
            Map<String,String> parameters=new HashMap<>();
            parameters.put("actionKey","appkey");
            parameters.put("id",uri.getPath().substring(1));
            parameters.put("from","room");
            parameters.put("ts",""+System.currentTimeMillis());
            OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                    .addInterceptor(new FixedHeaderInterceptor())
                    .addInterceptor(new DynamicHeaderInterceptor(null))
                    .addInterceptor(new FixedParameterInterceptor())
                    .addInterceptor(new DynamicParameterInterceptor(parameters))
                    .addInterceptor(new SortAndSignInterceptor())
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(BaseUrl.APILIVEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();
            LiveDetailNetAPI liveDetailNetAPI=retrofit.create(LiveDetailNetAPI.class);
            Call<RoomInfoBean> roomInfoBeanCall=liveDetailNetAPI.getRoomInfo();
            roomInfoBeanCall.enqueue(new Callback<RoomInfoBean>() {
                @Override
                public void onResponse(Call<RoomInfoBean> call, Response<RoomInfoBean> response) {
                    tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                    Bundle data=new Bundle();
                    data.putInt("upId",response.body().getData().getUid());
                    data.putInt("roomId",response.body().getData().getRoomId());
                    data.putInt("areaId",response.body().getData().getAreaId());
                    LiveDetailPagerAdapter liveDetailPagerAdapter=new LiveDetailPagerAdapter(getSupportFragmentManager(),data);
                    viewPager.setAdapter(liveDetailPagerAdapter);
                    Observable observable= Observable.create(new ObservableOnSubscribe<LivePackageBean>() {
                        @Override
                        public void subscribe(ObservableEmitter<LivePackageBean> emitter) {
                            try {
                                ArrayList<Byte> byteList=new ArrayList<>();
                                liveSocketChannel=SocketChannel.open(new InetSocketAddress("livecmt-2.bilibili.com",2243));
                                ByteBuffer byteBuffer= ByteBuffer.allocate(5120);
                                Gson gson=new Gson();
                                byte[] bytes=gson.toJson(new EnterRoomDataBean(Long.parseLong(uri.getPath().substring(1)),0)).getBytes();
                                liveSocketChannel.write(new LivePackageBean(LivePackageBean.ENTER_ROOM,bytes).getByteBuffer());
                                while(true){
                                    int readnum=liveSocketChannel.read(byteBuffer);
                                    if(readnum==-1){
                                        System.out.println("Connect is close");
                                        break;
                                    }
                                    if(readnum==0){
                                        continue;
                                    }
                                    byteBuffer.flip();
                                    try {
                                        while(byteBuffer.remaining()>=16){
                                            emitter.onNext(LivePackageBean.parse(byteBuffer));
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    byteBuffer.compact();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                    Disposable liveDisPosable=observable.subscribe(new Consumer<LivePackageBean>() {
                        @Override
                        public void accept(LivePackageBean livePackageBean) throws Exception {
                            if(livePackageBean.type==LivePackageBean.ENTER_ROOM_SUCCESS){
                                timer=new Timer();
                                TimerTask timerTask=new TimerTask() {
                                    @Override
                                    public void run() {
                                        Log.e(TAG,"heart timer run in"+Thread.currentThread().getName());
                                        try {
                                            liveSocketChannel.write(new LivePackageBean().getByteBuffer());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                timer.scheduleAtFixedRate(timerTask,30000L,30000L);
                            }else if(livePackageBean.type==LivePackageBean.DATA){
                                Gson gson=new GsonBuilder().disableHtmlEscaping().create();
                                JsonObject jsonElements=gson.fromJson(new InputStreamReader(new ByteArrayInputStream(livePackageBean.content)),JsonObject.class);
                                if(jsonElements.get("cmd").getAsString().equalsIgnoreCase("DANMU_MSG")){
                                    JsonArray info=jsonElements.get("info").getAsJsonArray();
                                    DanmakuBean danmakuBean=new DanmakuBean();
                                    danmakuBean.setPriority((byte)0);
                                    danmakuBean.setText(info.get(1).getAsString());
                                    danmakuBean.setTextColor(info.get(0).getAsJsonArray().get(3).getAsInt());
                                    danmakuBean.setTextSize(info.get(0).getAsJsonArray().get(2).getAsInt());
                                    danmakuBean.setType(info.get(0).getAsJsonArray().get(1).getAsInt());
                                    danmakuBean.setUserId(info.get(2).getAsJsonArray().get(0).getAsInt());
                                    danmakuBean.setUserName(info.get(2).getAsJsonArray().get(1).getAsString());
                                    ijkMediaView.addDanmaku(danmakuBean);
                                    viewPager.setOffscreenPageLimit(2);
                                    ((LiveDetailPagerAdapter)viewPager.getAdapter()).addDanmaku(danmakuBean);
                                }
                            }
                        }
                    });
                }

                @Override
                public void onFailure(Call<RoomInfoBean> call, Throwable t) {

                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        ijkMediaView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkMediaView.release();
        IjkMediaPlayer.native_profileEnd();
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
