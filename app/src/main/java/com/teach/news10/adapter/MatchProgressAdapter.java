package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.MatchProgressions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/5/14.
 */
public class MatchProgressAdapter extends RecyclerView.Adapter<MatchProgressAdapter.ViewHolder> {
    private Context mContext;
    private List<MatchProgressions.ContentBean.MatchesBean> matches;

    public MatchProgressAdapter(Context pContext, List<MatchProgressions.ContentBean.MatchesBean> pMatches) {
        mContext = pContext;
        matches = pMatches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.match_progress_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MatchProgressions.ContentBean.MatchesBean bean = matches.get(position);
        holder.matchTime.setText(bean.getStart_play().substring(5,16));
        Glide.with(mContext).load(bean.getTeam_A_logo()).into(holder.leftTeamLogo);
        holder.leftTeamName.setText(bean.getTeam_A_name());
        Glide.with(mContext).load(bean.getTeam_B_logo()).into(holder.rightTeamLogo);
        holder.rightTeamName.setText(bean.getTeam_B_name());
        holder.mScore.setText(bean.getFs_A()+"  : "+bean.getFs_B());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.score)
        TextView mScore;
        @BindView(R.id.match_time)
        TextView matchTime;
        @BindView(R.id.left_team_name)
        TextView leftTeamName;
        @BindView(R.id.left_team_logo)
        ImageView leftTeamLogo;
        @BindView(R.id.right_team_logo)
        ImageView rightTeamLogo;
        @BindView(R.id.right_team_name)
        TextView rightTeamName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
