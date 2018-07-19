package com.mixotc.abbs.model;

import android.content.Context;
import com.mixotc.abbs.db.bean.UserInfoBean;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/11
 * class note :
 */
public interface LoginModel {

    /**
     * M层登陆接口
     * @param context context
     * @param username 用户名
     * @param password 密码
     * @param loginCallback 回调接口
     */
    void login(Context context, String username, String password, LoginCallback loginCallback);

    interface LoginCallback {
        /**
         * 登陆成功
         * @param user 登陆用户
         */
        void onSucceed(UserInfoBean user);

        /**
         * 登陆失败
         * @param resultCode 结果码
         */
        void onFailed(int resultCode);
    }
}
