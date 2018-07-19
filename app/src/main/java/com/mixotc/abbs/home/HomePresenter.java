package com.mixotc.abbs.home;

import com.mixotc.abbs.model.HomeModel;
import com.mixotc.abbs.home.bean.NewsSummaryBean;
import com.mixotc.abbs.home.bean.PostSummaryBean;
import com.mixotc.abbs.home.bean.QaSummaryBean;

import java.util.List;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/02
 *    class note :
 */
public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mHomeView;
    private HomeModel mHomeModel;

    HomePresenter(HomeContract.View homeView) {
        this.mHomeView = homeView;
        this.mHomeModel = new HomeModelImpl();
        mHomeView.setPresenter(this);
    }

    @Override
    public void start() {
        loadInfo();
    }

    @Override
    public void onDestroy() {
        mHomeView = null;
    }

    @Override
    public void loadInfo() {
        //加载Top5 News
        mHomeModel.loadHomeTopNewsData(new HomeModel.ResultCallback<NewsSummaryBean>() {
            @Override
            public void onResult(List<NewsSummaryBean> result) {
                mHomeView.showHomeTopNews(result);
            }
        });
        //加载Top3 Post
        mHomeModel.loadHomeTopPostData(new HomeModel.ResultCallback<PostSummaryBean>() {
            @Override
            public void onResult(List<PostSummaryBean> resultList) {
                mHomeView.showHomeTopPosting(resultList);
            }
        });
        //加载Latest3 QA
        mHomeModel.loadHomeLatestQAData(new HomeModel.ResultCallback<QaSummaryBean>() {
            @Override
            public void onResult(List<QaSummaryBean> resultList) {
                mHomeView.showHomeLatestQA(resultList);
            }
        });
    }

    @Override
    public void startInfoDetails(int infoType, long infoId) {
        if (mHomeView != null) {
            mHomeView.showLoading("正在加载...");
            mHomeView.goToInfoDetails(infoType, infoId);
            mHomeView.hideLoading();
        }
    }

    public void setHomeModel(HomeModel homeModel) {
        this.mHomeModel = homeModel;
    }
}
