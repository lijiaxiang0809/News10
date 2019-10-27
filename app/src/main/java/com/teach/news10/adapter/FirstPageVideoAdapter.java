package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.NormalNewsInfo;
import com.teach.news10.design.RoundOrCircleImage;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;

import java.util.List;

/**
 * Created by 任小龙 on 2019/5/12.
 */
public class FirstPageVideoAdapter extends RecyclerView.Adapter<FirstPageVideoAdapter.ViewHolder> {
    private List<NormalNewsInfo.ArticlesBean> mList;
    private Context mContext;

    public FirstPageVideoAdapter(List<NormalNewsInfo.ArticlesBean> pList, Context pContext) {
        mList = pList;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.first_page_video_adapter_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideEngine.loadImage(holder.mImage,mList.get(position).getThumb());
        holder.mTitle.setText(mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundOrCircleImage mImage;
        TextView mTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.round_image);
            mTitle = itemView.findViewById(R.id.title);
        }
    }
}
