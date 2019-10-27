package com.teach.news10.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/4/30.
 */
public class DataTabOutInfo {
    /**
     * last_modify : 2017-03-28 00:00:00
     * season_id : 119
     * season_name : 2019季后赛
     * sub_tabs : [{"title":"排名","url":"http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=119&platform=&version=0"},{"title":"球员","url":"http://sport-data.dqdgame.com/sd/biz/data/ranking/person?season_id=119&platform=&version=0"},{"title":"球队","url":"http://sport-data.dqdgame.com/sd/biz/data/ranking/team?season_id=119&platform=&version=0"},{"title":"赛程","url":"http://sport-data.dqdgame.com/sd/biz/data/schedule?season_id=119&platform=&version=0"}]
     */

    private String last_modify;
    private String season_id;
    private String season_name;
    private List<SubTabsBean> sub_tabs;

    public String getLast_modify() {
        return last_modify;
    }

    public void setLast_modify(String last_modify) {
        this.last_modify = last_modify;
    }

    public String getSeason_id() {
        return season_id;
    }

    public void setSeason_id(String season_id) {
        this.season_id = season_id;
    }

    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

    public List<SubTabsBean> getSub_tabs() {
        return sub_tabs;
    }

    public void setSub_tabs(List<SubTabsBean> sub_tabs) {
        this.sub_tabs = sub_tabs;
    }

    public static class SubTabsBean {
        /**
         * title : 排名
         * url : http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=119&platform=&version=0
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
}
