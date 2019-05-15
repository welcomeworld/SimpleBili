package com.github.welcomeworld.simplebili;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class SimpleBaseActivity extends AppCompatActivity {
    public void updateSelf(String command){

    }

    public String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getComponentName().getShortClassName();
    }
}
