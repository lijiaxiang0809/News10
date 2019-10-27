package com.teach.news10.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/5/7.
 */
public class CircleInfo {
    /**
     * errno : 0
     * errmsg : success
     * data : {"list":[{"label":"关注","type":"fav","api":"http://bkbapi.dqdgame.com/group/app/thread/followList"},{"label":"推荐","type":"normal","api":"http://bkbapi.dqdgame.com/group/app/thread/feedList"},{"label":"视频","type":"normal","api":"http://bkbapi.dqdgame.com/group/app/thread/videoList"},{"label":"话题","type":"topic","api":"http://bkbapi.dqdgame.com/group/app/topic/list"}],"default_index":1}
     */

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"label":"关注","type":"fav","api":"http://bkbapi.dqdgame.com/group/app/thread/followList"},{"label":"推荐","type":"normal","api":"http://bkbapi.dqdgame.com/group/app/thread/feedList"},{"label":"视频","type":"normal","api":"http://bkbapi.dqdgame.com/group/app/thread/videoList"},{"label":"话题","type":"topic","api":"http://bkbapi.dqdgame.com/group/app/topic/list"}]
         * default_index : 1
         */

        private int default_index;
        private List<ListBean> list;

        public int getDefault_index() {
            return default_index;
        }

        public void setDefault_index(int default_index) {
            this.default_index = default_index;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * label : 关注
             * type : fav
             * api : http://bkbapi.dqdgame.com/group/app/thread/followList
             */

            private String label;
            private String type;
            private String api;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
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
        }
    }
}
