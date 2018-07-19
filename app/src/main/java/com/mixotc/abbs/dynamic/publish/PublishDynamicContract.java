package com.mixotc.abbs.dynamic.publish;

import android.content.Context;

import com.mixotc.abbs.base.BasePresenter;
import com.mixotc.abbs.base.BaseView;
import com.mixotc.abbs.db.bean.DynamicInfoBean;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe :
 * version :
 */
public interface PublishDynamicContract {

    interface View extends BaseView<Presenter> {
        /**
         * 发布成功接口
         */
        void onPublishSucceed();
    }

    interface Presenter extends BasePresenter {
        /**
         * P层开始发布接口
         * @param context context
         * @param dynamic 发布的动态
         */
        void startPublish(Context context, DynamicInfoBean dynamic);
    }
}
