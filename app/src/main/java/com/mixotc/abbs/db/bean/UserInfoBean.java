package com.mixotc.abbs.db.bean;

import android.database.Cursor;

import com.mixotc.abbs.db.table.UserInfoTable;
import com.mixotc.abbs.db.table.UserTable;

import java.io.Serializable;

/**
 * Author   : xiaoyu
 * Date     : 2018/7/16 下午8:27
 * Version  : v1.0.0
 * Describe :
 */
public class UserInfoBean implements Serializable {

    private long mUid;
    private String mUsername;
    private String mUserPwd;

    public UserInfoBean(long uid, String username, String password) {
        mUid = uid;
        mUsername = username;
        mUserPwd = password;
    }

    public long getUid() {
        return mUid;
    }

    public void setUid(long uid) {
        mUid = uid;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getUserPwd() {
        return mUserPwd;
    }

    public void setUserPwd(String password) {
        mUserPwd = password;
    }

    /** 从联系人表里创建实例 */
    public static UserInfoBean createFromCursor(Cursor cursor) {
        long uid = cursor.getLong(cursor.getColumnIndex(UserTable.USER_ID));
        String username = cursor.getString(cursor.getColumnIndex(UserTable.USER_NAME));
        String password = cursor.getString(cursor.getColumnIndex(UserTable.USER_PWD));
        return new UserInfoBean(uid, username, password);
    }

    /** 从联系人表里创建实例 */
    public static UserInfoBean createLocalFromCursor(Cursor cursor) {
        long uid = cursor.getLong(cursor.getColumnIndex(UserInfoTable.USER_ID));
        String username = cursor.getString(cursor.getColumnIndex(UserInfoTable.USER_NAME));
        String password = cursor.getString(cursor.getColumnIndex(UserInfoTable.USER_PWD));
        return new UserInfoBean(uid, username, password);
    }
}
