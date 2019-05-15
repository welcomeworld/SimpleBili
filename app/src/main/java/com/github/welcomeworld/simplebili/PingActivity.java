package com.github.welcomeworld.simplebili;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.welcomeworld.simplebili.adapter.PingRecyclerViewAdapter;
import com.github.welcomeworld.simplebili.bean.PingIpBean;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.SettingsNetAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PingActivity extends SimpleBaseActivity {


    @BindView(R.id.ping_toolbar)
    Toolbar toolbar;

    @BindView(R.id.ping_local_ip)
    TextView ipTextView;

    @BindView(R.id.ping_start)
    TextView startTextView;

    @BindView(R.id.ping_copy_result)
    TextView copyTextView;

    @BindView(R.id.ping_progress)
    ProgressBar progressBar;

    @BindView(R.id.ping_recyclerView)
    RecyclerView recyclerView;



    String[] hosts = {"www.bilibili.com","interface.bilibili.com","comment.bilibili.com","api.bilibili.com",
            "app.bilibili.com","passport.bilibili.com","account.bilibili.com","bangumi.bilibili.com",
            "live.bilibili.com","message.bilibili.com","elec.bilibili.com","pay.bilibili.com",
            "secure.bilibili.com","s.search.bilibili.com","chat.bilibili.com","api.biligame.com",
            "apigame.bilibili.com","www.im9.com","acg.tv","static.hdslb.com",
            "i0.hdslb.com","i1.hdslb.com","i2.hdslb.com"
    };
    List<String> data = new ArrayList<>();

    StringBuilder copyString = new StringBuilder();

    private int currentIndex = 0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        copyTextView.setEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(new PingRecyclerViewAdapter(data));
        recyclerView.setLayoutManager(linearLayoutManager);
        Map<String,String> parameters=new HashMap<>();
        parameters.put("ts",""+System.currentTimeMillis());
        OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                .addInterceptor(new FixedHeaderInterceptor())
                .addInterceptor(new DynamicHeaderInterceptor(null))
                .addInterceptor(new FixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters))
                .addInterceptor(new SortAndSignInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.APPURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
        SettingsNetAPI settingsNetAPI = retrofit.create(SettingsNetAPI.class);
        settingsNetAPI.getIpInfo().enqueue(new Callback<PingIpBean>() {
            @Override
            public void onResponse(Call<PingIpBean> call, Response<PingIpBean> response) {
                if(response.body()!=null){
                    ipTextView.setText(getString(R.string.ip_show,response.body().getData().getAddr(),response.body().getData().getCountry(),response.body().getData().getProvince(),response.body().getData().getIsp()));
                }
            }

            @Override
            public void onFailure(Call<PingIpBean> call, Throwable t) {
                ipTextView.setText(R.string.ip_error);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.ping_start)
    public void startCheck(){
        progressBar.setVisibility(View.VISIBLE);
        startTextView.setEnabled(false);
        startTextView.setTextColor(getResources().getColor(R.color.gray_background));
        Disposable disposable = Observable
                .create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                for(String host:hosts){
                    copyString.append("{");
                    copyString.append(host);
                    copyString.append(":");
                    try {
                        BufferedReader mReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("ping -c 1 -w 5 "+host).getInputStream()));
                        StringBuilder mRespBuff = new StringBuilder();
                        char[] buff = new char[1024];
                        int ch = 0;
                        while ((ch = mReader.read(buff)) != -1) {
                            mRespBuff.append(buff, 0, ch);
                        }
                        String tempString = mRespBuff.toString();
                        if(tempString.contains("time=")&&tempString.contains("ms")){
                            tempString = tempString.substring(tempString.indexOf("time=")+5);
                            tempString = tempString.substring(0,tempString.indexOf("ms")+2);
                        }else if(tempString.contains("packet loss")){
                            tempString = tempString.substring(0,tempString.indexOf("packet loss")+11);
                            tempString = tempString.substring(tempString.lastIndexOf(',')==-1?0:tempString.lastIndexOf(','));
                        }
                        if(tempString.length()<3){
                            emitter.onNext("出错返回："+mRespBuff.toString());
                            copyString.append("出错返回：");
                            copyString.append(mRespBuff.toString());
                            continue;
                        }
                        emitter.onNext(tempString);
                        copyString.append(tempString);
                        mReader.close();
                    }catch (Exception e){
                        emitter.onNext("连接失败，ping出错");
                        copyString.append("连接失败，ping出错");
                    }
                    copyString.append("}");
                    copyString.append("\n");
                }
                emitter.onNext("Success");
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s){
                        if(s.equals("Success")){
                            progressBar.setVisibility(View.GONE);
                            copyTextView.setEnabled(true);
                            copyTextView.setTextColor(getResources().getColor(R.color.pink));
                        }else{
                            data.add(s);
                            recyclerView.getAdapter().notifyItemInserted(currentIndex++);
                        }
                    }
                });
    }

    @OnClick(R.id.ping_copy_result)
    public void copyResult(){
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if(clipboardManager!=null){
            clipboardManager.setPrimaryClip(ClipData.newPlainText("pingText",ipTextView.getText()+"\n"+copyString.toString()));
            Toast.makeText(this,"已复制",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
