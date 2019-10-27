package com.teach.news10.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/5/4.
 */
public class ZipImportNewInfo {
    public List<NormalNewsInfo.RecommendBean> recommend;
    public List<NormalNewsInfo.ArticlesBean> articles;
    public List<MatchInfo> matchInfo;
    public FavTeamEntity mFavTeamEntity;
    public int id;
    public String label;
    public String prev;
    public String fresh;
    public String next;
    public int max;
    public int min;
    public int page;
    public String hotwords;
}
