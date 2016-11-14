package com.inerdstack.animationdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inerdstack.animationdemo.R;

/**
 * Created by wangjie on 2016/11/11.
 */

public class SolidAnimListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private static final int LIST_SIZE = 10;

    // 动画转变线
    private float mTurningLine;

    public SolidAnimListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.anim_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return LIST_SIZE;
    }

    /**
     * 获取当前item到recyclerview屏幕可见区域的高度差
     * @param position
     * @param itemHeight
     * @param parent
     */
    private float getItemOffsetY(int position, float itemHeight, RecyclerView parent) {
        if (position == 0 || position >= getItemCount()) return 0;

        // 获取当前item到recyclerview第一个（下标：0）的顶部的距离
        float itemOffsetHeight = itemHeight * position;
        // 获取recyclerview最顶部到可见区域的高度差
        float recyOffsetHeight = parent.getScrollY();
        // 返回当前item到recyclerview可见部分顶部的高度差
        return itemOffsetHeight - recyOffsetHeight;
    }

    private float getItemHeight(View itemView) {

        if (itemView == null) return 0;

        return itemView.getMeasuredHeight();

    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private ViewGroup containerView;

        public ViewHolder(View itemView) {
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
