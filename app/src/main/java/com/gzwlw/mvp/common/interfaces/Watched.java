package com.gzwlw.mvp.common.interfaces;

/**
 * 被观察者接口
 */
public interface Watched {

    /**
     * 定义一个用来增加观察者的方法
     *
     * @param watcher 观察者对象
     */
    void add(Watcher watcher);

    /**
     * 定义一个用来删除观察者权利的方法
     *
     * @param watcher 观察者对象
     */
    void remove(Watcher watcher);

    /**
     * 定义一个通知观察者对象数据发生了改变的方法
     *
     * @param object 信息数据
     */
    void notifyWatcher(Object object);
}
