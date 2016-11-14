package com.inerdstack.animationdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inerdstack.animationdemo.R;
import com.inerdstack.animationdemo.anim.CustomAnimation;
import com.inerdstack.animationdemo.anim.TurnProcess;
import com.inerdstack.animationdemo.util.DensityUtil;

/**
 * Created by wangjie on 2016/11/11.
 */

public class SolidAnimListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private static final int LIST_SIZE = 10;

    // 动画转变线
    private float mTurningLine;
    // Recyclerview视图
    private RecyclerView mParentView;

    public SolidAnimListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // recyclerview
        mParentView = (RecyclerView) parent;
        // recyclerview高度
        float height = mParentView.getMeasuredHeight();
        // 以recyclerview中等高度水平线作为动画转变的标准
        mTurningLine = height / 2;
        // 子项
        View view = LayoutInflater.from(mContext).inflate(R.layout.anim_list_item, parent, false);
        // 返回ViewHolder
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).initListener(mParentView, position);
    }

    @Override
    public int getItemCount() {
        return LIST_SIZE;
    }

    /**
     * 获取当前item到recyclerview屏幕可见区域的高度差
     *
     * @param position
     * @param itemHeight
     * @param scrollY
     */
    private float getItemOffsetY(int position, float itemHeight, float scrollY) {
        Log.i("anmm", "offset--position:" + position + ";itemHeight:" + itemHeight);
        if (position < 0 || position >= getItemCount()) return 0;

        // 获取当前item到recyclerview第一个（下标：0）的顶部的距离
        float itemOffsetHeight = itemHeight * position;
        // 返回当前item到recyclerview可见部分顶部的高度差
        return itemOffsetHeight - scrollY;
    }

    private float getItemHeight(View itemView) {

        return DensityUtil.dip2px(mContext, 160);

    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        // 视图容器
        private ViewGroup containerView;
        // 子项高度
        private float itemHeight;

        public ViewHolder(View itemView) {
            super(itemView);

            containerView = (ViewGroup) itemView.findViewById(R.id.root_view);
            itemHeight = getItemHeight(itemView);
        }

        /**
         * 初始化滑动监听
         */
        private void initListener(RecyclerView recyclerView, final int position) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    float scrollY = recyclerView.getScrollY();
//                    float itemTop = getItemOffsetY(position, itemHeight, scrollY);
//                    int process = TurnProcess.getProcess(itemTop, mTurningLine, itemHeight);
//                    initAnim(containerView, process);
//
//                    if (position == 6) {
//                        Log.i("anmm", "position -- " + position + "; process " + process);
//                        Log.i("anmm", "posttion -- itemHeight:" + itemHeight + "; scrollY:" + scrollY + ";itemTop:" + itemTop);
//                    }
                    float scrollY = recyclerView.getScrollY();
                    Log.i("hhh", "scrollY is " + scrollY);
                }
            });
        }
    }



    private void initAnim(ViewGroup viewGroup, int process) {
        CustomAnimation animation = new CustomAnimation(mContext);
        animation.setView(viewGroup);
        animation.setAlphaViewId(R.id.mark);
        animation.setCornersViewId(R.id.img_pic);
        animation.setImageViewId(R.id.img_pic);
        animation.setMarginViewId(R.id.container);
        animation.setMarginHorizontal(DensityUtil.dip2px(mContext, 16));
        animation.setCornerRadius(DensityUtil.dip2px(mContext, 6));

        animation.setAnimByProcess(process);
    }
}
