package com.mixotc.abbs.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.mixotc.abbs.db.table.UserInfoTable;

/**
 * Author   : xiaoyu
 * Date     : 2018/7/16 下午8:16
 * Version  : v1.0.0
 * Describe :
 */
public class UserInfoDatabaseHelper extends BaseDatabaseHelper {

    private static final String TAG = BaseDatabaseHelper.class.getSimpleName();

    /**
     * 数据库名字的后缀。名字组成为$uid + 后缀名称。每个用户使用单独的数据库避免数据交错。
     */
    private static final String DB_NAME_SUFFIX = "ouixo.db";

    /**
     * 当前数据库的版本号，从1开始，每次升级数据库版本号顺次加1，并说明对应的app版本号和升级内容
     *
     * @version 1 v1.0.0 创建数据库和表
     */
    private static final int DB_VERSION_CODE = 1;

    public UserInfoDatabaseHelper(Context context, String uid) {
        super(context, uid + DB_NAME_SUFFIX, DB_VERSION_CODE);
        SQLiteDatabase db;
        try {
            db = getWritableDatabase();
            // 如果更新失败则删除数据库重新创建，程序不能在错误的数据库上运行
            if (!mUpgradeSuccess) {
                if (db != null) {
                    db.close();
                }
                Log.e(TAG, "数据库更新失败，删除后重现创建，数据库名：" + uid + DB_NAME_SUFFIX);
                context.deleteDatabase(uid + DB_NAME_SUFFIX);
                getWritableDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.deleteDatabase(uid + DB_NAME_SUFFIX);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(UserInfoTable.CREATE_TABLE);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
