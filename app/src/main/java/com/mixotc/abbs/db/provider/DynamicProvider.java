package com.mixotc.abbs.db.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mixotc.abbs.db.DatabaseException;
import com.mixotc.abbs.db.bean.DynamicInfoBean;
import com.mixotc.abbs.db.helper.BbsDatabaseHelper;
import com.mixotc.abbs.db.params.InsertParams;

import java.util.ArrayList;
import java.util.List;
import static com.mixotc.abbs.db.table.DynamicTable.DYNAMIC_ID;
import static com.mixotc.abbs.db.table.DynamicTable.TABLE_NAME;
import static com.mixotc.abbs.db.table.DynamicTable.USER_ID;
import static com.mixotc.abbs.db.table.DynamicTable.createContentValues;
import static com.mixotc.abbs.dynamic.DynamicDetailsFragment.DYNAMIC_TYPE_HOT;
import static com.mixotc.abbs.dynamic.DynamicDetailsFragment.DYNAMIC_TYPE_NEW;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe :
 * version :
 */
public class DynamicProvider {

    private BbsDatabaseHelper mHelper;

    public DynamicProvider(Context context) {
        mHelper = new BbsDatabaseHelper(context);
    }

    /** 插入动态 */
    public void insertContacts(DynamicInfoBean dynamic) {
        try {
            List<InsertParams> params = new ArrayList<>();
            ContentValues contentValues = createContentValues(dynamic);
            InsertParams param = new InsertParams(TABLE_NAME, contentValues);
            params.add(param);
            mHelper.insert(params);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    /** 获得 关注用户动态list */
    public List<DynamicInfoBean> getAllFriendDynamicById(long uid) {
        List<DynamicInfoBean> list = new ArrayList<>();
        Cursor cursor = null;
        String sql = "select dynamic.dynamic_id, dynamic.user_id, dynamic.dynamic_info, "
                + "dynamic.dynamic_date, dynamic.dynamic_comment_num, dynamic.dynamic_like_num "
                + "from dynamic, user_friend where user_friend.user_id = ? "
                + "and user_friend.friend_id = dynamic.dynamic_user_id "
                + "order by dynamic.dynamic_id DESC";
        try {
            cursor = mHelper.rawQuery(sql, new String[]{String.valueOf(uid)});
            if (cursor == null) {
                return null;
            }
            while (cursor.moveToNext()) {
                DynamicInfoBean dib = DynamicInfoBean.createFromCursor(cursor);
                list.add(dib);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    /**获取指定用户发布的所有动态*/
    public List<DynamicInfoBean> getDynamicById(long uid) {
        List<DynamicInfoBean> list = new ArrayList<>();
        Cursor cursor = null;
        String sql = "select * from "
                + TABLE_NAME
                +" where "
                + USER_ID
                + "=? order by "
                + DYNAMIC_ID
                + " DESC";
        try {
            cursor = mHelper.rawQuery(sql, new String[]{String.valueOf(uid)});
            if (cursor == null) {
                return null;
            }
            while (cursor.moveToNext()) {
                DynamicInfoBean dib = DynamicInfoBean.createFromCursor(cursor);
                list.add(dib);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    /**获取指定类型的动态*/
    public List<DynamicInfoBean> getDynamicByType(int infoType) {
        List<DynamicInfoBean> list = new ArrayList<>();
        Cursor cursor = null;
        String sql = "";
        if (infoType == DYNAMIC_TYPE_HOT) {
            sql = "select * from dynamic order by dynamic_like_num DESC";
        } else if (infoType == DYNAMIC_TYPE_NEW) {
            sql = "select * from dynamic order by dynamic_id DESC";
        }
        try {
            cursor = mHelper.rawQuery(sql,null);
            if (cursor == null) {
                return null;
            }
            while (cursor.moveToNext()) {
                DynamicInfoBean dib = DynamicInfoBean.createFromCursor(cursor);
                list.add(dib);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    public void closeDb() {
        mHelper.closeDb();
    }
}
