package com.teach.news10.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.adapter.MatchProgressAdapter;
import com.teach.news10.bean.MatchProgressions;
import com.teach.news10.model.DataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.design.WheelViewPopup;

/**
 * Created by 任小龙 on 2019/5/13.
 */
public class MatchProgressFragment extends BaseMvpFragment<DataModel> {

    private static final String TAB_NAME = "tab_name";
    private static final String TAB_URL = "tab_url";
    @BindView(R.id.recyclerView_used)
    RecyclerView recyclerViewUsed;
    @BindView(R.id.next_day_text)
    TextView mNextText;
    @BindView(R.id.pre_day_text)
    TextView mPreText;
    @BindView(R.id.pre_day_used)
    RelativeLayout mPreRl;
    @BindView(R.id.next_day_used)
    LinearLayout mNextLL;
    @BindView(R.id.center_text)
    TextView mCenterText;
    private String tabName;
    private String tabUrl;
    private List<MatchProgressions.ContentBean.RoundsBean> mRounds;
    private List<MatchProgressions.ContentBean.MatchesBean> matches = new ArrayList<>();
    private MatchProgressAdapter mAdapter;
    private int mCurrentPos;
    private WheelViewPopup mWheelViewPopup;
    private List<String> mWheelData = new ArrayList<>();

    public static MatchProgressFragment newInstance(String param1, String param2) {
        MatchProgressFragment fragment = new MatchProgressFragment();
        Bundle args = new Bundle();
        args.putString(TAB_NAME, param1);
        args.putString(TAB_URL, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tabName = getArguments().getString(TAB_NAME);
            tabUrl = getArguments().getString(TAB_URL);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.match_progress_layout;
    }

    @Override
    public void initView() {
        initRecycleView(recyclerViewUsed, null);
        mAdapter = new MatchProgressAdapter(getContext(), matches);
        recyclerViewUsed.setAdapter(mAdapter);
        mWheelViewPopup = new WheelViewPopup(getActivity());
        mWheelViewPopup.mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelViewPopup.dismiss();
                mCurrentPos = mWheelViewPopup.getSelectedPos();
                getMatchWithDay();
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.MATCH_PROGRESS_INFO, tabUrl);
    }

    @Override
    public DataModel getModel() {
        return new DataModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        hideLoadingDialog();
        MatchProgressions matchProgressions = (MatchProgressions) t[0];
        List<MatchProgressions.ContentBean.MatchesBean> matches = matchProgressions.getContent().getMatches();
        if (!TextUtils.isEmpty(matchProgressions.getContent().getPrev())) {
            if (mPreRl.getVisibility() != View.VISIBLE) mPreRl.setVisibility(View.VISIBLE);
            mPreText.setText(matchProgressions.getContent().getPrev());
        } else mPreRl.setVisibility(View.INVISIBLE);
        if (!TextUtils.isEmpty(matchProgressions.getContent().getNext())) {
            mNextText.setText(matchProgressions.getContent().getNext());
            if (mNextLL.getVisibility() != View.VISIBLE)
                mNextLL.setVisibility(View.VISIBLE);
        } else mNextLL.setVisibility(View.INVISIBLE);
        if (this.matches.size() != 0) this.matches.clear();
        this.matches.addAll(matches);
        mAdapter.notifyDataSetChanged();
        switch (whichApi) {
            case ApiConfig.MATCH_PROGRESS_INFO:
                mRounds = matchProgressions.getContent().getRounds();
                mCenterText.setText(mRounds.size() > 1 ? mRounds.get(mRounds.size() - 1).getName() : matches.get(0).getRound_name());
                mCurrentPos = mRounds.size() > 1 ? mRounds.size() - 1 : 0;
                initPopData();
                break;
            case ApiConfig.GET_MATCH_WITH_DAY:
                mCenterText.setText(mRounds.get(mCurrentPos).getName());
                break;
        }
    }

    private void initPopData() {
        if (mWheelData.size() != 0) mWheelData.clear();
        for (int i = 0; i < mRounds.size(); i++) {
            mWheelData.add(mRounds.get(i).getName());
        }
        mWheelViewPopup.setWheelData(mWheelData);
        mWheelViewPopup.setDefaultSelected(mCurrentPos);
    }

    @OnClick({R.id.pre_day_used, R.id.next_day_used, R.id.center_used})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pre_day_used:
                mCurrentPos--;
                if (mCurrentPos < 0) mCurrentPos = 0;
                if (mCurrentPos == 0) mPreRl.setVisibility(View.INVISIBLE);
                if (mCurrentPos < mRounds.size() - 1 && mNextLL.getVisibility() == View.INVISIBLE)
                    mNextLL.setVisibility(View.VISIBLE);
                getMatchWithDay();
                mWheelViewPopup.setDefaultSelected(mCurrentPos);
                break;
            case R.id.next_day_used:
                mCurrentPos++;
                if (mCurrentPos >= mRounds.size()) mCurrentPos = mRounds.size() - 1;
                if (mCurrentPos == mRounds.size() - 1) mNextLL.setVisibility(View.INVISIBLE);
                if (mCurrentPos == 1 && mPreRl.getVisibility() == View.INVISIBLE)
                    mPreRl.setVisibility(View.VISIBLE);
                getMatchWithDay();
                mWheelViewPopup.setDefaultSelected(mCurrentPos);
                break;
            case R.id.center_used:
                mWheelViewPopup.showPopupWindow();
                break;
        }
    }

    private void getMatchWithDay() {
        showLoadingDialog();
        mPresenter.getData(ApiConfig.GET_MATCH_WITH_DAY, mRounds.get(mCurrentPos).getUrl());
    }
}
