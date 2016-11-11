package com.inerdstack.animationdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.inerdstack.animationdemo.R;
import com.inerdstack.animationdemo.adapter.SolidAnimListAdapter;

public class SolidAnimListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private SolidAnimListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_anim_list);

        // 初始化recyclerview
        initRecyclerView();
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mAdapter = new SolidAnimListAdapter(this);
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        // 初始化适配器
        initAdapter();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }
}
