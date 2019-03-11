package com.github.welcomeworld.simplebili.widget;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

public class BiliSimpleClickSpan extends ClickableSpan {

    public String url;

    public BiliSimpleClickSpan(String uri){
        this.url=uri;
    }

    @Override
    public void onClick(View widget) {
        Uri uri = Uri.parse(url);
        Context context = widget.getContext();
        Intent intent=new Intent();
        intent.setPackage(widget.getContext().getPackageName());
        intent.setData(uri);
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
