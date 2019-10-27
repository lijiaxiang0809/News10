package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.RecommondAndVideoInfo;
import com.teach.news10.design.RoundOrCircleImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/5/7.
 */
public class RecomondTopBannerAdapter extends RecyclerView.Adapter<RecomondTopBannerAdapter.ViewHolder> {
    Context mContext;
    List<RecommondAndVideoInfo.DataBean.TopicBannerBean.TopicListBean> topicList;

    public RecomondTopBannerAdapter(Context pContext, List<RecommondAndVideoInfo.DataBean.TopicBannerBean.TopicListBean> pTopicList) {
        mContext = pContext;
        topicList = pTopicList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.recomond_top_banner_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(topicList.get(position).getBg_url()).into(holder.imageBg);
        holder.title.setText(topicList.get(position).getTitle());
        holder.commentNum.setText(topicList.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return topicList != null ? topicList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_bg)
        RoundOrCircleImage imageBg;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.comment_num)
        TextView commentNum;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
