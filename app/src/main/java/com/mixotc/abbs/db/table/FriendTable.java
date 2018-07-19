package com.mixotc.abbs.db.table;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe :
 * version :
 */
public class FriendTable {

    /** 表名  */
    public static final String TABLE_NAME = "user_friend";

    /** id 序号 */
    public static final String FID = "fid";

    /** 用户id */
    public static final String USER_ID = "user_id";

    /** 关注用户id */
    public static final String FRIEND_ID = "friend_id";

    public static final String CREATE_TABLE;

    static {
        CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME
                + " ("
                + FID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_ID
                + " INTEGER,"
                + FRIEND_ID
                + " INTEGER);";
    }
}
