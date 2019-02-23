package com.github.welcomeworld.simplebili.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.github.welcomeworld.simplebili.R;

public class ImageTextView extends AppCompatTextView {

    private boolean drawableCenter=false;
    private Drawable  drawableStart,drawableEnd,drawableTop,drawableBottom;
    private int drawableStartWidth=0,drawableStartHeight=0,drawableTopWidth=0,drawableTopHeight=0,drawableEndWidth=0,drawableEndHeight=0,drawableBottomWidth=0,drawableBottomHeight=0;

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
                case R.styleable.ImageTextView_drawableEndHeight:
                    drawableEndHeight=typedArray.getDimensionPixelSize(itemId,0);
                    break;
                case R.styleable.ImageTextView_drawableEndWidth:
                    drawableEndWidth=typedArray.getDimensionPixelSize(itemId,0);
                    break;
                case R.styleable.ImageTextView_android_drawableEnd:
                    drawableEnd=typedArray.getDrawable(itemId);
                    break;
                case R.styleable.ImageTextView_drawableBottomHeight:
                    drawableBottomHeight=typedArray.getDimensionPixelSize(itemId,0);
                    break;
                case R.styleable.ImageTextView_drawableBottomWidth:
                    drawableBottomWidth=typedArray.getDimensionPixelSize(itemId,0);
                    break;
                case R.styleable.ImageTextView_android_drawableBottom:
                    drawableBottom=typedArray.getDrawable(itemId);
                    break;
                case R.styleable.ImageTextView_drawableCenter:
                    drawableCenter=typedArray.getBoolean(itemId,false);
            }
        }
        if(drawableStart!=null){
            drawableStart.setBounds(0,0,drawableStartWidth,drawableStartHeight);
        }
        if(drawableTop!=null){
            drawableTop.setBounds(0,0,drawableTopWidth,drawableTopHeight);
        }
        if(drawableEnd!=null){
            drawableEnd.setBounds(0,0,drawableEndWidth,drawableEndHeight);
        }
        if(drawableBottom!=null){
            drawableBottom.setBounds(0,0,drawableBottomWidth,drawableBottomHeight);
        }

        setCompoundDrawables(drawableStart,drawableTop,drawableEnd,drawableBottom);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(drawableCenter){
            int drawablePadding = getCompoundDrawablePadding();
            int textWidth = (int) getPaint().measureText(getText().toString().trim());
            int bodyWidth = drawableStartWidth+drawableTopWidth+drawableBottomWidth+drawableEndWidth + drawablePadding + textWidth;
            canvas.save();
            canvas.translate((getWidth() - bodyWidth) / 2, 0);
        }
        super.onDraw(canvas);
    }
}
