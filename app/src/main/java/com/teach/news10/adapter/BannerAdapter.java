package com.teach.news10.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.BannerInfo;
import com.teach.news10.design.banner.BannerLayout;

import java.util.List;


public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MzViewHolder> {
    private Context context;
    private List<BannerInfo> urlList;
    private BannerLayout.OnBannerItemClickListener onBannerItemClickListener;

    public BannerAdapter(Context context, List<BannerInfo> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    public void setOnBannerItemClickListener(BannerLayout.OnBannerItemClickListener onBannerItemClickListener) {
        this.onBannerItemClickListener = onBannerItemClickListener;
    }

    @Override
    public BannerAdapter.MzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MzViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(BannerAdapter.MzViewHolder holder, final int position) {
        if (urlList == null || urlList.isEmpty())
            return;
        Glide.with(context).load(urlList.get(position).imageUrl).into(holder.imageView);
//        holder.describe.setText(urlList.get(position).describe);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBannerItemClickListener != null) {
                    onBannerItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
       return urlList != null ? urlList.size():0;
    }

    class MzViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
//        TextView describe;
        MzViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
//            describe = itemView.findViewById(R.id.des);
        }
    }

}
