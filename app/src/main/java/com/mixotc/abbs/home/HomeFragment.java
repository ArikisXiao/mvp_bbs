package com.mixotc.abbs.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mixotc.abbs.R;
import com.mixotc.abbs.adapter.NewsAdapter;
import com.mixotc.abbs.adapter.PostAdapter;
import com.mixotc.abbs.adapter.QuestionAdapter;
import com.mixotc.abbs.home.bean.NewsSummaryBean;
import com.mixotc.abbs.home.bean.PostSummaryBean;
import com.mixotc.abbs.home.bean.QaSummaryBean;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

/**
 * @author Sai
 */
public class HomeFragment extends Fragment implements HomeContract.View {

    private static final int TYPE_NEWS = 1;
    private static final int TYPE_POST = 2;
    private static final int TYPE_QA = 3;
    private RecyclerView mRvTopNews;
    private ListView mLvTopPost;
    private ListView mLvLatestQa;
    private HomeContract.Presenter mHomePresenter;
    private NewsAdapter mNewsAdapter;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        //声明一个新的Presenter
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        //统计页面("Home"为页面名称，可自定义)
        MobclickAgent.onPageStart("Home");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("Home");
        MobclickAgent.onEvent(getContext(), "startHome");
    }

    /**
     * 重新Fragment的onDestroy()方法，销毁mHomePresenter,mNewsAdapter
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomePresenter.onDestroy();
        mNewsAdapter.onDestroy();
    }

    /**
     * view层接口，将Model层获取的Top5News显示在Home页上
     * @param resultList list of top 5 news
     */
    @Override
    public void showHomeTopNews(final List<NewsSummaryBean> resultList) {
        //设置RecyclerView的布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //设置为LinearLayout的横向布局
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTopNews.setLayoutManager(linearLayoutManager);
        mNewsAdapter = new NewsAdapter(getContext(), resultList);
        //给RecyclerView of Top news 设置Adapter
        mRvTopNews.setAdapter(mNewsAdapter);
        //设置RecyclerView 的 Items 点击事件
        mNewsAdapter.setRecyclerItemClickListener(new NewsAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //根据 position ，获取对应的 newsID 执行 P层接口 startNewsDetails 传参
                mHomePresenter.startInfoDetails(TYPE_NEWS, resultList.get(position).getNewsId());
            }
        });
    }

    @Override
    public void showHomeTopPosting(final List<PostSummaryBean> resultList) {
        PostAdapter postAdapter = new PostAdapter(getContext(), resultList);
        mLvTopPost.setAdapter(postAdapter);
        setListViewHeightBasedOnChildren(mLvTopPost);
        mLvTopPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mHomePresenter.startInfoDetails(TYPE_POST, resultList.get(position).getPostId());
            }
        });
    }

    @Override
    public void showHomeLatestQA(final List<QaSummaryBean> resultList) {
        QuestionAdapter questionAdapter = new QuestionAdapter(getContext(), resultList);
        mLvLatestQa.setAdapter(questionAdapter);
        setListViewHeightBasedOnChildren(mLvLatestQa);
        mLvLatestQa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mHomePresenter.startInfoDetails(TYPE_QA, resultList.get(position).getQaId());
            }
        });
    }

    @Override
    public void goToNavigation(int infoType) {}

    @Override
    public void goToInfoList(int infoType) {}

    @Override
    public void goToInfoDetails(int infoType, long infoId) {
        switch (infoType) {
            case TYPE_NEWS:
                showToast("前往新闻详情，还没做"+infoId);
                break;
            case TYPE_POST:
                showToast("前往帖子详情，还没做"+infoId);
                break;
            case TYPE_QA:
                showToast("前往问答详情，还没做"+infoId);
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mHomePresenter = presenter;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.dismiss();
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment;
        fragment = new HomeFragment();
        return fragment;
    }

    private void initView() {
        if (getView() != null) {
            mRvTopNews = getView().findViewById(R.id.rv_home_top_news);
            mLvTopPost = getView().findViewById(R.id.lv_home_top_post);
            mLvLatestQa = getView().findViewById(R.id.lv_home_latest_qa);
        }
        mProgressDialog = new ProgressDialog(getContext());
    }

    /**
     * 重绘ListView的高度
     * @param listView ListView需要重新绘制高度的listView
     */
    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            // 获取item高度
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 最后再加上分割线的高度和padding高度，否则显示不完整。
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1))
                + listView.getPaddingTop() + listView.getPaddingBottom();
        listView.setLayoutParams(params);
    }
}

