package com.mixotc.abbs.register;

import android.content.Context;

import static com.mixotc.abbs.register.RegisterModelImpl.REGISTER_RESULT_SUCCESS;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/16
 * class note : Register P层的实现类
 */
public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View mRegisterView;
    private RegisterModelImpl mRegisterModel;

    RegisterPresenter(RegisterContract.View registerView) {
        this.mRegisterView = registerView;
        this.mRegisterModel = new RegisterModelImpl();
        mRegisterView.setPresenter(this);
    }

    @Override
    public void start() {}

    @Override
    public void onDestroy() {
        mRegisterView = null;
    }

    @Override
    public void startRegister(Context context, String username, String password) {
        if (mRegisterView != null) {
            mRegisterView.showLoading("正在加载，请稍后...");
        }
        final int resultCode = mRegisterModel.getRegisterResult(context, username, password);
        if (resultCode == REGISTER_RESULT_SUCCESS) {
            mRegisterView.hideLoading();
            mRegisterView.onRegisterSucceed();
        } else {
            mRegisterView.hideLoading();
            mRegisterView.onUserIsExist();
        }
    }

    public void setRegisterModel(RegisterModelImpl registerModel) {
        this.mRegisterModel = registerModel;
    }
}
