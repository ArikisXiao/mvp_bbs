package com.mixotc.abbs.db.bean;

import android.database.Cursor;
import com.mixotc.abbs.R;
import static com.mixotc.abbs.db.table.DynamicTable.DYNAMIC_COMMENT_NUM;
import static com.mixotc.abbs.db.table.DynamicTable.DYNAMIC_DATE;
import static com.mixotc.abbs.db.table.DynamicTable.DYNAMIC_ID;
import static com.mixotc.abbs.db.table.DynamicTable.DYNAMIC_INFO;
import static com.mixotc.abbs.db.table.DynamicTable.DYNAMIC_LIKE_NUM;
import static com.mixotc.abbs.db.table.DynamicTable.USER_ID;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/12
 * class note : 动态信息的bean
 */
public class DynamicInfoBean {

    private long mDynamicId;
    private long mUserId;
    private String mUserNickName;
    private int mUserHead;
    private String mDate;
    private String mDynamicInfo;
    private Boolean mIsAppreciate;
    private int mAppreciateNum;
    private int mCommentNum;

    public DynamicInfoBean(long dynamicId, long userId, String userNickname, int userHead, String date, String dynamicInfo, Boolean isAppreciate, int appreciateNum, int commentNum) {
        this.mDynamicId = dynamicId;
        this.mUserId = userId;
        this.mUserNickName = userNickname;
        this.mUserHead = userHead;
        this.mDate = date;
        this.mDynamicInfo = dynamicInfo;
        this.mIsAppreciate = isAppreciate;
        this.mAppreciateNum = appreciateNum;
        this.mCommentNum = commentNum;
    }

    public long getDynamicId() {
        return mDynamicId;
    }

    public void setDynamicId(long dynamicId) {
        mDynamicId = dynamicId;
    }

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    public String getUserNickName() {
        return mUserNickName;
    }

    public void setUserNickName(String userNickName) {
        mUserNickName = userNickName;
    }

    public int getUserHead() {
        return mUserHead;
    }

    public void setUserHead(int userHead) {
        mUserHead = userHead;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getDynamicInfo() {
        return mDynamicInfo;
    }

    public void setDynamicInfo(String dynamicInfo) {
        mDynamicInfo = dynamicInfo;
    }

    public Boolean getIsAppreciate() {
        return mIsAppreciate;
    }

    public void setIsAppreciate(Boolean isAppreciate) {
        mIsAppreciate = isAppreciate;
    }

    public int getAppreciateNum() {
        return mAppreciateNum;
    }

    public void setAppreciateNum(int appreciateNum) {
        mAppreciateNum = appreciateNum;
    }

    public int getCommentNum() {
        return mCommentNum;
    }

    public void setCommentNum(int commentNum) {
        mCommentNum = commentNum;
    }

    /** 从动态表里创建实例 */
    public static DynamicInfoBean createFromCursor(Cursor cursor) {
        long dynamicId = cursor.getInt(cursor.getColumnIndex(DYNAMIC_ID));
        long fId = cursor.getLong(cursor.getColumnIndex(USER_ID));
        String dynamicInfo = cursor.getString(cursor.getColumnIndex(DYNAMIC_INFO));
        String dynamicDate = cursor.getString(cursor.getColumnIndex(DYNAMIC_DATE));
        int dynamicCommentNum = cursor.getInt(cursor.getColumnIndex(DYNAMIC_COMMENT_NUM));
        int dynamicLikeNum = cursor.getInt(cursor.getColumnIndex(DYNAMIC_LIKE_NUM));
        return new DynamicInfoBean(dynamicId, fId, "我是测试用户" + fId,
                R.mipmap.ic_example2, dynamicDate, dynamicInfo, false,
                dynamicLikeNum, dynamicCommentNum);
    }
}
