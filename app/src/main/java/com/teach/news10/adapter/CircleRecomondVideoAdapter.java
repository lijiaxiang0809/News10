package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.RecommondAndVideoInfo;
import com.teach.news10.design.RoundImage;
import com.teach.news10.local_utils.ScreenUtil;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by 任小龙 on 2019/5/7.
 */
public class CircleRecomondVideoAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<RecommondAndVideoInfo.DataBean.FeedsListBean> mList;
    private String label;

    private final int BANNER = 1;
    private final int ONLY_IMAGE = 2;
    private final int ROW_IMAGE = 3;
    private final int VIDEO = 4;
    private final int mPxWidth;

    @Override
    public int getItemViewType(int position) {
        if (label.equals("视频"))return VIDEO;
        if (position == 0)
            return BANNER;
        else if (mList.get(position).getThread_type() != null &&mList.get(position).getThread_type().equals("video"))
            return VIDEO;
        else if (mList.get(position).getThread_type() != null &&mList.get(position).getThread_type().equals("image") && mList.get(position).getAttachments_total() == 1)
            return ONLY_IMAGE;
        else return ROW_IMAGE;
    }

    public CircleRecomondVideoAdapter(Context pContext, List<RecommondAndVideoInfo.DataBean.FeedsListBean> pList, String pLabel) {
        mContext = pContext;
        mList = pList;
        label = pLabel;
        mPxWidth = ScreenUtil.getPxWidth(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER)
            return new BannerHolder(View.inflate(mContext, R.layout.circle_banner_layout, null));
        if (viewType == ONLY_IMAGE)
            return new OnlyImageHolder(View.inflate(mContext, R.layout.circle_only_image_layout, null));
        if (viewType == ROW_IMAGE)
            return new RowImageHolder(View.inflate(mContext, R.layout.circle_row_image_layout, null));
        if (viewType == VIDEO)
            return new VideoHolder(View.inflate(mContext, R.layout.circle_video_layout, null));
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) setBannerData(holder, position);
        if (getItemViewType(position) == ONLY_IMAGE) setOnlyData(holder, position);
        if (getItemViewType(position) == ROW_IMAGE) setRowData(holder, position);
        if (getItemViewType(position) == VIDEO) setVideoData(holder, position);
    }

    private void setRowData(RecyclerView.ViewHolder pHolder, int pPosition) {
        RowImageHolder holder = (RowImageHolder) pHolder;
        commonContent(holder, pPosition);
        GridLayoutManager manager;
        if (mList.get(pPosition).getAttachments_total() > 2) {
            manager = new GridLayoutManager(mContext, 3);
        } else {
            manager = new GridLayoutManager(mContext, 2);
        }
        holder.recyclerView.setLayoutManager(manager);
        List<RecommondAndVideoInfo.DataBean.FeedsListBean.AttachmentsBean> attachments = mList.get(pPosition).getAttachments();
        RecommondMoreImageAdapter adapter = new RecommondMoreImageAdapter(mContext, attachments);
        holder.recyclerView.setAdapter(adapter);
    }

    private void setVideoData(RecyclerView.ViewHolder pHolder, int pPosition) {
        VideoHolder holder = (VideoHolder) pHolder;
        commonContent(holder, pPosition);
        if (mList.get(pPosition).getVideo_info() == null) return;
        int height = Integer.parseInt(mList.get(pPosition).getVideo_info().getHeight());
        int width = Integer.parseInt(mList.get(pPosition).getVideo_info().getWidth());
        int resultPercent = height * holder.videoPlayer.widthRatio / width;
        holder.videoPlayer.heightRatio = resultPercent < holder.videoPlayer.widthRatio ? resultPercent : holder.videoPlayer.widthRatio;
        holder.videoPlayer.backButton.setVisibility(View.GONE);
        holder.videoPlayer.tinyBackImageView.setVisibility(View.GONE);
        holder.videoPlayer.setUp(mList.get(pPosition).getVideo_info().getUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST);
        GlideEngine.loadImage(holder.videoPlayer.thumbImageView,mList.get(pPosition).getVideo_info().getThumb());
    }

    private void setOnlyData(RecyclerView.ViewHolder pHolder, int pPosition) {
        OnlyImageHolder holder = (OnlyImageHolder) pHolder;
        if (mList.get(pPosition).getAttachments() != null && mList.get(pPosition).getAttachments().size() != 0) {
            RecommondAndVideoInfo.DataBean.FeedsListBean.AttachmentsBean attachmentsBean = mList.get(pPosition).getAttachments().get(0);
            int imageWitdh = Integer.parseInt(attachmentsBean.getWidth());
            int imageHeight = Integer.parseInt(attachmentsBean.getHeight());
            if (imageWitdh >mPxWidth ){
                imageHeight = mPxWidth*imageHeight/imageWitdh;
                imageWitdh = mPxWidth;
            }
            if (imageHeight >= imageWitdh) {
                imageHeight = imageHeight * 2 / 3;
                imageWitdh = imageWitdh * 2 / 3;
            }
            GlideEngine.loadImageWithSize(mContext,imageWitdh,imageHeight,holder.onlyImage,attachmentsBean.getUrl());
        }
        commonContent(holder, pPosition);
    }

    private void setBannerData(RecyclerView.ViewHolder pHolder, int pPosition) {
        BannerHolder holder = (BannerHolder) pHolder;
        RecommondAndVideoInfo.DataBean.FeedsListBean feedsListBean = mList.get(pPosition);
        if (feedsListBean.topic_banner != null && feedsListBean.topic_banner.getTopic_list() != null) {
            RecommondAndVideoInfo.DataBean.TopicBannerBean topic_banner = feedsListBean.topic_banner;
            Glide.with(mContext).load(topic_banner.getIcon()).into(holder.logoImage);
            holder.bannerText.setText(topic_banner.getTitle());
            List<RecommondAndVideoInfo.DataBean.TopicBannerBean.TopicListBean> topicList = topic_banner.getTopic_list();
            LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.bannerRecyclerView.setLayoutManager(manager);
            RecomondTopBannerAdapter adapter = new RecomondTopBannerAdapter(mContext, topicList);
            holder.bannerRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.logo_image)
        RoundImage logoImage;
        @BindView(R.id.banner_text)
        TextView bannerText;
        @BindView(R.id.banner_recyclerView)
        RecyclerView bannerRecyclerView;

        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class OnlyImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bottom_tab_text)
        TextView bottomTabText;
        @BindView(R.id.comment_rl)
        RelativeLayout commentRl;
        @BindView(R.id.thumbs_rl)
        RelativeLayout thumbsRl;
        @BindView(R.id.share_rl)
        RelativeLayout shareRl;
        @BindView(R.id.logo_image)
        RoundImage logoImage;
        @BindView(R.id.follow)
        TextView follow;
        @BindView(R.id.nick)
        TextView nick;
        @BindView(R.id.second_logo)
        ImageView secondLogo;
        @BindView(R.id.vip)
        ImageView vip;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.title_des)
        TextView titleDes;
        @BindView(R.id.only_image)
        ImageView onlyImage;
        @BindView(R.id.share_image)
        ImageView shareImage;
        @BindView(R.id.comment_image)
        ImageView commentImage;
        @BindView(R.id.thumbs_image)
        ImageView thumbsImage;

        public OnlyImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class RowImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bottom_tab_text)
        TextView bottomTabText;
        @BindView(R.id.comment_rl)
        RelativeLayout commentRl;
        @BindView(R.id.thumbs_rl)
        RelativeLayout thumbsRl;
        @BindView(R.id.share_rl)
        RelativeLayout shareRl;
        @BindView(R.id.logo_image)
        RoundImage logoImage;
        @BindView(R.id.follow)
        TextView follow;
        @BindView(R.id.nick)
        TextView nick;
        @BindView(R.id.second_logo)
        ImageView secondLogo;
        @BindView(R.id.vip)
        ImageView vip;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.title_des)
        TextView titleDes;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.share_image)
        ImageView shareImage;
        @BindView(R.id.comment_image)
        ImageView commentImage;
        @BindView(R.id.thumbs_image)
        ImageView thumbsImage;

        public RowImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class VideoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bottom_tab_text)
        TextView bottomTabText;
        @BindView(R.id.comment_rl)
        RelativeLayout commentRl;
        @BindView(R.id.thumbs_rl)
        RelativeLayout thumbsRl;
        @BindView(R.id.share_rl)
        RelativeLayout shareRl;
        @BindView(R.id.logo_image)
        RoundImage logoImage;
        @BindView(R.id.follow)
        TextView follow;
        @BindView(R.id.nick)
        TextView nick;
        @BindView(R.id.second_logo)
        ImageView secondLogo;
        @BindView(R.id.vip)
        ImageView vip;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.title_des)
        TextView titleDes;
        @BindView(R.id.video_player)
        JCVideoPlayerStandard videoPlayer;
        @BindView(R.id.share_image)
        ImageView shareImage;
        @BindView(R.id.comment_image)
        ImageView commentImage;
        @BindView(R.id.thumbs_image)
        ImageView thumbsImage;

        public VideoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void commonContent(RecyclerView.ViewHolder pHolder, int pPosition) {
        if (pHolder instanceof OnlyImageHolder) {
            OnlyImageHolder holder = (OnlyImageHolder) pHolder;
            if (mList.get(pPosition).getAuthor() != null) {
                RecommondAndVideoInfo.DataBean.FeedsListBean.AuthorBean author = mList.get(pPosition).getAuthor();
                Glide.with(mContext).load(author.getAvatar()).into(holder.logoImage);
                holder.nick.setText(author.getUsername());
                if (author.getPendant() != null && author.getPendant().size() != 0) {
                    GlideEngine.loadImageWithSize(mContext,author.getPendant().get(0).getWidth(), author.getPendant()
                            .get(0).getHeight(),holder.onlyImage,author.getPendant().get(0).getUrl());
                    if (author.getPendant().size() > 1) {
                        GlideEngine.loadImageWithSize(mContext,author.getPendant().get(1).getWidth(), author.getPendant()
                                .get(1).getHeight(),holder.vip,author.getPendant().get(1).getUrl());
                    }
                }
                holder.time.setText(mList.get(pPosition).getCreated_at().substring(5, mList.get(pPosition).getCreated_at().length() - 3));
                String content = mList.get(pPosition).getContent();
                holder.titleDes.setText(content/*.contains("[") ? EmojiUtils.replaceEmojiTextView(content.trim(),12):content*/);
            }
            if (mList.get(pPosition).getTopic() != null && !TextUtils.isEmpty(mList.get(pPosition).getTopic().getContent())) {
                holder.bottomTabText.setVisibility(View.VISIBLE);
                holder.bottomTabText.setText(mList.get(pPosition).getTopic().getContent());
            } else {
                holder.bottomTabText.setVisibility(View.GONE);
            }
        } else if (pHolder instanceof RowImageHolder) {
            RowImageHolder holder = (RowImageHolder) pHolder;
            if (mList.get(pPosition).getAuthor() != null) {
                RecommondAndVideoInfo.DataBean.FeedsListBean.AuthorBean author = mList.get(pPosition).getAuthor();
                Glide.with(mContext).load(author.getAvatar()).into(holder.logoImage);
                holder.nick.setText(author.getUsername());
                if (author.getPendant() != null && author.getPendant().size() != 0) {
                    GlideEngine.loadImageWithSize(mContext,author.getPendant().get(0).getWidth(), author.getPendant()
                            .get(0).getHeight(),holder.secondLogo,author.getPendant().get(0).getUrl());
                    if (author.getPendant().size() > 1) {
                        GlideEngine.loadImageWithSize(mContext,author.getPendant().get(1).getWidth(), author.getPendant()
                                .get(1).getHeight(),holder.vip,author.getPendant().get(1).getUrl());
                    }
                }
                holder.time.setText(mList.get(pPosition).getCreated_at().substring(5, mList.get(pPosition).getCreated_at().length() - 3));
                String content = mList.get(pPosition).getContent();
                holder.titleDes.setText(content/*.contains("[") ? EmojiUtils.replaceEmojiTextView(content.trim(),12):content*/);
            }
            if (mList.get(pPosition).getTopic() != null && !TextUtils.isEmpty(mList.get(pPosition).getTopic().getContent())) {
                holder.bottomTabText.setVisibility(View.VISIBLE);
                holder.bottomTabText.setText(mList.get(pPosition).getTopic().getContent());
            } else {
                holder.bottomTabText.setVisibility(View.GONE);
            }
        } else if (pHolder instanceof VideoHolder) {
            VideoHolder holder = (VideoHolder) pHolder;
            if (mList.get(pPosition).getAuthor() != null) {
                RecommondAndVideoInfo.DataBean.FeedsListBean.AuthorBean author = mList.get(pPosition).getAuthor();
                Glide.with(mContext).load(author.getAvatar()).into(holder.logoImage);
                holder.nick.setText(author.getUsername());
                if (author.getPendant() != null && author.getPendant().size() != 0) {
                    GlideEngine.loadImageWithSize(mContext,author.getPendant().get(0).getWidth(), author.getPendant()
                            .get(0).getHeight(),holder.secondLogo,author.getPendant().get(0).getUrl());
                    if (author.getPendant().size() > 1) {
                        GlideEngine.loadImageWithSize(mContext,author.getPendant().get(1).getWidth(), author.getPendant()
                                .get(1).getHeight(),holder.vip,author.getPendant().get(1).getUrl());
                    }
                }
                holder.time.setText(mList.get(pPosition).getCreated_at().substring(5, mList.get(pPosition).getCreated_at().length() - 3));
                holder.titleDes.setText(mList.get(pPosition).getContent());
            }
            if (mList.get(pPosition).getTopic() != null && !TextUtils.isEmpty(mList.get(pPosition).getTopic().getContent())) {
                holder.bottomTabText.setVisibility(View.VISIBLE);
                holder.bottomTabText.setText(mList.get(pPosition).getTopic().getContent());
            } else {
                holder.bottomTabText.setVisibility(View.GONE);
            }
        }
    }

}
