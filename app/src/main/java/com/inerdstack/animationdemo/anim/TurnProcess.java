package com.inerdstack.animationdemo.anim;

import android.util.Log;

/**
 * Created by wangjie on 2016/11/10.
 */

public class TurnProcess {

    private static float mSensitiy = 2.0f;
    private float itemTop;
    private float changeLine;

    public static final int STATE_DARK = 0x01;
    public static final int STATE_DARK_2_LIGHT = 0x02;
    public static final int STATE_LIGHT_2_DARK = 0x3;

    /**
     * 获取变化的状态
     * @param itemTop item到顶部的高度差
     * @param turnLine 动画转变线
     * @param itemHeight item的高度
     * @return
     */
    public static int getState(float itemTop, float turnLine, float itemHeight) {
        return getState(itemTop, turnLine, itemHeight, mSensitiy);
    }

    /**
     * 获取变化的状态
     * @param itemTop item到顶部的高度差
     * @param turnLine 动画转变线
     * @param itemHeight item的高度
     * @param sensity
     * @return
     */
    public static int getState(float itemTop, float turnLine, float itemHeight, float sensity) {
        mSensitiy = sensity;
        if (itemTop <= turnLine || itemTop > (turnLine + itemHeight * mSensitiy)) {
            return STATE_DARK;
        } else if (itemTop > turnLine && itemTop < turnLine + itemHeight * mSensitiy / 2) {
            return STATE_DARK_2_LIGHT;
        } else {
            return STATE_LIGHT_2_DARK;
        }
    }

    /**
     * 返回动画完成的进度
     * @param itemTop
     * @param turnLine
     * @param itemHeight
     * @return
     */
    public static int getProcess(float itemTop, float turnLine, float itemHeight) {
        return getProcess(itemTop, turnLine, itemHeight, mSensitiy);
    }

    /**
     * 返回动画完成的进度
     * @param itemTop
     * @param turnLine
     * @param itemHeight
     * @param sensity
     * @return
     */
    public static int getProcess(float itemTop, float turnLine, float itemHeight, float sensity) {
        Log.i("bb", "itemTOp:" + itemTop + "turnLIne:" + turnLine + "; itemHeight: " + itemHeight + "; sensity: " + sensity);
        mSensitiy = sensity;
        if (turnLine < itemTop || turnLine > (itemHeight * mSensitiy + itemTop)) {
            return 0;
        } else {
            float percent = (turnLine - itemTop) / (mSensitiy * itemHeight);
            Log.i("bb", "the percent is " + percent);
            return (int) (percent * 100);
        }
    }
}