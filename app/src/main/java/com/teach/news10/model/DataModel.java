package com.teach.news10.model;

import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.ICommonModel;
import com.teach.news10.Frame.ICommonView;
import com.teach.news10.Frame.NetConfig;
import com.teach.news10.Frame.NetManager;

/**
 * Created by 任小龙 on 2019/7/11.
 */
public class DataModel implements ICommonModel {
    NetManager manager = NetManager.getNetManager();
    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.GET_SORT_CUBA_DATA:
                String cubaSortUrl = (String) t[1];
                manager.method(manager.getNetService(NetConfig.DQD_BASE1).getCUBAResult(cubaSortUrl), view, whichApi, (int) t[0]);
                break;
            case ApiConfig.GET_MATCH_WITH_DAY:
            case ApiConfig.MATCH_PROGRESS_INFO:
                manager.method(manager.getNetService(NetConfig.DQD_BASE1).getProgress((String) t[0]), view, whichApi, 0);
                break;
            case ApiConfig.DATA_FOUR_TAB_OUT:
                String url = (String) t[0];
                manager.method(manager.getNetService(NetConfig.DQD_BASE1).getTabFather(url), view, whichApi, 0);
                break;
            case ApiConfig.GET_PAI_MING_DATA:
                String pmUrl = (String) t[1];
                manager.method(manager.getNetService(NetConfig.DQD_BASE1).getRankInfo(pmUrl), view, whichApi, (int) t[0]);
                break;
            case ApiConfig.TEAMER_RESULT_INFO:
                manager.method(manager.getNetService().getTeamerResult((String)t[0]),view,whichApi,0);
                break;
            case ApiConfig.MATCH_AND_DATA_TAB_DATA:
                manager.method(manager.getNetService(NetConfig.DQD_BASE1).getTabFatherInfo(), view, whichApi, 0);
                break;
            case ApiConfig.NBA_PERSON_DATA:
                manager.method(manager.getNetService().getPersonInfo((String) t[0]), view, whichApi, 0);
                break;
        }
    }
}
