package com.mixotc.abbs.dynamic;

import android.content.Context;

import com.mixotc.abbs.db.bean.DynamicInfoBean;
import com.mixotc.abbs.model.DynamicModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.mixotc.abbs.dynamic.DynamicDetailsFragment.DYNAMIC_TYPE_CONCERN;
import static com.mixotc.abbs.dynamic.DynamicDetailsFragment.DYNAMIC_TYPE_HOT;
import static com.mixotc.abbs.dynamic.DynamicDetailsFragment.DYNAMIC_TYPE_NEW;
import static org.junit.Assert.*;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/19
 * describe :
 * version :
 */
public class DynamicPresenterTest {

    @Mock
    private DynamicModelImpl mDynamicModel;
    @Mock
    private DynamicContract.View mDynamicView;
    @Mock
    private Context mContext;
    @Mock
    private DynamicModel.LoadResultCallback<DynamicInfoBean> mCallback;

    private DynamicPresenter mDynamicPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setThePresenterToView() {
        mDynamicPresenter = new DynamicPresenter(mDynamicView);
        Mockito.verify(mDynamicView).setPresenter(mDynamicPresenter);
    }

    @Test
    public void loadInfoSuccess() {
        mDynamicPresenter = new DynamicPresenter(mDynamicView);
        mDynamicPresenter.setDynamicModel(mDynamicModel);
        mDynamicPresenter.setResultCallback(mCallback);
        mDynamicPresenter.loadInfo(mContext, DYNAMIC_TYPE_CONCERN, 1);
        mDynamicPresenter.loadInfo(mContext, DYNAMIC_TYPE_NEW, 1);
        mDynamicPresenter.loadInfo(mContext, DYNAMIC_TYPE_NEW, 1);
        mDynamicPresenter.loadInfo(mContext, DYNAMIC_TYPE_HOT, 1);
        mDynamicPresenter.loadInfo(mContext, DYNAMIC_TYPE_HOT, 1);
        mDynamicPresenter.loadInfo(mContext, DYNAMIC_TYPE_HOT, 1);
        //测试加载数据的次数
        Mockito.verify(mDynamicModel, Mockito.times(1))
                .loadDynamicDataByID(mContext, 1, mCallback);
        Mockito.verify(mDynamicModel, Mockito.times(2))
                .loadDynamicDataByTimeDesc(mContext, mCallback);
        Mockito.verify(mDynamicModel, Mockito.times(3))
                .loadDynamicDateByHotDesc(mContext, mCallback);
    }
}