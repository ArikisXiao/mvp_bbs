package com.mixotc.abbs.dynamic;

import android.content.Context;

import com.mixotc.abbs.base.BasePresenter;
import com.mixotc.abbs.base.BaseView;
import com.mixotc.abbs.db.bean.DynamicInfoBean;

import java.util.List;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/03
 *    classnote :
 */
public interface DynamicContract {

    interface View extends BaseView<Presenter> {
        /**
         * view层展示动态信息的接口
         * @param resultList 信息list
         */
        void showDynamicInfo(List<DynamicInfoBean> resultList);

        /**
         * view层跳转至动态信息详情的接口
         * @param infoId 信息id
         */
        void goToInfoDetails(long infoId);

    }
    interface Presenter extends BasePresenter {
        /**
         * 加载信息的接口
         * @param context context
         * @param infoType 加载不同类型的动态 1、关注动态 2、热门动态 3、最新动态
         * @param uid 用户id
         */
        void loadInfo(Context context, int infoType, long uid);

        /**
         * 加载信息详情的接口
         * @param infoId 信息对应ID
         */
        void startInfoDetails(long infoId);
    }
}
