package com.github.welcomeworld.simplebili.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.SettingsActivity;

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

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if(preference.getKey().equals(getString(R.string.preference_key_reset_preference))){
            PreferenceManager.getDefaultSharedPreferences(preference.getContext()).edit().clear().apply();
            PreferenceManager.setDefaultValues(preference.getContext(),R.xml.settings,true);
            Toast.makeText(preference.getContext(),R.string.reset_completed,Toast.LENGTH_SHORT).show();
            ((SettingsActivity)getActivity()).updateSelf();
            return true;
        }else if(preference.getKey().equals(getString(R.string.preference_key_clear_cache))){
            Toast.makeText(preference.getContext(),R.string.clear_completed,Toast.LENGTH_SHORT).show();
            return true;
        }else if(preference.getKey().equals(getString(R.string.preference_key_check_update))){
            Toast.makeText(preference.getContext(),R.string.latest_version,Toast.LENGTH_SHORT).show();
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
