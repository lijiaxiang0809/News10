package com.teach.news10.design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.teach.news10.bean.SelfMatchInfo;

import java.util.List;


public class MatchInnerDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = MatchInnerDecoration.class.getSimpleName();
    private List<SelfMatchInfo> mListData;
    private Paint mPaint;
    private Rect mBounds;
    private static int COLOR_TITLE_BG = Color.parseColor("#eeeeee");
    private static int COLOR_TITLE_FONT = Color.parseColor("#aaaaaa");
    private static int mTitleFontSize;//title字体大小

    private int mHeaderViewCount = 0;
    private int mTitleHeight = 0;
    private int paddingLeft;

    public MatchInnerDecoration(Context context, List<SelfMatchInfo> pListData) {
        super();
        this.mListData = pListData;
        mPaint = new Paint();
        mBounds = new Rect();
        //dp转px
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        paddingLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics());
        mTitleFontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, context.getResources().getDisplayMetrics());
        mPaint.setTextSize(mTitleFontSize);
        mPaint.setAntiAlias(true);
    }

    public int getHeaderViewCount() {
        return mHeaderViewCount;
    }

    public MatchInnerDecoration setHeaderViewCount(int headerViewCount) {
        mHeaderViewCount = headerViewCount;
        return this;
    }

    @Override
    public void onDrawOver(Canvas c, final RecyclerView parent, RecyclerView.State state) {//最后调用 绘制在最上层
        int pos = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        pos -= getHeaderViewCount();
        if (mListData == null || mListData.isEmpty() || pos > mListData.size() - 1 || pos < 0) {
            return;
        }

        String tag = mListData.get(pos).day;
        View child = parent.findViewHolderForLayoutPosition(pos + getHeaderViewCount()).itemView;//出现一个奇怪的bug，有时候child为空，所以将 child = parent.getChildAt(i)。-》 parent.findViewHolderForLayoutPosition(pos).itemView

        boolean flag = false;//定义一个flag，Canvas是否位移过的标志
        if ((pos + 1) < mListData.size()) {//防止数组越界（一般情况不会出现）
            if (null != tag && !tag.equals(mListData.get(pos + 1).day)) {//当前第一个可见的Item的tag，不等于其后一个item的tag，说明悬浮的View要切换了
                if (child.getHeight() + child.getTop() < mTitleHeight) {//当第一个可见的item在屏幕中还剩的高度小于title区域的高度时，我们也该开始做悬浮Title的“交换动画”
                    c.save();//每次绘制前 保存当前Canvas状态，
                    flag = true;
                    c.translate(0, child.getHeight() + child.getTop() - mTitleHeight);//其实这里移动的是
                }
            }
        }
        mPaint.setColor(COLOR_TITLE_BG);
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + mTitleHeight, mPaint);
        mPaint.setColor(COLOR_TITLE_FONT);
        mPaint.getTextBounds(tag, 0, tag.length(), mBounds);
        c.drawText(tag, child.getPaddingLeft() + paddingLeft,
                parent.getPaddingTop() + mTitleHeight - (mTitleHeight / 2 - mBounds.height() / 2),
                mPaint);
        if (flag)
            c.restore();//恢复画布到之前保存的状态
    }

}
