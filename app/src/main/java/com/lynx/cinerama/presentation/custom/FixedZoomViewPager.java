package com.lynx.cinerama.presentation.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Lynx on 11/2/2016.
 */

public class FixedZoomViewPager extends ViewPager {

    public FixedZoomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedZoomViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
