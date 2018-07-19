package com.mixotc.abbs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.mixotc.abbs.adapter.MainFragmentPagerAdapter;
import com.mixotc.abbs.db.bean.UserInfoBean;
import com.mixotc.abbs.dynamic.DynamicFragment;
import com.mixotc.abbs.home.HomeFragment;
import com.mixotc.abbs.login.LoginActivity;
import com.mixotc.abbs.usercenter.UserCenterFragment;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/02
 *    class note : 主activity
 */

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "EXTRA_USER";
    private BottomNavigationBar mBottomNavigationBar;
    private CustomViewPager mCustomViewPager;
    private Toolbar mToolBar;
    private TextView mTvToolBarTitle;
    private UserInfoBean mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化界面控件
        initView();
        //初始化ToolBar
        initToolBar();
        //初始化ViewPager
        initViewPager();
        //初始化BottomNavigationBar
        initBottomNavigationBar();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    public static void start(Context context, UserInfoBean user) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra(EXTRA_USER, user);
        context.startActivity(starter);
    }

    private void initView() {
        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar_main);
        mCustomViewPager = findViewById(R.id.viewpager_main);
        mToolBar = findViewById(R.id.tb_home);
        mTvToolBarTitle = findViewById(R.id.tv_main_toolbar);
        Intent intent = getIntent();
        mUser = (UserInfoBean) intent.getSerializableExtra(EXTRA_USER);
    }

    private void initToolBar() {
        setSupportActionBar(mToolBar);
        //设置不显示App标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTvToolBarTitle.setTextSize(COMPLEX_UNIT_DIP, 18);
    }

    /**
     * 初始化ViewPager，设置ViewPager的相关信息，是否滑动，页面改变等
     */
    private void initViewPager() {
        //viewPager设置不可滑动
        mCustomViewPager.setmIsSlidingEnable(false);
        //设置ViewPager的list：首页、动态、消息、我的
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(DynamicFragment.newInstance(mUser));
        fragmentList.add(UserCenterFragment.newInstance());
        fragmentList.add(UserCenterFragment.newInstance());
        //设置ViewPager的Adapter
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mainFragmentPagerAdapter.setFragmentList(fragmentList);
        mCustomViewPager.setAdapter(mainFragmentPagerAdapter);
        //设置监听
        mCustomViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
                switch (position) {
                    case 0:
                        mTvToolBarTitle.setText(R.string.main_bottom_navigation_home_item);
                        break;
                    case 1:
                        mTvToolBarTitle.setText(R.string.main_bottom_navigation_dynamic_item);
                        break;
                    case 2:
                        mTvToolBarTitle.setText(R.string.main_bottom_navigation_message_item);
                        break;
                    case 3:
                        mTvToolBarTitle.setText(R.string.main_bottom_navigation_user_center_item);
                        break;
                    default:
                        mTvToolBarTitle.setText(R.string.app_real_name);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    /**
     * 初始化BottomNavigationBar,设置Bottom Navigation Bar 的 Mode
     * setMode() 内的参数有三种模式类型：
     * MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
     * MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
     * MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
     * etBackgroundStyle() 内的参数有三种样式
     * BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
     * 如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
     * BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
     * BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
     */
    private void initBottomNavigationBar() {
        mBottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.btn_main_bottom_navigation_home, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.btn_main_bottom_navigation_news, "动态"))
                .addItem(new BottomNavigationItem(R.drawable.btn_main_bottom_navigation_message, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.btn_main_bottom_navigation_user, "我的"))
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        mCustomViewPager.setCurrentItem(position);
                    }

                    @Override
                    public void onTabUnselected(int position) {}

                    @Override
                    public void onTabReselected(int position) {}
                });
    }
}
