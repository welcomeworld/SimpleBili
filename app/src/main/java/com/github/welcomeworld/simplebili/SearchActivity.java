package com.github.welcomeworld.simplebili;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends SimpleBaseActivity {
    @BindView(R.id.search_input)
    EditText searchInputText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String inputText = searchInputText.getText().toString();
                if(inputText.startsWith("av")){
                    Intent videoIntent = new Intent(SearchActivity.this,VideoDetailActivity.class);
                    videoIntent.setData(Uri.parse("bilibili://video/"+inputText.substring(2)));
                    startActivity(videoIntent);
                }else if(inputText.startsWith("bangumi")){
                    Intent bangumiIntent = new Intent(SearchActivity.this,BangumiDetailActivity.class);
                    bangumiIntent.setData(Uri.parse("https://m.bilibili.com/bangumi/play/ss"+inputText.substring(7)));
                    startActivity(bangumiIntent);
                }
                return false;
            }
        });
    }

    @OnClick(R.id.search_cancel)
    public void cancel(){
        onBackPressed();
    }
}
