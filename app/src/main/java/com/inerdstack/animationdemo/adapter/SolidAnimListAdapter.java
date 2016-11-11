package com.inerdstack.animationdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;

import com.inerdstack.animationdemo.R;

/**
 * Created by wangjie on 2016/11/11.
 */

public class SolidAnimListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private static final int LIST_SIZE = 10;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private ViewGroup containerView;

        public ViewHolder(View itemView, int process) {
            super(itemView);

            containerView = (ViewGroup) itemView.findViewById(R.id.root_view);
            // 初始化动画
            initAnim();
        }

        /**
         * 初始化动画
         */
        private void initAnim() {

        }
    }
}
