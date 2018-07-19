package com.mixotc.abbs.base;

/**
 * 基础的Presenter接口
 * @author Sai
 */

public interface BasePresenter {
    /**
     * Presenter的启动函数
     */
    void start();
    /**
     * Presenter的回收内存函数
     */
    void onDestroy();
}
