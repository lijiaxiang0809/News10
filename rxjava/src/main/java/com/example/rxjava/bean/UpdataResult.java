package com.example.rxjava.bean;


import java.io.Serializable;

/**
 * Created by 任小龙 on 2018/9/25.
 */

public class UpdataResult implements Serializable {
    private static final long serialVersionUID = -6485947139120274898L;
    public UpdataInfo data;
    public String code;
    public static class UpdataInfo implements Serializable{
        public String headImagePath;
    }
}
