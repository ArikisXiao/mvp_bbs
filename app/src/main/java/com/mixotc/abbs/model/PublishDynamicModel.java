package com.mixotc.abbs.model;

import android.content.Context;

import com.mixotc.abbs.db.bean.DynamicInfoBean;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe :
 * version :
 */
public interface PublishDynamicModel {
    /**
     * M层发布接口
     * @param context context
     * @param dynamic 动态bean
     */
    void publish(Context context, DynamicInfoBean dynamic);
}
