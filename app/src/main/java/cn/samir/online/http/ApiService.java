package cn.samir.online.http;


import java.util.ArrayList;

import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.CategoryDetail;
import cn.samir.domains.model.IssueList;
import cn.samir.domains.model.IssueNavigationCard;
import cn.samir.domains.model.MessageListModel;
import cn.samir.domains.model.TabInfoResult;
import cn.samir.domains.model.configs.Configs;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by xiaw on 2017/3/10 0010.
 */

public interface ApiService {


    String action_rankList = "rankList";
    String action_tag = "tag";
    String action_category = "category";
    String action_campaign = "campaign";


    String baseUrl = "http://baobab.kaiyanapp.com/";

    String url_api_v4 = "api/v4/";
    String url_api_v3 = "api/v3/";
    String url_api_v2 = "api/v2/";

    String followUrl = "http://baobab.kaiyanapp.com/" + url_api_v4 + "tabs/follow";

    String relativeUrl = "http://baobab.kaiyanapp.com/" + url_api_v4 + "video/related";

    //@Headers("Cache-Control: public, max-age=43200")
    @GET("api/v4/tabs/selected")
    Call<ApiResult<ArrayList<BaseModel>>> loadHome();
    //下一页

    @GET
    Call<ApiResult<ArrayList<BaseModel>>> loadUrl(@Url String url);

    @GET("api/v4/discovery")
    Call<TabInfoResult> loadFindCate();

    @GET
    Call<ApiResult<ArrayList<BaseModel>>> loadFindCateList(@Url String url);

    @GET("api/v3/queries/hot")
    Call<ArrayList<String>> loadHotKeyWord();

    @GET("api/v1/search")
    Call<ApiResult<ArrayList<BaseModel>>> loadByKeyWord(@Query("query") String query);


    @GET("api/v3/feed/issueNavigationList")
    Call<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>> loadIssueNavigationCard(@Query("date") long date);

    @GET
    Call<ApiResult<ArrayList<BaseModel<IssueNavigationCard>>>> loadIssueNavigationCard(@Url String nextPage);


    @GET("api/v2/feed")
    Call<ApiResult<ArrayList<IssueList>>> loadFeedHistory(@Query("num") int number, @Query("date") long date);


    @GET("/api/v4/categories/detail/tab")
    Call<CategoryDetail> loadCategoryDetailTab(@Query("id") int id);

    @GET("/api/v4/pgcs/detail/tab")
    Call<CategoryDetail> loadPcgsDetailTab(@Query("id") int id);


    @GET
    Call<TabInfoResult> loadTab(@Url String url);

    @GET("/api/v2/configs")
    Call<Configs> loadConfigs();


    @GET("/api/v2/push/common")
    Call loadNotifycation();


    @GET("/api/v3/messages")
    Call<MessageListModel> loadMessages();


}
