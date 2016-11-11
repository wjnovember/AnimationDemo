package com.inerdstack.animationdemo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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

    // 默认外边距，单位：px
    private static final float DEFAULT_MARGIN_HORIZONTAL = 45f;
    // 无控件
    private static final int NO_VIEW = -999;
    // 默认圆角半径，单位：px
    private static final float DEFAULT_CORNER_RADIUS = 18;
    // 默认的外边距
    private static final float DEFAULT_MARGIN_VERTICAL = 0;
    // 外边距
    private float mMarginHorizontal = DEFAULT_MARGIN_HORIZONTAL;
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

    
    private static CustomAnimation mInsteance;
    
//    private CustomAnimation() {
//        
//    }
//    
//    public static CustomAnimation getInstance() {
//        if (mInsteance == null) {
//            synchronized (CustomAnimation.class) {
//                if (mInsteance == null) {
//                    mInsteance = new CustomAnimation();
//                }
//            }
//        }
//        return mInsteance;
//    }

    /**
     * 通过进度值控制动画的进度
     * @param viewGroup
     * @param process
     */
    public void setAnimByProcess(ViewGroup viewGroup, int process) {
        setAnimByProcess(viewGroup, process, true, true, true, true);
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
    public void setAnimByProcess(ViewGroup viewGroup, int process, boolean enableAlpha,
                                        boolean enableCorners, boolean enableImage, boolean enableMargin) {
        if (viewGroup == null) {
            return;
        }

        /**
         * 蒙版透明度设置
         */
        if (enableAlpha && mAlphaViewId != NO_VIEW) {
            View view = viewGroup.findViewById(mAlphaViewId);
            if (process <= 50) {
                float alpha = (50 - process) / 50.0f;
                view.setAlpha(alpha);
            } else {
                float alpha = (process - 50) / 50.0f;
                view.setAlpha(alpha);
            }
        }

        /**
         * 圆角直角转化
         */
        if (enableCorners && mCornersViewId != NO_VIEW) {
            RoundedImageView imageView = (RoundedImageView) viewGroup.findViewById(mCornersViewId);
            if (process > 0 && process < 100) {
                imageView.setCornerRadius(0);
            } else {
                imageView.setCornerRadius(mCornerRadius);
            }
        }

        /**
         * 设置图片大小
         */
        if (enableImage && mImageViewId != NO_VIEW) {
            ImageView imageView = (ImageView) viewGroup.findViewById(mImageViewId);
            if (process > 25 && process <= 50) {
                // 计算缩放比
                float scale = 1.0f + (process - 25) / 25.0f * 0.2f;
                // 图片放大
                imageView.setScaleX(scale);
                imageView.setScaleY(scale);

            } else if (process > 50 && process <= 75) {
                // 计算缩放比
                float scale = 1.0f + (75 - process) / 25.0f * 0.2f;
                // 图片缩小
                imageView.setScaleX(scale);
                imageView.setScaleY(scale);

            }
        }

        /**
         * 设置外边距（横向）
         */
        if (enableMargin && mMarginViewId != NO_VIEW) {
            View view = viewGroup.findViewById(mMarginViewId);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();

        }
    }

    /**
     * 设置圆角半径
     * @param cornerRadius
     */
    public void setCornerRadius(float cornerRadius) {
        mCornerRadius = cornerRadius;
    }

    /**
     * 设置透明度变化控件的ID
     * @param resId
     */
    public void setAlphaViewId(int resId) {
        mAlphaViewId = resId;
    }

    /**
     * 设置圆角变化控件的ID
     * @param resId
     */
    public void setCornersViewId(int resId) {
        mCornersViewId = resId;
    }

    /**
     * 设置图片变化控件的ID
     * @param resId
     */
    public void setImageViewId(int resId) {
        mImageViewId = resId;
    }

    /**
     * 设置外边距变化控件的ID
     * @param resId
     */
    public void setMarginViewId(int resId) {
        mMarginViewId = resId;
    }
}
