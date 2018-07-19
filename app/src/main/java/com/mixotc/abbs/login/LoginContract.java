package com.mixotc.abbs.login;

import android.content.Context;

import com.mixotc.abbs.base.BasePresenter;
import com.mixotc.abbs.base.BaseView;
import com.mixotc.abbs.db.bean.UserInfoBean;

/**
 * @author Sai
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {
        /**
         * 登陆成功
         * @param user 登陆的用户
         */
        void onLoginSucceed(UserInfoBean user);

        /** 登陆失败 */
        void onLoginFailed();

        /** 用户不存在 */
        void onUserNotExist();
    }

    interface Presenter extends BasePresenter {
        /**
         * P层登陆接口
         * @param context context
         * @param username 用户名
         * @param password 密码
         */
        void startLogin(Context context, String username, String password);
    }
}
