package com.github.welcomeworld.simplebili;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class SimpleBaseActivity extends AppCompatActivity {
    public void updateSelf(String command){

    }

    public String TAG;

    int[] colors ={R.style.AppTheme,R.style.redTheme,R.style.yellowTheme,R.style.greenTheme,R.style.blueTheme,R.style.violetTheme};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(colors[PreferenceManager.getDefaultSharedPreferences(this).getInt("theme",0)]);
        TAG = this.getComponentName().getShortClassName();
    }
}
