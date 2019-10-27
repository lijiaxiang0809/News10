package com.example.rxjava.model;

import com.example.rxjava.IBackContent;
import com.example.rxjava.IRxModel;
import com.example.rxjava.IService;
import com.example.rxjava.RetrofitManager;
import com.example.rxjava.bean.ChannelInfo;
import com.example.rxjava.bean.NewsInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 任小龙 on 2018/11/7.
 *
 * concatMap和flatMap的功能是一样的， 将一个发射数据的Observable变换为多个Observables，
 * 然后将它们发射的数据放进一个单独的Observable。只不过最后合并ObservablesflatMap采用
 * 的merge，而concatMap采用的是连接(concat)。总之一句一话,他们的区别在于：concatMap是
 * 有序的，flatMap是无序的，concatMap最终输出的顺序与原序列保持一致，而flatMap则不一定，
 * 有可能出现交错。
 */

public class FlatMapModel implements IRxModel {
    @Override
    public void doSomething(final IBackContent back, Object... objects) {
//       doDemo(back);
        doNet(back);
    }

    private void doNet(final IBackContent back) {
        final IService iService = RetrofitManager.getRetrofitManager().getService();
        iService.getChannel()
                //第一个接口请求成功的字段，作为第二个接口的参数，一般使用flatmap
                //<第一个接口返回对象的类型，定义第二个接口类型>
                .flatMap(new Function<ChannelInfo, ObservableSource<NewsInfo>>() {
                    @Override
                    //当第一个接口请求成功后返回的对象
                    public ObservableSource<NewsInfo> apply(ChannelInfo channelInfo) throws Exception {
                        List<ChannelInfo.DataBean.NewsChannelListBean> newsChannelList = channelInfo.getData().getNewsChannelList();
                        String channelId = newsChannelList.get(1).getChannelId();
                        String content = "{\"channelId\":"+"\""+channelId+"\" ,\"cursor\": \"0\",}";
                        RequestBody body = RequestBody.create(null,content);
                        return iService.getNews(body);
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsInfo>() {
                    @Override
                    public void accept(NewsInfo newsInfo) throws Exception {
                        back.getResult(newsInfo.getData().getNewList().size());
                    }
                });
    }

    private void doDemo(final IBackContent back) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
            }
            //flatmap执行的话没有严格的顺序要求，而concatmap有严格的执行顺序
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List list = new ArrayList();
                for (int i = 0;i < 2;i++){
                    list.add(integer+"");
                }
                return Observable.fromIterable(list);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        back.getResult(s);
                    }
                });
    }
}
