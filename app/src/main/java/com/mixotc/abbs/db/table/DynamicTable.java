package com.mixotc.abbs.db.table;

import android.content.ContentValues;

import com.mixotc.abbs.db.bean.DynamicInfoBean;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe :
 * version :
 */
public class DynamicTable {

    public static final String TABLE_NAME = "dynamic";

    public static final String DYNAMIC_ID = "dynamic_id";

    public static final String USER_ID = "user_id";

    public static final String DYNAMIC_INFO = "dynamic_info";

    public static final String DYNAMIC_DATE = "dynamic_date";

    public static final String DYNAMIC_COMMENT_NUM = "dynamic_comment_num";

    public static final String DYNAMIC_LIKE_NUM = "dynamic_like_num";

    public static final String CREATE_TABLE;

    static {
        CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME
                + " ("
                + DYNAMIC_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_ID
                + " INTEGER,"
                + DYNAMIC_INFO
                + " TEXT,"
                + DYNAMIC_DATE
                + " TEXT,"
                + DYNAMIC_COMMENT_NUM
                + " INTEGER,"
                + DYNAMIC_LIKE_NUM
                + " INTEGER);";
    }

    /** 根据实例生成操作表的ContentValues */
    public static ContentValues createContentValues(DynamicInfoBean contact) {
        ContentValues contentValues = new ContentValues();
        if (contact != null) {
            contentValues.put(USER_ID, contact.getUserId());
            contentValues.put(DYNAMIC_INFO, contact.getDynamicInfo());
            contentValues.put(DYNAMIC_DATE, contact.getDate());
            contentValues.put(DYNAMIC_COMMENT_NUM, contact.getCommentNum());
            contentValues.put(DYNAMIC_LIKE_NUM, contact.getAppreciateNum());
        }
        return contentValues;
    }
}
