package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.Frame.BaseAdapter;
import com.teach.news10.Frame.OnRecyclerItemClick;
import com.teach.news10.R;
import com.teach.news10.bean.SlideInfo;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 任小龙 on 2019/4/20.
 */
public class SlideAdapter extends BaseAdapter<SlideInfo.ModulesBean, SlideAdapter.ViewHolder> {

    public SlideAdapter(Context pContext, List<SlideInfo.ModulesBean> pListData) {
        super(pContext, pListData);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.slide_layout, null));
    }

    @Override
    public void bindView(ViewHolder pViewHolder, final int pPosition) {
        super.bindView(pViewHolder, pPosition);
        Glide.with(mContext).load(mListData.get(pPosition).getImage()).into(pViewHolder.image);
        pViewHolder.slideText.setText(mListData.get(pPosition).getName());
        pViewHolder.favorItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClick != null)mClick.onItemClick(pPosition);
            }
        });
    }

    public OnRecyclerItemClick mClick;

    public void setItemClick(OnRecyclerItemClick pItemClick){
        mClick = pItemClick;
    }

    static class ViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.slide_text)
        TextView slideText;
        @BindView(R.id.favor_item)
        RelativeLayout favorItem;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
