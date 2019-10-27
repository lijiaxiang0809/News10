package com.teach.news10.Frame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 任小龙 on 2019/4/15.
 */
public class BaseAdapter<T,H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    public Context mContext;
    protected List<T> mListData;

    public BaseAdapter(Context pContext, List<T> pListData) {
        mContext = pContext;
        mListData = pListData;
    }

    private static Unbinder mBind = null;

    public void bindView(H h, int pPosition){};

    public View getLayout(int id) {
        return LayoutInflater.from(mContext).inflate(id, null);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindView((H) holder, position);
    }

    @Override
    public int getItemCount() {
        return mListData != null ? mListData.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            mBind = ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mBind != null) mBind.unbind();
    }

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(int pos);
    }

    public OnItemClickListener mOnItemClickListener;

    public OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener pOnItemClickListener){
        this.mOnItemClickListener = pOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener pOnItemLongClickListener){
        this.mOnItemLongClickListener = pOnItemLongClickListener;
    }
}
