package com.gzwlw.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基类的Fragment(模板方法模式)
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 定义onCreateView()方法为final类型,禁止子类重写该方法
     */
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setLayouts(), container, false);

        findViews(view);
        initView();
        initData();
        initEvent();

        return view;
    }

    /**
     * 模板方法,初始化布局
     *
     * @return 布局资源ID
     */
    protected abstract int setLayouts();

    /**
     * 模板方法,findView各种控件
     *
     * @param view View控件
     */
    protected abstract void findViews(View view);

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
}
