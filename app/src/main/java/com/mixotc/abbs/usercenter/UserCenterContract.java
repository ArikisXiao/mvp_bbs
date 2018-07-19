package com.mixotc.abbs.usercenter;

import com.mixotc.abbs.base.BasePresenter;
import com.mixotc.abbs.base.BaseView;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/03
 *    classnote :
 */
public interface UserCenterContract {

    interface View extends BaseView<Presenter> {}

    interface Presenter extends BasePresenter {}
}
