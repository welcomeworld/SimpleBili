package com.github.welcomeworld.simplebili.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.provider.Settings;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.adapter.BiliDanmukuParser;
import com.github.welcomeworld.simplebili.common.VideoDataSource;
import com.github.welcomeworld.simplebili.listener.IjkMediaListener;
import com.github.welcomeworld.simplebili.utils.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkMediaView extends FrameLayout implements SeekBar.OnSeekBarChangeListener,View.OnTouchListener,RadioGroup.OnCheckedChangeListener{

    private String TAG="IjkMediaView";

    private IjkMediaPlayer mMediaPlayer = null;

    private IjkMediaPlayer audioPlayer=null;

    /** * 视频文件地址 */
    private List<VideoDataSource> videoDataSources=null;
    private int currentSourceIndex=0;
    private int currentQuality=0;
    private Context mContext;

    private String shareString="";


    /** *控件大小*/
    private int width;
    private int height;

    GestureDetector gestureDetector;

    AudioManager audioManager;

    DanmakuContext danmakuContext;
    BaseDanmakuParser baseDanmakuParser;

    PopupWindow moreOptions;

    PopupWindow videoListPopupWindow;

    Observable<Long> videoObservable;
    Disposable videoDisposable;

    private boolean videoPrepared;
    private boolean audioPrepared;



    //RootView
    @BindView(R.id.surface)
    SurfaceView surfaceView;
    @BindView(R.id.pauseOrStart)
    ImageView pauseOrStartButton;
    @BindView(R.id.seek_bar)
    SeekBar seekBar;
    @BindView(R.id.current_position)
    TextView currentPosition;
    @BindView(R.id.duration)
    TextView durationView;
    @BindView(R.id.player_bottom)
    LinearLayout bottomLayout;
    @BindView(R.id.fullscreen)
    ImageView fullscreenButton;
    @BindView(R.id.player_top)
    LinearLayout topLayout;
    @BindView(R.id.lock)
    ImageView lockView;
    @BindView(R.id.cover)
    ImageView coverView;
    @BindView(R.id.share)
    ImageView shareView;
    @BindView(R.id.play_back)
    ImageView backButton;
    @BindView(R.id.play_title)
    TextView titleView;
    @BindView(R.id.player_volume)
    LinearLayout volumeLayout;
    @BindView(R.id.volume_seekbar)
    SeekBar volumeSeekBar;
    @BindView(R.id.volume_image)
    ImageView volumeImage;
    @BindView(R.id.player_bright)
    LinearLayout brightLayout;
    @BindView(R.id.bright_seekbar)
    SeekBar brightSeekBar;
    @BindView(R.id.fastfoward)
    TextView fastfowardView;
    @BindView(R.id.danmaku_status)
    ImageView danmakuStatusButton;
    @BindView(R.id.more)
    ImageView playerMoreButton;
    @BindView(R.id.play_video_list)
    TextView videoListButton;
    @BindView(R.id.danmaku_view)
    IDanmakuView danmakuView;





    //status
    private int INVALID_CODE=-1;
    private boolean locked=false;
    private boolean subViewHidded=false;
    private long progress=0;
    private long duration=0;
    private final int SEEKBAR_MAX=1000;
    private boolean volumechanging=false;
    private boolean brightchanging=false;
    private boolean fastfowarding=false;
    private int VOLUME_MAX=INVALID_CODE;
    private int screen_width=INVALID_CODE;
    private int screen_height=INVALID_CODE;
    private double lastXpercentage=0;
    private double lastYpercentage=0;
    private static final long FASTFORWARD_MAX=180000;
    private long fastforward_record=0;
    private float speed=1;
    private boolean systemBack=true;
    private int playMode=1;
    private boolean seekBarTracking=false;
    private boolean stopChangeSeek=false;

    private IjkMediaListener videoListener;
    private IjkMediaListener audioListener;

    public void addVideoDataSource(VideoDataSource videoDataSource){
        if(videoDataSource==null||videoDataSource.getVideoSources()==null){
            return;
        }
        if(videoDataSources!=null){
            videoDataSources.add(videoDataSource);
            initVideoListWindow();
        }else{
            videoDataSources=new ArrayList<>();
            videoDataSources.add(videoDataSource);
            setVideoDataSources(videoDataSources);
        }
    }

    public IjkMediaView(@NonNull Context context) {
        super(context);
        mContext=context;
        initVideoView(context);
    }
    public IjkMediaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initVideoView(context);
    }
    public IjkMediaView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initVideoView(context);
    }

    @SuppressLint("UseSparseArrays")
    private void initDanmaku() {
        HashMap<Integer,Integer> maxLinesPair=new HashMap<>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL,5);
        HashMap<Integer,Boolean> overlappingEnable=new HashMap<>();
        overlappingEnable.put(BaseDanmaku.TYPE_FIX_TOP,true);
        overlappingEnable.put(BaseDanmaku.TYPE_SCROLL_RL,true);
        danmakuContext=DanmakuContext.create();
        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN,3);
        danmakuContext.setDuplicateMergingEnabled(false);
        danmakuContext.setMaximumLines(maxLinesPair);
        danmakuContext.setScaleTextSize(1);
        danmakuContext.setScrollSpeedFactor(1);
        danmakuContext.preventOverlapping(overlappingEnable);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                Log.e("danmaku","prepared");
                if(mMediaPlayer!=null&&mMediaPlayer.isPlaying()){
                    danmakuView.start(mMediaPlayer.getCurrentPosition());
                }
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {
                Log.e("danmaku","shown");
            }

            @Override
            public void drawingFinished() {
                Log.e("danmaku","finished");
            }
        });
        createDanmakuParser(videoDataSources.get(currentSourceIndex).getDanmakuSource());
        danmakuView.enableDanmakuDrawingCache(true);
    }

    private void createDanmakuParser(String uri){
        if(uri==null){
            return;
        }
        ILoader iLoader= DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
        OkHttpClient okHttpClient=new OkHttpClient().newBuilder().build();
        okHttpClient.newCall(new Request.Builder().url(uri).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,e.getMessage()==null?"消息体为空！instance:"+e.toString():e.getMessage());
                Toast.makeText(getContext(),"弹幕加载失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
                try {
                    InflaterInputStream deflaterInputStream=new InflaterInputStream(response.body().byteStream(),new Inflater(true));
                    int a;
                    while((a=deflaterInputStream.read())!=-1){
                        outputStream.write(a);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e(TAG,"Malformedmessage"+e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG,"message"+e.getMessage());
                }
                try {
                    iLoader.load(new ByteArrayInputStream(outputStream.toByteArray()));
                } catch (IllegalDataException e) {
                    e.printStackTrace();
                    Log.e(TAG,"error Uri:"+uri+"message"+e.getMessage());
                }
                BaseDanmakuParser parser=new BiliDanmukuParser();
                parser.load(iLoader.getDataSource());
                baseDanmakuParser=parser;
                danmakuView.prepare(baseDanmakuParser,danmakuContext);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }



    @SuppressLint("ClickableViewAccessibility")
    private void initVideoView(Context context) {
        setFocusable(true);
        View rootView=LayoutInflater.from(context).inflate(R.layout.ijkmediaview,this,true);
        ButterKnife.bind(rootView);
        createSurfaceView();
        gestureDetector=new GestureDetector(context,new PlayerGestureDetectorListener());
        audioManager= (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        this.setOnTouchListener(this);
        seekBar.setMax(SEEKBAR_MAX);
        seekBar.setOnSeekBarChangeListener(this);
        brightSeekBar.setMax(SEEKBAR_MAX);
        volumeSeekBar.setMax(SEEKBAR_MAX);
        int settingBrightness;
        try {
            settingBrightness= Settings.System.getInt(mContext.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            settingBrightness=125;
            e.printStackTrace();
        }
        brightSeekBar.setProgress(settingBrightness*SEEKBAR_MAX/255);
        Activity activity= (Activity) context;
        WindowManager.LayoutParams layoutParams=activity.getWindow().getAttributes();
        layoutParams.screenBrightness=settingBrightness/(float)255;
        activity.getWindow().setAttributes(layoutParams);
        initPopupWindow();
        videoObservable=Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                while(!stopChangeSeek){
                    try {
                        emitter.onNext(1L);
                        Thread.sleep((long) (1000/speed));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    private void initPopupWindow(){
        screen_height=getResources().getDisplayMetrics().heightPixels;
        screen_width=getResources().getDisplayMetrics().widthPixels;
        if (screen_width < screen_height) {
            int temp=screen_width;
            screen_width=screen_height;
            screen_height=temp;
        }
        View popupView=LayoutInflater.from(mContext).inflate(R.layout.player_more,this,false);
        moreOptions=new PopupWindow(popupView,screen_width/2,screen_height,true);
        moreOptions.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        moreOptions.setTouchable(true);
        moreOptions.setAnimationStyle(R.style.rightPopupWindowTheme);
        RadioGroup speedList=popupView.findViewById(R.id.play_speed_list);
        speedList.setOnCheckedChangeListener(this);
        RadioGroup sizeList=popupView.findViewById(R.id.size_list);
        sizeList.setOnCheckedChangeListener(this);
        RadioGroup wayList=popupView.findViewById(R.id.play_way);
        wayList.setOnCheckedChangeListener(this);
    }

    public void setVideoDataSources(List<VideoDataSource> videoDataSources){
        setVideoDataSources(videoDataSources,0);
    }

    public void setVideoDataSources(List<VideoDataSource> videoDataSources,int currentIndex) {
        this.videoDataSources=videoDataSources;
        this.currentSourceIndex=currentIndex;
        titleView.setText(videoDataSources.get(currentSourceIndex).getTitle());
        load();
        initVideoListWindow();
    }

    private void initVideoListWindow(){
        View videoListView=LayoutInflater.from(mContext).inflate(R.layout.player_video_list,this,false);
        screen_height=getResources().getDisplayMetrics().heightPixels;
        screen_width=getResources().getDisplayMetrics().widthPixels;
        if (screen_width < screen_height) {
            int temp=screen_width;
            screen_width=screen_height;
            screen_height=temp;
        }
        videoListPopupWindow=new PopupWindow(videoListView,screen_width/2,screen_height,true);
        videoListPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        videoListPopupWindow.setTouchable(true);
        videoListPopupWindow.setAnimationStyle(R.style.rightPopupWindowTheme);
       RadioGroup radioGroup=videoListView.findViewById(R.id.video_list_radio);
       for(int i=0;i<videoDataSources.size();i++){
           RadioButton radioButton=new RadioButton(radioGroup.getContext());
           radioButton.setButtonDrawable(null);
           radioButton.setTextColor(getResources().getColorStateList(R.color.radio_color));
           radioButton.setText(videoDataSources.get(i).getTitle());
           if(i==currentSourceIndex){
               radioButton.setChecked(true);
           }
           radioButton.setId(i);
           radioGroup.addView(radioButton);
       }
       radioGroup.setOnCheckedChangeListener(this);
    }



    private void createSurfaceView() {
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(videoDataSources!=null){
                    load();
                }
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    @OnClick(R.id.pauseOrStart)
    public void pauseOrStart(){
        if(mMediaPlayer!=null){
            if(mMediaPlayer.isPlaying()){
                pause();
            }else{
                start();
            }
        }
    }

    @OnClick(R.id.fullscreen)
    public void fullscreen(){
        changeToLandscape();
        systemBack=false;
    }

    @OnClick(R.id.lock)
    public void lockOrUnlock(){
        if(locked){
            locked=false;
            lockView.setSelected(false);
            showSubView();
        }else{
            locked=true;
            lockView.setSelected(true);
            hideSubView();
        }
    }

    @OnClick(R.id.share)
    public void shareVideo(){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,videoDataSources.get(currentSourceIndex).getVideoSources().get(0));
        mContext.startActivity(Intent.createChooser(intent,"分享到:"));
    }

    @OnClick(R.id.player_top)
    public void topDoNothing(){}

    @OnClick(R.id.player_bottom)
    public void bottomDoNothing(){}

    @OnClick(R.id.play_back)
    public void playBack(){
        if(isSystemBack()){
            Activity activity= (Activity) mContext;
            activity.onBackPressed();
        }else{
            systemBack=true;
            changeToPortrait();
        }
    }

    @OnClick(R.id.danmaku_status)
    public void danmakuStatusChange(){
        if(danmakuStatusButton.isSelected()){
            danmakuStatusButton.setSelected(false);
            danmakuView.show();
        }else{
            danmakuStatusButton.setSelected(true);
            danmakuView.hide();
        }
    }

    @OnClick(R.id.more)
    public void moreOptions(){
        moreOptions.showAtLocation(this,Gravity.END,0,0);
    }

    @OnClick(R.id.play_video_list)
    public void showVideoList(){
        videoListPopupWindow.showAtLocation(this,Gravity.END,0,0);
    }

    /** * 加载视频 */
    private void load() {
        initDanmaku();
        createPlayer();
        try {
            mMediaPlayer.setDataSource(videoDataSources.get(currentSourceIndex).getVideoSources().get(currentQuality));
            if (audioPlayer != null) {
                audioPlayer.stop();
                audioPlayer.setDisplay(null);
                audioPlayer.release();
            }
            audioPlayer=null;
            if(videoDataSources.get(currentSourceIndex).isDash()){
                createAudioPlayer();
                audioPlayer.setDataSource(videoDataSources.get(currentSourceIndex).getAudioSources().get(0));
                audioPlayer.prepareAsync();
            }
            }
            catch (Exception e) {
            e.printStackTrace();
            }
            audioPrepared=false;
        videoPrepared=false;
        mMediaPlayer.setDisplay(surfaceView.getHolder());
        mMediaPlayer.setScreenOnWhilePlaying(true);
        mMediaPlayer.prepareAsync();
        }

    private void createAudioPlayer(){
            IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
            IjkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
            //ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"max-buffer-size",500*1024);
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 100);
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"enable-accurate-seek",1);
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"reconnect",1);
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"safe",0);
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "protocol_whitelist", "rtmp,concat,ffconcat,file,subfile,http,https,tls,rtp,tcp,udp,crypto");
            //ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"http_proxy","192.168.0.107");
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "Bilibili Freedoooooom/MarkII");
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"framedrop",5);
            audioPlayer = ijkMediaPlayer;
            if(audioListener==null){
                audioListener=new IjkMediaListener() {
                    @Override
                    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                        //seekBar.setSecondaryProgress(i*SEEKBAR_MAX/100);
                    }

                    @Override
                    public void onCompletion(IMediaPlayer iMediaPlayer) {
                        Log.d(TAG,"audioCompletion");
                        /*switch (playMode){
                            case 0:
                                pause();
                                break;
                            case 1:
                                if(currentSourceIndex<videoDataSources.size()-1){
                                    currentSourceIndex++;
                                    load();
                                }else{
                                    pause();
                                }
                                break;
                            case 2:
                                currentSourceIndex=currentSourceIndex>=videoDataSources.size()-1?0:currentSourceIndex+1;
                                load();
                                break;
                            case 3:
                                break;
                        }*/
                    }

                    @Override
                    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                        Log.e(TAG,"error_code"+i+":"+i1);
                        return false;
                    }

                    @Override
                    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                        //Log.e(TAG,"onInfo:i:"+i+"i1:"+i1);
                        return false;
                    }

                    @Override
                    public void onPrepared(IMediaPlayer iMediaPlayer) {
                        Log.e(TAG,"audioPrepared");
                        audioPrepared=true;
                        audioPlayer.pause();
                        if(videoPrepared){
                            mediaPrepared();
                        }
                    }

                    @Override
                    public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                        Log.e(TAG,"Player seek:"+iMediaPlayer.getCurrentPosition());
                    }

                    @Override
                    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

                    }
                };
            }
            audioPlayer.setOnPreparedListener(audioListener);
            audioPlayer.setOnInfoListener(audioListener);
            audioPlayer.setOnSeekCompleteListener(audioListener);
            audioPlayer.setOnCompletionListener(audioListener);
            audioPlayer.setOnBufferingUpdateListener(audioListener);
            audioPlayer.setOnErrorListener(audioListener);
            audioPlayer.setOnVideoSizeChangedListener(audioListener);
        }

    private void mediaPrepared(){
        mMediaPlayer.start();
        if(audioPlayer!=null){
            audioPlayer.start();
        }
            duration=mMediaPlayer.getDuration();
            currentPosition.setText(StringUtils.formatTime(0));
            durationView.setText(StringUtils.formatTime(duration));
            coverView.setVisibility(GONE);
            pauseOrStartButton.setSelected(true);
            if(danmakuView.isPrepared()){
                danmakuView.start(mMediaPlayer.getCurrentPosition());
            }
            videoDisposable= videoObservable.subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long ong) {
                    if(!fastfowarding&&!seekBarTracking&&mMediaPlayer.isPlaying()){
                        currentPosition.setText(StringUtils.formatTime(mMediaPlayer.getCurrentPosition()));
                        if(duration>0)
                            seekBar.setProgress((int) (mMediaPlayer.getCurrentPosition()*SEEKBAR_MAX/duration));
                    }
                }
            });
        }
    private void createPlayer() {
                    if (mMediaPlayer != null) {
                        mMediaPlayer.stop();
                        mMediaPlayer.setDisplay(null);
                        mMediaPlayer.release();
                    }
                    IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
                    IjkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
                    //ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"max-buffer-size",500*1024);
                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 100);
                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"enable-accurate-seek",1);
                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"reconnect",1);
                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"safe",0);
         ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "protocol_whitelist", "rtmp,concat,ffconcat,file,subfile,http,https,tls,rtp,tcp,udp,crypto");
                    //ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT,"http_proxy","192.168.0.107");
                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "user_agent", "Bilibili Freedoooooom/MarkII");
                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER,"framedrop",5);
                    mMediaPlayer = ijkMediaPlayer;
                    if(videoListener==null){
                        videoListener=new IjkMediaListener() {
                            @Override
                            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                                    seekBar.setSecondaryProgress(i*SEEKBAR_MAX/100);
                            }

                            @Override
                            public void onCompletion(IMediaPlayer iMediaPlayer) {
                                switch (playMode){
                                    case 0:
                                        pause();
                                        break;
                                    case 1:
                                        if(currentSourceIndex<videoDataSources.size()-1){
                                            currentSourceIndex++;
                                            load();
                                        }else{
                                            pause();
                                        }
                                        break;
                                    case 2:
                                        currentSourceIndex=currentSourceIndex>=videoDataSources.size()-1?0:currentSourceIndex+1;
                                        load();
                                        break;
                                    case 3:
                                        break;
                                }
                            }

                            @Override
                            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                                return false;
                            }

                            @Override
                            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                                return false;
                            }

                            @Override
                            public void onPrepared(IMediaPlayer iMediaPlayer) {
                                videoPrepared=true;
                                mMediaPlayer.pause();
                                if(audioPlayer==null||audioPrepared){
                                    mediaPrepared();
                                }
                            }

                            @Override
                            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                                Log.e(TAG,"Player seek:"+iMediaPlayer.getCurrentPosition());
                            }

                            @Override
                            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

                            }
                        };
                    }
                    mMediaPlayer.setOnPreparedListener(videoListener);
                    mMediaPlayer.setOnInfoListener(videoListener);
                    mMediaPlayer.setOnSeekCompleteListener(videoListener);
                    mMediaPlayer.setOnCompletionListener(videoListener);
                    mMediaPlayer.setOnBufferingUpdateListener(videoListener);
                    mMediaPlayer.setOnErrorListener(videoListener);
                    mMediaPlayer.setOnVideoSizeChangedListener(videoListener);
                }
    private void hideSubView(){
        subViewHidded=true;
        bottomLayout.setVisibility(GONE);
        topLayout.setVisibility(GONE);
        lockView.setVisibility(GONE);
    }
    private void showSubView(){
        subViewHidded=false;
        if(!locked){
            bottomLayout.setVisibility(VISIBLE);
            topLayout.setVisibility(VISIBLE);
        }
        lockView.setVisibility(VISIBLE);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.progress=progress;
        if(seekBarTracking){
            currentPosition.setText(StringUtils.formatTime(progress*duration/SEEKBAR_MAX));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        seekBarTracking=true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.e(TAG,"onStopTracking0:"+mMediaPlayer.getCurrentPosition());
        mMediaPlayer.seekTo(progress*duration/SEEKBAR_MAX);
        if(audioPlayer!=null){
            audioPlayer.seekTo(progress*duration/SEEKBAR_MAX);
        }
        danmakuView.seekTo(mMediaPlayer.getCurrentPosition());
        currentPosition.setText(StringUtils.formatTime(mMediaPlayer.getCurrentPosition()));
        seekBarTracking=false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_UP){
            if(fastfowarding){
                fastfowarding=false;
                fastfowardView.setVisibility(GONE);
                mMediaPlayer.seekTo(fastforward_record);
                if(audioPlayer!=null){
                    audioPlayer.seekTo(fastforward_record);
                }
                fastforward_record=0;
                lastXpercentage=0;
            }
            if(brightchanging||volumechanging){
                Observable<Integer> observable=Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        Thread.sleep(1000);
                        emitter.onNext(1);
                        emitter.onComplete();
                    }
                });
                        Disposable disposable=observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) {
                                if(!brightchanging&&!volumechanging&&!fastfowarding){
                                    brightLayout.setVisibility(GONE);
                                    volumeLayout.setVisibility(GONE);
                                }
                            }
                        });
            }
            brightchanging=false;
            volumechanging=false;
            lastYpercentage=0;
        }
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group.getId()==R.id.play_speed_list){
            switch (checkedId){
                case R.id.speed_0_5:
                    mMediaPlayer.setSpeed(0.5f);
                    if(audioPlayer!=null)
                        audioPlayer.setSpeed(0.5f);
                    speed=0.5f;
                    break;
                case R.id.speed_0_75:
                    mMediaPlayer.setSpeed(0.75f);
                    if(audioPlayer!=null)
                        audioPlayer.setSpeed(0.75f);
                    speed=0.75f;
                    break;
                case R.id.speed_1:
                    mMediaPlayer.setSpeed(1f);
                    if(audioPlayer!=null)
                        audioPlayer.setSpeed(0.1f);
                    speed=1f;
                    break;
                case R.id.speed_1_25:
                    mMediaPlayer.setSpeed(1.25f);
                    if(audioPlayer!=null)
                        audioPlayer.setSpeed(1.25f);
                    speed=1.25f;
                    break;
                case R.id.speed_1_5:
                    mMediaPlayer.setSpeed(1.5f);
                    if(audioPlayer!=null)
                        audioPlayer.setSpeed(1.5f);
                    speed=1.5f;
                    break;
                case R.id.speed_2:
                    mMediaPlayer.setSpeed(2f);
                    if(audioPlayer!=null)
                        audioPlayer.setSpeed(2f);
                    speed=2f;
                    break;
            }
            if(mMediaPlayer.getCurrentPosition()-danmakuView.getCurrentTime()>1000||mMediaPlayer.getCurrentPosition()-danmakuView.getCurrentTime()<-1000){
                danmakuView.seekTo(mMediaPlayer.getCurrentPosition());
            }
        }
        if(group.getId()==R.id.size_list){
            ViewGroup.LayoutParams layoutParams;
            screen_height=getResources().getDisplayMetrics().heightPixels;
            screen_width=getResources().getDisplayMetrics().widthPixels;
            switch (checkedId){
                case R.id.size_default:
                    layoutParams=surfaceView.getLayoutParams();
                    layoutParams.width=mMediaPlayer.getVideoWidth();
                    layoutParams.height=mMediaPlayer.getVideoHeight();
                    surfaceView.setLayoutParams(layoutParams);
                    break;
                case R.id.size_fullscreen:
                    layoutParams=surfaceView.getLayoutParams();
                    layoutParams.width=screen_width;
                    layoutParams.height=screen_height;
                    surfaceView.setLayoutParams(layoutParams);
                    break;
                case R.id.size_4_3:
                    layoutParams=surfaceView.getLayoutParams();
                    layoutParams.width=screen_height*4/3;
                    layoutParams.height=screen_height;
                    surfaceView.setLayoutParams(layoutParams);
                    break;
                case R.id.size_16_9:
                    layoutParams=surfaceView.getLayoutParams();
                    layoutParams.width=screen_height*16/9;
                    layoutParams.height=screen_height;
                    surfaceView.setLayoutParams(layoutParams);
                    break;
            }
        }
        if(group.getId()==R.id.play_way){
            switch (checkedId){
                case R.id.playway_pause:
                    playMode=0;
                    mMediaPlayer.setLooping(false);
                    if(audioPlayer!=null)
                        audioPlayer.setLooping(false);
                    break;
                case R.id.playway_auto:
                    playMode=1;
                    mMediaPlayer.setLooping(false);
                    if(audioPlayer!=null)
                        audioPlayer.setLooping(false);
                    break;
                case R.id.playway_list_recycle:
                    playMode=2;
                    mMediaPlayer.setLooping(false);
                    if(audioPlayer!=null)
                        audioPlayer.setLooping(false);
                    break;
                case R.id.playway_item_recycle:
                    playMode=3;
                    mMediaPlayer.setLooping(true);
                    if(audioPlayer!=null)
                        audioPlayer.setLooping(true);
                    break;
            }
        }
        if(group.getId()==R.id.video_list_radio){
            currentSourceIndex=checkedId;
            load();
        }

    }

    class PlayerGestureDetectorListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if(!locked){
                pauseOrStart();
                return true;
            }
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if(subViewHidded){
                subViewHidded=false;
                showSubView();
            }else{
                subViewHidded=true;
                hideSubView();
            }
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(locked){
                return false;
            }
            String fastfowardText;
            screen_height=getResources().getDisplayMetrics().heightPixels;
            screen_width=getResources().getDisplayMetrics().widthPixels;
            double change;
            double xpercentage= -distanceX/screen_width+lastXpercentage;
            double ypercentage= distanceY/screen_height+lastYpercentage;
            if(!brightchanging&&!volumechanging&&!fastfowarding){
                if(xpercentage>0.1||xpercentage<-0.1){
                    fastfowarding=true;
                    brightLayout.setVisibility(GONE);
                    volumeLayout.setVisibility(GONE);
                    fastforward_record=mMediaPlayer.getCurrentPosition();
                    fastfowardText=StringUtils.formatTime(fastforward_record)+"/"+StringUtils.formatTime(duration);
                    fastfowardView.setText(fastfowardText);
                    fastfowardView.setVisibility(VISIBLE);
                }else if(ypercentage>0.1||ypercentage<-0.1){
                    if(e1.getX()<screen_width/2){
                        brightchanging=true;
                        volumeLayout.setVisibility(GONE);
                        fastfowardView.setVisibility(GONE);
                        brightLayout.setVisibility(VISIBLE);
                    }else{
                        volumechanging  =true;
                        brightLayout.setVisibility(GONE);
                        fastfowardView.setVisibility(GONE);
                        volumeLayout.setVisibility(VISIBLE);
                        VOLUME_MAX=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                    }
                }else{
                    lastYpercentage=ypercentage;
                    lastXpercentage=xpercentage;
                }
                return false;
            }
            if(brightchanging){
                Activity activity= (Activity) mContext;
                WindowManager.LayoutParams layoutParams=activity.getWindow().getAttributes();
                change=ypercentage+brightSeekBar.getProgress()/(double) SEEKBAR_MAX<0?0:ypercentage+brightSeekBar.getProgress()/(double) SEEKBAR_MAX;
                change=change>1?1:change;
                layoutParams.screenBrightness= (float) (change);
                activity.getWindow().setAttributes(layoutParams);
                brightSeekBar.setProgress((int) (brightSeekBar.getProgress()+ypercentage*SEEKBAR_MAX));
                lastYpercentage=0;
            }else if(fastfowarding){
                change=xpercentage*FASTFORWARD_MAX+fastforward_record>duration?duration:xpercentage*FASTFORWARD_MAX+fastforward_record;
                fastforward_record= (long) change;
                fastfowardText=StringUtils.formatTime(fastforward_record)+"/"+StringUtils.formatTime(duration);
                fastfowardView.setText(fastfowardText);
                seekBar.setProgress((int) (change*SEEKBAR_MAX/duration));
                currentPosition.setText(StringUtils.formatTime(fastforward_record));
                lastXpercentage=0;
            }else {
                int volume_current = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                if(1>ypercentage*VOLUME_MAX&&ypercentage*VOLUME_MAX>-1){
                    lastYpercentage=ypercentage;
                    return false;
                }
                change=ypercentage*VOLUME_MAX>0?Math.floor(ypercentage*VOLUME_MAX):Math.ceil(ypercentage*VOLUME_MAX);
                if(change+ volume_current <=0){
                    volumeImage.setSelected(true);
                }else{
                    volumeImage.setSelected(false);
                }
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) change+ volume_current,0);
                volumeSeekBar.setProgress((int) ((change+ volume_current)*SEEKBAR_MAX/VOLUME_MAX));
                lastYpercentage=0;
            }
            return false;
        }


    }

    public boolean isSystemBack() {
        return systemBack;
    }

    public void changeToLandscape(){
        systemBack=false;
        Activity activity= (Activity) mContext;
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        ViewGroup.LayoutParams layoutParams=getLayoutParams();
        width=layoutParams.width;
        height=layoutParams.height;
        layoutParams.width=-1;
        layoutParams.height=-1;
        setLayoutParams(layoutParams);
        fullscreenButton.setVisibility(GONE);
        videoListButton.setVisibility(VISIBLE);
    }

    public void changeToPortrait(){
        Activity activity= (Activity) mContext;
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ViewGroup.LayoutParams layoutParams=getLayoutParams();
        layoutParams.height=height;
        layoutParams.width=width;
        setLayoutParams(layoutParams);
        fullscreenButton.setVisibility(VISIBLE);
        videoListButton.setVisibility(GONE);
    }

    public void pause(){
        if(audioPlayer!=null)
            audioPlayer.pause();
        if(mMediaPlayer!=null){
            mMediaPlayer.pause();
            pauseOrStartButton.setSelected(false);
            danmakuView.pause();
        }
    }

    public void start(){
        coverView.setVisibility(GONE);
        mMediaPlayer.start();
        if(audioPlayer!=null)
            audioPlayer.start();
        pauseOrStartButton.setSelected(true);
        if(danmakuView.isPrepared()){
            danmakuView.start(mMediaPlayer.getCurrentPosition());
        }
    }

    public void release(){
        stopChangeSeek=true;
        if(videoDisposable!=null){
            videoDisposable.dispose();
            videoDisposable=null;
        }
        if(audioPlayer!=null){
            audioPlayer.stop();
            audioPlayer.release();
            audioPlayer=null;
        }
        if(mMediaPlayer!=null){
            mMediaPlayer.stop();
            mMediaPlayer.setDisplay(null);
            mMediaPlayer.release();
            mMediaPlayer=null;
        }
    }

}