package com.github.welcomeworld.simplebili.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Binder;
import android.os.RemoteException;
import android.text.style.ImageSpan;

public class BiliImgSpan extends ImageSpan {
    public BiliImgSpan(Context context, Bitmap b) {
        super(context, b);
    }

    public BiliImgSpan(Context context, Bitmap b, int verticalAlignment) {
        super(context, b, verticalAlignment);
    }

    public BiliImgSpan(Drawable d) {
        super(d);
    }

    public BiliImgSpan(Drawable d, int verticalAlignment) {
        super(d, verticalAlignment);
    }

    public BiliImgSpan(Drawable d, String source) {
        super(d, source);
    }

    public BiliImgSpan(Drawable d, String source, int verticalAlignment) {
        super(d, source, verticalAlignment);
    }

    public BiliImgSpan(Context context, Uri uri) {
        super(context, uri);
    }

    public BiliImgSpan(Context context, Uri uri, int verticalAlignment) {
        super(context, uri, verticalAlignment);
    }

    public BiliImgSpan(Context context, int resourceId) {
        super(context, resourceId);
    }

    public BiliImgSpan(Context context, int resourceId, int verticalAlignment) {
        super(context, resourceId, verticalAlignment);
    }
}
