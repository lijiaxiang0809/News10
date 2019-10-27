package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.RankInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/5/3.
 */
public class TeamQuenAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean.DataBean2> teamList;
    private List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> cbaList;
    private String teamPos;
    private String fatherTab;
    private final int TITLE_TYPE = 1;
    private final int NORMAL_TYPE = 2;

    public TeamQuenAdapter(Context pContext, List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean.DataBean2> pTeamList,String pTeamPos) {
        mContext = pContext;
        teamList = pTeamList;
        this.teamPos = pTeamPos;
    }

    public TeamQuenAdapter(Context pContext,String fatherTab, List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> cbaList) {
        mContext = pContext;
        this.cbaList = cbaList;
        this.fatherTab = fatherTab;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewType == NORMAL_TYPE ? new ViewHolder(View.inflate(mContext, R.layout.team_quen_layout, null))
                : new TitleHolder(View.inflate(mContext, R.layout.team_title_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == NORMAL_TYPE) {
            ViewHolder normalHolder = (ViewHolder) holder;
            if (!TextUtils.isEmpty(fatherTab) && fatherTab.equals("CBA")){
                RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean cbaData = cbaList.get(position -1);
                normalHolder.num.setText(cbaData.rank);
                Glide.with(mContext).load(cbaData.team_logo).into(normalHolder.teamLogo);
                normalHolder.teamName.setText(cbaData.team_name);
                normalHolder.scoreResult.setText(cbaData.win_lost);
                normalHolder.scorePercent.setText(cbaData.win_rate);
                normalHolder.scoreSpace.setVisibility(View.GONE);
                normalHolder.recentlyResult.setText(cbaData.streak);
            } else {
                RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean.DataBean2 dataBean2 = teamList.get(position-1);
                normalHolder.num.setText(dataBean2.rank);
                Glide.with(mContext).load(dataBean2.team_logo).into(normalHolder.teamLogo);
                normalHolder.teamName.setText(dataBean2.team_name);
                normalHolder.scoreResult.setText(dataBean2.win_lost);
                normalHolder.scorePercent.setText(dataBean2.win_rate);
                normalHolder.scoreSpace.setText(dataBean2.gb);
                normalHolder.recentlyResult.setText(dataBean2.streak);
                normalHolder.teamBg.setBackgroundColor(ContextCompat.getColor(mContext,teamPos.equals("西部球队") ? R.color.west_item_bg : R.color.east_item_bg));
            }
        } else {
            TitleHolder titleHolder = (TitleHolder) holder;
            if (!TextUtils.isEmpty(fatherTab) && fatherTab.equals("CBA")){
                titleHolder.westOrEast.setText("球队");
                titleHolder.winSpace.setVisibility(View.GONE);
            } else {
                titleHolder.westOrEast.setText(teamPos);
                titleHolder.winSpace.setText("胜差");
            }
            titleHolder.winAndFail.setText("胜-负");
            titleHolder.winPercent.setText("胜率");
            titleHolder.recentlyResultTitle.setText("近况");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TITLE_TYPE : NORMAL_TYPE;
    }

    @Override
    public int getItemCount() {
        return !TextUtils.isEmpty(fatherTab) && fatherTab.equals("CBA") ? cbaList.size() + 1 : teamList.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.team_bg)
        RelativeLayout teamBg;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.team_logo)
        ImageView teamLogo;
        @BindView(R.id.team_name)
        TextView teamName;
        @BindView(R.id.score_result)
        TextView scoreResult;
        @BindView(R.id.score_percent)
        TextView scorePercent;
        @BindView(R.id.score_space)
        TextView scoreSpace;
        @BindView(R.id.recently_result)
        TextView recentlyResult;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class TitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.west_or_east)
        TextView westOrEast;
        @BindView(R.id.win_and_fail)
        TextView winAndFail;
        @BindView(R.id.win_percent)
        TextView winPercent;
        @BindView(R.id.win_space)
        TextView winSpace;
        @BindView(R.id.recently_result_title)
        TextView recentlyResultTitle;

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
