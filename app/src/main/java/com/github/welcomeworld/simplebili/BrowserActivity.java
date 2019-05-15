package com.github.welcomeworld.simplebili;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowserActivity extends SimpleBaseActivity {

    @BindView(R.id.browser_toolbar)
    Toolbar toolbar;
    @BindView(R.id.browser_webView)
    WebView webView;
    WebViewClient webViewClient;
    WebChromeClient webChromeClient;
    WebSettings webSettings;


    @SuppressLint("SetJavaScriptEnabled")
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
        webViewClient=new BiliWebViewClient();
        webChromeClient=new BiliWebChromeClient();
        webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(true);
        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
        webView.loadUrl(uri.toString());
        webView.requestFocus();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if(webView!=null){
            webView.removeAllViews();
            webView.destroy();
            webView=null;
        }
        super.onDestroy();
    }

    private class BiliWebChromeClient extends WebChromeClient{
        @Override
        public void onReceivedTitle(WebView view, String title) {
            toolbar.setTitle(title);
        }
    }
    private class BiliWebViewClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri uri=Uri.parse(url);
            if(uri.getHost().equalsIgnoreCase("feedback")||uri.getHost().equalsIgnoreCase("assistant")){
                view.loadUrl("https://github.com/welcomeworld/SimpleBili/issues");
                return true;
            }
            view.loadUrl(url);
            return true;
        }
    }
}
