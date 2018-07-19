package com.mixotc.abbs.model;

import android.content.Context;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/16
 * class note : register的M层接口
 */
public interface RegisterModel {
    /**
     * M层注册接口
     * @param context context
     * @param username 注册用户名
     * @param password 注册密码
     * @return 注册接口结果码
     */
    int getRegisterResult(Context context, String username, String password);
}
