package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public class RankItemEastAdapter extends RecyclerView.Adapter<RankItemEastAdapter.FirstHolder> {

    private Context mContext;
    private List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> data;
    private int fatherPostion;

    public RankItemEastAdapter(Context pContext, List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> pData, int pFatherPostion) {
        mContext = pContext;
        data = pData;
        fatherPostion = pFatherPostion;
    }

    @NonNull
    @Override
    public RankItemEastAdapter.FirstHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FirstHolder(View.inflate(mContext, R.layout.rank_pk_east_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RankItemEastAdapter.FirstHolder holder, int position) {
        Glide.with(mContext).load(data.get(position).getTeam_A_logo()).into(holder.leftTeamLogo);
        Glide.with(mContext).load(data.get(position).getTeam_B_logo()).into(holder.rightTeamLogo);
        holder.rankNum.setText(data.get(position).getTeam_A_rank() == 0 ? "":data.get(position).getTeam_A_rank()+"");
        holder.rankNumRight.setText(data.get(position).getTeam_B_rank() == 0 ? "":data.get(position).getTeam_B_rank()+"");
        if (TextUtils.isEmpty(data.get(position).getTeam_A_name())){
            holder.leftTeamName.setText("待定");
            holder.leftTeamName.setBackgroundColor(ContextCompat.getColor(mContext,R.color.grey_second));
        } else {
            holder.leftTeamName.setText(data.get(position).getTeam_A_name());
            holder.leftTeamName.setBackgroundColor(ContextCompat.getColor(mContext,R.color.blue_theme));
        }
        if (TextUtils.isEmpty(data.get(position).getTeam_B_name())){
            holder.rightTeamName.setText("待定");
            holder.rightTeamName.setBackgroundColor(ContextCompat.getColor(mContext,R.color.grey_second));
        } else {
            holder.rightTeamName.setText(data.get(position).getTeam_B_name());
            holder.rightTeamName.setBackgroundColor(ContextCompat.getColor(mContext,R.color.blue_theme));
        }
        if (data.get(position).getFs_A() == 0 && data.get(position).getFs_B() == 0){
            if (fatherPostion == 4){
                holder.vsText.setVisibility(View.VISIBLE);
            } else {
                holder.vsText.setVisibility(View.GONE);
            }
            holder.scoreText.setVisibility(View.GONE);
        } else {
            holder.scoreText.setVisibility(View.VISIBLE);
            holder.scoreText.setText(data.get(position).getFs_A()+" : "+data.get(position).getFs_B());
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class FirstHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.left_team_logo)
        ImageView leftTeamLogo;
        @BindView(R.id.rank_num)
        TextView rankNum;
        @BindView(R.id.left_team_name)
        TextView leftTeamName;
        @BindView(R.id.right_team_logo)
        ImageView rightTeamLogo;
        @BindView(R.id.rank_num_right)
        TextView rankNumRight;
        @BindView(R.id.right_team_name)
        TextView rightTeamName;
        @BindView(R.id.score_text)
        TextView scoreText;
        @BindView(R.id.vs_text)
        TextView vsText;
        public FirstHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
