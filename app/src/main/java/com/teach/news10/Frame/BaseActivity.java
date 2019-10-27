package com.teach.news10.Frame;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.teach.news10.R;
import com.teach.news10.boradcast.NetStatusBroadCast;
import com.teach.news10.design.LoadingDialogWithContent;
import com.teach.news10.utils.NormalConfig;

import static com.scwang.smartrefresh.layout.util.DensityUtil.px2dp;
import static com.teach.news10.local_utils.NetworkUtils.NETWORK_MOBILE;
import static com.teach.news10.local_utils.NetworkUtils.NETWORK_NONE;
import static com.teach.news10.local_utils.NetworkUtils.NETWORK_WIFI;

/**
 * Created by 任小龙 on 2019/6/27.
 */
public class BaseActivity extends AppCompatActivity implements NetStatusBroadCast.NetStatusListener {

    private LinearLayoutManager mManager;
    public Application10 mApplication;
    public NetStatusBroadCast mNetStatusBroadCast;
    private LoadingDialogWithContent mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLog("我就是一个类：" + this.getClass().getSimpleName());
        mApplication = (Application10) getApplication();
        mDialog = new LoadingDialogWithContent(this, getString(R.string.loading));
    }
    public void showLoadingDialog() {
        if (!mDialog.isShowing()) mDialog.show();
    }

    public void hideLoadingDialog() {
        if (mDialog.isShowing()) mDialog.dismiss();
    }

    public void showLog(Object content) {
        Log.e(NormalConfig.log1, content.toString());
    }

    public void showLog(boolean content) {
        Log.e(NormalConfig.log1, "" + content);
    }

    public void initRecycleView(RecyclerView recyclerView, RefreshLayout refreshLayout) {
        mManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mManager);
        if (refreshLayout != null) {
            refreshLayout.setHeaderHeight(px2dp(120));
            refreshLayout.setFooterHeight(px2dp(100));
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    refresh();
                }
            });
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    loadMore();
                }
            });
        }
    }

    public void registerNetWorkStatus() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetStatusBroadCast = new NetStatusBroadCast();
        mNetStatusBroadCast.setNetStatusListener(this);
        registerReceiver(mNetStatusBroadCast, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog != null){
            if (mDialog.isShowing())mDialog.cancel();
        }
    }

    protected int getLoadType(Object[] t) {
        return (int) ((Object[]) t[1])[0];
    }

    public void refresh() {
    }

    public void loadMore() {
    }

    public void showToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNetChanged(int state) {
        if (state == NETWORK_MOBILE || state == NETWORK_WIFI) onNetConnected();
        else if (state == NETWORK_NONE) onNetDisConnected();
    }

    public void onNetConnected() {
    }

    public void onNetDisConnected() {
    }
}
