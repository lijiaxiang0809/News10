package com.teach.news10.presenter;

import android.text.TextUtils;

import com.teach.news10.bean.RankInfo;
import com.teach.news10.bean.TempRankInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 任小龙 on 2019/5/3.
 */
public class DataInnerPresenter {
    public List<TempRankInfo> getRankCbaList(RankInfo.ContentBeanX.RoundsBean pRoundsBean) {
        List<TempRankInfo> tempRankInfoList = new ArrayList<>();
        List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> data = pRoundsBean.getContent().getData();
        List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> nonNullData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (!TextUtils.isEmpty(data.get(i).getTeam_A_name()) && !TextUtils.isEmpty(data.get(i).getTeam_B_name())) {
                nonNullData.add(data.get(i));
            }
        }
        List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> temp1;
        //第一行
        temp1 = new ArrayList<>();
        temp1.add(nonNullData.get(7));
        temp1.add(nonNullData.get(8));
        TempRankInfo tempRankInfo1 = new TempRankInfo();
        tempRankInfo1.data = temp1;
        tempRankInfoList.add(tempRankInfo1);
        //第2行
        temp1 = new ArrayList<>();
        temp1.add(nonNullData.get(3));
        temp1.add(nonNullData.get(4));
        TempRankInfo tempRankInfo2 = new TempRankInfo();
        tempRankInfo2.data = temp1;
        tempRankInfoList.add(tempRankInfo2);
        //第3行
        temp1 = new ArrayList<>();
        temp1.add(nonNullData.get(1));
        TempRankInfo tempRankInfo3 = new TempRankInfo();
        tempRankInfo3.data = temp1;
        tempRankInfoList.add(tempRankInfo3);
        //第4行
        temp1 = new ArrayList<>();
        temp1.add(nonNullData.get(0));
        TempRankInfo tempRankInfo4 = new TempRankInfo();
        tempRankInfo4.data = temp1;
        tempRankInfoList.add(tempRankInfo4);
        //第5行
        temp1 = new ArrayList<>();
        temp1.add(nonNullData.get(2));
        TempRankInfo tempRankInfo5 = new TempRankInfo();
        tempRankInfo5.data = temp1;
        tempRankInfoList.add(tempRankInfo5);
        //第6行
        temp1 = new ArrayList<>();
        temp1.add(nonNullData.get(5));
        temp1.add(nonNullData.get(6));
        TempRankInfo tempRankInfo6 = new TempRankInfo();
        tempRankInfo6.data = temp1;
        tempRankInfoList.add(tempRankInfo6);
        //第7行
        temp1 = new ArrayList<>();
        temp1.add(nonNullData.get(9));
        temp1.add(nonNullData.get(10));
        TempRankInfo tempRankInfo7 = new TempRankInfo();
        tempRankInfo7.data = temp1;
        tempRankInfoList.add(tempRankInfo7);

        return tempRankInfoList;
    }

    public List<TempRankInfo> getRankList(RankInfo.ContentBeanX.RoundsBean pRoundsBean) {
        List<TempRankInfo> tempRankInfoList = new ArrayList<>();
        List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> data = pRoundsBean.getContent().getData();
        List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> temp1;

        temp1 = new ArrayList<>();
        temp1.add(data.get(7));
        temp1.add(data.get(8));
        temp1.add(data.get(9));
        temp1.add(data.get(10));
        TempRankInfo tempRankInfo1 = new TempRankInfo();
        tempRankInfo1.data = temp1;
        tempRankInfoList.add(tempRankInfo1);

        temp1 = new ArrayList<>();
        temp1.add(data.get(3));
        temp1.add(data.get(4));
        TempRankInfo tempRankInfo2 = new TempRankInfo();
        tempRankInfo2.data = temp1;
        tempRankInfoList.add(tempRankInfo2);

        temp1 = new ArrayList<>();
        temp1.add(data.get(1));
        TempRankInfo tempRankInfo3 = new TempRankInfo();
        tempRankInfo3.data = temp1;
        tempRankInfoList.add(tempRankInfo3);

        temp1 = new ArrayList<>();
        temp1.add(data.get(0));
        TempRankInfo tempRankInfo4 = new TempRankInfo();
        tempRankInfo4.data = temp1;
        tempRankInfoList.add(tempRankInfo4);

        temp1 = new ArrayList<>();
        temp1.add(data.get(2));
        TempRankInfo tempRankInfo5 = new TempRankInfo();
        tempRankInfo5.data = temp1;
        tempRankInfoList.add(tempRankInfo5);

        temp1 = new ArrayList<>();
        temp1.add(data.get(5));
        temp1.add(data.get(6));
        TempRankInfo tempRankInfo6 = new TempRankInfo();
        tempRankInfo6.data = temp1;
        tempRankInfoList.add(tempRankInfo6);

        temp1 = new ArrayList<>();
        temp1.add(data.get(11));
        temp1.add(data.get(12));
        temp1.add(data.get(13));
        temp1.add(data.get(14));
        TempRankInfo tempRankInfo7 = new TempRankInfo();
        tempRankInfo7.data = temp1;
        tempRankInfoList.add(tempRankInfo7);

        return tempRankInfoList;
    }
}
