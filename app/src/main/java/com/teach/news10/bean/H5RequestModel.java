package com.teach.news10.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hujinghui on 2017/7/12.
 */


public class H5RequestModel implements Parcelable {

    private String method;

    private String url;

    private Map<String, String> param;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public H5RequestModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.method);
        dest.writeString(this.url);
        dest.writeInt(this.param.size());
        for (Map.Entry<String, String> entry : this.param.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    protected H5RequestModel(Parcel in) {
        this.method = in.readString();
        this.url = in.readString();
        int paramSize = in.readInt();
        this.param = new HashMap<String, String>(paramSize);
        for (int i = 0; i < paramSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.param.put(key, value);
        }
    }

    public static final Creator<H5RequestModel> CREATOR = new Creator<H5RequestModel>() {
        @Override
        public H5RequestModel createFromParcel(Parcel source) {
            return new H5RequestModel(source);
        }

        @Override
        public H5RequestModel[] newArray(int size) {
            return new H5RequestModel[size];
        }
    };
}
