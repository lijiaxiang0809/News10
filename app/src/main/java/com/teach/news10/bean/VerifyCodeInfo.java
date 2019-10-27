package com.teach.news10.bean;

/**
 * Created by lhy on 15/7/13.
 */
public class VerifyCodeInfo {
    public boolean success;

    public String verify_token;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getVerify_token() {
        return verify_token;
    }

    public void setVerify_token(String verify_token) {
        this.verify_token = verify_token;
    }
}
