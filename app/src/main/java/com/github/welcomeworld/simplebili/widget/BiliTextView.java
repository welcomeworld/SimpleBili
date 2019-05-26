package com.github.welcomeworld.simplebili.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BiliTextView extends android.support.v7.widget.AppCompatTextView {
    public BiliTextView(Context context) {
        super(context);
    }

    public BiliTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BiliTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        String finalString = text.toString();
        Pattern pattern = Pattern.compile("#\\{[^}]*\\}");
        Matcher matcher =  pattern.matcher(finalString);
        if(matcher.find()){
            String matchString = matcher.group();
            Log.e("fix",matchString);
            finalString = finalString.replace(matchString,matchString.substring(2,matchString.length()-1));
        }else {
            Log.e("fix_bug","not match");
        }
        Matcher matcher1 = Pattern.compile("\\{[^}]*\\}").matcher(finalString);
        if(matcher1.find()){
            finalString = matcher1.replaceAll("");
        }
        super.setText(finalString, type);
    }
}
