package com.teach.news10.model;

import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseObserver;
import com.teach.news10.Frame.ICommonModel;
import com.teach.news10.Frame.ICommonView;
import com.teach.news10.Frame.INetService;
import com.teach.news10.Frame.NetConfig;
import com.teach.news10.Frame.NetManager;
import com.teach.news10.bean.FavTeamEntity;
import com.teach.news10.bean.LeftMenuInfo;
import com.teach.news10.bean.MatchInfo;
import com.teach.news10.bean.NormalNewsInfo;
import com.teach.news10.bean.ZipImportNewInfo;
import com.teach.news10.utils.LoadStatusConfig;
import com.teach.news10.utils.NormalConfig;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 任小龙 on 2019/6/27.
 */
public class HomeModel implements ICommonModel {
    NetManager mManager = NetManager.getNetManager();
    ZipImportNewInfo zipImportNewInfo = null;
    int page = 1;

    @Override
    public void getData(final ICommonView view, final int whichApi, Object[] t) {

        switch (whichApi) {
            case ApiConfig.CIRCLE_RECONMMOND_VIDEO:
            case ApiConfig.CIRCLE_VIDEO:
                String api = (String) t[0];
                int load = (int) t[1];
                mManager.method(mManager.getNetService().getCircleRecommondAndVideoInfo(api), view, whichApi, load);
                break;
            case ApiConfig.CIRCLE_FAV_DATA:
                String apiFav = (String) t[0];
                mManager.method(mManager.getNetService().getCircleFavInfo(apiFav), view, whichApi, 0);
                break;
            case ApiConfig.CIRCLE_TOPIC_DATA:
                String topicUrl = (String) t[0];
                int loadType = (int) t[1];
                mManager.method(mManager.getNetService().getCircleTopicInfo(topicUrl), view, whichApi, loadType);
                break;
            case ApiConfig.CIRCLE_INFO:
                mManager.method(mManager.getNetService().getCircleInfo((String) t[0]), view, whichApi, 0);
                break;
            case ApiConfig.MATCH_AND_DATA_TAB_DATA:
                mManager.method(mManager.getNetService(NetConfig.DQD_BASE1).getTabFatherInfo(), view, whichApi, 0);
                break;
            case ApiConfig.DATA_FOUR_TAB_OUT:
                String url = (String) t[0];
                mManager.method(mManager.getNetService(NetConfig.DQD_BASE1).getTabFather(url), view, whichApi, 0);
                break;
            case ApiConfig.GET_PAI_MING_DATA:
                String pmUrl = (String) t[1];
                mManager.method(mManager.getNetService(NetConfig.DQD_BASE1).getRankInfo(pmUrl), view, whichApi, (int) t[0]);
                break;
            case ApiConfig.MATCH_INNER_DATA:
                String matchUrl = (String) t[0];
                mManager.method(mManager.getNetService().getMatchInnerData(matchUrl), view, whichApi, (int) t[1]);
                break;
            case ApiConfig.MATCH_INNER_DATA_INDEX:
                mManager.method(mManager.getNetService().getMatchInnerIndexData((String) t[0]), view, whichApi, 0);
                break;
            case ApiConfig.MATCH_INNER_TEAMER_DATA:
                mManager.method(mManager.getNetService().getTeamerInfo((String) t[0]), view, whichApi, 0);
                break;
            case ApiConfig.FAVERATE_CLICK:
                ArrayList<String> idList = (ArrayList<String>) t[0];
                String ids = "";
                for (int i = 0; i < idList.size(); i++) {
                    ids = ids + idList.get(i) + ",";
                }
                String upIds = ids.substring(0, ids.length() - 1);
                mManager.method(mManager.getNetService().markFavorId(upIds), view, whichApi);
                break;
            case ApiConfig.FAVERATE_LIST:
                mManager.method(mManager.getNetService().getFavorList(), view, whichApi);
                break;
            case ApiConfig.HOT_DATA:
                mManager.method(mManager.getNetService().getHotInfo((String) t[0]), view, whichApi, (int) t[1]);
                break;
            case ApiConfig.GET_NORMAL_DATA_WITHOUT_IMPORT:
            case ApiConfig.FIRST_PAGE_VIDEO_INFO:
                String urlNormal = (String) t[0];
                mManager.method(mManager.getNetService().getNormalWithoutImport(urlNormal), view, whichApi, (int) t[1]);
                break;
            case ApiConfig.INS_DATA:
                mManager.method(mManager.getNetService().getINSInfo((String) t[0]), view, whichApi, (int) t[1]);
                break;
            case ApiConfig.CLASS_FICATION_DATA:
                mManager.method(mManager.getNetService().getClassFicationInfo((String) t[0]), view, whichApi);
                break;
            case ApiConfig.SLIDE_LEFT_CENTER_DATA:
                mManager.method(mManager.getNetService().getLeftMenuInfo(), view, whichApi);
                break;
            case ApiConfig.TEST_LIST_DATA:
                int loadType1 = (int) t[0];
                if (loadType1 == LoadStatusConfig.REFRESH_LOAD) {
                    page = 1;
                } else if (loadType1 == LoadStatusConfig.MORE_LOAD) page++;
                String url3 = "http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=119&version=0&platform=";
                mManager.method(mManager.getNetService().getLevelInfo(url3), view, whichApi, (int) t[1]);
                break;
            case ApiConfig.FIRST_PAGE_TITLE_INFO:
                String url1 = "https://bkbapi.dqdgame.com/v2/config/index_menu?mark=gif&platform=android&version=132&android-channel=website";
                mManager.method(mManager.getNetService().getTitleInfo(url1), view, whichApi);
                break;
            case ApiConfig.SLIDE_INFO:
                mManager.method(mManager.getNetService().getSlideInfo(), view, whichApi);
                break;
            case ApiConfig.GET_IMPORT_ZIP_DATA:
                getZipData(view, whichApi, t);
                break;
        }
    }

