package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.MatchInnerInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/5/12.
 */
class MatchInnerChildAdapter extends RecyclerView.Adapter<MatchInnerChildAdapter.ViewHolder> {
    private final List<MatchInnerInfo.ListBean> list;
    private final Context context;

    public MatchInnerChildAdapter(List<MatchInnerInfo.ListBean> pList, Context pContext) {
        this.list = pList;
        this.context = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.match_inner_child_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MatchInnerInfo.ListBean bean = list.get(position);
        holder.centerFirstText.setText(bean.getRound_name());
        Glide.with(context).load(bean.getTeam_A_logo()).into(holder.leftTeamLogo);
        Glide.with(context).load(bean.getTeam_B_logo()).into(holder.rightTeamLogo);
        holder.leftTeamName.setText(bean.getTeam_A_name()+(!TextUtils.isEmpty(bean.getPlayoff_fs_A()) ? "("+bean.getPlayoff_fs_A()+")":""));
        holder.rightTeamName.setText(bean.getTeam_B_name()+(!TextUtils.isEmpty(bean.getPlayoff_fs_B()) ? "("+bean.getPlayoff_fs_B()+")":""));
        if (!TextUtils.isEmpty(bean.getFs_A()) && !bean.getFs_A().equals("0") && !bean.getFs_B().equals("0")){
            holder.scoreResult.setVisibility(View.VISIBLE);
            holder.notifyImage.setVisibility(View.GONE);
            holder.scoreResult.setText(bean.getFs_A()+" - "+bean.getFs_B());
        } else {
            holder.scoreResult.setVisibility(View.GONE);
            holder.notifyImage.setVisibility(View.VISIBLE);
        }
        holder.notifyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (!TextUtils.isEmpty(bean.getTVList())){
            holder.animVideo.setVisibility(View.VISIBLE);
            holder.matchVideo.setVisibility(View.GONE);
            holder.animVideo.setText(bean.getTVList());
        } else {
            holder.animVideo.setVisibility(View.GONE);
            holder.matchVideo.setVisibility(View.VISIBLE);
        }
        if (bean.mTeamerInfo != null && bean.mTeamerInfo.sts_A != null){
            if (!TextUtils.isEmpty(bean.mTeamerInfo.sts_A.logo))Glide.with(context).load(bean.mTeamerInfo.sts_A.logo).into(holder.leftTeamerImage);
            if (!TextUtils.isEmpty(bean.mTeamerInfo.sts_B.logo))Glide.with(context).load(bean.mTeamerInfo.sts_B.logo).into(holder.rightTeamerImage);
            holder.teamerName.setText(bean.mTeamerInfo.sts_A.name);
            holder.teamerNameRight.setText(bean.mTeamerInfo.sts_B.name);
            holder.teamerScore.setText(bean.mTeamerInfo.sts_A.tips);
            holder.teamerScoreRight.setText(bean.mTeamerInfo.sts_B.tips);
        } else {
           holder.bottom_rl.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bottom_rl)
        RelativeLayout bottom_rl;
        @BindView(R.id.teamer_ll_right)
        LinearLayout teamerLlRight;
        @BindView(R.id.teamer_ll_left)
        LinearLayout teamerLlLeft;
        @BindView(R.id.left_team_logo)
        ImageView leftTeamLogo;
        @BindView(R.id.left_team_name)
        TextView leftTeamName;
        @BindView(R.id.right_team_logo)
        ImageView rightTeamLogo;
        @BindView(R.id.right_team_name)
        TextView rightTeamName;
        @BindView(R.id.center_first_text)
        TextView centerFirstText;
        @BindView(R.id.score_result)
        TextView scoreResult;
        @BindView(R.id.notify_image)
        ImageView notifyImage;
        @BindView(R.id.anim_video)
        TextView animVideo;
        @BindView(R.id.match_video)
        LinearLayout matchVideo;
        @BindView(R.id.left_teamer_image)
        ImageView leftTeamerImage;
        @BindView(R.id.teamer_name)
        TextView teamerName;
        @BindView(R.id.teamer_score)
        TextView teamerScore;
        @BindView(R.id.right_teamer_image)
        ImageView rightTeamerImage;
        @BindView(R.id.teamer_name_right)
        TextView teamerNameRight;
        @BindView(R.id.teamer_score_right)
        TextView teamerScoreRight;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
