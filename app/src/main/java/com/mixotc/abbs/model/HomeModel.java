package com.mixotc.abbs.model;

import com.mixotc.abbs.home.bean.NewsSummaryBean;
import com.mixotc.abbs.home.bean.PostSummaryBean;
import com.mixotc.abbs.home.bean.QaSummaryBean;

import java.util.List;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/11
 * class note :
 */
public interface HomeModel {
    /**
     * 加载不同bean的接口
     * @param resultCallback 回调接口
     */
    void loadHomeTopNewsData(ResultCallback<NewsSummaryBean> resultCallback);

    /**
     * 加载不同bean的接口
     * @param resultCallback 回调接口
     */
    void loadHomeTopPostData(ResultCallback<PostSummaryBean> resultCallback);

    /**
     * 加载不同bean的接口
     * @param resultCallback 回调接口
     */
    void loadHomeLatestQAData(ResultCallback<QaSummaryBean> resultCallback);

    /**
     * 结果回调接口
     */
    interface ResultCallback<T> {
        /**
         * 获取resultList的接口
         * @param resultList 返回结果list
         */
        void onResult(List<T> resultList);
    }
}
