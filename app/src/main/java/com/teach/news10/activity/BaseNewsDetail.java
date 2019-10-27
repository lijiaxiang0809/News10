package com.teach.news10.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.teach.news10.Frame.BaseMvpActivity;
import com.teach.news10.R;
import com.teach.news10.TestActivity;
import com.teach.news10.bean.NormalNewsInfo;
import com.teach.news10.design.CommonTitle;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.NormalConfig;

import butterknife.BindView;

/**
 * Created by 任小龙 on 2019/7/17.
 */
public class BaseNewsDetail extends BaseMvpActivity<HomeModel> {
    protected NormalNewsInfo.ArticlesBean mArticlesBean;
    @BindView(R.id.title)
    CommonTitle title;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.news_detail_edit_comment)
    TextView newsDetailEditComment;
    @BindView(R.id.iv_bottom_comment)
    ImageView ivBottomComment;
    @BindView(R.id.tv_bottom_comment)
    TextView tvBottomComment;
    @BindView(R.id.layout_bottom_comment)
    FrameLayout layoutBottomComment;
    @BindView(R.id.news_detail_fav)
    ImageView newsDetailFav;
    @BindView(R.id.news_detail_top_share)
    ImageView newsDetailTopShare;
    @BindView(R.id.news_detail_send_comment)
    Button newsDetailSendComment;
    @BindView(R.id.news_detail_top_share_relative)
    LinearLayout newsDetailTopShareRelative;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initView() {
        mArticlesBean = getIntent().getParcelableExtra(NormalConfig.NEWS_DETAIL);
        title.mMoreText1.setText(mArticlesBean.getComments_total() + "评论");
        title.mMoreText1.setBackground(ContextCompat.getDrawable(this,R.drawable.comment_bg));
        title.mMoreText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseNewsDetail.this, TestActivity.class));
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public HomeModel getModel() {
        return null;
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }
}
