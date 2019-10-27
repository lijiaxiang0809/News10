package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.RecommondAndVideoInfo;

import java.util.List;

/**
 * Created by 任小龙 on 2019/5/7.
 */
public class RecommondMoreImageAdapter extends RecyclerView.Adapter<RecommondMoreImageAdapter.ViewHolder> {
    Context mContext;
    List<RecommondAndVideoInfo.DataBean.FeedsListBean.AttachmentsBean> attachments;

    public RecommondMoreImageAdapter(Context pContext, List<RecommondAndVideoInfo.DataBean.FeedsListBean.AttachmentsBean> pAttachments) {
        mContext = pContext;
        attachments = pAttachments;
    }

    @NonNull
    @Override
    public RecommondMoreImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.more_image_adapter,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommondMoreImageAdapter.ViewHolder holder, int position) {
        Glide.with(mContext).load(attachments.get(position).getUrl()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return attachments != null ? attachments.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }
}
