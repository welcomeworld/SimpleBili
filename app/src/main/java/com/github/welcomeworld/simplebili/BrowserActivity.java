package com.github.welcomeworld.simplebili;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowserActivity extends AppCompatActivity {

    @BindView(R.id.browser_toolbar)
    Toolbar toolbar;
    @BindView(R.id.browser_webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri=getIntent().getData();
        if(uri==null){
            Log.e("TAG",getResources().getString(R.string.uri_null_tip));
            finish();
            return;
        }
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);
        Log.e("TAG",uri.toString());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(uri.toString());
        webView.requestFocus();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
