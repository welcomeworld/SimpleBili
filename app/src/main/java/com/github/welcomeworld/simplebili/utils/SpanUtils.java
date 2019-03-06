package com.github.welcomeworld.simplebili.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import com.github.welcomeworld.simplebili.widget.BiliURLSpan;

public class SpanUtils {
    public static void UrlSpanChange(TextView view){
        Spannable spannable = (Spannable) view.getText();
        URLSpan[] spans = spannable.getSpans(0, spannable.length(), URLSpan.class);
        for (int i = 0; i < spans.length; i++) {
            String url = spans[i].getURL();
            int index = spannable.toString().indexOf(url);
            int end = index + url.length();
            if (index == -1) {
                if (url.contains("http://")) {
                    url = url.replace("http://", "");
                } else if (url.contains("https://")) {
                    url = url.replace("https://", "");
                } else if (url.contains("rtsp://")) {
                    url = url.replace("rtsp://", "");
                }
                index = spannable.toString().indexOf(url);
                end = index + url.length();
            }
            if (index != -1) {
                spannable.removeSpan(spans[i]);
                spannable.setSpan(new BiliURLSpan(spans[i].getURL()), index
                        , end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }
    }
}
