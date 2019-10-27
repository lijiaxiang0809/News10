package com.teach.news10.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wanglong on 2019/2/28.
 */

public class FavTeamEntity implements Parcelable,Serializable {


    private static final long serialVersionUID = -5663060898651575973L;

    public FavTeamEntity(){}

    public FavTeamEntity(Parcel in) {
        team_list = in.createTypedArrayList(FavTeamEntity.CREATOR);
        viewType = in.readInt();
        list = in.createTypedArrayList(FavTeamEntity.CREATOR);
        title = in.readString();
        name = in.readString();
        logo = in.readString();
        short_name = in.readString();
        id = in.readString();
        is_followed = in.readByte() != 0;
    }

    public static final Creator<FavTeamEntity> CREATOR = new Creator<FavTeamEntity>() {
        @Override
        public FavTeamEntity createFromParcel(Parcel in) {
            return new FavTeamEntity(in);
        }

        @Override
        public FavTeamEntity[] newArray(int size) {
            return new FavTeamEntity[size];
        }
    };

    @Override
    public String toString() {
        return "FavTeamEntity{" +
                "team_list=" + team_list +
                ", selected_teams=" + selected_teams +
                ", viewType=" + viewType +
                ", list=" + list +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", short_name='" + short_name + '\'' +
                ", id='" + id + '\'' +
                ", is_followed=" + is_followed +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(team_list);
        dest.writeStringList(selected_teams);
        dest.writeInt(viewType);
        dest.writeTypedList(list);
        dest.writeString(title);
        dest.writeString(name);
        dest.writeString(logo);
        dest.writeString(short_name);
        dest.writeString(id);
        dest.writeByte((byte) (is_followed ? 1 : 0));
    }

    public interface ViewType {
        int TYPE_LIST = 0;

        int TYPE_TITLE = 1;

        int TYPE_TITLE2 = 2;
    }

    /**
     * team_list : [{"list":[{"name":"休斯敦火箭","logo":"http://img1.dqdgame.com/fastdfs/M00/00/24/ChM9m1vGrr6Ae3A_AAAtr2JLoW4150.png","short_name":"火箭","id":"16"},{"name":"洛杉矶湖人","logo":"http://img1.dqdgame.com/fastdfs/M00/00/35/ChM9m1vJUWOAMZTcAABF2EuzBG4204.png","short_name":"湖人","id":"26"},{"name":"多伦多猛龙","logo":"http://img1.dqdgame.com/fastdfs/M00/00/04/ChM9m1u_HxmAXSW6AAA7haimyQ4555.png","short_name":"猛龙","id":"1"},{"name":"费城76人","logo":"http://img1.dqdgame.com/fastdfs/M00/00/04/ChM9m1u_G7eAFO_pAABFGVQtzWQ853.png","short_name":"76人","id":"3"},{"name":"辽宁本钢","logo":"https://img1.dqdgame.com/fastdfs/M00/00/74/ChM9m1vabMGAMg6AAABT0rWM6ZE024.png","short_name":"辽宁","id":"32"},{"name":"北京首钢","logo":"https://img1.dqdgame.com/fastdfs/M00/00/74/ChM9m1vabMGAB76EAABtd0NmZ3A779.png","short_name":"北京","id":"37"},{"name":"广东东莞银行","logo":"https://img1.dqdgame.com/fastdfs/M00/00/73/ChOlBlvabMGAeNOrAAB83qV7rYg464.png","short_name":"广东","id":"33"},{"name":"上海哔哩哔哩","logo":"https://img1.dqdgame.com/fastdfs/M00/00/74/ChOlBlvabMOARp2hAACXOQmF92U606.png","short_name":"上海","id":"40"},{"name":"四川五粮金樽","logo":"https://img1.dqdgame.com/fastdfs/M00/00/74/ChM9m1vabLiAVDZSAABwureBgn4818.png","short_name":"四川","id":"49"}],"title":"热门"},{},{}]
     * selected_teams : ["1","16"]
     */
    private ArrayList<FavTeamEntity> team_list;

    private ArrayList<String> selected_teams;

    private int viewType;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public void setTeam_list(ArrayList<FavTeamEntity> team_list) {
        this.team_list = team_list;
    }

    public void setSelected_teams(ArrayList<String> selected_teams) {
        this.selected_teams = selected_teams;
    }

    public ArrayList<FavTeamEntity> getTeam_list() {
        return team_list;
    }

    public ArrayList<String> getSelected_teams() {
        return selected_teams;
    }

    /**
     * list : [{"name":"休斯敦火箭","logo":"http://img1.dqdgame.com/fastdfs/M00/00/24/ChM9m1vGrr6Ae3A_AAAtr2JLoW4150.png","short_name":"火箭","id":"16"},{"name":"洛杉矶湖人","logo":"http://img1.dqdgame.com/fastdfs/M00/00/35/ChM9m1vJUWOAMZTcAABF2EuzBG4204.png","short_name":"湖人","id":"26"},{"name":"多伦多猛龙","logo":"http://img1.dqdgame.com/fastdfs/M00/00/04/ChM9m1u_HxmAXSW6AAA7haimyQ4555.png","short_name":"猛龙","id":"1"},{"name":"费城76人","logo":"http://img1.dqdgame.com/fastdfs/M00/00/04/ChM9m1u_G7eAFO_pAABFGVQtzWQ853.png","short_name":"76人","id":"3"},{"name":"辽宁本钢","logo":"https://img1.dqdgame.com/fastdfs/M00/00/74/ChM9m1vabMGAMg6AAABT0rWM6ZE024.png","short_name":"辽宁","id":"32"},{"name":"北京首钢","logo":"https://img1.dqdgame.com/fastdfs/M00/00/74/ChM9m1vabMGAB76EAABtd0NmZ3A779.png","short_name":"北京","id":"37"},{"name":"广东东莞银行","logo":"https://img1.dqdgame.com/fastdfs/M00/00/73/ChOlBlvabMGAeNOrAAB83qV7rYg464.png","short_name":"广东","id":"33"},{"name":"上海哔哩哔哩","logo":"https://img1.dqdgame.com/fastdfs/M00/00/74/ChOlBlvabMOARp2hAACXOQmF92U606.png","short_name":"上海","id":"40"},{"name":"四川五粮金樽","logo":"https://img1.dqdgame.com/fastdfs/M00/00/74/ChM9m1vabLiAVDZSAABwureBgn4818.png","short_name":"四川","id":"49"}]
     * title : 热门
     */
    private ArrayList<FavTeamEntity> list;
    private String title;

    public void setList(ArrayList<FavTeamEntity> list) {
        this.list = list;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<FavTeamEntity> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }

    /**
     * name : 休斯敦火箭
     * logo : http://img1.dqdgame.com/fastdfs/M00/00/24/ChM9m1vGrr6Ae3A_AAAtr2JLoW4150.png
     * short_name : 火箭
     * id : 16
     */
    private String name;
    private String logo;
    private String short_name;
    private String id;
    private boolean is_followed;

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public String getShort_name() {
        return short_name;
    }

    public String getId() {
        return id;
    }

    public boolean isFollowed() {
        return is_followed;
    }

    public void setFollowed(boolean followed) {
        this.is_followed = followed;
    }
}
