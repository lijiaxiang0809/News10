package com.teach.news10.bean;

/**
 * Created by 任小龙 on 2019/7/25.
 */
public class LoginInfo {
    public String headPath;
    public String token;
    public String msg;
    public String nick;

    public LoginInfo(String pHeadPath, String pToken, String pMsg,String nick) {
        headPath = pHeadPath;
        token = pToken;
        msg = pMsg;
        this.nick = nick;
    }
}
