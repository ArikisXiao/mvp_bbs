package com.mixotc.abbs.login;

import android.content.Context;

import com.mixotc.abbs.db.bean.UserInfoBean;
import com.mixotc.abbs.model.LoginModel;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/02
 *    class note : Login P层实现类
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;
    private LoginModel mLoginModel;

    LoginPresenter(LoginContract.View loginView) {
        this.mLoginView = loginView;
        this.mLoginModel = new LoginModelImpl();
        mLoginView.setPresenter(this);
    }

    @Override
    public void startLogin(Context context, String username, String password) {
        if (mLoginView != null) {
            mLoginView.showLoading("正在登陆，请稍后...");
        }
        mLoginModel.login(context, username, password, mLoginCallback);
        mLoginView.hideLoading();
    }

    @Override
    public void start() {}

    @Override
    public void onDestroy() {
        mLoginView = null;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.mLoginModel = loginModel;
    }

    public void setLoginCallback(LoginModel.LoginCallback loginCallback) {
        mLoginCallback = loginCallback;
    }

    private LoginModel.LoginCallback mLoginCallback = new LoginModel.LoginCallback() {
        @Override
        public void onSucceed(UserInfoBean user) {
            mLoginView.onLoginSucceed(user);
        }

        @Override
        public void onFailed(int resultCode) {
            if (resultCode == -1) {
                mLoginView.onLoginFailed();
            } else if (resultCode == 0) {
                mLoginView.onUserNotExist();
            }
        }
    };
}