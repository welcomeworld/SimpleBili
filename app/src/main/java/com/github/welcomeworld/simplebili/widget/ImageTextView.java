package com.github.welcomeworld.simplebili.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.github.welcomeworld.simplebili.R;

public class ImageTextView extends AppCompatTextView {

    private Drawable  drawableStart,drawableEnd,drawableTop,drawableBottom;
    private int drawableStartWidth=0,drawableStartHeight=0,drawableTopWidth=0,drawableTopHeight=0;

    public ImageTextView(Context context) {
        super(context);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.ImageTextView);
        for(int i=0;i<typedArray.getIndexCount();i++){
            int itemId=typedArray.getIndex(i);
            switch (itemId){
                case R.styleable.ImageTextView_drawableStartHeight:
                    drawableStartHeight=typedArray.getDimensionPixelSize(itemId,0);
                    break;
                case R.styleable.ImageTextView_drawableStartWidth:
                    drawableStartWidth=typedArray.getDimensionPixelSize(itemId,0);
                    break;
                case R.styleable.ImageTextView_android_drawableStart:
                    drawableStart=typedArray.getDrawable(itemId);
                    break;
                case R.styleable.ImageTextView_drawableTopHeight:
                    drawableTopHeight=typedArray.getDimensionPixelSize(itemId,0);
                    break;
                case R.styleable.ImageTextView_drawableTopWidth:
                    drawableTopWidth=typedArray.getDimensionPixelSize(itemId,0);
                    break;
                case R.styleable.ImageTextView_android_drawableTop:
                    drawableTop=typedArray.getDrawable(itemId);
                    break;
            }
        }
        if(drawableStart!=null){
            drawableStart.setBounds(0,0,drawableStartWidth,drawableStartHeight);
        }
        if(drawableTop!=null){
            drawableTop.setBounds(0,0,drawableTopWidth,drawableTopHeight);
        }
        setCompoundDrawables(drawableStart,drawableTop,drawableEnd,drawableBottom);
        typedArray.recycle();
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("ImageTextView","go in here");
    }
}
