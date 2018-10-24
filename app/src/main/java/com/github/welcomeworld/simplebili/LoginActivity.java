package com.github.welcomeworld.simplebili;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class LoginActivity extends AppCompatActivity {

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
