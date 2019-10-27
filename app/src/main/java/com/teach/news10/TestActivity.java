package com.teach.news10;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.teach.news10.Frame.BaseMvpActivity;
import com.teach.news10.activity.HomeActivity;
import com.teach.news10.design.CommonTitle;
import com.teach.news10.model.HomeModel;

public class TestActivity extends BaseMvpActivity<HomeModel> {


    private WebView webView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        CommonTitle title = findViewById(R.id.title);
        title.mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                webView.loadUrl("javascript:showAlert()");
                webView.evaluateJavascript("javascript:showAlert()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        showToast(value);
                    }
                });
            }
        });

        title.mTvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                webView.evaluateJavascript("javascript:showContent(\"传进来的参数\")", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        showToast(value);
                    }
                });
                return true;
            }
        });

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        //允许使用JS
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl("file:///android_asset/policy.html");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(TestActivity.this);
                b.setTitle("标题");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        showLog(e.getMessage() != null ? e.getMessage() + ":" + whichApi : "");
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {

        }
    }

    public void jump(View view) {
//        startActivity(new Intent(this, SplashActivity.class));
        startActivity(new Intent(this, HomeActivity.class));
    }
}
