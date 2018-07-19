package com.mixotc.abbs.home;

import com.mixotc.abbs.base.BasePresenter;
import com.mixotc.abbs.base.BaseView;
import com.mixotc.abbs.home.bean.NewsSummaryBean;
import com.mixotc.abbs.home.bean.PostSummaryBean;
import com.mixotc.abbs.home.bean.QaSummaryBean;

import java.util.List;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/02
 *    classnote : 主页Home的协议接口
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {
        /**
         * 根据resultList包含的信息，展示
         * @param resultList infoList
         */
        void showHomeTopNews(List<NewsSummaryBean> resultList);

        void showHomeTopPosting(List<PostSummaryBean> resultList);

        void showHomeLatestQA(List<QaSummaryBean> resultList);

        /**
         * 根据导航ID，进入不同的界面
         * @param infoType 01、论坛  02、新闻  03、问答  04、签到  05、活动  06、微店  07、精选  08、群组
         */
        void goToNavigation(int infoType);

        /**
         * 根据类型id，进入不同的信息列表
         * @param infoType 01、资讯  02、帖子  03、问答
         */
        void goToInfoList(int infoType);

        /**
         * 根据类型id，进入不同的信息详情
         * @param infoType 01、资讯  02、帖子  03、问答
         * @param infoId info id
         */
        void goToInfoDetails(int infoType, long infoId);
    }

    interface Presenter extends BasePresenter {
        /**
         * 加载Home页的数据
         */
        void loadInfo();
        /**
         * 根据类型id，infoID，进入信息详情页
         * @param infoType 01、资讯  02、帖子  03、问答
         * @param infoId info独有的id
         */
        void startInfoDetails(int infoType, long infoId);
    }
}
