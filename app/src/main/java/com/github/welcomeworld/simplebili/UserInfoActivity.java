package com.github.welcomeworld.simplebili;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.welcomeworld.simplebili.bean.UserInfoMineBean;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.UserNetAPI;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserInfoActivity extends SimpleBaseActivity {
    @BindView(R.id.userInfo)
    TextView infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        if(!BiliLocalStatus.isLogin()){
            infoView.setText("还没有登录");
            return;
        }
        Map<String,String> parameters1=new HashMap<>();
        parameters1.put("ts",""+System.currentTimeMillis());
        OkHttpClient.Builder okHttpClientBuilder1=new OkHttpClient.Builder()
                .addInterceptor(new FixedHeaderInterceptor())
                .addInterceptor(new DynamicHeaderInterceptor(null))
                .addInterceptor(new FixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters1))
                .addInterceptor(new SortAndSignInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit1=new Retrofit.Builder()
                .baseUrl(BaseUrl.APPURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder1.build())
                .build();
        retrofit1.create(UserNetAPI.class).getMine().enqueue(new Callback<UserInfoMineBean>() {
            @Override
            public void onResponse(Call<UserInfoMineBean> call, Response<UserInfoMineBean> response) {
                if(response.body()==null||response.body().getCode()!=0){
                    infoView.setText("获取信息失败");
                    return;
                }
                infoView.setText(response.body().getData().getName());
            }

            @Override
            public void onFailure(Call<UserInfoMineBean> call, Throwable t) {
                infoView.setText("获取信息失败");
            }
        });
    }
}
