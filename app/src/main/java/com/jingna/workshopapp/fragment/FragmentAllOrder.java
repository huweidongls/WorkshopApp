package com.jingna.workshopapp.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.FragmentAllOrderAdapter;
import com.jingna.workshopapp.base.OrderBaseFragment;
import com.jingna.workshopapp.bean.OrderListBean;
import com.jingna.workshopapp.bean.WxPayBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.wxapi.WXShare;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentAllOrder extends OrderBaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refreshs)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.empty_order_bloacks)
    RelativeLayout empty_order_bloack;
    private FragmentAllOrderAdapter adapter;
    private List<OrderListBean.DataBean> mList;

    private int page = 1;

    private static final int SDK_PAY_FLAG = 1;

    private WXShare wxShare;
    private IWXAPI api;

    private boolean isFirst = true;

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_all_order, null);
        api = WXAPIFactory.createWXAPI(getContext(), null);
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isFirst){
            isFirst = false;
        }else {
            ViseHttp.GET(NetUrl.AppOrderActivityList)
                    .addParam("pageNum", "1")
                    .addParam("pageSize", "10")
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
                                        adapter = new FragmentAllOrderAdapter(mList, new FragmentAllOrderAdapter.ClickListener() {
                                            @Override
                                            public void onPay(int pos) {
                                                ViseHttp.GET(NetUrl.AppOrderlistOrdersSubmitted)
                                                        .addParam("id",mList.get(pos).getId())
                                                        .request(new ACallback<String>() {
                                                            @Override
                                                            public void onSuccess(String data) {
                                                                try {
                                                                    JSONObject jsonObject = new JSONObject(data);
                                                                    if (jsonObject.optString("status").equals("200")){
                                                                        Gson gson = new Gson();
                                                                        WxPayBean wxPayBean = gson.fromJson(data, WxPayBean.class);
                                                                        wxPay(wxPayBean);
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
                                            public void onReturnPrice(final int pos) {
                                                ViseHttp.GET(NetUrl.AppOrderorderRefund)
                                                        .addParam("id",mList.get(pos).getId())
                                                        .request(new ACallback<String>() {
                                                            @Override
                                                            public void onSuccess(String data) {
                                                                try {
                                                                    JSONObject jsonObject1 = new JSONObject(data);
                                                                    if (jsonObject1.optString("data").equals("已退款")){
                                                                        ToastUtil.showShort(getContext(), "退款成功!");
                                                                        mList.remove(pos);
                                                                        adapter.notifyDataSetChanged();
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
                                        });
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
                                    adapter = new FragmentAllOrderAdapter(mList, new FragmentAllOrderAdapter.ClickListener() {
                                        @Override
                                        public void onPay(int pos) {
                                            ViseHttp.GET(NetUrl.AppOrderlistOrdersSubmitted)
                                                    .addParam("id",mList.get(pos).getId())
                                                    .request(new ACallback<String>() {
                                                        @Override
                                                        public void onSuccess(String data) {
                                                            try {
                                                                JSONObject jsonObject = new JSONObject(data);
                                                                if (jsonObject.optString("status").equals("200")){
                                                                    Gson gson = new Gson();
                                                                    WxPayBean wxPayBean = gson.fromJson(data, WxPayBean.class);
                                                                    wxPay(wxPayBean);
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
                                        public void onReturnPrice(final int pos) {
                                            ViseHttp.GET(NetUrl.AppOrderorderRefund)
                                                    .addParam("id",mList.get(pos).getId())
                                                    .request(new ACallback<String>() {
                                                        @Override
                                                        public void onSuccess(String data) {
                                                            try {
                                                                JSONObject jsonObject1 = new JSONObject(data);
                                                                if (jsonObject1.optString("data").equals("已退款")){
                                                                    ToastUtil.showShort(getContext(), "退款成功!");
                                                                    mList.remove(pos);
                                                                    adapter.notifyDataSetChanged();
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
                                    });
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
    public void wxPay(WxPayBean model) {
        api.registerApp(WXShare.APP_ID);
        PayReq req = new PayReq();
        req.appId = model.getData().getAppid();
        req.partnerId = model.getData().getPartnerid();
        req.prepayId = model.getData().getPrepayid();
        req.nonceStr = model.getData().getNoncestr();
        req.timeStamp = model.getData().getTimestamp() + "";
        req.packageValue = "Sign=WXPay";
        req.sign = model.getData().getPaySign();
        req.extData = "app data";
        api.sendReq(req);
    }
}
