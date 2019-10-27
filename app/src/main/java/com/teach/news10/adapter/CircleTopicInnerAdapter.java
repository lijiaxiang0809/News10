package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teach.news10.R;
import com.teach.news10.bean.CircleTopicInfo;

import java.util.List;


/**
 * Created by 任小龙 on 2019/5/11.
 */
public class CircleTopicInnerAdapter extends RecyclerView.Adapter<CircleTopicInnerAdapter.ViewHolder> {
    List<CircleTopicInfo.DataBean.TopicListBean.ListBean> list;
    Context mContext;
    int rowNum;

    public CircleTopicInnerAdapter(List<CircleTopicInfo.DataBean.TopicListBean.ListBean> pList, Context pContext, int rowNum) {
        list = pList;
        mContext = pContext;
        this.rowNum = rowNum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.circle_topic_inner_adapter_layout, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (rowNum == 2) {
            holder.mContentNoBg.setVisibility(View.VISIBLE);
            holder.mContent.setVisibility(View.GONE);
            holder.mContentNoBg.setText(list.get(position).getContent());
        } else {
            holder.mContent.setVisibility(View.VISIBLE);
            holder.mContentNoBg.setVisibility(View.GONE);
            holder.mContent.setText(list.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mContent,mContentNoBg;

        public ViewHolder(View itemView) {
            super(itemView);
            mContent = itemView.findViewById(R.id.content);
            mContentNoBg = itemView.findViewById(R.id.content_nobg);
        }
    }
}
