package com.teach.news10.design;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teach.news10.R;

/**
 * Created by 任小龙 on 2019/4/8.
 */

public class CommonTitle extends RelativeLayout {

    private final String mTitle;
    private final boolean mCanBack;
    private final int mMoreImg,mBackImage;
    private final String mMoreText;
    public ImageView mMoreImage;
    public TextView mMoreText1;
    public TextView mTvTitle;
    public ImageView mBackBtn;
    private RelativeLayout mTitleRl;
    private final int mBackColor;

    public CommonTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CommonTitle, 0, 0);
        try {
            mTitle = ta.getString(R.styleable.CommonTitle_titleText);
            mCanBack = ta.getBoolean(R.styleable.CommonTitle_canBack, true);
            mMoreImg = ta.getResourceId(R.styleable.CommonTitle_moreImg, 0);
            mBackImage = ta.getResourceId(R.styleable.CommonTitle_backImage, 0);
            mMoreText = ta.getString(R.styleable.CommonTitle_moreText);
            mBackColor = ta.getColor(R.styleable.CommonTitle_backColor, ContextCompat.getColor(context, R.color.app_theme_color));
            setUpView(context);
        } finally {
            ta.recycle();
        }
    }

    public void setTitleContent(String content){
        mTvTitle.setText(content);
    }

    private void setUpView(Context context){
        mTitleRl = findViewById(R.id.title_rl);
        mTitleRl.setBackgroundColor(mBackColor);
        mTvTitle = findViewById(R.id.title_content);
        mTvTitle.setText(mTitle);
        mBackBtn = findViewById(R.id.back_image);
        mBackBtn.setImageResource(mBackImage);
        mBackBtn.setVisibility(mCanBack ? VISIBLE : INVISIBLE);
        if (mCanBack){
            mBackBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            });
        }
        mMoreImage = findViewById(R.id.img_more);
        mMoreImage.setImageResource(mMoreImg);
        mMoreText1 = findViewById(R.id.txt_more);
        mMoreText1.setText(mMoreText);
    }

    public void setTitle(String title){
        mTvTitle.setText(title);
    }
}
