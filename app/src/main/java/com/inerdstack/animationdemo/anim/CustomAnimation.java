package com.inerdstack.animationdemo.anim;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.inerdstack.animationdemo.util.DensityUtil;
import com.inerdstack.animationdemo.R;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by wangjie on 2016/11/10.
 * 
 * 单例
 * 1. 蒙版透明度变化：1.0->0.0: 1%~50%; 0.0->1.0: 51%~100%
 * 2. 圆角->直角：1%；直角->圆角：51%
 * 3. 图片变化在26%~75%之间;26%：开始变大；51%：开始变小；
 * 4. 边距变化在1%~25%和76%~100%之间
 */
public class CustomAnimation {

    // 无控件
    private static final int NO_VIEW = -999;
    // 默认圆角半径，单位：px
    private static final float DEFAULT_CORNER_RADIUS = 18;
    // 默认的外边距
    private static final float DEFAULT_MARGIN_VERTICAL = 0;
    // 外边距
    private float mMarginHorizontal;
    // 透明度变化视图
    private int mAlphaViewId = NO_VIEW;
    // 圆角变化视图
    private int mCornersViewId = NO_VIEW;
    // 图片变化视图
    private int mImageViewId = NO_VIEW;
    // 边距变化视图
    private int mMarginViewId = NO_VIEW;
    // 外上边距
    private float mMarginTop = DEFAULT_MARGIN_VERTICAL;
    // 外下边距
    private float mMarginBottom = DEFAULT_MARGIN_VERTICAL;

    // 圆角半径，单位：px
    private float mCornerRadius = DEFAULT_CORNER_RADIUS;

    private ViewGroup mRootView;

    private int mProcess = 0;

    // 屏幕尺寸
    private float mScreenWidth;
    // 图片控件原始尺寸
    private float mImgOrgWidth;

    private Context mContext;

    public CustomAnimation(Context context) {
        this.mContext = context;
        mScreenWidth = DensityUtil.getWindowWidth((Activity) context);
        mMarginHorizontal = DensityUtil.dip2px(context, 16);
        mImgOrgWidth = mScreenWidth - mMarginHorizontal * 2;
    }

    /**
     * 通过进度值控制动画的进度
     * @param process
     */
    public void setAnimByProcess(int process) {

        if (process < 0) {
            process = 0;
        } else if (process > 100) {
            process = 100;
        }

        this.mProcess = process;
        setAnimByProcess(mRootView, process, true, true, true, true);
    }

    /**
     * 设置视图容器
     * @param viewGroup
     */
    public void setView(ViewGroup viewGroup) {
        mRootView = viewGroup;
    }

