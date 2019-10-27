package com.example.rxjava.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2018/11/7.
 */

public class ChannelInfo {
    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<NewsChannelListBean> newsChannelList;

        public List<NewsChannelListBean> getNewsChannelList() {
            return newsChannelList;
        }

        public void setNewsChannelList(List<NewsChannelListBean> newsChannelList) {
            this.newsChannelList = newsChannelList;
        }

        public static class NewsChannelListBean {
            /**
             * channelId : 0
             * channelName : 资讯
             */

            private String channelId;
            private String channelName;

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getChannelName() {
                return channelName;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
            }
        }
    }
}
