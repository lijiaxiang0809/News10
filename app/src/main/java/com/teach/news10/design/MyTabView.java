package com.teach.news10.design;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teach.news10.Frame.Application10;
import com.teach.news10.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 任小龙 on 2019/4/30.
 */
public class MyTabView extends LinearLayout {

    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.tab1)
    RelativeLayout tab1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.tab2)
    RelativeLayout tab2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.tab3)
    RelativeLayout tab3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.tab4)
    RelativeLayout tab4;
    private Unbinder mBind;
    private final int mColor;
    private final int mTransparent;
    private final int mBlackColor;
    private final int mWhite;

    public MyTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mColor = ContextCompat.getColor(Application10.getAppContext(), R.color.app_theme_color);
        mTransparent = ContextCompat.getColor(Application10.getAppContext(), R.color.transparent);
        mWhite = ContextCompat.getColor(Application10.getAppContext(), R.color.white);
        mBlackColor = ContextCompat.getColor(Application10.getAppContext(), R.color.black_theme);
        initView();
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.my_tab_view, this, true);
        mBind = ButterKnife.bind(this, inflate);
        tab1.setBackground(ContextCompat.getDrawable(Application10.getAppContext(),R.drawable.top_tab_shape_left));
        text1.setTextColor(mWhite);
    }

    public void setTab(List<String> pStringList){
        if (pStringList == null)return;
        switch (pStringList.size()){
            case 4:
                text1.setText(pStringList.get(0));
                text2.setText(pStringList.get(1));
                text3.setText(pStringList.get(2));
                text4.setText(pStringList.get(3));
                break;
            case 3:
                tab2.setVisibility(GONE);
                text1.setText(pStringList.get(0));
                text3.setText(pStringList.get(1));
                text4.setText(pStringList.get(2));
                break;
            case 2:
                tab3.setVisibility(GONE);
                tab2.setVisibility(GONE);
                text1.setText(pStringList.get(0));
                text4.setText(pStringList.get(1));
                break;
        }
    }

    private void setUp(){
        tab1.setBackgroundColor(mTransparent);
        tab4.setBackgroundColor(mTransparent);
        tab3.setBackgroundColor(mTransparent);
        text1.setTextColor(mBlackColor);
        text4.setTextColor(mBlackColor);
        text2.setTextColor(mBlackColor);
        tab2.setBackgroundColor(mTransparent);
        text3.setTextColor(mBlackColor);
    }

    public void resetView(){
        setUp();
        tab1.setBackground(ContextCompat.getDrawable(Application10.getAppContext(),R.drawable.top_tab_shape_left));
        text1.setTextColor(mWhite);
    }

    @OnClick({R.id.tab1, R.id.tab2, R.id.tab3, R.id.tab4})
    public void onViewClicked(View view) {
        setUp();
        switch (view.getId()) {
            case R.id.tab1:
                mBottomClick.onFirstClick();
                tab1.setBackground(ContextCompat.getDrawable(Application10.getAppContext(),R.drawable.top_tab_shape_left));
                text1.setTextColor(mWhite);
                break;
            case R.id.tab2:
                tab2.setBackgroundColor(mColor);
                text2.setTextColor(mWhite);
                mBottomClick.onSecondClick();
                break;
            case R.id.tab3:
                tab3.setBackgroundColor(mColor);
                text3.setTextColor(mWhite);
                mBottomClick.onThirdClick();
                break;
            case R.id.tab4:
                tab4.setBackground(ContextCompat.getDrawable(Application10.getAppContext(),R.drawable.top_tab_shape_right));
                text4.setTextColor(mWhite);
                mBottomClick.onFourthClick();
                break;
        }
    }
    public OnTabClick mBottomClick;

    public void setOnTabClickListener(OnTabClick pOnBottomClick) {
        this.mBottomClick = pOnBottomClick;
    }

    public interface OnTabClick {
        void onFirstClick();

        void onSecondClick();

        void onThirdClick();

        void onFourthClick();
    }
}
