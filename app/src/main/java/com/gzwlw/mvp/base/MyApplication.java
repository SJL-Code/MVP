package com.gzwlw.mvp.base;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    /**
     * 全局的上下文
     */
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
