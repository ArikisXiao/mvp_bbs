package com.mixotc.abbs.register;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/19
 * describe :
 * version :
 */
public class RegisterPresenterTest {

    @Mock
    private RegisterContract.View mRegisterView;
    @Mock
    private RegisterModelImpl mRegisterModel;
    @Mock
    private Context mContext;

    private RegisterPresenter mRegisterPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setPresenterToView() {
        mRegisterPresenter = new RegisterPresenter(mRegisterView);
        Mockito.verify(mRegisterView).setPresenter(mRegisterPresenter);
    }

    @Test
    public void registerSuccess() {
        mRegisterPresenter = new RegisterPresenter(mRegisterView);
        mRegisterPresenter.setRegisterModel(mRegisterModel);
        Mockito.when(mRegisterModel.getRegisterResult(mContext, "aaaaaa", "aaaaaa")).thenReturn(1);
        mRegisterPresenter.startRegister(mContext, "aaaaaa", "aaaaaa");
        Mockito.verify(mRegisterModel).getRegisterResult(mContext, "aaaaaa", "aaaaaa");
        Mockito.verify(mRegisterView, Mockito.atMost(1)).onRegisterSucceed();
        Mockito.verify(mRegisterView, Mockito.times(0)).onUserIsExist();
    }

    @Test
    public void registerFail() {
        mRegisterPresenter = new RegisterPresenter(mRegisterView);
        mRegisterPresenter.setRegisterModel(mRegisterModel);
        Mockito.when(mRegisterModel.getRegisterResult(mContext, "aaaaaa", "aaaaaa")).thenReturn(-1);
        mRegisterPresenter.startRegister(mContext, "aaaaaa", "aaaaaa");
        Mockito.verify(mRegisterModel).getRegisterResult(mContext, "aaaaaa", "aaaaaa");
        Mockito.verify(mRegisterView, Mockito.times(0)).onRegisterSucceed();
        Mockito.verify(mRegisterView, Mockito.times(1)).onUserIsExist();
    }
}