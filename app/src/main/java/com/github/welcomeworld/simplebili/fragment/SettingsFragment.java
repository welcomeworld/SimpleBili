package com.github.welcomeworld.simplebili.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.welcomeworld.simplebili.BuildConfig;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.SettingsActivity;
import com.github.welcomeworld.simplebili.bean.UpdateBean;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.UpdateNetAPI;
import com.github.welcomeworld.simplebili.utils.DownloadManager;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(BaseUrl.GITHUBRAWURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofit.create(UpdateNetAPI.class).checkUpdate().enqueue(new Callback<UpdateBean>() {
                @Override
                public void onResponse(Call<UpdateBean> call, Response<UpdateBean> response) {
                    if(response.body() == null){
                        return;
                    }
                    if(response.body().getVersionCode() == BuildConfig.VERSION_CODE){
                        Toast.makeText(preference.getContext(),R.string.latest_version,Toast.LENGTH_SHORT).show();
                    }else {

                        AlertDialog alertDialog = new AlertDialog.Builder(preference.getContext())
                                .setCancelable(true)
                                .setView(LayoutInflater.from(getActivity()).inflate(R.layout.dailog_update,null,false))
                                .create();
                        alertDialog.show();
                        alertDialog.findViewById(R.id.dialog_update_cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.findViewById(R.id.dialog_update_ok).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                                DownloadManager.getInstance().downloadUpdate(response.body(), new Observer<Long>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Long aLong) {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/SimpleBili/update",response.body().getVersionName()+".apk");
                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_VIEW);
                                        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        getActivity().startActivity(intent);
                                    }
                                });
                                Toast.makeText(preference.getContext(),"开始下载更新",Toast.LENGTH_SHORT).show();
                            }
                        });
                        ((TextView)alertDialog.findViewById(R.id.dialog_update_content)).setText(response.body().getDescription());
                    }
                }

                @Override
                public void onFailure(Call<UpdateBean> call, Throwable t) {
                    Toast.makeText(preference.getContext(),R.string.can_not_arrive,Toast.LENGTH_SHORT).show();
                }
            });
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
