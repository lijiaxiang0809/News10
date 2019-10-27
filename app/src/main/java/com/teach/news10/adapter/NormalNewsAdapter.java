package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.Frame.OnRecyclerItemClick;
import com.teach.news10.R;
import com.teach.news10.bean.BannerInfo;
import com.teach.news10.bean.FavTeamEntity;
import com.teach.news10.bean.MatchInfo;
import com.teach.news10.bean.NormalNewsInfo;
import com.teach.news10.design.GlideImageLoader;
import com.teach.news10.design.RoundImage;
import com.teach.news10.design.RoundOrCircleImage;
import com.teach.news10.design.banner.BannerLayout;
import com.teach.news10.local_utils.SharedPrefrenceUtils;
import com.teach.news10.utils.NormalConfig;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 任小龙 on 2019/5/5.
 */
public class NormalNewsAdapter extends RecyclerView.Adapter implements OnBannerListener {
    private Context mContext;
    private List<NormalNewsInfo.ArticlesBean> articles;
    private List<NormalNewsInfo.RecommendBean> recommend;
    private List<MatchInfo> matchInfo;
    private String label;

    private final int BANNER = 1;
    private final int NORMAL = 3;
    private final int VIDEO = 4;
    private final int FAVOR = 5;

    public OnRecyclerItemClick mOnRecyclerItemClick;

    public void setOnRecyclerItemClick(OnRecyclerItemClick pOnRecyclerItemClick){
        this.mOnRecyclerItemClick = pOnRecyclerItemClick;
    }

    @Override
    public int getItemViewType(int position) {
        if (label.equals("头条")){
            if (position == 0) return BANNER;
            else if (articles.size() != 0 && articles.get(position - 1).isIs_video()) return VIDEO;
            else if (articles.size() != 0 && articles.get(position - 1).favTeamEntity != null)
                return FAVOR;
            else return NORMAL;
        } else {
            if (articles != null && articles.size() != 0){
                if (articles.get(position).isIs_video())return VIDEO;
                else return NORMAL;
            }
        }
        return NORMAL;
    }

