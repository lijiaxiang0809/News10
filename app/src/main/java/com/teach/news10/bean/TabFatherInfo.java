package com.teach.news10.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 任小龙 on 2019/4/29.
 */
public class TabFatherInfo implements Parcelable,Serializable{

    private static final long serialVersionUID = 5640567649826324530L;
    private String default_index;
    private String live_index;
    private List<DataTabsBean> data_tabs;
    private List<LiveTabsBean> live_tabs;

    protected TabFatherInfo(Parcel in) {
        default_index = in.readString();
        live_index = in.readString();
        data_tabs = in.createTypedArrayList(DataTabsBean.CREATOR);
        live_tabs = in.createTypedArrayList(LiveTabsBean.CREATOR);
    }

    public static final Creator<TabFatherInfo> CREATOR = new Creator<TabFatherInfo>() {
        @Override
        public TabFatherInfo createFromParcel(Parcel in) {
            return new TabFatherInfo(in);
        }

        @Override
        public TabFatherInfo[] newArray(int size) {
            return new TabFatherInfo[size];
        }
    };

    public String getDefault_index() {
        return default_index;
    }

    public void setDefault_index(String default_index) {
        this.default_index = default_index;
    }

    public String getLive_index() {
        return live_index;
    }

    public void setLive_index(String live_index) {
        this.live_index = live_index;
    }

    public List<DataTabsBean> getData_tabs() {
        return data_tabs;
    }

    public void setData_tabs(List<DataTabsBean> data_tabs) {
        this.data_tabs = data_tabs;
    }

    public List<LiveTabsBean> getLive_tabs() {
        return live_tabs;
    }

    public void setLive_tabs(List<LiveTabsBean> live_tabs) {
        this.live_tabs = live_tabs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(default_index);
        dest.writeString(live_index);
        dest.writeTypedList(data_tabs);
        dest.writeTypedList(live_tabs);
    }

    public static class DataTabsBean implements Parcelable {

        private int id;
        private String label;
        private String logo;
        private String competition_id;
        private SeasonBean season;
        private int position;
        private int last_modify;
        private String country_code;
        private ArrayList<SubTabsBean> sub_tabs;

        protected DataTabsBean(Parcel in) {
            id = in.readInt();
            label = in.readString();
            logo = in.readString();
            competition_id = in.readString();
            position = in.readInt();
            last_modify = in.readInt();
            country_code = in.readString();
            sub_tabs = in.createTypedArrayList(SubTabsBean.CREATOR);
        }

        public static final Creator<DataTabsBean> CREATOR = new Creator<DataTabsBean>() {
            @Override
            public DataTabsBean createFromParcel(Parcel in) {
                return new DataTabsBean(in);
            }

            @Override
            public DataTabsBean[] newArray(int size) {
                return new DataTabsBean[size];
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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCompetition_id() {
            return competition_id;
        }

        public void setCompetition_id(String competition_id) {
            this.competition_id = competition_id;
        }

        public SeasonBean getSeason() {
            return season;
        }

        public void setSeason(SeasonBean season) {
            this.season = season;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getLast_modify() {
            return last_modify;
        }

        public void setLast_modify(int last_modify) {
            this.last_modify = last_modify;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public List<SubTabsBean> getSub_tabs() {
            return sub_tabs;
        }

        public void setSub_tabs(ArrayList<SubTabsBean> sub_tabs) {
            this.sub_tabs = sub_tabs;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(label);
            dest.writeString(logo);
            dest.writeString(competition_id);
            dest.writeInt(position);
            dest.writeInt(last_modify);
            dest.writeString(country_code);
            dest.writeTypedList(sub_tabs);
        }

        public static class SeasonBean {
            /**
             * title : 2019季后赛
             * url : http://sport-data.dqdgame.com/sd/biz/data/seasons?competition_id=1&version=0&platform=
             */

            private String title;
            private String url;

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
        }

        public static class SubTabsBean implements Parcelable {
            /**
             * title : 排名
             * url : http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=119&version=0&platform=
             */

            private String title;
            private String url;

            protected SubTabsBean(Parcel in) {
                title = in.readString();
                url = in.readString();
            }

            public static final Creator<SubTabsBean> CREATOR = new Creator<SubTabsBean>() {
                @Override
                public SubTabsBean createFromParcel(Parcel in) {
                    return new SubTabsBean(in);
                }

                @Override
                public SubTabsBean[] newArray(int size) {
                    return new SubTabsBean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(title);
                dest.writeString(url);
            }
        }
    }

    public static class LiveTabsBean implements Parcelable {
        /**
         * id : 5
         * label : 关注
         * sort : 1
         * api : https://bkbapi.dqdgame.com/data/tab/fav_new?version=0&platform=
         * type : favor
         * default_index : 1
         */

        private int id;
        private String label;
        private int sort;
        private String api;
        private String type;
        private String default_index;

        protected LiveTabsBean(Parcel in) {
            id = in.readInt();
            label = in.readString();
            sort = in.readInt();
            api = in.readString();
            type = in.readString();
            default_index = in.readString();
        }

        public static final Creator<LiveTabsBean> CREATOR = new Creator<LiveTabsBean>() {
            @Override
            public LiveTabsBean createFromParcel(Parcel in) {
                return new LiveTabsBean(in);
            }

            @Override
            public LiveTabsBean[] newArray(int size) {
                return new LiveTabsBean[size];
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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDefault_index() {
            return default_index;
        }

        public void setDefault_index(String default_index) {
            this.default_index = default_index;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(label);
            dest.writeInt(sort);
            dest.writeString(api);
            dest.writeString(type);
            dest.writeString(default_index);
        }
    }
}
