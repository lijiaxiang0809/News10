package com.teach.news10.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseAdapter;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.activity.FavlistsActivity;
import com.teach.news10.activity.HomeActivity;
import com.teach.news10.adapter.FaverateAdapter;
import com.teach.news10.bean.CommitFavInfo;
import com.teach.news10.bean.FavTeamEntity;
import com.teach.news10.local_utils.SharedPrefrenceUtils;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.NormalConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FaverateFragment extends BaseMvpFragment<HomeModel> implements BaseAdapter.OnItemClickListener, View.OnClickListener {
    private static final String NORMAL_DATA = "normal_data";
    private static final String SELECTED_DATA = "selected_data";
    private ArrayList<FavTeamEntity> mArrayList;
    private ArrayList<String> mSelectedList;
    private List<FavTeamEntity> mSelectedGirlFriend = new ArrayList<>();
    private FaverateAdapter mAdapter;
    private String mClickId;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static FaverateFragment newInstance(ArrayList<FavTeamEntity> list, ArrayList<String> mSelectedList) {
        FaverateFragment fragment = new FaverateFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(NORMAL_DATA, list);
        args.putStringArrayList(SELECTED_DATA, mSelectedList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArrayList = getArguments().getParcelableArrayList(NORMAL_DATA);
            mSelectedList = getArguments().getStringArrayList(SELECTED_DATA);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_faverate;
    }

    @Override
    public void initView() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new FaverateAdapter(mArrayList, mSelectedList, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        FavlistsActivity activity = (FavlistsActivity) getActivity();
        activity.title.mMoreText1.setOnClickListener(this);
    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onItemClick(int pos) {
        mClickId = mArrayList.get(pos).getId();
        if (mSelectedList.contains(mClickId)){
            mSelectedList.remove(mClickId);
        }
        else {
            mSelectedList.add(mClickId);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        showLog(e.getMessage() + whichApi);
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        hideLoadingDialog();
        CommitFavInfo commitFavInfo = (CommitFavInfo) t[0];
        if (commitFavInfo.getMessage() != null && commitFavInfo.getMessage().equals("success")) {
        } else showToast(getString(R.string.commit_error));
    }

    @Override
    public void onClick(View v) {
        if (mSelectedList != null && mSelectedList.size() !=0) {
            showLoadingDialog();
//            mPresenter.getData(ApiConfig.FAVERATE_CLICK, mSelectedList);//提交接口不能用了
            Observable.timer(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long pLong) throws Exception {
                            if (mArrayList != null && mSelectedList != null && mArrayList.size() != 0 && mSelectedList.size() != 0 ){
                                for (int i = 0 ;i < mArrayList.size();i++){
                                    if (mSelectedList.contains(mArrayList.get(i).getId())){
                                        mSelectedGirlFriend.add(mArrayList.get(i));
                                    }
                                }
                            }
                            SharedPrefrenceUtils.putSerializableList(getContext(), NormalConfig.CATCH_GIRL_FRIEND,mSelectedGirlFriend);
                            startActivity(new Intent(getContext(), HomeActivity.class));
                            hideLoadingDialog();
                            getActivity().finish();
                        }
                    });
        } else showToast("你提交0个搞毛线");
    }
}
