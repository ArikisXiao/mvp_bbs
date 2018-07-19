package com.mixotc.abbs.dynamic.publish;

import android.content.Context;

import com.mixotc.abbs.db.bean.DynamicInfoBean;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe :
 * version :
 */
public class PublishDynamicPresenter implements PublishDynamicContract.Presenter {

    private PublishDynamicContract.View mView;
    private PublishDynamicModelImpl mModel;

    public PublishDynamicPresenter(PublishDynamicContract.View view) {
        mView = view;
        mModel = new PublishDynamicModelImpl();
        mView.setPresenter(this);
    }

    @Override
    public void startPublish(Context context, DynamicInfoBean dynamic) {
        if (mView != null) {
            mView.showLoading("正在发布，请稍后...");
        }
        mModel.publish(context, dynamic);
        mView.hideLoading();
        mView.onPublishSucceed();
    }

    @Override
    public void start() {}

    @Override
    public void onDestroy() {
        mView = null;
    }

    public void setModel(PublishDynamicModelImpl model) {
        this.mModel = model;
    }
}