    private void getZipData(final ICommonView view, final int whichApi, Object[] t) {
        String api = (String) t[0];
        final String match = (String) t[1];
        String faveUrl = (String) t[2];
        final int load = (int) t[3];

        INetService service = NetManager.getNetManager().getNetService();
        Observable<NormalNewsInfo> importNews = service.getImportNews(api);
        final Observable<List<MatchInfo>> matchInfo = service.getMatchInfo(match);
        final Observable<FavTeamEntity> favInfo = service.getFavInfo(faveUrl);
        if (!faveUrl.equals(NormalConfig.NON_NULL))
            zipThree(importNews, matchInfo, favInfo, view, whichApi, load);
        else zipTwo(importNews, matchInfo, view, whichApi, load);
    }

    private void zipTwo(Observable<NormalNewsInfo> importNews, Observable<List<MatchInfo>> matchInfo
            , final ICommonView view, final int whichApi, int load) {
        Observable<ZipImportNewInfo> zip = Observable.zip(importNews, matchInfo, new BiFunction<NormalNewsInfo, List<MatchInfo>, ZipImportNewInfo>() {
            @Override
            public ZipImportNewInfo apply(NormalNewsInfo pNormalNewsInfo, List<MatchInfo> pMatchInfo) throws Exception {
                ZipImportNewInfo zipImportNewInfo = new ZipImportNewInfo();
                List<NormalNewsInfo.RecommendBean> recommend = pNormalNewsInfo.getRecommend();
                List<NormalNewsInfo.ArticlesBean> articles = pNormalNewsInfo.getArticles();
                zipImportNewInfo.recommend = recommend;
                zipImportNewInfo.matchInfo = pMatchInfo;
                zipImportNewInfo.articles = articles;
                return zipImportNewInfo;
            }
        });
        NetManager.getNetManager().method(zip, view, whichApi, load);
    }

    private void zipThree(Observable<NormalNewsInfo> importNews, Observable<List<MatchInfo>> matchInfo, Observable<FavTeamEntity> favInfo
            , final ICommonView view, final int whichApi, final int load) {
        Observable.concat(importNews, matchInfo, favInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object pO) throws Exception {
                        if (zipImportNewInfo == null)
                            zipImportNewInfo = new ZipImportNewInfo();
                        if (pO instanceof NormalNewsInfo) {
                            NormalNewsInfo info = (NormalNewsInfo) pO;
                            zipImportNewInfo.recommend = info.getRecommend();
                            zipImportNewInfo.articles = info.getArticles();
                            zipImportNewInfo.fresh = info.getFresh();
                            zipImportNewInfo.next = info.getNext();
                            zipImportNewInfo.id = info.getId();
                            zipImportNewInfo.label = info.getLabel();
                            zipImportNewInfo.prev = info.getPrev();
                            zipImportNewInfo.hotwords = info.getHotwords();
                        } else if (pO instanceof FavTeamEntity) {
                            FavTeamEntity entity = (FavTeamEntity) pO;
                            zipImportNewInfo.mFavTeamEntity = entity;
                        } else {
                            List<MatchInfo> matchInfo1 = (List<MatchInfo>) pO;
                            zipImportNewInfo.matchInfo = matchInfo1;
                        }
                        if (zipImportNewInfo != null && zipImportNewInfo.articles != null
                                && zipImportNewInfo.matchInfo != null && zipImportNewInfo.mFavTeamEntity != null) {
                            view.onResponse(whichApi, zipImportNewInfo, load);
                            zipImportNewInfo = null;
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable pThrowable) throws Exception {
                        view.onError(whichApi, pThrowable);
                    }
                });
    }
}
