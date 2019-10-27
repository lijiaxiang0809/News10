package com.teach.news10.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 任小龙 on 2019/4/22.
 */
public class FirstPageTitleInfo implements Parcelable,Serializable{

    private static final long serialVersionUID = -2954791646074972158L;
    private int errCode;
    private String message;
    private DataBean data;

    protected FirstPageTitleInfo(Parcel in) {
        errCode = in.readInt();
        message = in.readString();
    }

    public static final Creator<FirstPageTitleInfo> CREATOR = new Creator<FirstPageTitleInfo>() {
        @Override
        public FirstPageTitleInfo createFromParcel(Parcel in) {
            return new FirstPageTitleInfo(in);
        }

        @Override
        public FirstPageTitleInfo[] newArray(int size) {
            return new FirstPageTitleInfo[size];
        }
    };

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(errCode);
        dest.writeString(message);
    }

    public static class DataBean {

        private int default_index;
        private int big_image_slide;
        private List<ListBean> list;

        public int getDefault_index() {
            return default_index;
        }

        public void setDefault_index(int default_index) {
            this.default_index = default_index;
        }

        public int getBig_image_slide() {
            return big_image_slide;
        }

        public void setBig_image_slide(int big_image_slide) {
            this.big_image_slide = big_image_slide;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {

            private int id;
            private String label;
            private boolean recommend;
            private String type;
            private String api;
            private String index_match_url;

            protected ListBean(Parcel in) {
                id = in.readInt();
                label = in.readString();
                recommend = in.readByte() != 0;
                type = in.readString();
                api = in.readString();
                index_match_url = in.readString();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel in) {
                    return new ListBean(in);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public boolean isRecommend() {
                return recommend;
            }

            public void setRecommend(boolean recommend) {
                this.recommend = recommend;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getApi() {
                return api;
            }

            public void setApi(String api) {
                this.api = api;
            }

            public String getIndex_match_url() {
                return index_match_url;
            }

            public void setIndex_match_url(String index_match_url) {
                this.index_match_url = index_match_url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(label);
                dest.writeByte((byte) (recommend ? 1 : 0));
                dest.writeString(type);
                dest.writeString(api);
                dest.writeString(index_match_url);
            }
        }
    }
}
