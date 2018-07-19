package com.mixotc.abbs.login;

import android.content.Context;

import com.mixotc.abbs.db.bean.UserInfoBean;
import com.mixotc.abbs.model.LoginModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/10
 * class note :
 */
public class LoginPresenterTest {

    @Mock
    private LoginModelImpl mLoginModelImpl;
    @Mock
    private LoginContract.View mLoginView;
    @Mock
    private Context mContext;
    @Mock
    private LoginModel.LoginCallback mCallback;
    @Mock
    private UserInfoBean mUser;

    private LoginPresenter mLoginPresenter;

    @Before
    public void setUpMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setThePresenterToView() {
        // Get a reference to the class under test
        mLoginPresenter = new LoginPresenter(mLoginView);
        Mockito.verify(mLoginView).setPresenter(mLoginPresenter);
    }

    @Test
    public void startLoginSucceed() {
        mLoginPresenter = new LoginPresenter(mLoginView);
        mLoginPresenter.setLoginModel(mLoginModelImpl);
        mLoginPresenter.setLoginCallback(mCallback);
        mLoginPresenter.startLogin(mContext, "admin", "admin");
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                LoginModel.LoginCallback loginCallback = (LoginModel.LoginCallback) arguments[3];
                loginCallback.onSucceed(mUser);
                return loginCallback;
            }
        }).when(mLoginModelImpl).login(mContext, "admin", "admin", mCallback);
        Mockito.verify(mLoginModelImpl).login(mContext, "admin", "admin", mCallback);
        Mockito.verify(mLoginModelImpl, Mockito.times(1))
                .login(mContext, "admin", "admin", mCallback);
    }

    @Test
    public void startLoginFailed() {
        mLoginPresenter = new LoginPresenter(mLoginView);
        mLoginPresenter.setLoginModel(mLoginModelImpl);
        mLoginPresenter.setLoginCallback(mCallback);
        mLoginPresenter.startLogin(mContext, "admin", "admin");
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                LoginModel.LoginCallback loginCallback = (LoginModel.LoginCallback) arguments[3];
                loginCallback.onFailed(-1);
                return loginCallback;
            }
        }).when(mLoginModelImpl).login(mContext, "admin", "admin", mCallback);
        Mockito.verify(mLoginModelImpl).login(mContext, "admin", "admin", mCallback);
        Mockito.verify(mLoginModelImpl, Mockito.times(1))
                .login(mContext, "admin", "admin", mCallback);
    }
}