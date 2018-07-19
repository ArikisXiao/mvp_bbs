package com.mixotc.abbs.home.bean;

/**
 *    @author Sai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/03
 *    class note : TOP新闻的实体类
 */
public class NewsSummaryBean {

    private long mNewsId;
    private String mNewsTitle;
    private String mReadingNum;
    private String mNewsCommentNum;
    private int mNewsImage;

    public NewsSummaryBean(long newsId, String newsTitle, String readingNum, String newsCommentNum, int newsImage) {
        this.mNewsId = newsId;
        this.mNewsTitle = newsTitle;
        this.mReadingNum = readingNum;
        this.mNewsCommentNum = newsCommentNum;
        this.mNewsImage = newsImage;
    }

    public long getNewsId() {
        return mNewsId;
    }

    public void setNewsId(long newsId) {
        this.mNewsId = newsId;
    }

    public String getNewsTitle() {
        return mNewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.mNewsTitle = newsTitle;
    }

    public String getReadingNum() {
        return mReadingNum;
    }

    public void setReadingNum(String readingNum) {
        this.mReadingNum = readingNum;
    }

    public String getNewsCommentNum() {
        return mNewsCommentNum;
    }

    public void setNewsCommentNum(String newsCommentNum) {
        this.mNewsCommentNum = newsCommentNum;
    }

    public int getNewsImage() {
        return mNewsImage;
    }

    public void setNewsImage(int newsImage) {
        this.mNewsImage = newsImage;
    }
}
