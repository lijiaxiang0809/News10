package com.teach.news10.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/6/27.
 */
public class LeftMenuInfo {

    public LeftMenuConfig config;

    public List<Modules> modules;

    public class LeftMenuConfig{
        public String sidebar_bg_image;
    }
    public class Modules {
        public String id;
        public String name;
        public String image;
        public String scheme;
    }
}
