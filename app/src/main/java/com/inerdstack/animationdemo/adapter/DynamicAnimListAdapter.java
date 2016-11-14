package com.inerdstack.animationdemo.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inerdstack.animationdemo.CustomLinearLayoutManager;
import com.inerdstack.animationdemo.R;
import com.inerdstack.animationdemo.anim.CustomAnimation;
import com.inerdstack.animationdemo.anim.TurnProcess;
import com.inerdstack.animationdemo.util.DensityUtil;

/**
 * Created by wangjie on 2016/11/14.
 */

public class DynamicAnimListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private static final int LIST_SIZE = 10;

    // 动画转变线
    private float mTurningLine;
    // Recyclerview视图
    private RecyclerView mParentView;

    public DynamicAnimListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // recyclerview
        mParentView = (RecyclerView) parent;
        // 以recyclerview中等高度水平线作为动画转变的标准
        mTurningLine = 300;
        // 子项
        View view = LayoutInflater.from(mContext).inflate(R.layout.anim_list_item, parent, false);

        // 返回ViewHolder
        return new DynamicAnimListAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DynamicAnimListAdapter.ViewHolder) holder).initListener(mParentView, position);
    }

    @Override
    public int getItemCount() {
        return LIST_SIZE;
    }

    private float getItemHeight(View itemView) {

        return DensityUtil.dip2px(mContext, 160);

    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        // 视图容器
        private ViewGroup containerView;

        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);

            containerView = (ViewGroup) itemView.findViewById(R.id.root_view);
            this.itemView = itemView;
        }

        /**
         * 初始化滑动监听
         */
        @RequiresApi(api = Build.VERSION_CODES.M)
        private void initListener(RecyclerView recyclerView, final int position) {
            // 初始化item高度
            final float itemHeight = DensityUtil.dip2px(mContext, 160);
            final float recyclerviewHeight = recyclerView.getMeasuredHeight();
            final float turningPart = recyclerviewHeight - itemHeight * 1.5f;
            final float totalHeight = getItemCount() * itemHeight - recyclerviewHeight;
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    // 获取recyclerview的布局管理器
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                    ((CustomLinearLayoutManager)layoutManager).setSpeedRatio(0.5);
                    float offsetY = getScrollDistance(recyclerView);
                    float percent = offsetY / totalHeight;
                    mTurningLine = itemHeight + turningPart * percent;
                    Log.i("sss", "the trunningline is " + mTurningLine);
                    float itemTop = getItemOffsetY(offsetY, position, itemHeight);
                    int process = TurnProcess.getProcess(itemTop, mTurningLine, itemHeight);
                    if (position == 0) {
                        Log.i("ddd", "offsetY--" + offsetY + ";totalHeight--" + totalHeight +
                                ";recyclerviewHeight--" + recyclerviewHeight + ";trunningPart:" + turningPart +
                                ";turnline--" + mTurningLine + ";itemheight:" + itemHeight + ";itemTop:" + itemTop +
                                ";process:" + process);
                    }
                    initAnim(containerView, process);

                }
            });
        }
    }

    /**
     * 获取item的顶部到recyclerview可见区域的距离
     *
     * @param offsetY    recyclerview已经滑动的距离
     * @param position
     * @param itemHeight
     * @return
     */
    private float getItemOffsetY(float offsetY, float position, float itemHeight) {
        return position * itemHeight - offsetY;
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

    /**
     * 获取滚动的距离
     */
    private int getScrollDistance(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        View firstVisibleItem = recyclerView.getChildAt(0);
        int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
        int itemHeight = firstVisibleItem.getHeight();
        int firstItemBottom = layoutManager.getDecoratedBottom(firstVisibleItem);
        return (firstItemPosition + 1) * itemHeight - firstItemBottom;
    }
}
