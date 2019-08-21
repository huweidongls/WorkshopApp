package com.jingna.workshopapp.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.FragmentDaiShouHuoOrderAdapter;
import com.jingna.workshopapp.adapter.FragmentYiQuXiaoAdapter;
import com.jingna.workshopapp.base.OrderBaseFragment;
import com.jingna.workshopapp.bean.OrderListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/8/8.
 */

public class FragmentYiQuXiaoOrder extends OrderBaseFragment{
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    private FragmentYiQuXiaoAdapter adapter;
    private List<OrderListBean.DataBean> mList;
    @BindView(R.id.empty_order_bloack)
    RelativeLayout empty_order_bloack;
    private int page=1;
    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_yiquxiao_order, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                ViseHttp.GET(NetUrl.AppOrderActivityList)
                        .addParam("pageNum", "1")
                        .addParam("pageSize", "10")
                        .addParam("type","3")
                        .addParam("userId", SpUtils.getUserId(getContext()))
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.optString("status").equals("200")) {
                                        Gson gson = new Gson();
                                        OrderListBean bean = gson.fromJson(data, OrderListBean.class);
                                        mList.clear();
                                        mList.addAll(bean.getData());
                                        adapter.notifyDataSetChanged();
                                        page = 2;
                                    }
                                    refreshLayout.finishRefresh(500);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishRefresh(500);
                            }
                        });
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                ViseHttp.GET(NetUrl.AppOrderActivityList)
                        .addParam("pageNum", page+"")
                        .addParam("pageSize", "10")
                        .addParam("type","3")
                        .addParam("userId", SpUtils.getUserId(getContext()))
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.optString("status").equals("200")) {
                                        Gson gson = new Gson();
                                        OrderListBean bean = gson.fromJson(data, OrderListBean.class);
                                        mList.addAll(bean.getData());
                                        adapter.notifyDataSetChanged();
                                        page = page+1;
                                    }
                                    refreshLayout.finishLoadMore(500);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishLoadMore(500);
                            }
                        });
            }
        });

        ViseHttp.GET(NetUrl.AppOrderActivityList)
                .addParam("pageNum", "1")
                .addParam("pageSize", "10")
                .addParam("type","3")
                .addParam("userId", SpUtils.getUserId(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")) {
                                Gson gson = new Gson();
                                OrderListBean bean = gson.fromJson(data, OrderListBean.class);
                                mList = bean.getData();
                                if (mList.size()>0){
                                    adapter = new FragmentYiQuXiaoAdapter(mList);
                                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                                    recyclerView.setLayoutManager(manager);
                                    recyclerView.setAdapter(adapter);
                                    page=2;
                                }else{
                                    empty_order_bloack.setVisibility(View.VISIBLE);
                                }

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
    public void hide() {

    }
}
