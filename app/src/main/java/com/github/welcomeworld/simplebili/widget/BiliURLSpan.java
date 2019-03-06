package com.github.welcomeworld.simplebili.widget;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;

import com.github.welcomeworld.simplebili.R;

public class BiliURLSpan extends URLSpan {
    public BiliURLSpan(String url) {
        super(url);
    }

    public BiliURLSpan(Parcel src) {
        super(src);
    }

    @Override
    public void onClick(View widget) {
        Uri uri = Uri.parse(getURL());
        Context context = widget.getContext();
        Intent intent1=new Intent();
        Intent intent = new Intent("com.github.welcomeworld.simplebili.action.BROWSER", uri);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.w("URLSpan", "Actvity was not found for intent, " + intent.toString());
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }
}
