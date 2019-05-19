package com.github.welcomeworld.simplebili;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.welcomeworld.simplebili.common.VideoDataSource;
import com.github.welcomeworld.simplebili.utils.FileUtils;
import com.github.welcomeworld.simplebili.widget.IjkMediaView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayActivity extends SimpleBaseActivity {
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
            String path;
            if(getIntent().getBooleanExtra("real_path",false)){
                path = uri.toString();
            }else {
                path= FileUtils.getPath(this,uri);
            }
            File pathDir;
            if(path!=null&&path.lastIndexOf('/')>0){
                pathDir=new File(path.substring(0,path.lastIndexOf('/')));
            }else{
                return;
            }
            List<VideoDataSource> dataSources=new ArrayList<>();
            VideoDataSource videoDataSource1=new VideoDataSource();
            videoDataSource1.setTitle(path);
            ArrayList<String> paths1=new ArrayList<>();
            paths1.add(path);
            videoDataSource1.setVideoSources(paths1);
            dataSources.add(videoDataSource1);
            if(pathDir.listFiles()!=null&&pathDir.listFiles().length>1){
                for(File child:pathDir.listFiles()){
                    if(!child.isDirectory()){
                        if(child.getName().endsWith("mp4")&&!child.getAbsolutePath().equalsIgnoreCase(path)){
                            VideoDataSource videoDataSource=new VideoDataSource();
                            videoDataSource.setTitle(child.getAbsolutePath());
                            ArrayList<String> paths=new ArrayList<>();
                            paths.add(child.getAbsolutePath());
                            videoDataSource.setVideoSources(paths);
                            dataSources.add(videoDataSource);
                        }
                    }
                }
            }
            ijkMediaView.setVideoDataSources(dataSources);
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