    public NormalNewsAdapter(Context pContext, List<NormalNewsInfo.ArticlesBean> pArticles, List<NormalNewsInfo.RecommendBean> pRecommend, List<MatchInfo> pMatchInfo, String pLabel) {
        mContext = pContext;
        articles = pArticles;
        recommend = pRecommend;
        matchInfo = pMatchInfo;
        label = pLabel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == NORMAL)
            return new NormalHolder(View.inflate(mContext, R.layout.news_normal_adapter, null));
        if (viewType == VIDEO)
            return new VideoHolder(View.inflate(mContext, R.layout.news_video_layout, null));
        if (viewType == BANNER)
            return new BannerHolder(View.inflate(mContext, R.layout.news_top_adapter, null));
        if (viewType == FAVOR)
            return new FavorHolder(View.inflate(mContext, R.layout.favor_adapter_layout, null));
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == NORMAL) {
            setUpNormalData(holder, position);
        } else if (getItemViewType(position) == VIDEO) {
            setUpVideoData(holder, position);
        } else if (getItemViewType(position) == BANNER) {
            setUpBannerData(holder, position);
        } else if (getItemViewType(position) == FAVOR){
            setUpFavorData(holder, position);
        }
    }

    private void setUpFavorData(RecyclerView.ViewHolder pHolder, int position) {
        int pPosition = position - 1;
        FavorHolder holder = (FavorHolder) pHolder;
        int colume = 1;
        FavTeamEntity favTeamEntity = null;
        List<FavTeamEntity> data = new ArrayList<>();
        if(articles.get(pPosition).favTeamEntity != null && articles.get(pPosition).favTeamEntity.getSelected_teams() != null){
            favTeamEntity = articles.get(pPosition).favTeamEntity;
            ArrayList<String> selected_teams = favTeamEntity.getSelected_teams();
            if (selected_teams != null){
                List<String> strings = new ArrayList<>();
                if (selected_teams.size() > 4) {
                    strings = selected_teams.subList(0, 4);
                } else {
                    strings.addAll(selected_teams);
                }
                ArrayList<FavTeamEntity> team_list = favTeamEntity.getTeam_list().get(0).getList();
                for (int i = 0; i < team_list.size(); i++) {
                    if (strings.contains(team_list.get(i).getId())) {
                        data.add(team_list.get(i));
                    }
                }
            }
            if (favTeamEntity.getSelected_teams() != null && favTeamEntity.getSelected_teams().size() != 0) {
                if (favTeamEntity.getSelected_teams().size() >= 4) {
                    colume = 5;
                } else colume = favTeamEntity.getSelected_teams().size() + 1;
            }
        } else {
            List<FavTeamEntity> tempData = SharedPrefrenceUtils.getSerializableList(mContext, NormalConfig.CATCH_GIRL_FRIEND);
            if (tempData == null)return;
            data.addAll(tempData);
            if (data.size() >= 4) {
                colume = 5;
            } else colume = data.size() + 1;
        }
        GridLayoutManager manager = new GridLayoutManager(mContext, colume);
        holder.favorRecyclerview.setLayoutManager(manager);
        FavorNewAdapter adapter = new FavorNewAdapter(mContext, data);
        holder.favorRecyclerview.setAdapter(adapter);
    }

    private void setUpBannerData(RecyclerView.ViewHolder pHolder, int position) {
        List<String> bannerData = new ArrayList<>();
        List<String> bannerDes = new ArrayList<>();
        BannerHolder holder = (BannerHolder) pHolder;
        GlideEngine.loadCornerImage(holder.mRedImage,R.drawable.banner_bg,null,0);
        List<BannerInfo> bannerInfos = new ArrayList<>();
        if (recommend != null && recommend.size() != 0) {
            for (int i = 0; i < recommend.size(); i++) {
                BannerInfo info = new BannerInfo();
                info.imageUrl = recommend.get(i).getThumb();
                info.describe = recommend.get(i).getTitle();
                bannerData.add( recommend.get(i).getThumb());
                bannerDes.add(recommend.get(i).getTitle());
                bannerInfos.add(info);
            }
        }
        holder.banner.setImages(bannerData)
                .setBannerTitles(bannerDes)
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
//        BannerAdapter adapter = new BannerAdapter(mContext, bannerInfos);
//        holder.banner.setAdapter(adapter);
//        holder.banner.setItemSpace(40);
        if (matchInfo == null || matchInfo.size() == 0) return;
        MatchInfo matchInfo = this.matchInfo.get(0);
        Glide.with(mContext).load(matchInfo.getTeam_A_logo()).into(holder.logo);
        Glide.with(mContext).load(matchInfo.getTeam_B_logo()).into(holder.logoRight);
        holder.teamName.setText(!TextUtils.isEmpty(matchInfo.getPlayoff_fs_A()) ? matchInfo.getTeam_A_name() + "(" + matchInfo.getPlayoff_fs_A() + ")" :  matchInfo.getTeam_A_name());
        holder.teamNameRight.setText(!TextUtils.isEmpty(matchInfo.getPlayoff_fs_B()) ? matchInfo.getTeam_B_name() + "(" + matchInfo.getPlayoff_fs_B() + ")" : matchInfo.getTeam_B_name());
        holder.titleDes.setText(matchInfo.getMatch_title());
        if (matchInfo.getStatus().equals("Fixture")){
            holder.score.setText(matchInfo.getStart_play());
            holder.score.setTextSize(TypedValue.COMPLEX_UNIT_SP,8);
        } else {
            holder.score.setText(matchInfo.getFs_A() + " - " + matchInfo.getFs_B());
            holder.score.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        }
        if (matchInfo.getStatus().equals("Playing")) holder.status.setText("直播中");
        else if (matchInfo.getStatus().equals("Played")) holder.status.setText("已结束");
        else if (matchInfo.getStatus().equals("Fixture")) holder.status.setText("未开始");
        else holder.status.setText("鸡毛");
        holder.matchRight.setVisibility(View.GONE);
        if (this.matchInfo.size() <= 1) {
        } else {
            MatchInfo matchInfo2 = this.matchInfo.get(1);
            Glide.with(mContext).load(matchInfo2.getTeam_A_logo()).into(holder.logo2);
            Glide.with(mContext).load(matchInfo2.getTeam_B_logo()).into(holder.logoRight2);
            holder.teamName2.setText(matchInfo2.getTeam_A_name() + "(" + matchInfo2.getPlayoff_fs_A() + ")");
            holder.teamNameRight2.setText(matchInfo2.getTeam_B_name() + "(" + matchInfo2.getPlayoff_fs_B() + ")");
            holder.titleDes2.setText(matchInfo2.getMatch_title());
            holder.score2.setText(matchInfo2.getFs_A() + " - " + matchInfo2.getFs_B());
            if (matchInfo.getStatus().equals("Playing")) holder.status2.setText("直播中");
            else if (matchInfo.getStatus().equals("Played")) holder.status2.setText("已结束");
            else if (matchInfo.getStatus().equals("Fixture")) holder.status2.setText("未开始");
            else holder.status2.setText("搞meshi");
        }
    }

    private void setUpVideoData(RecyclerView.ViewHolder pHolder, final int position) {
        int pPosition = position;
        if (label.equals("头条")){
            pPosition = position - 1;
        }
        VideoHolder holder = (VideoHolder) pHolder;
        JCVideoPlayerStandard videoPlayer = holder.videoPlayer;
        videoPlayer.tinyBackImageView.setVisibility(View.GONE);
        videoPlayer.backButton.setVisibility(View.GONE);
        videoPlayer.setUp(articles.get(pPosition).getVideo_info().getVideo_src(), JCVideoPlayer.SCREEN_LAYOUT_LIST);
        GlideEngine.loadImage(videoPlayer.thumbImageView,articles.get(pPosition).getCover().getPic());
        Glide.with(mContext).load(articles.get(pPosition).getThumb()).into(holder.avarter);
        holder.title.setText(articles.get(pPosition).getTitle());
        holder.time.setText(articles.get(pPosition).getVideo_info().getVideo_time());
//        onItemClick(holder.item,position);
    }

    private void onItemClick(View pItem, final int position) {
        pItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecyclerItemClick != null){
                    if (label.equals("头条"))mOnRecyclerItemClick.onItemClick(position-1);
                    else mOnRecyclerItemClick.onItemClick(position);
                }
            }
        });
    }

    private void setUpNormalData(RecyclerView.ViewHolder pHolder, int position) {
        int pPosition = label.equals("头条") ? position - 1 : position;
        NormalHolder holder = (NormalHolder) pHolder;
        Glide.with(mContext).load(articles.get(pPosition).getThumb()).into(holder.rightImage);
        holder.title.setText(articles.get(pPosition).getTitle());
        if (articles.get(pPosition).isTop()) holder.isTop.setVisibility(View.VISIBLE);
        else holder.isTop.setVisibility(View.GONE);
        holder.isDeep.setVisibility(TextUtils.isEmpty(articles.get(pPosition).getLabel()) ? View.GONE : View.VISIBLE);
        holder.comment.setText(articles.get(pPosition).getComments_total() + "评论");
        onItemClick(holder.item,position);
    }

    @Override
    public void OnBannerClick(int position) {

    }

    public static class NormalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.right_image)
        RoundOrCircleImage rightImage;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.is_top)
        TextView isTop;
        @BindView(R.id.is_deep)
        TextView isDeep;
        @BindView(R.id.comment)
        TextView comment;
        @BindView(R.id.item)
        RelativeLayout item;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class VideoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item)
        RelativeLayout item;
        @BindView(R.id.avarter)
        RoundImage avarter;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.from)
        TextView from;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.video_player)
        JCVideoPlayerStandard videoPlayer;
        @BindView(R.id.time)
        TextView time;

        public VideoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.logo)
        ImageView logo;
        @BindView(R.id.team_name)
        TextView teamName;
        @BindView(R.id.title_des)
        TextView titleDes;
        @BindView(R.id.score)
        TextView score;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.logo_right)
        ImageView logoRight;
        @BindView(R.id.team_name_right)
        TextView teamNameRight;
        @BindView(R.id.logo2)
        ImageView logo2;
        @BindView(R.id.team_name2)
        TextView teamName2;
        @BindView(R.id.title_des2)
        TextView titleDes2;
        @BindView(R.id.score2)
        TextView score2;
        @BindView(R.id.status2)
        TextView status2;
        @BindView(R.id.logo_right2)
        ImageView logoRight2;
        @BindView(R.id.team_name_right2)
        TextView teamNameRight2;
        @BindView(R.id.match_right)
        LinearLayout matchRight;
        @BindView(R.id.red_bg_image)
        ImageView mRedImage;

        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class FavorHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.favor_title)
        TextView favorTitle;
        @BindView(R.id.favor_recyclerview)
        RecyclerView favorRecyclerview;

        public FavorHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return label.equals("头条") ? articles.size() + 1 : articles.size();
    }
}
