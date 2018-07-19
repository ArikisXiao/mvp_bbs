package com.mixotc.abbs.dynamic;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.mixotc.abbs.R;
import com.mixotc.abbs.db.bean.DynamicInfoBean;
import com.mixotc.abbs.adapter.DynamicInfoAdapter;
import com.mixotc.abbs.db.bean.UserInfoBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.List;
import static com.mixotc.abbs.dynamic.DynamicFragment.ARGUMENT_USER;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/12
 * class note :
 */
public class DynamicDetailsFragment extends Fragment implements DynamicContract.View, DynamicInfoAdapter.ClickCallBack {

    public static final String ARGUMENT_INFO_TYPE = "ARGUMENT_INFO_TYPE";
    public static final int DYNAMIC_TYPE_CONCERN = 1;
    public static final int DYNAMIC_TYPE_HOT = 2;
    public static final int DYNAMIC_TYPE_NEW = 3;
    private ListView mLvDynamicInfo;
    private ProgressDialog mProgressDialog;
    private SmartRefreshLayout mRefreshLayout;
    private UserInfoBean mUser;
    private int mInfoType;
    private DynamicContract.Presenter mDynamicPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dynamic_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDynamicPresenter = new DynamicPresenter(this);
        initView();
        initData(mInfoType);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh(2000);
        MobclickAgent.onPageStart("DynamicList" + mInfoType);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("DynamicList" + mInfoType);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDynamicPresenter.onDestroy();
    }

    @Override
    public void showDynamicInfo(final List<DynamicInfoBean> resultList) {
        DynamicInfoAdapter mDynamicInfoAdapter = new DynamicInfoAdapter(getContext(), resultList, this);
        mLvDynamicInfo.setAdapter(mDynamicInfoAdapter);
        mLvDynamicInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDynamicPresenter.startInfoDetails(resultList.get(position).getDynamicId());
            }
        });
    }

    @Override
    public void goToInfoDetails(long infoId) {
        showToast("前往动态详情" + infoId);
    }

    @Override
    public void setPresenter(DynamicContract.Presenter presenter) {
        this.mDynamicPresenter = presenter;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_dynamic_item_appreciate:
                break;
            default:
                break;
        }
    }

    public static DynamicDetailsFragment newInstance(int infoType, UserInfoBean user) {
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_INFO_TYPE, infoType);
        args.putSerializable(ARGUMENT_USER, user);
        DynamicDetailsFragment dynamicDetailsFragment = new DynamicDetailsFragment();
        dynamicDetailsFragment.setArguments(args);
        return dynamicDetailsFragment;
    }

    private void initData(int infoType) {
        mDynamicPresenter.loadInfo(getContext(), infoType, mUser.getUid());
    }

    private void initView() {
        mProgressDialog = new ProgressDialog(getContext());
        if (getView() != null) {
            mLvDynamicInfo = getView().findViewById(R.id.lv_dynamic_info);
            mRefreshLayout = getView().findViewById(R.id.refresh_dynamic_list);
        }
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initData(mInfoType);
                mRefreshLayout.finishRefresh(2000);
            }
        });
        Bundle bundle = getArguments();
        if (bundle != null) {
            mInfoType = (int) bundle.get(ARGUMENT_INFO_TYPE);
            mUser = (UserInfoBean) bundle.getSerializable(ARGUMENT_USER);
        }
    }
}
