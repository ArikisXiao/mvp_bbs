package com.mixotc.abbs.base;

/**
 * 基础BaseView接口
 * @author Sai
 */

public interface BaseView<T> {
    /**
     * 设置presenter的接口
     * @param presenter presenter
     */
    void setPresenter(T presenter);
    /**
     * View层显示Toast的Base接口
     * @param msg 需要显示的信息
     */
    void showToast(String msg);
    /**
     * View层弹出加载ppw的接口
     * @param msg 加载信息
     */
    void showLoading(String msg);
    /**
     * View层隐藏加载ppw的接口
     */
    void hideLoading();
}
