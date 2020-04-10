package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.MaintenanceAfterSaleAdapter;
import com.jingna.workshopapp.bean.AfterSaleOrderListBean;
import com.jingna.workshopapp.bean.WxPayBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ViseUtil;
import com.jingna.workshopapp.wxapi.WXShare;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaintenanceAfterSaleActivity extends AppCompatActivity {

    private Context context = MaintenanceAfterSaleActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;

    private List<AfterSaleOrderListBean.DataBean> mList;
    private MaintenanceAfterSaleAdapter adapter;
    private WXShare wxShare;
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_after_sale);
        StatusBarUtils.setStatusBar(MaintenanceAfterSaleActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(MaintenanceAfterSaleActivity.this);
        api = WXAPIFactory.createWXAPI(MaintenanceAfterSaleActivity.this, null);
        initData();
    }

    @OnClick({R.id.add_order, R.id.rl_back})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.add_order:
                intent.setClass(MaintenanceAfterSaleActivity.this, MaintenancEequipmentActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void initData() {
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context));
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("userId", SpUtils.getUserId(context));
                ViseUtil.Get(context, NetUrl.AfterSaleOrdergetByUserIdOrder, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        AfterSaleOrderListBean bean = gson.fromJson(s, AfterSaleOrderListBean.class);
                        mList.clear();
                        mList.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.AfterSaleOrdergetByUserIdOrder, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AfterSaleOrderListBean bean = gson.fromJson(s, AfterSaleOrderListBean.class);
                mList = bean.getData();
                adapter = new MaintenanceAfterSaleAdapter(mList, new MaintenanceAfterSaleAdapter.ClickListener() {
                    @Override
                    public void onPay(int pos) {
                        Map<String, String> map1 = new LinkedHashMap<>();
                        map1.put("orderId", mList.get(pos).getId());
                        ViseUtil.Post(context, NetUrl.AfterSaleOrdergetByWxPay, map1, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                Gson gson = new Gson();
                                WxPayBean wxPayBean = gson.fromJson(s, WxPayBean.class);
                                wxPay(wxPayBean);
                            }
                        });
                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(MaintenanceAfterSaleActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });
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
