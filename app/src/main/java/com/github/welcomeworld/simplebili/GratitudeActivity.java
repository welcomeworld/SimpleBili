package com.github.welcomeworld.simplebili;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.welcomeworld.simplebili.adapter.GratitudeRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GratitudeActivity extends AppCompatActivity {

    @BindView(R.id.gratitude_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.gratitude_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratitude);
        ButterKnife.bind(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setAdapter(new GratitudeRecyclerViewAdapter());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
