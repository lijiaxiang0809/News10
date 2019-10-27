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
import com.teach.news10.bean.INSInfo;
import com.teach.news10.design.RoundOrCircleImage;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/5/12.
 */
public class INSAdapter extends RecyclerView.Adapter<INSAdapter.ViewHolder> {

    private Context mContext;
    private List<INSInfo.FeedlistBean> mList;

    public INSAdapter(Context pContext, List<INSInfo.FeedlistBean> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.ins_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        INSInfo.FeedlistBean bean = mList.get(position);
        GlideEngine.loadImage(holder.avarter,bean.getAvatar());
        holder.text1.setText(bean.getAccount());
        holder.text2.setText(bean.getNote());
        holder.text3.setText(bean.getPublished_at());
        holder.text4.setText(bean.getOriginal_text()+"\n"+bean.getAuto_translation());
        if (bean.getAlbum().getPics() != null && bean.getAlbum().getPics().size() != 0){
            INSInfo.FeedlistBean.AlbumBean.PicsBean picsBean = bean.getAlbum().getPics().get(0);
            GlideEngine.loadImageWithSize(mContext,picsBean.getWidth(),picsBean.getHeight(),holder.image,picsBean.getUrl());
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avarter)
        RoundOrCircleImage avarter;
        @BindView(R.id.text1)
        TextView text1;
        @BindView(R.id.text2)
        TextView text2;
        @BindView(R.id.text3)
        TextView text3;
        @BindView(R.id.text4)
        TextView text4;
        @BindView(R.id.image)
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
