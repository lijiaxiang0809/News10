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

import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

/**
 * Created by 任小龙 on 2019/5/3.
 */
public class RankItemWestAdapter extends RecyclerView.Adapter<RankItemWestAdapter.FirstHolder> {
    private Context mContext;
    private List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> data;
    private int fatherPostion;

    public RankItemWestAdapter(Context pContext, List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> pData, int pFatherPostion) {
        mContext = pContext;
        data = pData;
        fatherPostion = pFatherPostion;
    }

    @NonNull
    @Override
    public RankItemWestAdapter.FirstHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FirstHolder(View.inflate(mContext, R.layout.rank_pk_west_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RankItemWestAdapter.FirstHolder holder, int position) {
        controlCenterLine(holder);
        Glide.with(mContext).load(data.get(position).getTeam_A_logo()).into(holder.leftTeamLogo);
        Glide.with(mContext).load(data.get(position).getTeam_B_logo()).into(holder.rightTeamLogo);
        holder.rankNumLeft.setText(data.get(position).getTeam_A_rank() == 0 ? "" : data.get(position).getTeam_A_rank()+"");
        holder.rankNumRight.setText(data.get(position).getTeam_B_rank() == 0 ? "" : data.get(position).getTeam_B_rank()+"");
        if (TextUtils.isEmpty(data.get(position).getTeam_A_name())){
            holder.leftTeamName.setText("待定");
            holder.leftTeamName.setBackgroundColor(ContextCompat.getColor(mContext,R.color.grey_second));
        } else {
            holder.leftTeamName.setText(data.get(position).getTeam_A_name());
            holder.leftTeamName.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_theme_color));
        }
        if (TextUtils.isEmpty(data.get(position).getTeam_B_name())){
            holder.rightTeamName.setText("待定");
            holder.rightTeamName.setBackgroundColor(ContextCompat.getColor(mContext,R.color.grey_second));
        } else {
            holder.rightTeamName.setText(data.get(position).getTeam_B_name());
            holder.rightTeamName.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_theme_color));
        }
        if (data.get(position).getFs_A() == 0 && data.get(position).getFs_B() == 0){
            if (fatherPostion == 2 || fatherPostion == 3){
                holder.vsText.setVisibility(View.VISIBLE);
                if (fatherPostion == 3){
                    holder.vsText.setTextSize(dp2px(12));
                }
            } else {
                holder.vsText.setVisibility(View.GONE);
            }
            holder.resultScore.setVisibility(View.GONE);
        } else {
            holder.resultScore.setVisibility(View.VISIBLE);
            holder.resultScore.setText(data.get(position).getFs_A()+" : "+data.get(position).getFs_B());
        }
    }

    private void controlCenterLine(FirstHolder holder) {
        if (fatherPostion != 3) {
            holder.leftVerticalLine.setVisibility(View.VISIBLE);
            holder.rightVerticalLine.setVisibility(View.VISIBLE);
            holder.rightBottomLine.setVisibility(View.VISIBLE);
            holder.bottomVerticalLine.setVisibility(View.VISIBLE);
        } else {
            holder.leftVerticalLine.setVisibility(View.GONE);
            holder.rightVerticalLine.setVisibility(View.GONE);
            holder.rightBottomLine.setVisibility(View.GONE);
            holder.bottomVerticalLine.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class FirstHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.result_score)
        TextView resultScore;
        @BindView(R.id.left_team_logo)
        ImageView leftTeamLogo;
        @BindView(R.id.rank_num_left)
        TextView rankNumLeft;
        @BindView(R.id.left_team_name)
        TextView leftTeamName;
        @BindView(R.id.left_vertical_line)
        View leftVerticalLine;
        @BindView(R.id.right_team_logo)
        ImageView rightTeamLogo;
        @BindView(R.id.rank_num_right)
        TextView rankNumRight;
        @BindView(R.id.right_team_name)
        TextView rightTeamName;
        @BindView(R.id.right_vertical_line)
        View rightVerticalLine;
        @BindView(R.id.right_bottom_line)
        View rightBottomLine;
        @BindView(R.id.vs_text)
        TextView vsText;
        @BindView(R.id.bottom_vertical_line)
        View bottomVerticalLine;
        @BindView(R.id.father_rl)
        RelativeLayout fatherRl;

        public FirstHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
