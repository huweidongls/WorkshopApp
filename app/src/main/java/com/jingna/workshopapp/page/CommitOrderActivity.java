package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.AddressBean;
import com.jingna.workshopapp.bean.CommitOrderChejianBean;
import com.jingna.workshopapp.bean.CommitOrderWeituoBean;
import com.jingna.workshopapp.bean.OrderShebeiBean;
import com.jingna.workshopapp.bean.WxPayBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.Base64Utils;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.SpUtils;
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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommitOrderActivity extends BaseActivity {

    private Context context = CommitOrderActivity.this;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phonenum)
    TextView tvPhonenum;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_invoice)
    TextView tvInvoice;
    @BindView(R.id.iv_wx)
    ImageView ivWx;
    @BindView(R.id.iv_zfb)
    ImageView ivZfb;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_all_price)
    TextView tvAllPrice;
    @BindView(R.id.ll_weituo)
    LinearLayout llWeituo;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_bottom_price)
    TextView tvBottomPrice;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;

    private String addressId = "";

    private Map<String, String> map;//发票map
    private int invoiceId = 0;//是否开发票，0不开，1开
    private String payType = "0";//0微信  1支付宝

    private WXShare wxShare;
    private IWXAPI api;

    private String type = "";
    private String id = "";

    private String start = "";
    private String end = "";
    private String json = "";

    private int num = 1;//委托商品数量
    private int price;//商品价格

    private int payPriceAll=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_order);

        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        json = getIntent().getStringExtra("json");
        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        api = WXAPIFactory.createWXAPI(context, null);
        StatusBarUtils.setStatusBar(CommitOrderActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CommitOrderActivity.this);
        initData();

    }

    private void initData() {

        if(type.equals("1")){
            rlAddress.setVisibility(View.GONE);
            llWeituo.setVisibility(View.GONE);
            ViseHttp.GET(NetUrl.AppOrderorderConfiguration)
                    .addParam("workshopId", id)
                    .addParam("appGoodsOrders", Base64Utils.setEncryption(json))
                    .addParam("startTime", start)
                    .addParam("endTime", end)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    Gson gson = new Gson();
                                    CommitOrderChejianBean orderChejianBean = gson.fromJson(data, CommitOrderChejianBean.class);
                                    Glide.with(context).load(NetUrl.BASE_URL+orderChejianBean.getData().getWorkshopPicture()).into(ivImg);
                                    tvTitle.setText(orderChejianBean.getData().getWorkshopName());
                                    tvPrice.setText("¥"+orderChejianBean.getData().getOrderPrice());
                                    tvAllPrice.setText("¥"+(orderChejianBean.getData().getOrderPrice()+orderChejianBean.getData().getEquipmentMoney()));
                                    tvBottomPrice.setText("¥"+(orderChejianBean.getData().getOrderPrice()+orderChejianBean.getData().getEquipmentMoney()));
                                    payPriceAll = orderChejianBean.getData().getOrderPrice()+orderChejianBean.getData().getEquipmentMoney();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
        }else {
            rlAddress.setVisibility(View.VISIBLE);
            llWeituo.setVisibility(View.VISIBLE);
            ViseHttp.GET("/MemAdress/queryList")
                    .addParam("memberId", SpUtils.getUserId(context))
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    Gson gson = new Gson();
                                    AddressBean bean = gson.fromJson(data, AddressBean.class);
                                    List<AddressBean.DataBean> list = bean.getData();
                                    for (AddressBean.DataBean bean1 : list){
                                        if(bean1.getAcquiescentAdress().equals("1")){
                                            addressId = bean1.getId()+"";
                                            tvName.setText(bean1.getConsignee());
                                            tvPhonenum.setText(bean1.getConsigneeTel());
                                            tvAddress.setText(bean1.getLocation()+bean1.getAdress());
                                        }
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
            ViseHttp.GET(NetUrl.AppOrderentrustedProcessingOrder)
                    .addParam("id", id)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    Gson gson = new Gson();
                                    CommitOrderWeituoBean orderWeituoBean = gson.fromJson(data, CommitOrderWeituoBean.class);
                                    Glide.with(context).load(NetUrl.BASE_URL+orderWeituoBean.getData().getAppCategoryPic()).into(ivImg);
                                    tvTitle.setText(orderWeituoBean.getData().getCategoryName());
                                    price = orderWeituoBean.getData().getMoney();
                                    tvPrice.setText("¥"+orderWeituoBean.getData().getMoney());
                                    tvAllPrice.setText("¥"+(orderWeituoBean.getData().getMoney()*num));
                                    tvBottomPrice.setText("¥"+(orderWeituoBean.getData().getMoney()*num));
                                    tvStartTime.setText(orderWeituoBean.getData().getStartTime());
                                    tvEndTime.setText(orderWeituoBean.getData().getEndTime());
                                    payPriceAll = orderWeituoBean.getData().getMoney()*num;
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

    @OnClick({R.id.rl_back, R.id.rl_address, R.id.ll_invoice, R.id.rl_wx, R.id.rl_zfb, R.id.tv_commit, R.id.rl_jian, R.id.rl_jia})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_address:
                intent.setClass(context, AddressActivity.class);
                intent.putExtra("type", "order");
                startActivityForResult(intent, 1001);
                break;
            case R.id.ll_invoice:
                intent.setClass(context, InvoiceActivity.class);
                intent.putExtra("price", payPriceAll+"");
                intent.putExtra("bean",(Serializable) map);
                startActivityForResult(intent, 100);
                break;
            case R.id.rl_wx:
                payType = "0";
                Glide.with(context).load(R.mipmap.dh).into(ivWx);
                Glide.with(context).load(R.mipmap.dh_null).into(ivZfb);
                break;
            case R.id.rl_zfb:
                payType = "1";
                Glide.with(context).load(R.mipmap.dh_null).into(ivWx);
                Glide.with(context).load(R.mipmap.dh).into(ivZfb);
                break;
            case R.id.tv_commit:
                commit();
                break;
            case R.id.rl_jian:
                if(num > 1){
                    num = num - 1;
                    tvNum.setText(num+"");
                    tvAllPrice.setText("¥"+(price*num));
                    tvBottomPrice.setText("¥"+(price*num));
                }
                break;
            case R.id.rl_jia:
                num = num + 1;
                tvNum.setText(num+"");
                tvAllPrice.setText("¥"+(price*num));
                tvBottomPrice.setText("¥"+(price*num));
                break;
        }
    }

    private void commit() {

        if(type.equals("1")){
            if(invoiceId == 0){
                ViseHttp.POST(NetUrl.AppOrderorderSubmission)
                        .addParam("userId", SpUtils.getUserId(context))
                        .addParam("workshopId", id)
                        .addParam("startTime", start)
                        .addParam("endTime", end)
                        .addParam("appGoodsOrders", json)
                        .addParam("invoiceId", "0")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    Logger.e("JSON:",data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        WxPayBean wxPayBean = gson.fromJson(data, WxPayBean.class);
                                        wxPay(wxPayBean);
                                        finish();
                                    }else if(jsonObject.optString("status").equals("500")){
                                        ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
            }else {
                ViseHttp.POST(NetUrl.AppOrderorderSubmission)
                        .addParam("userId", SpUtils.getUserId(context))
                        .addParam("workshopId", id)
                        .addParam("startTime", start)
                        .addParam("endTime", end)
                        .addParam("appGoodsOrders", json)
                        .addParam("invoiceId", "1")
                        .addParams(map)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        WxPayBean wxPayBean = gson.fromJson(data, WxPayBean.class);
                                        wxPay(wxPayBean);
                                        finish();
                                    }else if(jsonObject.optString("status").equals("500")){
                                        ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
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
        }else {
            if(invoiceId == 0){
                ViseHttp.POST(NetUrl.AppOrderwtjgOrderConfiguration)
                        .addParam("userId", SpUtils.getUserId(context))
                        .addParam("goodsNum", num+"")
                        .addParam("addressId", addressId)
                        .addParam("wtjgId", id)
                        .addParam("invoiceId", "0")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
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
            }else {
                ViseHttp.POST(NetUrl.AppOrderwtjgOrderConfiguration)
                        .addParam("userId", SpUtils.getUserId(context))
                        .addParam("goodsNum", num+"")
                        .addParam("addressId", addressId)
                        .addParam("wtjgId", id)
                        .addParam("invoiceId", "1")
                        .addParams(map)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
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
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100&&data != null){
            AddressBean.DataBean bean = (AddressBean.DataBean) data.getSerializableExtra("address");
            addressId = bean.getId()+"";
            tvName.setText(bean.getConsignee());
            tvPhonenum.setText(bean.getConsigneeTel());
            tvAddress.setText(bean.getLocation()+bean.getAdress());
        }else if(resultCode == 101&&data != null){
            invoiceId = 1;
            tvInvoice.setText("开发票");
            map = (Map<String, String>) data.getSerializableExtra("map");
        }
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
