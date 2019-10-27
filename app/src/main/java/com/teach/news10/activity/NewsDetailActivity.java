package com.teach.news10.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.teach.news10.jscallback.JSCallback;

/**
 * Created by 任小龙 on 2019/7/17.
 */
public class NewsDetailActivity extends BaseNewsDetail {
//    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @SuppressLint("AddJavascriptInterface")
    @Override
    public void initData() {
        super.initData();
        if (mArticlesBean == null) return;
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);// 设置是否可以交互Javascript
        webSettings.setBuiltInZoomControls(true);//允许缩放控制
        webSettings.setUseWideViewPort(true);//让Webivew支持<meta>标签的viewport属性 是否任意比例缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//设置显示模式（狭窄显示）
        //辅助WebView处理JavaScript的对话框，网站图标，网站title，加载进度等
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);// 去掉底部和右边的滚动条
        webSettings.setDomStorageEnabled(true);//允许本地DOM存储
        webSettings.setAllowFileAccess(true);//允许访问文件
        webSettings.setAppCacheEnabled(true);//允许缓存本地
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存模式
        webSettings.setGeolocationEnabled(true);//允许定位
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setTextZoom(100);//设置页面上的文本缩放百分比，默认100。
        webSettings.setBuiltInZoomControls(false);// 设置是否支持缩放
        webSettings.setSupportZoom(false);// 设置是否支持变焦,仅支持双击缩放
        webSettings.setDefaultFontSize(14);
        //在js中调用本地java方法
        webView.addJavascriptInterface(new JSCallback(), "Android");
//        Android 5.0上Webview默认不允许加载Http与Https混合内容
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.loadUrl(mArticlesBean.getUrl());
    }

    public class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Uri url = request.getUrl();
            }
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            showLog("onProgressChanged:" + newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            showLog("onReceivedTitle:" + title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            showLog("onJsAlert");
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
            showLog("onReceivedIcon:" + icon.toString());
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
            showLog("onReceivedTouchIconUrl:" + url);
        }

        @Override
        public boolean onJsTimeout() {
            showToast("加载超时");
            return super.onJsTimeout();
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }
    }

    public class WebViewLoadCallBack implements ValueCallback {

        @Override
        public void onReceiveValue(Object value) {
            showLog(value.toString());
        }
    }

    @Override
    protected void onDestroy() {
        webView.loadUrl("about:blank");
        webView.removeAllViews();
        webView.destroy();
        super.onDestroy();
    }
}
