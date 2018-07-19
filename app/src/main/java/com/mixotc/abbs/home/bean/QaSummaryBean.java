package com.mixotc.abbs.home.bean;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/05
 *    classnote :
 */
public class QaSummaryBean {
    private long mQaId;
    private boolean mAnswerState;
    private String mQuestionDate;
    private long mUserId;
    private String mUserNickName;
    private int mUserHeadImage;
    private String mQuestionCredit;
    private String mQuestionClassify;
    private String mAnswerNum;
    private String mQuestionTitle;

    public QaSummaryBean(long qaId, boolean answerState, String questionDate, long userId, String userNickName, int userHeadImage, String questionCredit, String questionClassify, String answerNum, String questionTitle) {
        this.mQaId = qaId;
        this.mAnswerState = answerState;
        this.mQuestionDate = questionDate;
        this.mUserId = userId;
        this.mUserNickName = userNickName;
        this.mUserHeadImage = userHeadImage;
        this.mQuestionCredit = questionCredit;
        this.mQuestionClassify = questionClassify;
        this.mAnswerNum = answerNum;
        this.mQuestionTitle = questionTitle;
    }

    public long getQaId() {
        return mQaId;
    }

    public void setQaId(long qaId) {
        this.mQaId = qaId;
    }

    public boolean isAnswerState() {
        return mAnswerState;
    }

    public void setAnswerState(boolean answerState) {
        this.mAnswerState = answerState;
    }

    public String getQuestionDate() {
        return mQuestionDate;
    }

    public void setQuestionDate(String questionDate) {
        this.mQuestionDate = questionDate;
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

    public String getQuestionCredit() {
        return mQuestionCredit;
    }

    public void setQuestionCredit(String questionCredit) {
        this.mQuestionCredit = questionCredit;
    }

    public String getQuestionClassify() {
        return mQuestionClassify;
    }

    public void setQuestionClassify(String questionClassify) {
        this.mQuestionClassify = questionClassify;
    }

    public String getAnswerNum() {
        return mAnswerNum;
    }

    public void setAnswerNum(String answerNum) {
        this.mAnswerNum = answerNum;
    }

    public String getQuestionTitle() {
        return mQuestionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.mQuestionTitle = questionTitle;
    }
}
