package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.LevelAndRoundInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/6/27.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    List<LevelAndRoundInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> mList;
    Context mContext;

    public TestAdapter(List<LevelAndRoundInfo.ContentBeanX.RoundsBean.ContentBean.DataBean> pList, Context pContext) {
        mList = pList;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.test_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mList == null) return;
        LevelAndRoundInfo.ContentBeanX.RoundsBean.ContentBean.DataBean dataBean = mList.get(position);
        Glide.with(mContext).load(dataBean.getTeam_A_logo()).into(holder.leftImage);
        Glide.with(mContext).load(dataBean.getTeam_B_logo()).into(holder.rightImage);
        holder.leftText.setText(dataBean.getTeam_A_name());
        holder.rightText.setText(dataBean.getTeam_B_name());
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.left_image)
        ImageView leftImage;
        @BindView(R.id.left_text)
        TextView leftText;
        @BindView(R.id.right_image)
        ImageView rightImage;
        @BindView(R.id.right_text)
        TextView rightText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
