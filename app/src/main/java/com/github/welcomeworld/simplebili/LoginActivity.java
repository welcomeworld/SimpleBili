package com.github.welcomeworld.simplebili;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.welcomeworld.simplebili.bean.LoginKeyBean;
import com.github.welcomeworld.simplebili.bean.LoginResultBean;
import com.github.welcomeworld.simplebili.common.BiliLocalStatus;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.net.retrofit.UserNetAPI;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends SimpleBaseActivity{

    @BindView(R.id.login_password_input)
    TextInputEditText passwordInput;
    @BindView(R.id.login_username_input)
    TextInputEditText usernameInput;
    @BindView(R.id.login_toolbar)
    Toolbar toolbar;
    @BindView(R.id.login_login)
    Button loginButton;
    @BindView(R.id.login_register)
    Button registerButton;
    @BindView(R.id.ic_22)
    ImageView imageView22;
    @BindView(R.id.ic_33)
    ImageView imageView33;

    private boolean userNameNull=true;
    private boolean passwordNull=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick(R.id.login_register)
    public void register(){
        Uri uri=Uri.parse("http:passport.bilibili.com/register/quickregister.html#/?appkey=1d8b6e7d45233436&menu=0");
        //Uri uri=Uri.parse("https:passport.bilibili.com/register/quickregister.html#/?appkey=1d8b6e7d45233436&menu=0");
        Intent intent=new Intent(LoginActivity.this,BrowserActivity.class);
        intent.setData(uri);
        startActivity(intent);
    }

    @OnClick(R.id.login_login)
    public void login(){
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
                .baseUrl(BaseUrl.PASSPORTURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder1.build())
                .build();
        retrofit1.create(UserNetAPI.class).getKey().enqueue(new Callback<LoginKeyBean>() {
            @Override
            public void onResponse(Call<LoginKeyBean> call, Response<LoginKeyBean> response) {
                if(response.body()==null||response.body().getData()==null){
                    return;
                }
                Map<String,String> parameters=new HashMap<>();
                parameters.put("password",encypt(response.body().getData().getHash()+passwordInput.getText().toString(),response.body().getData().getKey()));
                //parameters.put("password",passwordInput.getText().toString());
                parameters.put("ts",""+System.currentTimeMillis());
                parameters.put("username",usernameInput.getText().toString());
                OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                        .addInterceptor(new FixedHeaderInterceptor())
                        .addInterceptor(new DynamicHeaderInterceptor(null))
                        .addInterceptor(new FixedParameterInterceptor())
                        .addInterceptor(new DynamicParameterInterceptor(parameters))
                        .addInterceptor(new SortAndSignInterceptor())
                        .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(BaseUrl.PASSPORTURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClientBuilder.build())
                        .build();
                UserNetAPI userNetAPI = retrofit.create(UserNetAPI.class);
                userNetAPI.login().enqueue(new Callback<LoginResultBean>() {
                    @Override
                    public void onResponse(Call<LoginResultBean> call, Response<LoginResultBean> response) {
                        if(response.body() == null){
                            return;
                        }
                        if(response.body().getCode() == 0) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            BiliLocalStatus.setLogin(true);
                            BiliLocalStatus.setAccessKey(response.body().getData().getTokenInfo().getAccessToken());
                            BiliLocalStatus.setMid(response.body().getData().getTokenInfo().getMid());
                            startActivity(new Intent(LoginActivity.this,UserInfoActivity.class));
                            ((MApplication)getApplication()).getDatabase().getDao().setToken(response.body().getData().getTokenInfo());
                            finish();
                            //Log.e("LoginActivity", "accessToken" + response.body().getData().getTokenInfo().getAccessToken() + "refreshToken" + response.body().getData().getTokenInfo().getRefreshToken());
                        } else {
                            Toast.makeText(LoginActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("LoginActivity","fail:"+response.body().getCode());
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResultBean> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<LoginKeyBean> call, Throwable t) {

            }
        });
    }

    public String encypt(String password,String RSA){
        StringBuilder rsaBuilder = new StringBuilder();
        for(String temp : RSA.split("\n")){
            if(!temp.startsWith("-")){
                rsaBuilder.append(temp);
            }
        }
        PublicKey publicKey;
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(rsaBuilder.toString().getBytes(),Base64.DEFAULT));
            publicKey = KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);
        } catch (Exception e) {
            return "";
        }

        //加密密码
        String cipheredPassword;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            cipheredPassword = new String(
                    Base64.encode(
                            cipher.doFinal(password.getBytes()),Base64.DEFAULT
                    )
            );
        } catch (Exception e) {
            return "";
        }
        return cipheredPassword;
    }


    @OnTextChanged(value = R.id.login_username_input,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
    public void textChange(CharSequence charSequence){
        userNameNull = charSequence.toString().trim().equals("");
        if(!userNameNull&&!passwordNull){
            loginButton.setEnabled(true);
        }else{
            loginButton.setEnabled(false);
        }
    }

    @OnTextChanged(value = R.id.login_password_input,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void passwordTextChange(CharSequence charSequence){
        passwordNull = charSequence.toString().trim().equals("");
        if(!userNameNull&&!passwordNull){
            loginButton.setEnabled(true);
        }else{
            loginButton.setEnabled(false);
        }
    }

    @OnFocusChange(R.id.login_username_input)
    public void onUserNameFocus(View view,boolean hasFocus){
        if(hasFocus){
            imageView22.setImageResource(R.mipmap.ic_22);
            imageView33.setImageResource(R.mipmap.ic_33);
        }
    }
    @OnFocusChange(R.id.login_password_input)
    public void onPasswordFocus(View view,boolean hasFocus){
        if(hasFocus){
            imageView22.setImageResource(R.mipmap.ic_22_hide);
            imageView33.setImageResource(R.mipmap.ic_33_hide);
        }
    }
}
