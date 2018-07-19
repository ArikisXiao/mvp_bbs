package com.mixotc.abbs.db.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mixotc.abbs.db.DatabaseException;
import com.mixotc.abbs.db.helper.UserInfoDatabaseHelper;
import com.mixotc.abbs.db.bean.UserInfoBean;
import com.mixotc.abbs.db.params.InsertParams;
import com.mixotc.abbs.db.table.UserInfoTable;

import java.util.ArrayList;
import java.util.List;

import static com.mixotc.abbs.db.table.UserInfoTable.TABLE_NAME;
import static com.mixotc.abbs.db.table.UserInfoTable.USER_ID;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe :
 * version :
 */
public class UserLocalProvider {

    private UserInfoDatabaseHelper mHelper;

    public UserLocalProvider(Context context, String uid) {
        mHelper = new UserInfoDatabaseHelper(context, uid);
    }

    /** 插入用户 */
    public void insertContacts(UserInfoBean user) {
        try {
            List<InsertParams> params = new ArrayList<>();
            ContentValues contentValues = UserInfoTable.createContentValues(user);
            InsertParams param = new InsertParams(TABLE_NAME, contentValues);
            params.add(param);
            mHelper.insert(params);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    /** 获取一个用户 */
    public UserInfoBean getContactById(long uid) {
        UserInfoBean user = null;
        Cursor cursor = null;
        try {
            cursor = mHelper.query(TABLE_NAME, null, USER_ID + "=?", new String[]{String.valueOf(uid)}, null, null, null);
            if (cursor == null) {
                return null;
            }
            if (cursor.moveToFirst()) {
                user = UserInfoBean.createLocalFromCursor(cursor);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return user;
    }

    public void closeDb() {
        mHelper.closeDb();
    }
}
