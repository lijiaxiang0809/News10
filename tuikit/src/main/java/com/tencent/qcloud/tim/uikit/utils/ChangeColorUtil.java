package com.tencent.qcloud.tim.uikit.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.tencent.qcloud.tim.uikit.R;

/**
 * Created by 任小龙 on 2019/7/29.
 */
public class ChangeColorUtil {
    private static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    public static Drawable changeDrawableColor(Context pContext, int drawableRes, int toColor) {
        return tintDrawable(ContextCompat.getDrawable(pContext, drawableRes), ColorStateList.valueOf(ContextCompat.getColor(pContext, toColor)));
    }
}
