package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teach.news10.R;
import com.teach.news10.bean.HotInfo;

import java.util.List;

/**
 * Created by 任小龙 on 2019/4/26.
 */
public class HotFatherAdapter extends RecyclerView.Adapter<HotFatherAdapter.ViewHolder> {
    private List<HotInfo.ContentsBean> mContentsBeanList;
    private Context mContext;

    public HotFatherAdapter(List<HotInfo.ContentsBean> pContentsBeanList, Context pContext) {
        mContentsBeanList = pContentsBeanList;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.hot_father_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextView.setText(mContentsBeanList.get(position).getDay());
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        holder.mRecyclerView.setLayoutManager(manager);
        HotAdapter adapter = new HotAdapter(mContentsBeanList.get(position).getArticles(),mContext);
        holder.mRecyclerView.setAdapter(adapter);
        holder.mRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return mContentsBeanList.size();
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
