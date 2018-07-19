package com.mixotc.abbs.model;


import android.content.Context;

import com.mixotc.abbs.db.bean.DynamicInfoBean;

import java.util.List;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/03
 *    class note :
 */
public interface DynamicModel {
    /**
     * 加载关注列表
     * @param context context
     * @param uid 加载关注列表
     * @param resultCallback 结果回调接口
     */
    void loadDynamicDataByID(Context context, long uid, LoadResultCallback<DynamicInfoBean> resultCallback);

    /**
     * 加载最新动态列表
     * @param context context
     * @param resultCallback 结果回调
     */
    void loadDynamicDataByTimeDesc(Context context, LoadResultCallback<DynamicInfoBean> resultCallback);

    /**
     * 加载热门动态列表
     * @param context context
     * @param resultCallback 结果回调
     */
    void loadDynamicDateByHotDesc(Context context, LoadResultCallback<DynamicInfoBean> resultCallback);

    interface LoadResultCallback<DynamicInfoBean> {
        /**
         * 加载成功，返回结果
         * @param resultList 结果list
         */
        void onSucceed(List<DynamicInfoBean> resultList);

        /**
         * 加载失败，返回 code
         * @param resultCode code
         */
        void onFailed(int resultCode);
    }
}
