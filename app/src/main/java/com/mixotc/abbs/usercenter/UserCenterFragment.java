package com.mixotc.abbs.usercenter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mixotc.abbs.R;
import com.umeng.analytics.MobclickAgent;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/02
 *    class note :
 */
public class UserCenterFragment extends Fragment implements UserCenterContract.View {

    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressDialog = new ProgressDialog(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        //统计页面("Home"为页面名称，可自定义)
        MobclickAgent.onPageStart("UserCenter");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("UserCenter");
    }

    @Override
    public void setPresenter(UserCenterContract.Presenter presenter) {}

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

    public static UserCenterFragment newInstance() {
        return new UserCenterFragment();
    }
}
