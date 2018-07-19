package com.mixotc.abbs.dynamic;

import android.content.Context;

import com.mixotc.abbs.model.DynamicModel;
import com.mixotc.abbs.db.bean.DynamicInfoBean;

import java.util.List;

import static com.mixotc.abbs.dynamic.DynamicDetailsFragment.DYNAMIC_TYPE_CONCERN;
import static com.mixotc.abbs.dynamic.DynamicDetailsFragment.DYNAMIC_TYPE_HOT;
import static com.mixotc.abbs.dynamic.DynamicDetailsFragment.DYNAMIC_TYPE_NEW;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/03
 *    class note :
 */
public class DynamicPresenter implements DynamicContract.Presenter {

    private DynamicContract.View mDynamicView;
    private DynamicModelImpl mDynamicModel;

    DynamicPresenter(DynamicContract.View dynamicView) {
        this.mDynamicView = dynamicView;
        this.mDynamicModel = new DynamicModelImpl();
        mDynamicView.setPresenter(this);
    }

    @Override
    public void start() {}

    @Override
    public void onDestroy() {
        mDynamicView = null;
    }

    @Override
    public void loadInfo(Context context, final int infoType, long uid) {
        switch (infoType) {
            case DYNAMIC_TYPE_CONCERN:
                mDynamicModel.loadDynamicDataByID(context, uid, mResultCallback);
                break;
            case DYNAMIC_TYPE_NEW:
                mDynamicModel.loadDynamicDataByTimeDesc(context, mResultCallback);
                break;
            case DYNAMIC_TYPE_HOT:
                mDynamicModel.loadDynamicDateByHotDesc(context, mResultCallback);
                break;
            default:
                break;
        }
    }

    @Override
    public void startInfoDetails(long infoId) {
        if (mDynamicView != null) {
            mDynamicView.showLoading("正在加载,请稍后...");
            mDynamicView.goToInfoDetails(infoId);
            mDynamicView.hideLoading();
        }
    }

    public void setDynamicModel(DynamicModelImpl dynamicModel) {
        this.mDynamicModel = dynamicModel;
    }

    public void setResultCallback(DynamicModel.LoadResultCallback<DynamicInfoBean> resultCallback) {
        this.mResultCallback = resultCallback;
    }

    private DynamicModel.LoadResultCallback<DynamicInfoBean> mResultCallback = new DynamicModel.LoadResultCallback<DynamicInfoBean>() {
        @Override
        public void onSucceed(List<DynamicInfoBean> resultList) {
            mDynamicView.showDynamicInfo(resultList);
        }

        @Override
        public void onFailed(int resultCode) {
            mDynamicView.showToast("加载错误，错误码：" + resultCode);
        }
    };
}
