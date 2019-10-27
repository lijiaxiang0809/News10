
package com.teach.news10.jscallback;

import android.webkit.JavascriptInterface;

import com.teach.news10.Frame.Application10;
import com.teach.news10.local_utils.HtmlUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by hujinghui on 16/8/31.
 * webview里js回调本地方法的接口,尽量全部覆盖方法,避免h5回调没有实现的方法时抛异常导致后续逻辑如显示图片无法继续
 */

public interface WebViewJsInterface {
    @JavascriptInterface
    void getAuthorization();

    @JavascriptInterface
    void setGalleries(String response);

    @JavascriptInterface
    void gallery(String response);

    @JavascriptInterface
    void play(String site, String src, String hash, String refer);

    @JavascriptInterface
    void play(String type, String src, String stream);

    @JavascriptInterface
    void startBrowser(final String url, final int target, final String title);

    @JavascriptInterface
    void appShare(String share);

    @JavascriptInterface
    void empty();

    @JavascriptInterface
    void isShowTitle();

    @JavascriptInterface
    void postReport();

    @JavascriptInterface
    void addLoading();

    @JavascriptInterface
    void removeLoading();

    @JavascriptInterface
    void resetHeight(int height);

    /**
     * 网页内部带header跳转
     *
     * @param url
     */
    @JavascriptInterface
    void jumpInnerPage(String url);


    @JavascriptInterface
    void payMoney(String json);


    @JavascriptInterface
    void shareUrl(String url, String title, String desc);

    @JavascriptInterface
    void cancel_order(String orderId);

    @JavascriptInterface
    void pay(String orderId, String orderPrice);

    @JavascriptInterface
    String getDeviceInfo();

//    @JavascriptInterface
//    String getHostsList();

    @JavascriptInterface
    String replaceAdsParams(String url);


    //广告location的时候会自动decode url，导致参数丢失，没有找到原因，先用这个方法代替广告的跳转
    @JavascriptInterface
    void adRedirect(String url);

    class BaseWebViewJsInterface implements WebViewJsInterface {

        @Override
        @JavascriptInterface
        public void getAuthorization() {

        }

        @Override
        @JavascriptInterface
        public void setGalleries(String response) {

        }

        @Override
        @JavascriptInterface
        public void gallery(String response) {

        }

        @Override
        @JavascriptInterface
        public void play(String site, String src, String hash, String refer) {

        }

        @Override
        @JavascriptInterface
        public void play(String type, String src, String stream) {

        }

        @Override
        @JavascriptInterface
        public void startBrowser(String url, int target, String title) {

        }

        @Override
        @JavascriptInterface
        public void appShare(String share) {

        }

        @Override
        @JavascriptInterface
        public void empty() {

        }

        @Override
        @JavascriptInterface
        public void isShowTitle() {

        }

        @Override
        @JavascriptInterface
        public void postReport() {

        }

        @Override
        @JavascriptInterface
        public void addLoading() {

        }

        @Override
        @JavascriptInterface
        public void removeLoading() {

        }

        @Override
        @JavascriptInterface
        public void resetHeight(int height) {

        }

        @Override
        @JavascriptInterface
        public void jumpInnerPage(String url) {

        }

        @Override
        @JavascriptInterface
        public void payMoney(String json) {

        }

        @Override
        @JavascriptInterface
        public void shareUrl(String url, String title, String desc) {

        }

        @Override
        @JavascriptInterface
        public void cancel_order(String orderId) {

        }

        @Override
        @JavascriptInterface
        public void pay(String orderNo, String orderPrice) {

        }

        @Override
        @JavascriptInterface
        public String replaceAdsParams(String url) {
            return HtmlUtil.replaceAdsUrlParams(Application10.getAppContext(), url);
        }

        @Override
        @JavascriptInterface
        public void adRedirect(String url) {

        }

        @Override
        @JavascriptInterface
        public String getDeviceInfo() {
            JSONObject jsonObject = new JSONObject();
            Map<String, String> params = HtmlUtil.getAdsParams(Application10.getAppContext(), 1);
            params.put("pos", "dqd_/t_android_/t_article_/t_0");
            if (params != null && !params.isEmpty()) {
                Iterator<String> it = params.keySet().iterator();
                String key;
                while (it.hasNext()) {
                    key = it.next();
                    try {
                        jsonObject.put(key, params.get(key));
                    } catch (JSONException pE) {
                        pE.printStackTrace();
                    }
                }
            }
            return jsonObject.toString();
        }
    }
}
