
package com.teach.news10.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CommentEntity implements Parcelable {
    public static final int SECTION_HEAD = 5;

    public static final int SECTION_SORT = 3;

    public static final int SECTION_TOP_LOAD_MORE = 4;

    public static final int SECTION = 1;

    public static final int SECTION_NOT_SORT = 6;

    public static final int UP_SECTION = 2;

    public static final int VIDEO = 7;

    public static final int ARTICLE = 200;

    public static final int LOAD_PREV_MORE = 8;

    public static final int TYPE_FIRST_REPLY = 9;

    public static final int VIEW_TYPE_REWARD = 15;

    public static final int VIEW_TYPE_EMPTY = 18;

    /**
     * 战报
     */
    public static final int VIEW_TYPE_PK_REPORT = 19;

    /**
     * 圈子详情页头部
     */
    public static final int VIEW_TYPE_GROUP_DETAIL_HEADER = 20;

    /**
     * 圈子详情页头部分享人展示
     */
    public static final int VIEW_TYPE_GROUP_DETAIL_SHARE = 21;

    public static final int LEVEL = 101;

    public static final int PROGRESS_EVALUATE = 102;

    public static final int PROGRESS_DETAIL = 103;

    public static final int EVALUATE_EMPTY = 104;

    public static final int EVALUATE_TOAST = 105;

    public static final int EVALUATE_TITLE = 106;

    public static final int EVALUATE_GUIDE = 107;

    public static final int EVALUATE_EMPTY2 = 108;

    /**
     * 小图－标题－描述
     */
    public static final int TYPE_PIC_TXT = 9;
    /**
     * 标题－大图
     */
    public static final int TYPE_BIG_PIC_TXT = 10;
    /**
     * 标题－三小图
     */
    public static final int TYPE_THREE_PIC_TXT = 11;
    /**
     * 单图
     */
    public static final int TYPE_BANNER = 12;
    /**
     * 小图－标题－描述－下载
     */
    public static final int TYPE_PIC_TXT_DOWNLOAD = 13;
    /**
     * 标题－大图－下载
     */
    public static final int TYPE_BIG_PIC_TXT_DOWNLOAD = 14;

    /**
     * 微头条
     */
    public static final int TYPE_MINI_TOP = 15;

    /**
     * 标题－大图－下载
     */
    public static final int TYPE_ONE_PICTURE_TEXT = 16;

    public int type;

    private boolean is_host;

    public int openStatus;

    public int position;

    private String content;

    private String id;

    private String alias;

    private String reply_total;

    private String answer_id;

    private String created_at;

    private String fold;

    private String up;
    private String user_id;

    private CommentEntity quote;

    private NormalNewsInfo.ArticlesBean article;


    private int commentType;


    private String jump;

    public String scheme;

    private boolean recommend;

    public int attachments_total;

    private boolean reward;

    private String reward_tips;

    private String comment_tips_text;

    private String comment_tips_img_url;

    private String status;

    private String tips;

    /**
     * 是否是作者本人
     */
    public boolean is_root;


    private List<CommentEntity> reply_list;

    private CommentEntity left_score_info;

    private CommentEntity right_score_info;

    private boolean agreed;

    private boolean is_publish;

    private boolean has_option;

    /**
     * 这两个是通知里跳转用的
     */


    private String comment_id;

    private String location_id;

    private String option_id;

    private String name;

    private String color;

    private String font_color;

    private String score;

    private String level;

    private String total_score;

    private String rank;

    private String comment_total;

    private String player_id;

    private List<CommentEntity> option_list;

    private CommentEntity player_info;

    private String player_name;

    private String logo;

    private String post_tips;

    private String selfonly;

    public String getSelfonly() {
        return selfonly;
    }

    public void setSelfonly(String selfonly) {
        this.selfonly = selfonly;
    }

    public String getPost_tips() {
        return post_tips;
    }

    public void setPost_tips(String post_tips) {
        this.post_tips = post_tips;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public CommentEntity getPlayer_info() {
        return player_info;
    }

    public void setPlayer_info(CommentEntity player_info) {
        this.player_info = player_info;
    }


    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isReward() {
        return reward;
    }

    public void setReward(boolean reward) {
        this.reward = reward;
    }

    public String getReward_tips() {
        return reward_tips;
    }

    public void setReward_tips(String reward_tips) {
        this.reward_tips = reward_tips;
    }

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }

    public boolean isPublish() {
        return is_publish;
    }

    public void setPublish(boolean is_publish) {
        this.is_publish = is_publish;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getScheme() {
        return scheme;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getComment_tips_text() {
        return comment_tips_text;
    }

    public void setComment_tips_text(String comment_tips_text) {
        this.comment_tips_text = comment_tips_text;
    }

    public String getComment_tips_img_url() {
        return comment_tips_img_url;
    }

    public void setComment_tips_img_url(String comment_tips_img_url) {
        this.comment_tips_img_url = comment_tips_img_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public CommentEntity() {
    }

    public CommentEntity(int type, String content) {
        this.type = type;
        this.content = content;
    }


    public int getAttachments_total() {
        return attachments_total;
    }

    public void setAttachments_total(int attachments_total) {
        this.attachments_total = attachments_total;
    }


    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }


    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isIs_host() {
        return is_host;
    }

    public void setIs_host(boolean is_host) {
        this.is_host = is_host;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getFold() {
        return fold;
    }

    public void setFold(String fold) {
        this.fold = fold;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }


    public CommentEntity getQuote() {
        return quote;
    }

    public void setQuote(CommentEntity quote) {
        this.quote = quote;
    }


    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public void setOpenStatus(int openStatus) {
        this.openStatus = openStatus;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean is_root() {
        return is_root;
    }

    public void setIs_root(boolean is_root) {
        this.is_root = is_root;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getReply_total() {
        return reply_total;
    }

    public void setReply_total(String reply_total) {
        this.reply_total = reply_total;
    }

    public List<CommentEntity> getReply_list() {
        return reply_list;
    }

    public void setReply_list(List<CommentEntity> reply_list) {
        this.reply_list = reply_list;
    }

    public CommentEntity getLeft_score_info() {
        return left_score_info;
    }

    public void setLeft_score_info(CommentEntity left_score_info) {
        this.left_score_info = left_score_info;
    }

    public CommentEntity getRight_score_info() {
        return right_score_info;
    }

    public void setRight_score_info(CommentEntity right_score_info) {
        this.right_score_info = right_score_info;
    }

    public boolean is_publish() {
        return is_publish;
    }

    public void setIs_publish(boolean is_publish) {
        this.is_publish = is_publish;
    }

    public String getOption_id() {
        return option_id;
    }

    public void setOption_id(String option_id) {
        this.option_id = option_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFont_color() {
        return font_color;
    }

    public void setFont_color(String font_color) {
        this.font_color = font_color;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment_total() {
        return comment_total;
    }

    public void setComment_total(String comment_total) {
        this.comment_total = comment_total;
    }

    public List<CommentEntity> getOption_list() {
        return option_list;
    }

    public void setOption_list(List<CommentEntity> option_list) {
        this.option_list = option_list;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public boolean isHas_option() {
        return has_option;
    }

    public void setHas_option(boolean has_option) {
        this.has_option = has_option;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeByte(this.is_host ? (byte) 1 : (byte) 0);
        dest.writeInt(this.openStatus);
        dest.writeInt(this.position);
        dest.writeString(this.content);
        dest.writeString(this.id);
        dest.writeString(this.alias);
        dest.writeString(this.reply_total);
        dest.writeString(this.answer_id);
        dest.writeString(this.created_at);
        dest.writeString(this.fold);
        dest.writeString(this.up);
        dest.writeString(this.user_id);
        dest.writeParcelable(this.quote, flags);
        dest.writeInt(this.commentType);
        dest.writeString(this.jump);
        dest.writeString(this.scheme);
        dest.writeByte(this.recommend ? (byte) 1 : (byte) 0);
        dest.writeInt(this.attachments_total);
        dest.writeByte(this.reward ? (byte) 1 : (byte) 0);
        dest.writeString(this.reward_tips);
        dest.writeString(this.comment_tips_text);
        dest.writeString(this.comment_tips_img_url);
        dest.writeString(this.status);
        dest.writeString(this.tips);
        dest.writeByte(this.is_root ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.reply_list);
        dest.writeParcelable(this.left_score_info, flags);
        dest.writeParcelable(this.right_score_info, flags);
        dest.writeByte(this.agreed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_publish ? (byte) 1 : (byte) 0);
        dest.writeByte(this.has_option ? (byte) 1 : (byte) 0);
        dest.writeString(this.comment_id);
        dest.writeString(this.location_id);
        dest.writeString(this.option_id);
        dest.writeString(this.name);
        dest.writeString(this.color);
        dest.writeString(this.font_color);
        dest.writeString(this.score);
        dest.writeString(this.level);
        dest.writeString(this.total_score);
        dest.writeString(this.rank);
        dest.writeString(this.comment_total);
        dest.writeString(this.player_id);
        dest.writeTypedList(this.option_list);
        dest.writeParcelable(this.player_info, flags);
        dest.writeString(this.player_name);
        dest.writeString(this.logo);
        dest.writeString(this.post_tips);
        dest.writeString(this.selfonly);
    }

    protected CommentEntity(Parcel in) {
        this.type = in.readInt();
        this.is_host = in.readByte() != 0;
        this.openStatus = in.readInt();
        this.position = in.readInt();
        this.content = in.readString();
        this.id = in.readString();
        this.alias = in.readString();
        this.reply_total = in.readString();
        this.answer_id = in.readString();
        this.created_at = in.readString();
        this.fold = in.readString();
        this.up = in.readString();
        this.user_id = in.readString();
        this.quote = in.readParcelable(CommentEntity.class.getClassLoader());
        this.commentType = in.readInt();
        this.jump = in.readString();
        this.scheme = in.readString();
        this.recommend = in.readByte() != 0;
        this.attachments_total = in.readInt();
        this.reward = in.readByte() != 0;
        this.reward_tips = in.readString();
        this.comment_tips_text = in.readString();
        this.comment_tips_img_url = in.readString();
        this.status = in.readString();
        this.tips = in.readString();
        this.is_root = in.readByte() != 0;
        this.reply_list = in.createTypedArrayList(CommentEntity.CREATOR);
        this.left_score_info = in.readParcelable(CommentEntity.class.getClassLoader());
        this.right_score_info = in.readParcelable(CommentEntity.class.getClassLoader());
        this.agreed = in.readByte() != 0;
        this.is_publish = in.readByte() != 0;
        this.has_option = in.readByte() != 0;
        this.comment_id = in.readString();
        this.location_id = in.readString();
        this.option_id = in.readString();
        this.name = in.readString();
        this.color = in.readString();
        this.font_color = in.readString();
        this.score = in.readString();
        this.level = in.readString();
        this.total_score = in.readString();
        this.rank = in.readString();
        this.comment_total = in.readString();
        this.player_id = in.readString();
        this.option_list = in.createTypedArrayList(CommentEntity.CREATOR);
        this.player_info = in.readParcelable(CommentEntity.class.getClassLoader());
        this.player_name = in.readString();
        this.logo = in.readString();
        this.post_tips = in.readString();
        this.selfonly = in.readString();
    }

    public static final Creator<CommentEntity> CREATOR = new Creator<CommentEntity>() {
        @Override
        public CommentEntity createFromParcel(Parcel source) {
            return new CommentEntity(source);
        }

        @Override
        public CommentEntity[] newArray(int size) {
            return new CommentEntity[size];
        }
    };
}
