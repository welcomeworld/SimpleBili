package com.github.welcomeworld.simplebili;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.github.welcomeworld.simplebili.widget.IjkMediaView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayActivity extends Activity {
    @BindView(R.id.ijkVideoView)
    IjkMediaView ijkMediaView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        Uri uri=getIntent().getData();
        if(uri!=null) {
            ijkMediaView.setVideoPath(uri);
            ijkMediaView.changeToLandscape();
        }else{
            Toast.makeText(this,R.string.uri_null_tip,Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        IjkMediaPlayer.native_profileEnd();
    }
}
