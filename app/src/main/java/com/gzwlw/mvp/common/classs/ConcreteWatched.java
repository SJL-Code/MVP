package com.gzwlw.mvp.common.classs;

import com.gzwlw.mvp.common.interfaces.Watched;
import com.gzwlw.mvp.common.interfaces.Watcher;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者实现类
 */
public class ConcreteWatched implements Watched {

    private static ConcreteWatched concreteWatched;

    /**
     * 定义一个List来封装被观察者Watcher
     */
    private List<Watcher> list;

    private ConcreteWatched() {
        list = new ArrayList<>();
    }

    /**
     * 单例
     */
    public static ConcreteWatched getInstance() {
        if (null == concreteWatched) {
            synchronized (ConcreteWatched.class) {
                if (null == concreteWatched) {
                    concreteWatched = new ConcreteWatched();
                }
            }
        }
        return concreteWatched;
    }

    /**
     * 添加观察者
     *
     * @param watcher 观察者对象
     */
    @Override
    public void add(Watcher watcher) {
        list.add(watcher);
    }

    /**
     * 移除观察者
     *
     * @param watcher 观察者对象
     */
    @Override
    public void remove(Watcher watcher) {
        list.remove(watcher);
    }

    /**
     * 通知观察者对象数据发生了改变
     *
     * @param object 改变的数据源
     */
    @Override
    public void notifyWatcher(Object object) {
        for (Watcher watcher : list) {
            watcher.netWorkStateNotify(object);
        }
    }
}
