package com.mixotc.abbs.dynamic.publish;

import android.content.Context;

import com.mixotc.abbs.db.bean.DynamicInfoBean;

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
public class PublishDynamicPresenterTest {

    @Mock
    private PublishDynamicContract.View mPublishView;
    @Mock
    private PublishDynamicModelImpl mPublishModel;
    @Mock
    private Context mContext;
    @Mock
    private DynamicInfoBean mDynamicInfoBean;

    private PublishDynamicPresenter mPublishPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setThePresenterToView() {
        mPublishPresenter = new PublishDynamicPresenter(mPublishView);
        Mockito.verify(mPublishView).setPresenter(mPublishPresenter);
    }

    @Test
    public void publishSuccess() {
        mPublishPresenter = new PublishDynamicPresenter(mPublishView);
        mPublishPresenter.setModel(mPublishModel);
        mPublishPresenter.startPublish(mContext, mDynamicInfoBean);
        Mockito.verify(mPublishModel).publish(mContext, mDynamicInfoBean);
        Mockito.verify(mPublishView).onPublishSucceed();
    }

}