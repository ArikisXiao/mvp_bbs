package com.mixotc.abbs.db.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mixotc.abbs.db.helper.BbsDatabaseHelper;
import com.mixotc.abbs.db.DatabaseException;
import com.mixotc.abbs.db.bean.UserInfoBean;
import com.mixotc.abbs.db.params.InsertParams;

import java.util.ArrayList;
import java.util.List;

import static com.mixotc.abbs.db.table.UserTable.TABLE_NAME;
import static com.mixotc.abbs.db.table.UserTable.USER_NAME;
import static com.mixotc.abbs.db.table.UserTable.USER_PWD;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe :
 * version :
 */
public class UserProvider {

    private BbsDatabaseHelper mHelper;

    public UserProvider(Context context) {
        mHelper = new BbsDatabaseHelper(context);
    }

    /** 插入用户 */
    public void insertContacts(String username, String password) {
        try {
            List<InsertParams> params = new ArrayList<>();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_NAME, username);
            contentValues.put(USER_PWD, password);
            InsertParams param = new InsertParams(TABLE_NAME, contentValues);
            params.add(param);
            mHelper.insert(params);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    /** 验证一个用户是否存在*/
    public boolean getUsernameExist(String username) {
        UserInfoBean user;
        user = getContactByUsername(username);
        return user != null;
    }

    /** 验证登陆*/
    public int getLoginSuccess(String username, String password) {
        UserInfoBean user = getContactByUsername(username);
        if (user == null) {
            return 0;
        } else if (user.getUserPwd().equals(password)) {
            return 1;
        } else {
            return -1;
        }

    }
    /** 获取一个用户 */
    public UserInfoBean getContactByUsername(String username) {
        UserInfoBean user = null;
        Cursor cursor = null;
        try {
            cursor = mHelper.query(TABLE_NAME, null, USER_NAME + "=?", new String[]{username}, null, null, null);
            if (cursor == null) {
                return null;
            }
            if (cursor.moveToFirst()) {
                user = UserInfoBean.createFromCursor(cursor);
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

    /** 关闭 */
    public void closeDb() {
        mHelper.closeDb();
    }
}
