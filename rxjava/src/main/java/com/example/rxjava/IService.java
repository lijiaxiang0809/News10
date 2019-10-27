package com.example.rxjava;

import com.example.rxjava.bean.ChannelInfo;
import com.example.rxjava.bean.NewsInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by 任小龙 on 2018/11/7.
 */

public interface IService {
    @POST("firstga/app/news/listNewsChannel")
    Observable<ChannelInfo> getChannel();

    @POST("firstga/app/news/downListNews")
    @Headers("Content-Type:application/json")
    Observable<NewsInfo> getNews(@Body RequestBody body);
}
