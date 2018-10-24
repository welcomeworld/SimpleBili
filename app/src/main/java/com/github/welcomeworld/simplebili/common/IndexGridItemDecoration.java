package com.github.welcomeworld.simplebili.common;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

public class IndexGridItemDecoration extends RecyclerView.ItemDecoration {

    private int itemSpace;
    private int itemNum;

    public IndexGridItemDecoration(Context context, int itemHalfSpace, int itemNum) {
        this.itemSpace= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,itemHalfSpace,context.getResources().getDisplayMetrics());
        this.itemNum=itemNum;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top=itemSpace;
        outRect.bottom=itemSpace;
        if(parent.getChildLayoutPosition(view)%itemNum!=0){
            outRect.left=itemSpace;
            outRect.right=itemSpace*2;
        }else{
            outRect.left=itemSpace*2;
            outRect.right=itemSpace;
        }
    }
}
