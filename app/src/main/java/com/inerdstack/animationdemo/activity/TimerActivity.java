package com.inerdstack.animationdemo.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.inerdstack.animationdemo.anim.CustomAnimation;
import com.inerdstack.animationdemo.util.DensityUtil;
import com.inerdstack.animationdemo.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {

    private int process = 0;

    private CustomAnimation animation;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    animation.setAnimByProcess(process);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
        animation = new CustomAnimation(this);
        animation.setView(viewGroup);
        animation.setAlphaViewId(R.id.mark);
        animation.setCornersViewId(R.id.img_pic);
        animation.setImageViewId(R.id.img_pic);
        animation.setMarginViewId(R.id.container);
        animation.setMarginHorizontal(DensityUtil.dip2px(this, 16));
        animation.setCornerRadius(DensityUtil.dip2px(this, 6));

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (process == 100) {
                    process = 0;
                }
                process++;
                mHandler.sendEmptyMessage(0);
            }
        };

        Timer timer = new Timer();

        timer.schedule(task, 0, 50);
    }
}
