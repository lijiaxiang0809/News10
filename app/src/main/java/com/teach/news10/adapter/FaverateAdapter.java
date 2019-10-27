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
import com.teach.news10.R;
import com.teach.news10.bean.FavTeamEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/4/18.
 */
public class FaverateAdapter extends RecyclerView.Adapter<FaverateAdapter.ViewHolder> {

    private ArrayList<FavTeamEntity> mArrayList;
    private ArrayList<String> mSelectedList;
    private Context mContext;

    public FaverateAdapter(ArrayList<FavTeamEntity> pArrayList, ArrayList<String> pSelectedList, Context pContext) {
        mArrayList = pArrayList != null ? pArrayList : new ArrayList<FavTeamEntity>();
        mSelectedList = pSelectedList != null ? pSelectedList : new ArrayList<String>();
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.faverate_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(mContext).load(mArrayList.get(position).getLogo()).into(holder.image);
        holder.teamName.setText(mArrayList.get(position).getShort_name()!=null?mArrayList.get(position).getShort_name():"");
        if (mSelectedList.contains(mArrayList.get(position).getId())){
            holder.bottomImage.setVisibility(View.VISIBLE);
            holder.item.setBackgroundResource(R.drawable.faverate_bg_seleted);
        } else {
            holder.bottomImage.setVisibility(View.GONE);
            holder.item.setBackgroundResource(R.drawable.faverate_bg_normal);
        }
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null)mOnItemClickListener.onItemClick(position);
            }
        });
    }

    public BaseAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(BaseAdapter.OnItemClickListener pOnItemClickListener){
        this.mOnItemClickListener = pOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.bottom_image)
        ImageView bottomImage;
        @BindView(R.id.item)
        RelativeLayout item;
        @BindView(R.id.team_name)
        TextView teamName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
