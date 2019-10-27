package com.example.rxjava;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 任小龙 on 2018/11/14.
 */

public class RetrofitManager {
    public static RetrofitManager sRetrofitManager;
    public static String DYTH_BASEURL = "https://www.firstgainfo.com/firstga/app/";

    private RetrofitManager() {
    }

    public static RetrofitManager getRetrofitManager() {
        if (sRetrofitManager == null){
            synchronized (RetrofitManager.class){
                if (sRetrofitManager == null)
                    sRetrofitManager = new RetrofitManager();
            }
        }
        return sRetrofitManager;
    }

    public IService getService(){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.firstgainfo.com/")
                .build().create(IService.class);
    }
}
