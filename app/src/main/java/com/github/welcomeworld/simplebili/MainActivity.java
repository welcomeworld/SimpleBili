package com.github.welcomeworld.simplebili;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.open_file)
    Button openFileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.open_file)
    public void openFile(){
        Intent fileIntent=new Intent(Intent.ACTION_GET_CONTENT);
        fileIntent.setType("video/*");
        startActivityForResult(Intent.createChooser(fileIntent,getResources().getString(R.string.choose__tip)),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            Uri uri=data.getData();
            Intent videoIntent=new Intent(MainActivity.this,PlayActivity.class);
            videoIntent.setData(uri);
            startActivity(videoIntent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
