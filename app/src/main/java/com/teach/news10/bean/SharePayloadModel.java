package com.teach.news10.bean;

import java.io.Serializable;

/**
 * Created by qiaoliang on 2017/11/16.
 */

public class SharePayloadModel implements Serializable {

    public String url;
    public String share_title;
    public String title;
    public String description;
    public String share_thumb_url;

    public SharePayloadModel() {
    }

}
