package com.teach.news10.bean;

/**
 * Created by 任小龙 on 2019/5/11.
 */
public class CircleFavInfo {
    /**
     * errno : 1002
     * errmsg : 请先登录
     * data : null
     */

    private int errno;
    private String errmsg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
