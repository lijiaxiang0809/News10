package com.teach.news10.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by 任小龙 on 2019/7/26.
 */
public class ControlClickRel extends RelativeLayout {
    public ControlClickRel(Context context) {
        super(context);
    }

    public ControlClickRel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
