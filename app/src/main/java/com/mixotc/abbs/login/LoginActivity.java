package com.mixotc.abbs.login;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mixotc.abbs.MainActivity;
import com.mixotc.abbs.R;
import com.mixotc.abbs.db.bean.UserInfoBean;
import com.mixotc.abbs.register.RegisterActivity;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Locale;

import static com.umeng.analytics.MobclickAgent.*;
import static com.umeng.analytics.MobclickAgent.setScenarioType;

/**
 * @author Sai
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {
    
    private static final int TIME_CLICK = 2000;
    private static final int MIN_PWD_LEN = 6;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private ImageView mIvEyesBtn;
    private Button mBtnLoginSubmit;
    private LoginContract.Presenter mLoginPresenter;
    private ProgressDialog mProgressDialog;
    private boolean mIsHide = true;
    /**
     *  第一次按下返回键的时间
     */
    private long mFirstPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUm();
        initView();
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        MobclickAgent.onPageStart("Login");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        MobclickAgent.onPageEnd("Login");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroy();
    }

    @Override
    public void onLoginSucceed(UserInfoBean user) {
        showToast("欢迎你" + user.getUsername());
        MainActivity.start(this, user);
        finish();
    }

    @Override
    public void onLoginFailed() {
        showToast("密码错误,请重试！");
    }

    @Override
    public void onUserNotExist() {
        showToast("用户不存在！");
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mLoginPresenter = presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //登陆按钮
            case R.id.btn_login_submit:
                mLoginPresenter.startLogin(this, mEtUsername.getText().toString(), mEtPassword.getText().toString());
                break;
            //小眼睛按钮
            case R.id.iv_login_eyes:
                if (mIsHide) {
                    mIvEyesBtn.setImageResource(R.drawable.btn_login_show_eyes);
                    HideReturnsTransformationMethod method = HideReturnsTransformationMethod.getInstance();
                    mEtPassword.setTransformationMethod(method);
                    mEtPassword.setSelection(mEtPassword.getText().length());
                    mIsHide = false;
                } else {
                    mIvEyesBtn.setImageResource(R.drawable.btn_login_hide_eyes);
                    TransformationMethod method = PasswordTransformationMethod.getInstance();
                    mEtPassword.setTransformationMethod(method);
                    mEtPassword.setSelection(mEtPassword.getText().length());
                    mIsHide = true;
                }
                break;
            case R.id.tv_login_register:
                startActivity(new Intent(this, RegisterActivity.class));
            default:
                break;
        }
    }

    /**
     * 重写的返回按钮点击事件，连续点击退出
     * System.currentTimeMillis() 当前系统的时间
     */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mFirstPressedTime < TIME_CLICK) {
            super.onBackPressed();
        } else {
            showToast("再按一次退出");
            mFirstPressedTime = System.currentTimeMillis();
        }
    }
    @Override
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
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

    public void initUm() {
        /*
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
        /*
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true);

        setScenarioType(this, EScenarioType.E_UM_NORMAL);
        //设置手动统计
        MobclickAgent.openActivityDurationTrack(false);
    }

    private void initView() {
        mEtUsername = findViewById(R.id.et_login_username);
        mEtPassword = findViewById(R.id.et_login_password);
        mBtnLoginSubmit = findViewById(R.id.btn_login_submit);
        TextView tvRegister = findViewById(R.id.tv_login_register);
        mIvEyesBtn = findViewById(R.id.iv_login_eyes);
        //设置控件的点击监听
        tvRegister.setOnClickListener(this);
        mBtnLoginSubmit.setOnClickListener(this);
        mIvEyesBtn.setOnClickListener(this);
        //设置loginBtn的初始状态
        mBtnLoginSubmit.setEnabled(false);
        //监听Username和Password的输入情况
        mEtPassword.addTextChangedListener(mTextWatcher);
        mProgressDialog = new ProgressDialog(this);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!"".equals(mEtUsername.getText().toString()) && !"".equals(s.toString())) {
                if (s.length() >= MIN_PWD_LEN) {
                    mBtnLoginSubmit.setEnabled(true);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if ("".equals(s.toString())) {
                mBtnLoginSubmit.setEnabled(false);
            } else {
                if (s.length() >= MIN_PWD_LEN) {
                    mBtnLoginSubmit.setEnabled(true);
                }
            }
        }
    };
}
