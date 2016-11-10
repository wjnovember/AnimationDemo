package com.inerdstack.animationdemo;

/**
 * Created by wangjie on 2016/11/10.
 */

public class TurnProcess {

    private float itemTop;
    private float changeLine;

    public static final int STATE_DARK = 0x01;
    public static final int STATE_DARK_2_LIGHT = 0x02;
    public static final int STATE_LIGHT_2_DARK = 0x3;

    /**
     * 获取变化的状态
     * @param itemTop
     * @param turnLine
     * @param itemHeight
     * @return
     */
    public static int getState(float itemTop, float turnLine, float itemHeight) {
        if (itemTop <= turnLine || itemTop > (turnLine + 2 * itemHeight)) {
            return STATE_DARK;
        } else if (itemTop > turnLine && itemTop < turnLine + itemHeight) {
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
        if (itemTop < turnLine || itemTop > (turnLine + 2 * itemHeight)) {
            return 0;
        } else {
            float percent = (itemTop - turnLine) / (2 * itemHeight);
            return (int) (percent * 100);
        }
    }
}
