package com.mixotc.abbs.dynamic.publish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mixotc.abbs.R;
import com.mixotc.abbs.db.bean.DynamicInfoBean;
import com.mixotc.abbs.db.bean.UserInfoBean;
import com.umeng.analytics.MobclickAgent;

import static com.mixotc.abbs.MainActivity.EXTRA_USER;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/13
 * class note :
 */
public class PublishDynamicActivity extends AppCompatActivity implements View.OnClickListener, PublishDynamicContract.View {

    private PublishDynamicContract.Presenter mPresenter;
    private TextView mTvSubmit;
    private EditText mEtDynamicInfo;
    private TextView mTvTextNum;
    private long mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_dynamic);
        Intent intent = getIntent();
        UserInfoBean mUser = (UserInfoBean) intent.getSerializableExtra(EXTRA_USER);
        mUserId = mUser.getUid();
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        MobclickAgent.onPageStart("Publish");
        MobclickAgent.onEvent(this, "startPublish");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        MobclickAgent.onPageEnd("Publish");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void setPresenter(PublishDynamicContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onPublishSucceed() {
        finish();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(String msg) {}

    @Override
    public void hideLoading() {}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_publish_toolbar_back:
                finish();
                break;
            case R.id.tv_publish_top_summit:
                MobclickAgent.onEvent(this, "PublishSubmit");
                DynamicInfoBean mDynamic = new DynamicInfoBean(0, mUserId,
                        "测试用户" + mUserId,
                        R.mipmap.ic_example2, null,
                        mEtDynamicInfo.getText().toString(),
                        false, 0, 0);
                mPresenter.startPublish(this, mDynamic);
                break;
            default:
                break;
        }
    }

    public static void start(Context context, UserInfoBean user) {
        Intent starter = new Intent(context, PublishDynamicActivity.class);
        starter.putExtra(EXTRA_USER, user);
        context.startActivity(starter);
    }

    private void initView() {
        mPresenter = new PublishDynamicPresenter(this);
        ImageView mIvBack = findViewById(R.id.iv_publish_toolbar_back);
        mTvSubmit = findViewById(R.id.tv_publish_top_summit);
        mEtDynamicInfo = findViewById(R.id.et_publish_info);
        mTvTextNum = findViewById(R.id.tv_publish_edit_text_num);
        mTvSubmit.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mEtDynamicInfo.addTextChangedListener(mWatcher);
        mTvSubmit.setClickable(false);
    }

    private TextWatcher mWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int input = 140 - s.length();
            mTvTextNum.setText(String.format("还可以输入%s字", input));
            mTvSubmit.setClickable(true);
            mTvSubmit.setTextColor(getResources().getColor(R.color.blue_light));
        }

        @Override
        public void afterTextChanged(Editable s) {
            String input = String.valueOf(140 - s.length());
            mTvTextNum.setText(String.format("还可以输入%s字", input));
            mTvSubmit.setClickable(true);
            mTvSubmit.setTextColor(getResources().getColor(R.color.blue_light));
            if (s.length() == 0) {
                mTvSubmit.setTextColor(Color.GRAY);
                mTvSubmit.setClickable(false);
            }
        }
    };
}