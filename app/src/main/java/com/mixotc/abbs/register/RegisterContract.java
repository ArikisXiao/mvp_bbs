package com.mixotc.abbs.register;

import android.content.Context;

import com.mixotc.abbs.base.BasePresenter;
import com.mixotc.abbs.base.BaseView;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/16
 * class note : Register的协议接口
 */
public interface RegisterContract {

    interface View extends BaseView<Presenter> {

        /** 注册成功 */
        void onRegisterSucceed();

        /** 用户已经存在 */
        void onUserIsExist();
    }

    interface Presenter extends BasePresenter {

        /**
         * P层接口，调用M层 接口
         * @param context context
         * @param username 注册的用户名
         * @param password 注册的密码
         */
        void startRegister(Context context, String username, String password);
    }
}
