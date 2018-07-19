package com.mixotc.abbs.register;

import android.content.Context;
import com.mixotc.abbs.db.provider.UserProvider;
import com.mixotc.abbs.model.RegisterModel;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/16
 * class note : Register的M层接口实现类
 */
public class RegisterModelImpl implements RegisterModel {

    static final int REGISTER_RESULT_SUCCESS = 1;
    private static final int REGISTER_RESULT_USER_IS_EXIST = -1;

    @Override
    public int getRegisterResult(Context context, String username, String password) {
        UserProvider userProvider = new UserProvider(context);
        int resultCode;
        if (userProvider.getUsernameExist(username)) {
            resultCode = REGISTER_RESULT_USER_IS_EXIST;
        } else {
            userProvider.insertContacts(username, password);
            resultCode = REGISTER_RESULT_SUCCESS;
        }
        userProvider.closeDb();
        return resultCode;
    }
}
