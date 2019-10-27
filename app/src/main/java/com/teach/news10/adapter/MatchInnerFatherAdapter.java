package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teach.news10.R;
import com.teach.news10.bean.SelfMatchInfo;

import java.util.List;


/**
 * Created by 任小龙 on 2019/5/12.
 */
public class MatchInnerFatherAdapter extends RecyclerView.Adapter<MatchInnerFatherAdapter.ViewHolder> {
    List<SelfMatchInfo> mListData;
    Context mContext;

    public MatchInnerFatherAdapter(List<SelfMatchInfo> pList, Context pContext) {
        mListData = pList;
        mContext = pContext;
    }

    @NonNull
    @Override
    public MatchInnerFatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.hot_father_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MatchInnerFatherAdapter.ViewHolder holder, int position) {
        SelfMatchInfo info = mListData.get(position);
        holder.mTextView.setText(info.day);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        holder.mRecyclerView.setLayoutManager(manager);
        MatchInnerChildAdapter adapter = new MatchInnerChildAdapter(info.data,mContext);
        holder.mRecyclerView.setAdapter(adapter);
        holder.mRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return mListData != null ? mListData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        RecyclerView mRecyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.recyclerView);
            mTextView = itemView.findViewById(R.id.title_days);
        }
    }
}
