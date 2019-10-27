package com.teach.news10.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teach.news10.R;
import com.teach.news10.bean.FavTeamEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2019/5/6.
 */
public class FavorNewAdapter extends RecyclerView.Adapter<FavorNewAdapter.Viewholder> {

    private Context mContext;
    List<FavTeamEntity> data;

    public FavorNewAdapter(Context pContext, List<FavTeamEntity> pData) {
        mContext = pContext;
        data = pData;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(View.inflate(mContext, R.layout.favor_adapter_son_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        if (position < data.size()) {
            holder.teamName.setText(data.get(position).getShort_name());
            Glide.with(mContext).load(data.get(position).getLogo()).into(holder.topImage);
        } else {
            holder.teamName.setText("更多");
            Glide.with(mContext).load(R.drawable.fav_more_three_red_point).into(holder.topImage);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.top_image)
        ImageView topImage;
        @BindView(R.id.team_name)
        TextView teamName;

        public Viewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
