package com.gzwlw.mvp.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Activity堆栈管理器工具
 */
public class StackManagerUtil {

    // 堆栈管理器工具唯一的实例
    private static StackManagerUtil mInstance;

    // Stack中对应的Activity列表
    private Stack<Activity> mActivityStack;

    private StackManagerUtil() {
        mActivityStack = new Stack<>();
    }

    /**
     * 获得堆栈管理器工具唯一的实例
     *
     * @return 堆栈管理器工具唯一的实例
     */
    public static StackManagerUtil getInstance() {
        if (null == mInstance) {
            synchronized (StackManagerUtil.class) {
                if (null == mInstance) {
                    mInstance = new StackManagerUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 将当前的Activity推入栈中
     *
     * @param activity 要推入栈中的Activity
     */
    public void pushActivity(Activity activity) {
        mActivityStack.push(activity);
    }

    /**
     * 将当前的Activity推出栈中
     *
     * @param activity 要推出栈中的Activity
     */
    public void removeActivity(Activity activity) {
        mActivityStack.remove(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity 要结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls 要结束指定类名的Class
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 查找栈中是否存在指定的Activity
     *
     * @param cls 指定的Class
     * @return true:存在指定的Activity false:不存在指定的Activity
     */
    public boolean checkActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * finish指定的Activity之上所有的Activity
     *
     * @param actCls
     * @param isIncludeSelf finish时是否包含指定的Activity
     * @return true:成功 false:失败
     */
    public boolean finishToActivity(Class<? extends Activity> actCls, boolean isIncludeSelf) {
        List<Activity> activityList = new ArrayList<>();
        int size = mActivityStack.size();
        Activity activity;
        for (int i = size - 1; i >= 0; i--) {
            activity = mActivityStack.get(i);
            if (activity.getClass().isAssignableFrom(actCls)) {
                for (Activity a : activityList) {
                    a.finish();
                }
                return true;
            } else if (i == size - 1 && isIncludeSelf) {
                activityList.add(activity);
            } else if (i != size - 1) {
                activityList.add(activity);
            }
        }
        return false;
    }

    /**
     * 结束所有的Activity
     */
    public void finishAllActivity() {
        Activity activity;
        while (!mActivityStack.empty()) {
            activity = mActivityStack.pop();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 退出应用程序
     *
     * @param context 上下文对象
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getApplicationContext().getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
