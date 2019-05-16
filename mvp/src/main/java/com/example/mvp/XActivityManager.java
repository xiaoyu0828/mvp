package com.example.mvp;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * @author yangxu
 * @date 2019/5/16
 */
public class XActivityManager {

    private static Stack<Activity> activityStack;

    private static class Holder {
        private static final XActivityManager INSTANCE = new XActivityManager();
    }
    private XActivityManager() {
    }
    public static final XActivityManager get() {
        return Holder.INSTANCE;
    }

    //添加Activity
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    //获取栈顶Activity（堆栈中最后一个压入的）
    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    //指定Activity是否存在栈中
    public boolean isActivityExist(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    //销毁栈顶Activity
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    //销毁指定Activity
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    //将指定Activity从栈中移除
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    //销毁指定Activity
    public void finishActivity(Class<?> cls) {

        Stack<Activity> tempList = new Stack<>();

        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                if (activity != null){
                    tempList.add(activity);
                }

            }
        }

        activityStack.remove(tempList);
        for (Activity activity : tempList) {
            if (activity != null){
                activity.finish();
            }
        }

    }

    //销毁栈中所有Activity
    public void finishAllActivity() {
        if (activityStack == null) return;
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}
