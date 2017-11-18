package com.gzwlw.mvp.base;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gzwlw.mvp.broadcast.NetWorkStateReceiver;
import com.gzwlw.mvp.common.classs.ConcreteWatched;
import com.gzwlw.mvp.common.interfaces.Watcher;
import com.gzwlw.mvp.common.utils.StackManagerUtil;

/**
 * 基类的Activity(模板方法模式)
 */
public abstract class BaseActivity extends AppCompatActivity implements Watcher {

    private BroadcastReceiver receiver;

    /**
     * 定义onCreate()方法为final类型,禁止子类重写该方法
     */
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayouts());

        initView();
        initData();
        initEvent();

        // 将当前Activity推入栈中
        StackManagerUtil.getInstance().pushActivity(this);
    }

    /**
     * 模板方法,初始化布局
     *
     * @return 布局资源ID
     */
    protected abstract int setLayouts();

    /**
     * 模板方法,初始化各种控件
     */
    protected abstract void initView();

    /**
     * 模板方法,加载各种数据
     */
    protected abstract void initData();

    /**
     * 模板方法,初始化各种响应事件
     */
    protected abstract void initEvent();

    @Override
    protected void onResume() {
        super.onResume();

        register();
        if (null != ConcreteWatched.getInstance()) {
            // 添加一个观察者对象
            ConcreteWatched.getInstance().add(this);
        }
    }

    /**
     * 动态注册用于监控当前网络状态的广播
     */
    private void register() {
        receiver = new NetWorkStateReceiver();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (null != ConcreteWatched.getInstance()) {
            // 移除一个观察者对象
            ConcreteWatched.getInstance().remove(this);
        }

        if (null != receiver) {
            // 注销广播
            unregisterReceiver(receiver);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 将当前的Activity推出栈中
        StackManagerUtil.getInstance().removeActivity(this);
    }
}