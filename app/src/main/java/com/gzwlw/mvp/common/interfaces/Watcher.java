package com.gzwlw.mvp.common.interfaces;

/**
 * 观察者(订阅者)接口,需要用到观察者模式的类需实现此接口
 */
public interface Watcher {

    /**
     * 定义一个用来获取更新信息(网络状态)接收的方法
     *
     * @param object 信息数据
     */
    void netWorkStateNotify(Object object);
}
