package com.mixotc.abbs.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mixotc.abbs.R;
import com.mixotc.abbs.login.LoginActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * @author Sai
 * class note : Register View层的实现类
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,RegisterContract.View {

    private RegisterContract.Presenter mPresenter;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private EditText mETPasswordChecked;
    private Button mBtnSubmit;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        MobclickAgent.onPageStart("Register");
        MobclickAgent.onEvent(this, "goToRegister");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        MobclickAgent.onPageEnd("Register");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onRegisterSucceed() {
        startActivity(new Intent(this, LoginActivity.class));
        showToast("注册成功");
        finish();
    }

    @Override
    public void onUserIsExist() {
        showToast("注册失败，用户已存在");
    }

    @Override
    public void onClick(View v) {
        if (mEtPassword.getText().toString().equals(mETPasswordChecked.getText().toString())) {
            MobclickAgent.onEvent(this, "RegisterSubmit");
            mPresenter.startRegister(this, mEtUsername.getText().toString(),
                    mEtPassword.getText().toString());
        } else {
            showToast("两次密码输入不一致！");
            mETPasswordChecked.setText("");
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
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

    private void initView() {
        mEtUsername = findViewById(R.id.et_register_username);
        mEtPassword = findViewById(R.id.et_register_password);
        mETPasswordChecked = findViewById(R.id.et_register_password_checked);
        mBtnSubmit = findViewById(R.id.btn_register_submit);
        mBtnSubmit.setEnabled(false);
        mBtnSubmit.setOnClickListener(this);
        mPresenter = new RegisterPresenter(this);
        mETPasswordChecked.addTextChangedListener(mTextWatcher);
        mProgressDialog = new ProgressDialog(this);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if ("".equals(s.toString())) {
                mBtnSubmit.setEnabled(false);
            } else if (mEtUsername.getText().length() != 0) {
                if (mEtPassword.getText().length() != 0) {
                    mBtnSubmit.setEnabled(true);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if ("".equals(s.toString())) {
                mBtnSubmit.setEnabled(false);
            } else if (mEtUsername.getText().length() != 0) {
                if (mEtPassword.getText().length() != 0) {
                    mBtnSubmit.setEnabled(true);
                }
            }
        }
    };
}
