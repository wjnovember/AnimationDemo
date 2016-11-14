package com.inerdstack.animationdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.inerdstack.animationdemo.R;
import com.inerdstack.animationdemo.adapter.DynamicAnimListAdapter;

public class DynamicAnimListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private DynamicAnimListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_anim_list);


        // 初始化recyclerview
        initRecyclerView();
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mAdapter = new DynamicAnimListAdapter(this);
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
