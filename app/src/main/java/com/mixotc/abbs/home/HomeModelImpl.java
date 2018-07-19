package com.mixotc.abbs.home;

import com.mixotc.abbs.R;
import com.mixotc.abbs.model.HomeModel;
import com.mixotc.abbs.home.bean.NewsSummaryBean;
import com.mixotc.abbs.home.bean.PostSummaryBean;
import com.mixotc.abbs.home.bean.QaSummaryBean;

import java.util.ArrayList;
import java.util.List;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/02
 *    class note : M层 HomeModel的实现类，进行从服务器申请数据，目前为本地假数据
 */
public class HomeModelImpl implements HomeModel {

    /**
     * 加载Top 5 of News
     * @param resultCallback 回调接口
     */
    @Override
    public void loadHomeTopNewsData(ResultCallback<NewsSummaryBean> resultCallback) {
        List<NewsSummaryBean> newsResultList = new ArrayList<>();
        //新闻id，新闻标题，图片，阅读量，评论数
        newsResultList.add(new NewsSummaryBean(1,"我是标题我是标题我是标题我是标题我是标题", "50","12000",R.mipmap.ic_example1));
        newsResultList.add(new NewsSummaryBean(2,"我是标题我是标题我是标题我是标题我是标题", "50","9000", R.mipmap.ic_example2));
        newsResultList.add(new NewsSummaryBean(3,"我是标题我是标题我是标题我是标题我是标题", "50","50050",R.mipmap.ic_example3));
        newsResultList.add(new NewsSummaryBean(4,"TITLE", "5200000","50050000", R.mipmap.ic_example4));
        newsResultList.add(new NewsSummaryBean(5,"TITLE", "1990","50050", R.mipmap.ic_example5));
        resultCallback.onResult(newsResultList);
    }

    /**
     * 加载 Top 3 of Post
     * @param resultCallback 回调接口
     */
    @Override
    public void loadHomeTopPostData(ResultCallback<PostSummaryBean> resultCallback) {
        List<PostSummaryBean> postResultList = new ArrayList<>();
        postResultList.add(new PostSummaryBean(1,1,"测试1",R.mipmap.ic_example1, "我是帖子标题我是帖子标题我是帖子标题","科技信息","10000","1000","yy-mm-dd"));
        postResultList.add(new PostSummaryBean(2,2,"测试2",R.mipmap.ic_example2, "我是帖子标题我是帖子标题我是帖子标题","科技信息","10000","1000","yy-mm-dd"));
        postResultList.add(new PostSummaryBean(3,3,"测试3",R.mipmap.ic_example3, "我是帖子标题我是帖子标题我是帖子标题","科技信息","10000","1000","yy-mm-dd"));
        resultCallback.onResult(postResultList);
    }

    /**
     * 加载 Latest 3 of QA
     * @param resultCallback 回调接口
     */
    @Override
    public void loadHomeLatestQAData(ResultCallback<QaSummaryBean> resultCallback) {
        List<QaSummaryBean> qaResultList = new ArrayList<>();
        qaResultList.add(new QaSummaryBean(1, false, "2018-07-06",1, "测试1", R.mipmap.ic_example1, "20", "摄影天地", "1200","我是提问标题我是提问标题我是提问标题我是提问标题"));
        qaResultList.add(new QaSummaryBean(2, true, "2018-07-06",3, "测试2", R.mipmap.ic_example3, "20", "摄影天地", "999","我是提问标题我是提问标题我是提问标题我是提问标题"));
        qaResultList.add(new QaSummaryBean(3, false, "2018-07-06",3, "测试3", R.mipmap.ic_example5, "20", "摄影天地", "1222","我是提问标题我是提问标题我是提问标题我是提问标题"));
        resultCallback.onResult(qaResultList);
    }
}