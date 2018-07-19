package com.mixotc.abbs.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mixotc.abbs.db.table.DynamicTable;
import com.mixotc.abbs.db.table.FriendTable;
import com.mixotc.abbs.db.table.UserTable;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe :
 * version :
 */
public class BbsDatabaseHelper extends BaseDatabaseHelper {

    private static final String TAG = BaseDatabaseHelper.class.getSimpleName();

    /** 模拟的数据库名 */
    private static final String DATABASE_NAME_BBS = "abbs";
    /** 数据库版本 */
    private static final int DATABASE_VERSION = 1;

    public BbsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME_BBS, DATABASE_VERSION);
        SQLiteDatabase db;
        try {
            db = getWritableDatabase();
            // 如果更新失败则删除数据库重新创建，程序不能在错误的数据库上运行
            if (!mUpgradeSuccess) {
                if (db != null) {
                    db.close();
                }
                Log.e(TAG, "数据库更新失败，删除后重现创建，数据库名：" + DATABASE_NAME_BBS);
                context.deleteDatabase(DATABASE_NAME_BBS);
                getWritableDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.deleteDatabase(DATABASE_NAME_BBS);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(UserTable.CREATE_TABLE_USER);
            db.execSQL(FriendTable.CREATE_TABLE);
            db.execSQL(DynamicTable.CREATE_TABLE);
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
