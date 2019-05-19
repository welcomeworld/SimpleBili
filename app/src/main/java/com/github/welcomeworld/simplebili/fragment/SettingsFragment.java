package com.github.welcomeworld.simplebili.fragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.SettingsActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this::onSharedPreferenceChanged);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this::onSharedPreferenceChanged);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    @SuppressLint("CheckResult")
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if(preference.getKey().equals(getString(R.string.preference_key_reset_preference))){
            PreferenceManager.getDefaultSharedPreferences(preference.getContext()).edit().clear().apply();
            PreferenceManager.setDefaultValues(preference.getContext(),R.xml.settings,true);
            Toast.makeText(preference.getContext(),R.string.reset_completed,Toast.LENGTH_SHORT).show();
            ((SettingsActivity)getActivity()).updateSelf();
            return true;
        }else if(preference.getKey().equals(getString(R.string.preference_key_clear_cache))){
            Observable.create(new ObservableOnSubscribe<Long>() {

                @Override
                public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                    Glide.get(getActivity()).clearDiskCache();
                    emitter.onNext(1L);
                }
            }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
                    Toast.makeText(preference.getContext(),R.string.clear_completed,Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }else if(preference.getKey().equals(getString(R.string.preference_key_check_update))){
            Toast.makeText(preference.getContext(),R.string.latest_version,Toast.LENGTH_SHORT).show();
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
