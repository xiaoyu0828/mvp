package com.example.mvp;


import android.util.Log;

/**
 * Created by yangxu on 2018/9/20
 * 屏蔽快速的无效点击
 */
public class ClickUtils {

    private static long lastClickTime=0;
    private final static int SPACE_TIME = 200;
    private static final String TAG = "ClickUtils";

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isFastClick;

        if (currentTime - lastClickTime > SPACE_TIME) {
            isFastClick = false;
        } else {
            isFastClick = true;
        }
        Log.i(TAG,"---doubleClick-"+isFastClick+"----space-----"+(currentTime - lastClickTime));
        lastClickTime = currentTime;
        return isFastClick;
    }
    public synchronized static boolean isDoubleClick(long threshold ) {
        long currentTime = System.currentTimeMillis();
        boolean isFastClick;

        if (currentTime - lastClickTime > threshold) {
            isFastClick = false;
        } else {
            isFastClick = true;
        }
        Log.i(TAG,"---doubleClick-"+isFastClick+"----space-----"+(currentTime - lastClickTime));
        lastClickTime = currentTime;
        return isFastClick;
    }
}
