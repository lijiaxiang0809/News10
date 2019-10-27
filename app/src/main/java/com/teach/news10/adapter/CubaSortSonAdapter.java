package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.DataCubaInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/7/16.
 */
public class CubaSortSonAdapter extends RecyclerView.Adapter {
    DataCubaInfo.ContentBeanX.RoundsBean.ContentBean.DataBeanX dataBeanX;
    Context mContext;
    private int TITLE = 0;
    private int NORMAL = 1;
    private final List<DataCubaInfo.ContentBeanX.RoundsBean.ContentBean.DataBeanX.DataBean> mData;

    public CubaSortSonAdapter(DataCubaInfo.ContentBeanX.RoundsBean.ContentBean.DataBeanX pDataBeanX, Context pContext) {
        dataBeanX = pDataBeanX;
        mData = dataBeanX.getData();
        mContext = pContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == NORMAL)
            return new NormalHolder(LayoutInflater.from(mContext).inflate(R.layout.team_sort_cuba_son_layout, parent, false));
        else
            return new TitleHolder(LayoutInflater.from(mContext).inflate(R.layout.team_sort_cuba_son_title_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position > 0) {
            NormalHolder normalHolder = (NormalHolder) holder;
            DataCubaInfo.ContentBeanX.RoundsBean.ContentBean.DataBeanX.DataBean bean = mData.get(position - 1);
            normalHolder.num.setText(bean.getRank());
            Glide.with(mContext).load(bean.getTeam_logo()).into(normalHolder.teamLogo);
            normalHolder.teamName.setText(bean.getTeam_name());
            normalHolder.winAndFail.setText(bean.getWin_lost());
            normalHolder.winPercent.setText(bean.getWin_rate());
            normalHolder.winScore.setText(bean.getGb());
            normalHolder.allScore.setText(bean.getStreak());
        } else {
            TitleHolder titleHolder = (TitleHolder) holder;
            titleHolder.group.setText(dataBeanX.getName());
            List<String> header = dataBeanX.getHeader();
            titleHolder.winAndFail.setText(header.get(0));
            titleHolder.winPercent.setText(header.get(1));
            titleHolder.winScore.setText(header.get(2));
            titleHolder.allScore.setText(header.get(3));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TITLE : NORMAL;
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    public class TitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.group)
        TextView group;
        @BindView(R.id.win_and_fail)
        TextView winAndFail;
        @BindView(R.id.win_percent)
        TextView winPercent;
        @BindView(R.id.win_score)
        TextView winScore;
        @BindView(R.id.all_score)
        TextView allScore;
        @BindView(R.id.team_bg)
        RelativeLayout teamBg;

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class NormalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.team_num)
        TextView num;
        @BindView(R.id.team_logo)
        ImageView teamLogo;
        @BindView(R.id.team_name)
        TextView teamName;
        @BindView(R.id.win_and_fail)
        TextView winAndFail;
        @BindView(R.id.win_percent)
        TextView winPercent;
        @BindView(R.id.win_score)
        TextView winScore;
        @BindView(R.id.all_score)
        TextView allScore;
        @BindView(R.id.team_bg)
        RelativeLayout teamBg;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
