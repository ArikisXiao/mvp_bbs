package com.mixotc.abbs.dynamic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixotc.abbs.CustomViewPager;
import com.mixotc.abbs.R;
import com.mixotc.abbs.adapter.DynamicFragmentPagerAdapter;
import com.mixotc.abbs.db.bean.UserInfoBean;
import com.mixotc.abbs.dynamic.publish.PublishDynamicActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;
import static com.mixotc.abbs.dynamic.DynamicDetailsFragment.*;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/02
 *    class note :
 */
public class DynamicFragment extends Fragment implements View.OnClickListener {

    public static final String ARGUMENT_USER = "ARGUMENT_USER";
    private TabLayout mTabLayout;
    private CustomViewPager mCustomViewPager;
    private UserInfoBean mUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dynamic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initViewPager();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("DynamicList");
        MobclickAgent.onEvent(getContext(), "startDynamic");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("DynamicList");
    }

    public static DynamicFragment newInstance(UserInfoBean user) {
        Bundle args = new Bundle();
        args.putSerializable(ARGUMENT_USER, user);
        DynamicFragment fragment = new DynamicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        if (getView() != null) {
            mTabLayout = getView().findViewById(R.id.tablayout_dynamic_top_navigation);
            mCustomViewPager = getView().findViewById(R.id.viewpager_dynamic);
            FloatingActionButton mBtnPublish = getView().findViewById(R.id.float_btn_dynamic_publish);
            mBtnPublish.setOnClickListener(this);
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUser = (UserInfoBean) bundle.getSerializable(ARGUMENT_USER);
        }
    }

    private void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        fragmentList.add(DynamicDetailsFragment.newInstance(DYNAMIC_TYPE_CONCERN, mUser));
        fragmentList.add(DynamicDetailsFragment.newInstance(DYNAMIC_TYPE_NEW, mUser));
        fragmentList.add(DynamicDetailsFragment.newInstance(DYNAMIC_TYPE_HOT, mUser));
        /* 设置标题list */
        titleList.add("关注");
        titleList.add("最新");
        titleList.add("热门");
        DynamicFragmentPagerAdapter dynamicFragmentPagerAdapter;
        dynamicFragmentPagerAdapter = new DynamicFragmentPagerAdapter(getChildFragmentManager());
        dynamicFragmentPagerAdapter.setFragmentList(fragmentList);
        dynamicFragmentPagerAdapter.setTitleList(titleList);
        mCustomViewPager.setAdapter(dynamicFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mCustomViewPager);
        mCustomViewPager.setCurrentItem(1);
        mTabLayout.getTabAt(1).select();
    }

    @Override
    public void onClick(View v) {
        PublishDynamicActivity.start(getContext(), mUser);
    }
}
