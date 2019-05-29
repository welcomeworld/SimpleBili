package com.github.welcomeworld.simplebili;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.bean.UserInfoMineBean;
import com.github.welcomeworld.simplebili.bean.UserSpaceBean;
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
    @BindView(R.id.userinfo_avatar)
    ImageView avatarView;
    @BindView(R.id.userinfo_space_face)
    ImageView faceView;
    @BindView(R.id.userinfo_name)
    TextView nameView;
    @BindView(R.id.userinfo_follow_prefix)
    TextView followView;
    @BindView(R.id.userinfo_fans_prefix)
    TextView fanView;
    @BindView(R.id.userinfo_sign)
    TextView signView;
    @BindView(R.id.userinfo_sex)
    ImageView sexView;
    @BindView(R.id.userinfo_level)
    ImageView levelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        if(!BiliLocalStatus.isLogin()&&getIntent().getIntExtra("mid",-1)==-1){
            return;
        }
        Map<String,String> parameters1=new HashMap<>();
        parameters1.put("ts",""+System.currentTimeMillis());
        parameters1.put("channel","bili");
        parameters1.put("from","0");
        parameters1.put("ps","10");
        parameters1.put("vmid",BiliLocalStatus.getMid()+"");
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
        retrofit1.create(UserNetAPI.class).getSpace().enqueue(new Callback<UserSpaceBean>() {
            @Override
            public void onResponse(Call<UserSpaceBean> call, Response<UserSpaceBean> response) {
                if(response.body()==null||response.body().getCode()!=0){
                    Toast.makeText(UserInfoActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    return;
                }
                UserSpaceBean.DataBean data = response.body().getData();
                nameView.setText(data.getCard().getName());
                Glide.with(faceView).load(data.getImages().getImgUrl()+"@720w_240h_1e_1c.webp").into(faceView);
                Glide.with(avatarView).load(data.getCard().getFace()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new CenterCrop(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(avatarView.getDrawable())).into(avatarView);
                followView.setText(data.getCard().getAttention()+"");
                fanView.setText(data.getCard().getFans()+"");
                signView.setText(data.getCard().getSign());
                levelView.setImageLevel(data.getCard().getLevelInfo().getCurrentLevel());
                switch (data.getCard().getSex()){
                    case "男":
                        sexView.setImageLevel(30);
                        break;
                    case "女":
                        sexView.setImageLevel(40);
                        break;
                        default:sexView.setImageLevel(80);
                }
            }

            @Override
            public void onFailure(Call<UserSpaceBean> call, Throwable t) {
                Toast.makeText(UserInfoActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
