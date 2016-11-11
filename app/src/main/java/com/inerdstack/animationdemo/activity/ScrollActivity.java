package com.inerdstack.animationdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.inerdstack.animationdemo.R;
import com.inerdstack.animationdemo.anim.CustomAnimation;
import com.inerdstack.animationdemo.util.DensityUtil;

public class ScrollActivity extends AppCompatActivity {

    private int process = 0;

    private CustomAnimation animation;

    private View mRootView;

    final int[] lastY = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mRootView = findViewById(R.id.root_view);

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
        animation = new CustomAnimation(this);
        animation.setView(viewGroup);
        animation.setAlphaViewId(R.id.mark);
        animation.setCornersViewId(R.id.img_pic);
        animation.setImageViewId(R.id.img_pic);
        animation.setMarginViewId(R.id.container);
        animation.setMarginHorizontal(DensityUtil.dip2px(this, 16));
        animation.setCornerRadius(DensityUtil.dip2px(this, 6));

        mRootView.setClickable(true);
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastY[0] = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int curY = (int) event.getY();
                        // 滑动1/2屏幕的长度可以完成一个动画周期
                        int height = DensityUtil.getWindowHeight(ScrollActivity.this) / 2;
                        int delta = curY - lastY[0];
                        Log.i("scc", "delta " + delta);
                        Log.i("scc", "screenHeight " + height);
                        int process = (int) (((delta * 1.0f) / height) * 100);
                        Log.i("scc", "process " + process);
                        // 在原来的进度上进行动画操作
                        process += animation.getProcess();
                        if (process < 0) {
                            process += 100;
                        } else if (process > 100) {
                            process -= 100;
                        }
                        animation.setAnimByProcess(process);
                        lastY[0] = curY;
                        break;
                }
                return false;
            }
        });

    }
}
