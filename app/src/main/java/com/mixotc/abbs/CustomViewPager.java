package com.mixotc.abbs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/02
 *    classnote : 重写的ViewPager,设置不可滑动
 */
public class CustomViewPager extends ViewPager {

    private boolean mIsSlidingEnable = true ;

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.mIsSlidingEnable && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.mIsSlidingEnable && super.onTouchEvent(ev);
    }

    public void setmIsSlidingEnable(boolean mIsSlidingEnable) {
        this.mIsSlidingEnable = mIsSlidingEnable;
    }
}

