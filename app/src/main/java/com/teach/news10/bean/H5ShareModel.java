package com.teach.news10.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * H5分享
 * Created by guojun on 16/5/21 14:39.
 */
public class H5ShareModel implements Parcelable {

    /**
     * is_picture : true
     * title : haha
     * content : haha
     */

    private boolean is_immediately = true;
    private boolean setDef;
    private boolean is_picture;
    private String title;
    private String content;
    private String url;
    public String share_thumb_url;
    private boolean is_showBtn;

    public boolean isIs_showBtn() {
        return is_showBtn;
    }

    public void setIs_showBtn(boolean is_showBtn) {
        this.is_showBtn = is_showBtn;
    }

    public boolean isIs_immediately() {
        return is_immediately;
    }

    public void setIs_immediately(boolean is_immediately) {
        this.is_immediately = is_immediately;
    }

    public boolean isSetDef() {
        return setDef;
    }

    public void setSetDef(boolean setDef) {
        this.setDef = setDef;
    }

    public String getShare_thumb_url() {
        return share_thumb_url;
    }

    public void setShare_thumb_url(String share_thumb_url) {
        this.share_thumb_url = share_thumb_url;
    }
    ///~~~over

    public boolean isIs_picture() {
        return is_picture;
    }

    public void setIs_picture(boolean is_picture) {
        this.is_picture = is_picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static H5ShareModel parse(String json) {
        if (TextUtils.isEmpty(json))
            return null;
        try {
            H5ShareModel h5ShareModel = JSON.parseObject(json, H5ShareModel.class);
            return h5ShareModel;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public H5ShareModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.is_immediately ? (byte) 1 : (byte) 0);
        dest.writeByte(this.setDef ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_picture ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.url);
        dest.writeString(this.share_thumb_url);
        dest.writeByte(this.is_showBtn ? (byte) 1 : (byte) 0);
    }

    protected H5ShareModel(Parcel in) {
        this.is_immediately = in.readByte() != 0;
        this.setDef = in.readByte() != 0;
        this.is_picture = in.readByte() != 0;
        this.title = in.readString();
        this.content = in.readString();
        this.url = in.readString();
        this.share_thumb_url = in.readString();
        this.is_showBtn = in.readByte() != 0;
    }

    public static final Creator<H5ShareModel> CREATOR = new Creator<H5ShareModel>() {
        @Override
        public H5ShareModel createFromParcel(Parcel source) {
            return new H5ShareModel(source);
        }

        @Override
        public H5ShareModel[] newArray(int size) {
            return new H5ShareModel[size];
        }
    };
}
