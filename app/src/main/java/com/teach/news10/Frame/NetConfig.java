package com.teach.news10.Frame;

/**
 * Created by 任小龙 on 2019/6/27.
 */
public class NetConfig {
    public static String BaseUrl;
    public static int API_TYPE = 1;//1:正式服务器 2：外测服务器 3：内测服务器
    public static String DQD_BASE1 = "http://sport-data.dqdgame.com/";

    static {
        if (API_TYPE == 1) BaseUrl = "https://bkbapi.dqdgame.com/";
        else if (API_TYPE == 2) BaseUrl = "";
        else BaseUrl = "";
    }
}
