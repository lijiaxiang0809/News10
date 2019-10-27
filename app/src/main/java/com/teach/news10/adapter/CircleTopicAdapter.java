package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.CircleTopicInfo;
import com.teach.news10.design.RoundOrCircleImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/5/11.
 */
public class CircleTopicAdapter extends RecyclerView.Adapter<CircleTopicAdapter.ViewHolder> {
    List<CircleTopicInfo.DataBean.TopicListBean> mList;
    Context mContext;

    public CircleTopicAdapter(List<CircleTopicInfo.DataBean.TopicListBean> pList, Context pContext) {
        mList = pList;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.circle_topic_adapter_layout, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position).getLogo_url()).into(holder.logo);
        holder.title.setText(mList.get(position).getTitle());
        int rowNum = mList.get(position).getType().equals("line_two") ? 2 : 4;
        GridLayoutManager manager = new GridLayoutManager(mContext,rowNum);
        holder.contentRecyclerView.setLayoutManager(manager);
        List<CircleTopicInfo.DataBean.TopicListBean.ListBean> list = mList.get(position).getList();
        CircleTopicInnerAdapter adapter = new CircleTopicInnerAdapter(list,mContext,rowNum);
        holder.contentRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.logo)
        RoundOrCircleImage logo;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content_recyclerView)
        RecyclerView contentRecyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
