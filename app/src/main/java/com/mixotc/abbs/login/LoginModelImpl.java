package com.mixotc.abbs.login;

import android.content.Context;

import com.mixotc.abbs.db.bean.UserInfoBean;
import com.mixotc.abbs.db.provider.UserProvider;
import com.mixotc.abbs.model.LoginModel;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/7/17 16:02
 *    class note : Login功能M层接口的实现类
 */
public class LoginModelImpl implements LoginModel {

    @Override
    public void login(Context context, String username, String password, LoginCallback loginCallback) {
        UserProvider userProvider = new UserProvider(context);
        UserInfoBean user;
        int resultCode = userProvider.getLoginSuccess(username, password);
        if (resultCode == 1) {
            user = userProvider.getContactByUsername(username);
            loginCallback.onSucceed(user);
        } else {
            loginCallback.onFailed(resultCode);
        }
        userProvider.closeDb();
    }
}
