package com.teach.news10.Frame;

import com.example.rxjava.bean.ImageInfo;
import com.teach.news10.bean.CircleFavInfo;
import com.teach.news10.bean.CircleInfo;
import com.teach.news10.bean.CircleTopicInfo;
import com.teach.news10.bean.ClassFicationInfo;
import com.teach.news10.bean.CommitFavInfo;
import com.teach.news10.bean.DataCubaInfo;
import com.teach.news10.bean.DataTabOutInfo;
import com.teach.news10.bean.FavTeamEntity;
import com.teach.news10.bean.FirstPageTitleInfo;
import com.teach.news10.bean.HotInfo;
import com.teach.news10.bean.INSInfo;
import com.teach.news10.bean.LeftMenuInfo;
import com.teach.news10.bean.LevelAndRoundInfo;
import com.teach.news10.bean.MatchInfo;
import com.teach.news10.bean.MatchInnerInfo;
import com.teach.news10.bean.MatchInnerTeamerInfo;
import com.teach.news10.bean.MatchProgressions;
import com.teach.news10.bean.NBAPersonInfo;
import com.teach.news10.bean.NormalNewsInfo;
import com.teach.news10.bean.RankInfo;
import com.teach.news10.bean.RecommondAndVideoInfo;
import com.teach.news10.bean.SlideInfo;
import com.teach.news10.bean.TabFatherInfo;
import com.teach.news10.bean.TeamerResultInfo;
import com.teach.news10.bean.VerifyCodeInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by 任小龙 on 2019/6/27.
 */
public interface INetService {
    @GET("app/global/2/android.json?mark=gif&platform=android&version=132&android-channel=website")
    Observable<LeftMenuInfo> getLeftMenuInfo();

    @GET()
    Observable<LevelAndRoundInfo> getLevelInfo(@Url String url);

    @GET()
    Observable<FirstPageTitleInfo> getTitleInfo(@Url String url);

    @GET("app/global/2/android.json?mark=gif&platform=android&version=132&android-channel=website")
    Observable<SlideInfo> getSlideInfo();

    @GET
    Observable<ClassFicationInfo> getClassFicationInfo(@Url String url);

    @GET
    Observable<INSInfo> getINSInfo(@Url String url);

    @GET()
    Observable<NormalNewsInfo> getNormalWithoutImport(@Url String url);

    @GET
    Observable<NormalNewsInfo> getImportNews(@Url String url);

    @GET
    Observable<List<MatchInfo>> getMatchInfo(@Url String url);

    @GET
    Observable<FavTeamEntity> getFavInfo(@Url String url);

    @GET
    Observable<HotInfo> getHotInfo(@Url String url);

    @GET("v2/league/favlists")
    Observable<FavTeamEntity> getFavorList();

    @POST("v2/team/batch/follow")
    @FormUrlEncoded
    Observable<CommitFavInfo> markFavorId(@Field("team_ids") String ids);

    @GET("sd/biz/index")
    Observable<TabFatherInfo> getTabFatherInfo();

    @GET()
    Observable<List<DataTabOutInfo>> getTabFather(@Url String url);

    @GET()
    Observable<MatchProgressions> getProgress(@Url String url);

    @GET
    Observable<RankInfo> getRankInfo(@Url String url);

    @GET
    Observable<CircleInfo> getCircleInfo(@Url String url);

    @GET
    Observable<RecommondAndVideoInfo> getCircleRecommondAndVideoInfo(@Url String url);

    @POST("/v2/sms/send")
    @FormUrlEncoded
    Observable<VerifyCodeInfo> getVerify(@FieldMap Map<String,String> params, @HeaderMap Map<String,String> maps);

    @POST("/v2/sms/send")
    @Headers("Content-Type:application/json")
    Observable<VerifyCodeInfo> getVerify2(@Body RequestBody pBody);

    @GET
    Observable<CircleFavInfo> getCircleFavInfo(@Url String url);

    @GET
    Observable<CircleTopicInfo> getCircleTopicInfo(@Url String url);

    @GET
    Observable<MatchInnerInfo> getMatchInnerData(@Url String url);

    @GET("data/tab/matchs/max/person")
    Observable<MatchInnerTeamerInfo> getTeamerInfo(@Query("match_ids") String ids);

    @GET
    Observable<List<MatchInnerInfo.ListBean>> getMatchInnerIndexData(@Url String url);

    @GET
    Observable<NBAPersonInfo> getPersonInfo(@Url String url);

    @GET
    Observable<TeamerResultInfo> getTeamerResult(@Url String url);

    @GET
    Observable<DataCubaInfo> getCUBAResult(@Url String url);

    @GET
    Observable<String> getVersion(@Url String url);

    @POST("study/public/file_upload.php")
    Observable<ImageInfo> uploadImage(@Body RequestBody body);
}
