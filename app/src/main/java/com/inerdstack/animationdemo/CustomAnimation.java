package com.inerdstack.animationdemo;

import android.view.ViewGroup;

/**
 * Created by wangjie on 2016/11/10.
 * 
 * 单例
 * 1. 蒙版透明度变化：1.0->0.0->1.0
 * 2. 圆角->直角：1%；直角->圆角：51%
 * 3. 图片变化在25%~75%之间;25%：开始变大；51%：开始变小；
 * 4. 边距变化在0%~24%和76%~100%之间
 */
public class CustomAnimation {

    // 默认外边距
    private static final float DEFAULT_MARGIN = 45;
    // 无控件
    private static final int NO_VIEW = -999;
    // 外边距
    private float mMargin = DEFAULT_MARGIN;
    // 透明度变化视图
    private int mAlphaViewId = NO_VIEW;
    // 圆角变化视图
    private int mCornersViewId = NO_VIEW;
    // 图片变化视图
    private int mImageViewId = NO_VIEW;
    // 边距变化视图
    private int mMarginViewId = NO_VIEW;
    
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
        
        if (enableAlpha && mAlphaViewId != NO_VIEW) {
            
        }
        
        if (enableCorners && mCornersViewId != NO_VIEW) {
            
        }
        
        if (enableImage && mImageViewId != NO_VIEW) {
            
        }
        
        if (enableMargin && mMarginViewId != NO_VIEW) {
            
        }
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
