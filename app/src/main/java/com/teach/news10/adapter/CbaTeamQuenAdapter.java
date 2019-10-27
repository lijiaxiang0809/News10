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
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/5/3.
 */
public class CbaTeamQuenAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> cbaList;
    private String fatherTab;
    private final int TITLE_TYPE = 1;
    private final int NORMAL_TYPE = 2;

    public CbaTeamQuenAdapter(Context pContext, String fatherTab, List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> cbaList) {
        mContext = pContext;
        this.cbaList = cbaList;
        this.fatherTab = fatherTab;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewType == NORMAL_TYPE ? new ViewHolder(View.inflate(mContext, R.layout.cba_team_quen_layout, null))
                : new TitleHolder(View.inflate(mContext, R.layout.cba_team_title_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == NORMAL_TYPE) {
            ViewHolder normalHolder = (ViewHolder) holder;
            RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean cbaData = cbaList.get(position - 1);
            normalHolder.num.setText(cbaData.rank);
            GlideEngine.loadImage(normalHolder.teamLogo,cbaData.team_logo);
            normalHolder.teamName.setText(cbaData.team_name);
            normalHolder.scoreResult.setText(cbaData.win_lost);
            normalHolder.scorePercent.setText(cbaData.win_rate);
            normalHolder.recentlyResult.setText(cbaData.streak);
        } else {
            TitleHolder titleHolder = (TitleHolder) holder;
            titleHolder.westOrEast.setText("球队");
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
        return cbaList.size() + 1;
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
        @BindView(R.id.recently_result_title)
        TextView recentlyResultTitle;

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
