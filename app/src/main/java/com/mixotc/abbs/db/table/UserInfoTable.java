package com.mixotc.abbs.db.table;

import android.content.ContentValues;

import com.mixotc.abbs.db.bean.UserInfoBean;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe : 用户信息表
 * version : 1.0
 */
public class UserInfoTable {

    /** 表名字 */
    public static final String TABLE_NAME = "user_info";

    /** 用户id */
    public static final String USER_ID = "user_id";

    /** 用户名 */
    public static final String USER_NAME = "user_name";

    /** 用户密码 */
    public static final String USER_PWD = "user_pwd";

    public static final String CREATE_TABLE;

    static {
        CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME
                + " ("
                + USER_ID
                + " INTEGER PRIMARY KEY,"
                + USER_NAME
                + " TEXT,"
                + USER_PWD
                + " TEXT);";
    }

    /** 根据实例生成操作表的ContentValues */
    public static ContentValues createContentValues(UserInfoBean contact) {
        ContentValues contentValues = new ContentValues();
        if (contact != null) {
            contentValues.put(USER_ID, contact.getUid());
            contentValues.put(USER_NAME, contact.getUsername());
            contentValues.put(USER_PWD, contact.getUserPwd());
        }
        return contentValues;
    }
}
