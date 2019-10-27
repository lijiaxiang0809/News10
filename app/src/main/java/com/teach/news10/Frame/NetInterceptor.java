package com.teach.news10.Frame;

import android.content.Context;
import android.util.Log;

import com.teach.news10.jiajun.AES;
import com.teach.news10.jiajun.EncryptionByMD5;
import com.teach.news10.jiajun.RSASignature;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.teach.news10.local_utils.NetworkUtils.isNetworkConnected;

/**
 * Created by 任小龙 on 2019/4/2.
 */
public class NetInterceptor {
    private static volatile NetInterceptor sNetInterceptor;

    private NetInterceptor() {
    }

    public static NetInterceptor getNetInterceptor() {
        if (sNetInterceptor == null) {//考虑效率问题
            synchronized (NetManager.class) {
                if (sNetInterceptor == null) {//考虑多个线程问题
                    sNetInterceptor = new NetInterceptor();
                }
            }
        }
        return sNetInterceptor;
    }

    /**
     * addInterceptor 添加拦截器
     * addNetworkInterceptor 添加网络拦截器，缓存拦截器需要在这个方法和addInterceptor这个里边同时添加
     * sslSocketFactory 信任所有ssl证书，相当于跳过证书
     *
     * @return
     */
    public OkHttpClient getClientWithoutCache() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(getLogInterceptor())
                .addInterceptor(new CommonHeadersInterceptor())
//                .addInterceptor(new CommonParamsInterceptor())
//                .addInterceptor(new CommonParamsPostInterceptor())
                .sslSocketFactory(NetTrustManager.getNetTrustManager().createSSLSocketFactory()).hostnameVerifier(new NetTrustManager.TrustAllHostnameVerifier())
                .build();
    }

    public OkHttpClient getClientWithCache() {
        return new OkHttpClient.Builder()
                .cache(new Cache(new File(Application10.sApplication.getCacheDir(), "NetCache10"), 10 * 1024 * 1024))
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(getLogInterceptor())
                .addInterceptor(new CommonHeadersInterceptor())
                .addInterceptor(cacheInterceptor(Application10.getAppContext()))
                .addNetworkInterceptor(cacheInterceptor(Application10.getAppContext()))
                .sslSocketFactory(NetTrustManager.getNetTrustManager().createSSLSocketFactory()).hostnameVerifier(new NetTrustManager.TrustAllHostnameVerifier())
                .build();
    }

    static HttpLoggingInterceptor getLogInterceptor() {
        //设置log拦截器拦截内容
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("------retrofit-------", message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

    static class CommonHeadersInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .headers(Headers.of(NetHeaders.getHeadMap()))
                    .build();
            return chain.proceed(request);
        }
    }

    static class CommonParamsInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl httpUrl = request.url()
                    .newBuilder()
                    .scheme(request.url().scheme())
                    .host(request.url().host())
                    .addQueryParameter("token1", Application10.getApplication().mToken)
                    .addQueryParameter("token2", "2")
                    .build();
            Request newRequest = request.newBuilder()
                    .method(request.method(), request.body())
                    .url(httpUrl)
                    .build();

            return chain.proceed(newRequest);
        }
    }

    /*static class CommonParamsPostInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            FormBody.Builder formbodyBuilder = new FormBody.Builder();
            formbodyBuilder.add("state", "app");
            formbodyBuilder.add("__openid", "");//无加密-maybe  BaseApp.getInstance().aes.encrypt(userid)
            formbodyBuilder.add("__timestamp", "");
            formbodyBuilder.add("deviceType", "");
            formbodyBuilder.add("deviceToken", new AES().encrypt("ABC"));
            formbodyBuilder.add("currenVersion", "");
            formbodyBuilder.add("buztype", ""); //buztype
            formbodyBuilder.add("userIdType", "");
            formbodyBuilder.add("__para", "");
            String rsa = RSASignature.sign(EncryptionByMD5.getMD5(getSignValue("").getBytes()), RSASignature.RSA_PRIVATE);
            formbodyBuilder.add("__sign", rsa);
            //这个是公共参数的requestBody
            RequestBody formbody = formbodyBuilder.build();
            //这个普通的RequestBody
            RequestBody body = request.body();
            //将普通的requestbody转成了字符串
            String postBodyString = bodyToString(body);
            //（如果有非公共参数，总参数字符串等于非公共参数通过&符号连接公共参数，如果没有，则只有公共参数）
            postBodyString += (postBodyString.length() > 0 ? "&" : "") + bodyToString(formbody);
            Log.i("公共参---->", "postBodyString--->=" + postBodyString);
            Request newRequest = request.newBuilder().post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString)).build();
            return chain.proceed(newRequest);
        }
    }

    private static String getSignValue(String buztype) {
        return "__timestamp=" + timesign
                + "&deviceType=" + BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getDeviceType())
                + "&deviceToken=" + BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getDeviceToken())
                + "&currenVersion=" + BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getCurrenVersion())
                + "&userIdType=" + BaseApp.getInstance().aes.encrypt(BaseApp.getInstance().getUserIdType())
                + "&buztype=" + BaseApp.getInstance().aes.encrypt(buztype);
    }*/

    /**
     * 网络优先数据缓存拦截器
     *
     * @return 拦截器对象
     */
    public static Interceptor cacheInterceptor(final Context context) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();//获取请求
                //没有网络的时候强制使用缓存
                if (!isNetworkConnected(context)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    Log.e("睚眦", "没网读取缓存");
                }
                Response response = chain.proceed(request);
                if (isNetworkConnected(context)) {
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public,max-age" + 0)
                            .build();
                } else {
                    int maxTime = 4 * 24 * 60 * 30;
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public,only-if-cached,max-state=" + maxTime)
                            .build();
                }
            }
        };
        return interceptor;
    }
}
