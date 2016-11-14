package com.inerdstack.animationdemo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by wangjie on 2016/11/14.
 */

public class CustomLinearLayoutManager extends LinearLayoutManager {

    private double speedRatio;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int a = super.scrollVerticallyBy(dy, recycler, state);
        if (a == speedRatio * dy) {
            return dy;
        }
        return a;
    }

    public void setSpeedRatio(double speedRatio) {
        this.speedRatio = speedRatio;
    }
}
