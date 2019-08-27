package com.jingna.workshopapp.page;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.CommentAddPicAdapter;
import com.jingna.workshopapp.adapter.FragmentAllOrderAdapter;
import com.jingna.workshopapp.adapter.MaintenanceAfterSaleAdapter;
import com.jingna.workshopapp.bean.AfterSaleOrderListBean;
import com.jingna.workshopapp.bean.OrderListBean;
import com.jingna.workshopapp.bean.WxPayBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.wxapi.WXShare;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaintenanceAfterSaleActivity extends AppCompatActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
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
    @OnClick({R.id.add_order,R.id.rl_back})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.add_order:
                intent.setClass(MaintenanceAfterSaleActivity.this, MaintenancEequipmentActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void initData(){
        ViseHttp.GET(NetUrl.AfterSaleOrdergetByUserIdOrder)
                .addParam("userId", SpUtils.getUserId(MaintenanceAfterSaleActivity.this))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                AfterSaleOrderListBean bean = gson.fromJson(data, AfterSaleOrderListBean.class);
                                mList = bean.getData();
                                adapter = new MaintenanceAfterSaleAdapter(mList, new MaintenanceAfterSaleAdapter.ClickListener() {
                                    @Override
                                    public void onPay(int pos) {
                                        ViseHttp.POST(NetUrl.AfterSaleOrdergetByWxPay)
                                                .addParam("orderId",mList.get(pos).getId())
                                                .request(new ACallback<String>() {
                                                    @Override
                                                    public void onSuccess(String datas) {
                                                        try {
                                                            JSONObject jsonObject1 = new JSONObject(datas);
                                                            if (jsonObject1.optString("status").equals("200")){
                                                                Gson gson = new Gson();
                                                                WxPayBean wxPayBean = gson.fromJson(datas, WxPayBean.class);
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
                                });
                                LinearLayoutManager manager = new LinearLayoutManager(MaintenanceAfterSaleActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
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
