package com.jingna.workshopapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.EntrustListAdapter;
import com.jingna.workshopapp.adapter.EntrustTypeAdapter;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.bean.BannerBean;
import com.jingna.workshopapp.bean.EntrustListBean;
import com.jingna.workshopapp.bean.EntrustTypeBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.SearchActivity;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentEntrust extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_type)
    RecyclerView rvType;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;

    private List<EntrustTypeBean.DataBean> mTypeList;
    private EntrustTypeAdapter typeAdapter;
    private List<EntrustListBean.DataBean> mList;
    private EntrustListAdapter adapter;

    private int page = 1;
    private String type = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entrust, null);

        ButterKnife.bind(this, view);
        initBanner();
        initType();
        initData();

        return view;
    }

    @OnClick({R.id.ll_search})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.ll_search:
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void initData() {

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                ViseHttp.GET(NetUrl.AppShopWtjgfindAllWtjgEquipment)
                        .addParam("type", type)
                        .addParam("pageSize", "1")
                        .addParam("pageNum", "10")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        EntrustListBean bean = gson.fromJson(data, EntrustListBean.class);
                                        mList.clear();
                                        mList.addAll(bean.getData());
                                        adapter.notifyDataSetChanged();
                                        page = 2;
                                    }
                                    refreshLayout.finishRefresh(1000);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishRefresh(1000);
                            }
                        });
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                ViseHttp.GET(NetUrl.AppShopWtjgfindAllWtjgEquipment)
                        .addParam("type", type)
                        .addParam("pageSize", page+"")
                        .addParam("pageNum", "10")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        EntrustListBean bean = gson.fromJson(data, EntrustListBean.class);
                                        mList.addAll(bean.getData());
                                        adapter.notifyDataSetChanged();
                                        page = page+1;
                                    }
                                    refreshLayout.finishLoadMore(1000);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishLoadMore(1000);
                            }
                        });
            }
        });

        ViseHttp.GET(NetUrl.AppShopWtjgfindAllWtjgEquipment)
                .addParam("type", type)
                .addParam("pageSize", "1")
                .addParam("pageNum", "10")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                EntrustListBean bean = gson.fromJson(data, EntrustListBean.class);
                                mList = bean.getData();
                                adapter = new EntrustListAdapter(mList);
                                GridLayoutManager manager = new GridLayoutManager(getContext(), 2){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                                page = 2;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    private void initType() {

        ViseHttp.GET(NetUrl.AppShopWtjgfindAllWtjgType)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                EntrustTypeBean typeBean = gson.fromJson(data, EntrustTypeBean.class);
                                mTypeList = typeBean.getData();
                                mTypeList.add(0, new EntrustTypeBean.DataBean(0, 0, "全部", 0));
                                typeAdapter = new EntrustTypeAdapter(mTypeList, new EntrustTypeAdapter.ClickListener() {
                                    @Override
                                    public void onItemClick(int pos) {
                                        onType(pos);
                                    }
                                });
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                rvType.setLayoutManager(manager);
                                rvType.setAdapter(typeAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    /**
     * 根据选择type刷新列表
     */
    private void onType(int pos) {

        if(pos == 0){
            type = "";
        }else {
            type = mTypeList.get(pos).getId()+"";
        }
        ViseHttp.GET(NetUrl.AppShopWtjgfindAllWtjgEquipment)
                .addParam("type", type)
                .addParam("pageSize", "1")
                .addParam("pageNum", "10")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                EntrustListBean bean = gson.fromJson(data, EntrustListBean.class);
                                mList.clear();
                                mList.addAll(bean.getData());
                                adapter.notifyDataSetChanged();
                                page = 2;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    private void initBanner() {

        ViseHttp.GET(NetUrl.IndexPageApifindBanner)
                .addParam("type", "1")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                BannerBean bannerBean = gson.fromJson(data, BannerBean.class);
                                List<String> bannerList = new ArrayList<>();
                                for (BannerBean.DataBean bean : bannerBean.getData()){
                                    bannerList.add(NetUrl.BASE_URL+bean.getAppPic());
                                }
                                init(banner, bannerList);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            StatusBarUtils.setStatusBarTransparent(getActivity());
        }
    }

}
