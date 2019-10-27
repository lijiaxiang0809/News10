package com.teach.news10.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class NewsExtraModel implements Parcelable {
    public long newsId;
    public String url;
    public String title;
    public boolean isHeadLine;
    public String template;
    public boolean isRedirect;
    public long navigationStartTime;


    public long getNavigationStartTime() {
        return navigationStartTime;
    }

    public void setNavigationStartTime(long navigationStartTime) {
        this.navigationStartTime = navigationStartTime;
    }


    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHeadLine(boolean headLine) {
        isHeadLine = headLine;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    public static class Builder {
        private NewsExtraModel mModel;

        public Builder() {
            mModel = new NewsExtraModel();
        }

        public Builder title(String title) {
            mModel.setTitle(title);
            return this;
        }

        public Builder newsId(String newsId) {
            long id = 0;
            try {
                if (!TextUtils.isEmpty(newsId))
                    id = Long.parseLong(newsId);
            } catch (Exception e) {
                if (newsId.contains("?")) {
                    String[] str = newsId.split("\\?");
                    if (str.length > 1) {
                        id = Long.parseLong(str[0]);
                    }
                }
            }
            newsId(id);
            return this;
        }

        public Builder newsId(long newsId) {
            mModel.setNewsId(newsId);
            return this;
        }

        public Builder template(String template) {
            mModel.setTemplate(template);
            return this;
        }

        public Builder url(String url) {
            mModel.setUrl(url);
            return this;
        }

        public Builder redirect(boolean isRedirect) {
            mModel.setRedirect(isRedirect);
            return this;
        }


        public Builder isHeadLine(boolean isHeadLine) {
            mModel.setHeadLine(isHeadLine);
            return this;
        }

        public Builder navigationStartTime(long navigationStartTime) {
            mModel.setNavigationStartTime(navigationStartTime);
            return this;
        }

        public NewsExtraModel build() {
            return mModel;
        }
    }

    private NewsExtraModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.newsId);
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeByte(this.isHeadLine ? (byte) 1 : (byte) 0);
        dest.writeString(this.template);
        dest.writeByte(this.isRedirect ? (byte) 1 : (byte) 0);
        dest.writeLong(this.navigationStartTime);
    }

    protected NewsExtraModel(Parcel in) {
        this.newsId = in.readLong();
        this.url = in.readString();
        this.title = in.readString();
        this.isHeadLine = in.readByte() != 0;
        this.template = in.readString();
        this.isRedirect = in.readByte() != 0;
        this.navigationStartTime = in.readLong();
    }

    public static final Creator<NewsExtraModel> CREATOR = new Creator<NewsExtraModel>() {
        @Override
        public NewsExtraModel createFromParcel(Parcel source) {
            return new NewsExtraModel(source);
        }

        @Override
        public NewsExtraModel[] newArray(int size) {
            return new NewsExtraModel[size];
        }
    };
}
