package com.mixotc.abbs.home.bean;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/05
 */
public class PostSummaryBean {
    private long mPostId;
    private long mUserId;
    private String mUserNickName;
    private int mUserHeadImage;
    private String mPostTitle;
    private String mPostClassify;
    private String mReadingNum;
    private String mCommentNum;
    private String mPostDate;

    public PostSummaryBean(long postId, long userId, String userNickName, int userHeadImage, String postTitle, String postClassify, String readingNum, String commentNum, String postDate) {
        this.mPostId = postId;
        this.mUserId = userId;
        this.mUserNickName = userNickName;
        this.mUserHeadImage = userHeadImage;
        this.mPostTitle = postTitle;
        this.mPostClassify = postClassify;
        this.mReadingNum = readingNum;
        this.mCommentNum = commentNum;
        this.mPostDate = postDate;
    }

    public long getPostId() {
        return mPostId;
    }

    public void setPostId(long postId) {
        this.mPostId = postId;
    }

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        this.mUserId = userId;
    }

    public String getUserNickName() {
        return mUserNickName;
    }

    public void setUserNickName(String userNickName) {
        this.mUserNickName = userNickName;
    }

    public int getUserHeadImage() {
        return mUserHeadImage;
    }

    public void setUserHeadImage(int userHeadImage) {
        this.mUserHeadImage = userHeadImage;
    }

    public String getPostTitle() {
        return mPostTitle;
    }

    public void setPostTitle(String postTitle) {
        this.mPostTitle = postTitle;
    }

    public String getPostClassify() {
        return mPostClassify;
    }

    public void setPostClassify(String postClassify) {
        this.mPostClassify = postClassify;
    }

    public String getReadNum() {
        return mReadingNum;
    }

    public void setReadNum(String readingNum) {
        this.mReadingNum = readingNum;
    }

    public String getCommentNum() {
        return mCommentNum;
    }

    public void setCommentNum(String commentNum) {
        this.mCommentNum = commentNum;
    }

    public String getPostDate() {
        return mPostDate;
    }

    public void setPostDate(String postDate) {
        this.mPostDate = postDate;
    }
}
