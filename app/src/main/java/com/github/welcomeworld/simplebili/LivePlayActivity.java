package com.github.welcomeworld.simplebili;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.github.welcomeworld.simplebili.common.VideoDataSource;
import com.github.welcomeworld.simplebili.widget.IjkMediaView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LivePlayActivity extends AppCompatActivity {
    @BindView(R.id.ijkVideoView)
    IjkMediaView ijkMediaView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
            VideoDataSource videoDataSource=new VideoDataSource();
            videoDataSource.setTitle(getIntent().getExtras().getString("title"));
            List<String> videos=new ArrayList<>();
            videos.add(uri.toString());
            videoDataSource.setVideoSources(videos);
            ijkMediaView.addVideoDataSource(videoDataSource);
            ijkMediaView.changeToLandscape();
        }
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
