package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.AfterSaleOrderDetailsTopayAdapter;
import com.jingna.workshopapp.bean.AfterSaleOrderDetailsToPayBean;
import com.jingna.workshopapp.bean.WxPayBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.wxapi.WXShare;
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
import butterknife.OnClick;

public class AfterSaleOrderDetailsTopayActivity extends AppCompatActivity {
    private Context context = AfterSaleOrderDetailsTopayActivity.this;
    private int pay=1;
    private int moeny_all=0;
    private String id="";
    private AfterSaleOrderDetailsTopayAdapter adapter;
    @BindView(R.id.iv_pay_wx)
    ImageView iv_pay_wx;
    @BindView(R.id.iv_pay_zfb)
    ImageView iv_pay_zfb;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_tel)
    TextView tv_tel;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.goods_all_price)
    TextView goods_all_price;
    @BindView(R.id.goods_yunfei)
    TextView goods_yunfei;
    @BindView(R.id.pay_price)
    TextView pay_price;
    @BindView(R.id.conmit_all_price)
    TextView conmit_all_price;
    private List<AfterSaleOrderDetailsToPayBean.DataBean.AfterSaleOrderItemsBean> mList;
    private WXShare wxShare;
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale_order_details_topay);
        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(AfterSaleOrderDetailsTopayActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(AfterSaleOrderDetailsTopayActivity.this);
        api = WXAPIFactory.createWXAPI(AfterSaleOrderDetailsTopayActivity.this, null);
        initData();
    }
    private void initData(){
        ViseHttp.GET(NetUrl.AfterSaleOrdergetByWxPayDetails)
                .addParam("orderId",id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                AfterSaleOrderDetailsToPayBean bean = gson.fromJson(data,AfterSaleOrderDetailsToPayBean.class);
                                mList = bean.getData().getAfterSaleOrderItems();
                                tv_name.setText(bean.getData().getAddresUname());
                                tv_tel.setText(bean.getData().getAddresPhone());
                                tv_address.setText(bean.getData().getAddresName());
                                goods_all_price.setText("¥"+bean.getData().getRepairMoney()+"元");
                                goods_yunfei.setText("¥"+bean.getData().getRepairTimeMoney()+"元");
                                pay_price.setText("¥"+bean.getData().getCarMoney()+"元");
                                moeny_all = bean.getData().getCarMoney()+bean.getData().getRepairTimeMoney()+bean.getData().getRepairMoney();
                                conmit_all_price.setText("¥"+moeny_all+"元");
                                adapter = new AfterSaleOrderDetailsTopayAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(AfterSaleOrderDetailsTopayActivity.this);
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
    @OnClick({R.id.rl_back, R.id.iv_wx,R.id.iv_zfb,R.id.submit_order})
    public void onClick(View view){
        //Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_zfb:
                pay=2;
                Glide.with(context).load(R.mipmap.duihao).into(iv_pay_zfb);
                Glide.with(context).load(R.drawable.img_radios).into(iv_pay_wx);
                break;
            case R.id.iv_wx:
                pay=1;
                Glide.with(context).load(R.mipmap.duihao).into(iv_pay_wx);
                Glide.with(context).load(R.drawable.img_radios).into(iv_pay_zfb);
                break;
            case R.id.submit_order:
                order_submit();
                break;
        }
    }
    private void order_submit(){
        ViseHttp.POST(NetUrl.AfterSaleOrdergetByWxPay)
                .addParam("orderId",id)
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
