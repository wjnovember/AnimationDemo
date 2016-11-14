package com.inerdstack.animationdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.inerdstack.animationdemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnTimer;

    private Button mBtnScroller;

    private Button mBtnSolid;

    private Button mBtnDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnTimer = (Button) findViewById(R.id.btn_timer);
        mBtnScroller = (Button) findViewById(R.id.btn_scroll);
        mBtnSolid = (Button) findViewById(R.id.btn_solid_anim);
        mBtnDynamic = (Button) findViewById(R.id.btn_dynamic_anim);

        mBtnScroller.setOnClickListener(this);
        mBtnTimer.setOnClickListener(this);
        mBtnSolid.setOnClickListener(this);
        mBtnDynamic.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_timer:
                startActivity(new Intent(MainActivity.this, TimerActivity.class));
                break;
            case R.id.btn_scroll:
                startActivity(new Intent(MainActivity.this, ScrollActivity.class));
                break;
            case R.id.btn_solid_anim:
                startActivity(new Intent(MainActivity.this, SolidAnimListActivity.class));
                break;
            case R.id.btn_dynamic_anim:
                startActivity(new Intent(MainActivity.this, DynamicAnimListActivity.class));
                break;
        }
    }
}
