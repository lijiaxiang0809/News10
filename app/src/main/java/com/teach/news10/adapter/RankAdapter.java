package com.teach.news10.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.teach.news10.R;
import com.teach.news10.bean.RankInfo;
import com.teach.news10.bean.TempRankInfo;
import com.teach.news10.local_utils.ScreenUtil;

import java.util.List;


/**
 * Created by 任小龙 on 2019/5/3.
 */
public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    private Context mContext;
    private List<TempRankInfo> rankList;
    private String mFatherTab;

    public RankAdapter(Context pContext, List<TempRankInfo> pRankList, String pS) {
        mContext = pContext;
        rankList = pRankList;
        mFatherTab = pS;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.rank_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> data = rankList.get(position).data;
        if (mFatherTab.equals("NBA")) {
            if (position == 0 || position == 6) setData(position, 4, holder.mRecyclerView1, data);
            else if (position == 1 || position == 5)
                setData(position, 2, holder.mRecyclerView1, data);
            else if (position == 2 || position == 3 || position == 4)
                setData(position, 1, holder.mRecyclerView1, data);
        } else {
            if (position == 2 || position == 3 || position == 4)
                setData(position, 1, holder.mRecyclerView1, data);
            else setData(position, 2, holder.mRecyclerView1, data);
        }
    }

    private void setData(int lineNum, int rowNum, RecyclerView pRecyclerView1, List<RankInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> pData) {
        if (mFatherTab.equals("CBA") && (lineNum == 0 || lineNum == 6)) {
            pRecyclerView1.addItemDecoration(new SpaceItemInnerDecoration(ScreenUtil.getPxWidth(mContext) / 4));
        }
        if (lineNum == 1 || lineNum == 5)
            pRecyclerView1.addItemDecoration(new SpaceItemDecoration(ScreenUtil.getPxWidth(mContext) / 16));
        if (lineNum == 2 || lineNum == 4)
            pRecyclerView1.addItemDecoration(new SpaceItemDecoration(ScreenUtil.getPxWidth(mContext) * 3 / 16));
        if (lineNum == 3)
            pRecyclerView1.addItemDecoration(new SpaceItemDecoration(ScreenUtil.getPxWidth(mContext) *5 / 16));
        GridLayoutManager manager = new GridLayoutManager(mContext, rowNum);
        pRecyclerView1.setLayoutManager(manager);
        RecyclerView.Adapter adapter;
        if (lineNum < 3)
            adapter = new RankItemWestAdapter(mContext, pData, lineNum);
        else if (lineNum == 3)
            adapter = new RankCenterItemEastAdapter(mContext, pData, lineNum);
        else adapter = new RankItemEastAdapter(mContext, pData, lineNum);
        pRecyclerView1.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return rankList.size();
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.right = space;
            outRect.left = space;
        }
    }

    public class SpaceItemInnerDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpaceItemInnerDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.right = space;
            } else {
                outRect.left = space;
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView1;

        public ViewHolder(View itemView) {
            super(itemView);
            mRecyclerView1 = itemView.findViewById(R.id.item_recyclerView1);
        }
    }
}
