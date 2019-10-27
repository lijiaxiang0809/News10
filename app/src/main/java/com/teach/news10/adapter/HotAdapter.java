package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.HotInfo;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 任小龙 on 2019/4/26.
 */
public class HotAdapter extends RecyclerView.Adapter {
    List<HotInfo.ContentsBean.ArticlesBean> mList;
    Context mContext;
    private int NORMAL_TYPE = 1;
    private int VIDEO_TYPE = 2;

    public HotAdapter(List<HotInfo.ContentsBean.ArticlesBean> pList, Context pContext) {
        mList = pList;
        mContext = pContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewType == NORMAL_TYPE ? new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.hot_adapter_layout, parent, false))
                : new VideoHolder(LayoutInflater.from(mContext).inflate(R.layout.hot_video_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == NORMAL_TYPE) {
            ViewHolder viewHolder = (ViewHolder) holder;
            Glide.with(mContext).load(mList.get(position).getThumb()).into(viewHolder.leftImage);
            viewHolder.title.setText(mList.get(position).getTitle());
            viewHolder.commentsTotal.setText(mList.get(position).getComments_total() + "评论");
            if (!TextUtils.isEmpty(mList.get(position).label)) {
                viewHolder.deep.setText(mList.get(position).label);
                viewHolder.deep.setVisibility(View.VISIBLE);
            } else viewHolder.deep.setVisibility(View.GONE);
            viewHolder.top.setVisibility(mList.get(position).isTop() ? View.VISIBLE : View.GONE);
        } else {
            VideoHolder videoHolder = (VideoHolder) holder;
            videoHolder.title.setText(mList.get(position).getTitle());
            if (mList.get(position).video_info != null){
                videoHolder.videoPlayer.setUp(mList.get(position).video_info.video_src, JCVideoPlayer.SCREEN_LAYOUT_LIST);
                videoHolder.time.setText(mList.get(position).video_info.video_time);
            }
            videoHolder.videoPlayer.backButton.setVisibility(View.GONE);
            videoHolder.videoPlayer.tinyBackImageView.setVisibility(View.GONE);
            GlideEngine.loadImage(videoHolder.videoPlayer.thumbImageView,mList.get(position).getThumb());
            videoHolder.commentsTotal.setText(mList.get(position).getComments_total() + "评论");
            if (!TextUtils.isEmpty(mList.get(position).label)) {
                videoHolder.deep.setText(mList.get(position).label);
                videoHolder.deep.setVisibility(View.VISIBLE);
            } else videoHolder.deep.setVisibility(View.GONE);
            videoHolder.top.setVisibility(mList.get(position).isTop() ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return !mList.get(position).isIs_video() ? NORMAL_TYPE : VIDEO_TYPE;
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.left_image)
        ImageView leftImage;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.comments_total)
        TextView commentsTotal;
        @BindView(R.id.deep)
        TextView deep;
        @BindView(R.id.top)
        TextView top;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.video_player)
        JCVideoPlayerStandard videoPlayer;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.comments_total)
        TextView commentsTotal;
        @BindView(R.id.deep)
        TextView deep;
        @BindView(R.id.top)
        TextView top;

        public VideoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