    /**
     * 通过进度值控制动画的进度
     * @param viewGroup 父容器
     * @param process 动画变化进度
     * @param enableAlpha 使能透明度变化
     * @param enableCorners 使能边距变化
     * @param enableImage 使能图片变化
     * @param enableMargin 使能外边距变化
     */
    private void setAnimByProcess(ViewGroup viewGroup, int process, boolean enableAlpha,
                                        boolean enableCorners, boolean enableImage, boolean enableMargin) {
        if (viewGroup == null) {
            return;
        }

        // 减少动画的有效帧数，关键帧不可丢失
        if (process != 0 && process != 25 && process != 26 && process != 50 && process != 51 &&
                process != 75 && process != 76 && process != 100 && process % 2 == 0) {
            return;
        }

        mProcess = process;

        /**
         * 蒙版透明度设置
         */
        if (enableAlpha && mAlphaViewId != NO_VIEW) {
            View view = viewGroup.findViewById(mAlphaViewId);
            if (process > 0 && process <= 25) {
                float alpha = (25 - process) / 25.0f;
                view.setAlpha(alpha);
            } else if (process > 75 && process <= 100) {
                float alpha = (process - 75) / 25.0f;
                view.setAlpha(alpha);
            }
        }

        /**
         * 圆角直角转化
         */
        if (enableCorners && mCornersViewId != NO_VIEW) {
            RoundedImageView imageView = (RoundedImageView) viewGroup.findViewById(mCornersViewId);
            View view = viewGroup.findViewById(mAlphaViewId);
            if (process > 0 && process < 100) {
                imageView.setCornerRadius(0);
                view.setBackgroundColor(0x99000000);
            } else {
                imageView.setCornerRadius(mCornerRadius);
                view.setBackground(ContextCompat.getDrawable(mContext, R.drawable.mark_bg));
            }
        }

        /**
         *
         * 设置图片大小
         */
        if (enableImage && mImageViewId != NO_VIEW) {
            ImageView imageView = (ImageView) viewGroup.findViewById(mImageViewId);
            float curWidth = 0;

            if (process <= 25) {
                float percent = process / 25.0f;
                float marginHorizontal = mMarginHorizontal * percent;
                curWidth = mImgOrgWidth + 2 * marginHorizontal;

            } else if (process > 25 && process <= 50) {
                float percent = (process - 25) / 25.0f;
                float marginHorizontal = mMarginHorizontal * percent;
                curWidth = mScreenWidth + 2 * marginHorizontal;

            } else if (process > 50 && process <= 75) {
                float percent = (75 - process) / 25.0f;
                float marginHorizontal = mMarginHorizontal * percent;
                curWidth =  mScreenWidth + 2 * marginHorizontal;

            } else {
                float percent = (100 - process) / 25.0f;
                float marginHorizontal = mMarginHorizontal * percent;
                curWidth = mImgOrgWidth + 2 * marginHorizontal;
            }

            float scale = curWidth / mImgOrgWidth;
            imageView.setScaleX(scale);
            imageView.setScaleY(scale);
        }

        /**
         * 设置外边距（横向）
         */
        if (enableMargin && mMarginViewId != NO_VIEW) {
            View view = viewGroup.findViewById(mMarginViewId);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
            if (process > 0 && process <= 25) {
                float percent = (25 - process) / 25.0f;
                float marginHorizontal = mMarginHorizontal * percent;
                lp.setMargins((int)marginHorizontal, (int)mMarginTop, (int)marginHorizontal, (int)mMarginBottom);
                view.setLayoutParams(lp);

            } else if (process > 75 && process <= 100) {
                float percent = (process - 75) / 25.0f;
                float marginHorizontal = mMarginHorizontal * percent;
                lp.setMargins((int)marginHorizontal, (int)mMarginTop, (int)marginHorizontal, (int)mMarginBottom);
                view.setLayoutParams(lp);

            }
        }
    }

    /**
     * 返回动画进度
     * @return
     */
    public int getProcess() {
        return mProcess;
    }

    /**
     * 设置圆角半径
     * @param cornerRadius
     */
    public void setCornerRadius(float cornerRadius) {
        Log.i("animm", "setCornerRadius");
        mCornerRadius = cornerRadius;
    }

    /**
     * 设置横向外边距
     * @param marginHorizontal
     */
    public void setMarginHorizontal(float marginHorizontal) {
        Log.i("animm", "setMarginHorizontal");
        mMarginHorizontal = marginHorizontal;
    }

    /**
     * 设置顶外边距
     * @param marginTop
     */
    public void setMarginTop(float marginTop) {
        Log.i("animm", "setMarginTop");
        mMarginTop = marginTop;
    }

    /**
     * 设置底部外边距
     * @param marginBottom
     */
    public void setMarginBottom(float marginBottom) {
        Log.i("animm", "setMarginBottom");
        mMarginBottom = marginBottom;
    }

    public void setDefaultMarginVertical(float marginVertical) {
        Log.i("animm", "setDefaultMarginVertical");
        mMarginTop = marginVertical;
        mMarginBottom = marginVertical;
    }

    /**
     * 设置透明度变化控件的ID
     * @param resId
     */
    public void setAlphaViewId(int resId) {
        Log.i("animm", "setAlphaViewId");
        mAlphaViewId = resId;
    }

    /**
     * 设置圆角变化控件的ID
     * @param resId
     */
    public void setCornersViewId(int resId) {
        Log.i("animm", "setCornersViewId");
        mCornersViewId = resId;
    }

    /**
     * 设置图片变化控件的ID
     * @param resId
     */
    public void setImageViewId(int resId) {
        Log.i("animm", "setImageViewId");
        mImageViewId = resId;
    }

    /**
     * 设置外边距变化控件的ID
     * @param resId
     */
    public void setMarginViewId(int resId) {
        Log.i("animm", "setMarginViewId");
        mMarginViewId = resId;
    }
}
