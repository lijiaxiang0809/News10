
package com.teach.news10.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsDescModel implements Parcelable {
    public long id;

    public String description;

    public String share;

    public String thumb;

    public String channel;

    public String api;

    public String label;

    public String title;

    public String url;

    public int comments_total;

    public String published_at;

    public boolean collected;

    public String account_name;

    public String account_bg_image;

    private AuthorEntity author;

    public String template;

    public boolean is_redirect_h5;

    /**
     * 是否显示评论数 默认显示
     */
    public boolean show_comments = true;

    public boolean isShare_miniprogram() {
        return share_miniprogram;
    }

    public void setShare_miniprogram(boolean share_miniprogram) {
        this.share_miniprogram = share_miniprogram;
    }

    public boolean share_miniprogram;
    public String miniprogram_id;
    public String miniprogram_path;
    public String miniprogram_thumb;

    public String getMiniprogram_id() {
        return miniprogram_id;
    }

    public void setMiniprogram_id(String miniprogram_id) {
        this.miniprogram_id = miniprogram_id;
    }

    public String getMiniprogram_path() {
        return miniprogram_path;
    }

    public void setMiniprogram_path(String miniprogram_path) {
        this.miniprogram_path = miniprogram_path;
    }

    public String getMiniprogram_thumb() {
        return miniprogram_thumb;
    }

    public void setMiniprogram_thumb(String miniprogram_thumb) {
        this.miniprogram_thumb = miniprogram_thumb;
    }



    public boolean isShow_comments() {
        return show_comments;
    }

    public void setShow_comments(boolean show_comments) {
        this.show_comments = show_comments;
    }

    public NewsDescModel() {
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getComments_total() {
        return comments_total;
    }

    public void setComments_total(int comments_total) {
        this.comments_total = comments_total;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public void setAccount_bg_image(String account_bg_image) {
        this.account_bg_image = account_bg_image;
    }

    public String getAccount_name() {
        return account_name;
    }

    public String getAccount_bg_image() {
        return account_bg_image;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public static class AuthorEntity implements Parcelable {
        private String name;

        private String bg_image;

        private String user_id;

        private String icon;

        private String level;

        private String medal_url;

        public String getMedal_url() {
            return medal_url;
        }

        public void setMedal_url(String medal_url) {
            this.medal_url = medal_url;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBg_image(String bg_image) {
            this.bg_image = bg_image;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public String getBg_image() {
            return bg_image;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getIcon() {
            return icon;
        }

        public String getLevel() {
            return level;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.bg_image);
            dest.writeString(this.user_id);
            dest.writeString(this.icon);
            dest.writeString(this.level);
            dest.writeString(this.medal_url);
        }

        public AuthorEntity() {
        }

        protected AuthorEntity(Parcel in) {
            this.name = in.readString();
            this.bg_image = in.readString();
            this.user_id = in.readString();
            this.icon = in.readString();
            this.level = in.readString();
            this.medal_url = in.readString();
        }

        public static final Creator<AuthorEntity> CREATOR = new Creator<AuthorEntity>() {
            public AuthorEntity createFromParcel(Parcel source) {
                return new AuthorEntity(source);
            }

            public AuthorEntity[] newArray(int size) {
                return new AuthorEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.description);
        dest.writeString(this.share);
        dest.writeString(this.thumb);
        dest.writeString(this.channel);
        dest.writeString(this.api);
        dest.writeString(this.label);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeInt(this.comments_total);
        dest.writeString(this.published_at);
        dest.writeByte(this.collected ? (byte) 1 : (byte) 0);
        dest.writeString(this.account_name);
        dest.writeString(this.account_bg_image);
        dest.writeParcelable(this.author, flags);
        dest.writeString(this.template);
        dest.writeByte(this.is_redirect_h5 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.show_comments ? (byte) 1 : (byte) 0);
        dest.writeByte(this.share_miniprogram ? (byte) 1 : (byte) 0);
        dest.writeString(this.miniprogram_id);
        dest.writeString(this.miniprogram_path);
        dest.writeString(this.miniprogram_thumb);
    }

    protected NewsDescModel(Parcel in) {
        this.id = in.readLong();
        this.description = in.readString();
        this.share = in.readString();
        this.thumb = in.readString();
        this.channel = in.readString();
        this.api = in.readString();
        this.label = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.comments_total = in.readInt();
        this.published_at = in.readString();
        this.collected = in.readByte() != 0;
        this.account_name = in.readString();
        this.account_bg_image = in.readString();
        this.author = in.readParcelable(AuthorEntity.class.getClassLoader());
        this.template = in.readString();
        this.is_redirect_h5 = in.readByte() != 0;
        this.show_comments = in.readByte() != 0;
        this.share_miniprogram = in.readByte() != 0;
        this.miniprogram_id = in.readString();
        this.miniprogram_path = in.readString();
        this.miniprogram_thumb = in.readString();
    }

    public static final Creator<NewsDescModel> CREATOR = new Creator<NewsDescModel>() {
        @Override
        public NewsDescModel createFromParcel(Parcel source) {
            return new NewsDescModel(source);
        }

        @Override
        public NewsDescModel[] newArray(int size) {
            return new NewsDescModel[size];
        }
    };
}
