package com.mixotc.abbs.db.table;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe : 所有用户用户数据库，用于模拟服务器数据库
 * version : v1.0.0
 */
public class UserTable {

    /** 表名字 */
    public static final String TABLE_NAME = "user_info";

    /** 用户id */
    public static final String USER_ID = "user_id";

    /** 用户名 */
    public static final String USER_NAME = "user_name";

    /** 用户密码 */
    public static final String USER_PWD = "user_pwd";

    public static final String CREATE_TABLE_USER;

    static {
        CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME
                + " ("
                + USER_ID
                + " INTEGER PRIMARY KEY autoincrement,"
                + USER_NAME
                + " TEXT,"
                + USER_PWD
                + " TEXT);";
    }
}
