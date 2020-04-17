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
import com.jingna.workshopapp.util.StringUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.util.ViseUtil;
import com.jingna.workshopapp.wxapi.WXShare;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.LinkedHashMap;
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
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;

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
    private double price;//商品价格

    private double payPriceAll=0.00;

    private double yunfei = 0.00;

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
            Map<String, String> map1 = new LinkedHashMap<>();
            map1.put("workshopId", id);
            map1.put("appGoodsOrders", Base64Utils.setEncryption(json));
            map1.put("startTime", start);
            map1.put("endTime", end);
            ViseUtil.Get(context, NetUrl.AppOrderorderConfiguration, map1, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    CommitOrderChejianBean orderChejianBean = gson.fromJson(s, CommitOrderChejianBean.class);
                    Glide.with(context).load(NetUrl.BASE_URL+orderChejianBean.getData().getWorkshopPicture()).into(ivImg);
                    tvTitle.setText(orderChejianBean.getData().getWorkshopName());
                    tvPrice.setText("¥"+ StringUtils.roundByScale(orderChejianBean.getData().getOrderPrice(), 2));
                    tvAllPrice.setText("¥"+StringUtils.roundByScale((orderChejianBean.getData().getOrderPrice()+orderChejianBean.getData().getEquipmentMoney()), 2));
                    tvBottomPrice.setText("¥"+StringUtils.roundByScale((orderChejianBean.getData().getOrderPrice()+orderChejianBean.getData().getEquipmentMoney()), 2));
                    payPriceAll = orderChejianBean.getData().getOrderPrice()+orderChejianBean.getData().getEquipmentMoney();
                }
            });
        }else {
            rlAddress.setVisibility(View.VISIBLE);
            llWeituo.setVisibility(View.VISIBLE);
            Map<String, String> map2 = new LinkedHashMap<>();
            map2.put("memberId", SpUtils.getUserId(context));
            ViseUtil.Get(context, "/MemAdress/queryList", map2, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    AddressBean bean = gson.fromJson(s, AddressBean.class);
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
            });
            Map<String, String> map3 = new LinkedHashMap<>();
            map3.put("id", id);
            ViseUtil.Get(context, NetUrl.AppOrderentrustedProcessingOrder, map3, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    CommitOrderWeituoBean orderWeituoBean = gson.fromJson(s, CommitOrderWeituoBean.class);
                    Glide.with(context).load(NetUrl.BASE_URL+orderWeituoBean.getData().getAppCategoryPic()).into(ivImg);
                    yunfei = orderWeituoBean.getData().getFreightMoney();
                    tvTitle.setText(orderWeituoBean.getData().getCategoryName());
                    price = orderWeituoBean.getData().getMoney();
                    tvPrice.setText("¥"+StringUtils.roundByScale(orderWeituoBean.getData().getMoney(), 2));
                    tvAllPrice.setText("¥"+StringUtils.roundByScale((orderWeituoBean.getData().getMoney()*num), 2));
                    tvBottomPrice.setText("¥"+StringUtils.roundByScale((orderWeituoBean.getData().getMoney()*num+yunfei), 2));
                    tvStartTime.setText(orderWeituoBean.getData().getStartTime());
                    tvEndTime.setText(orderWeituoBean.getData().getEndTime());
                    payPriceAll = orderWeituoBean.getData().getMoney()*num;
                    if(orderWeituoBean.getData().getFreightMoney()==0){
                        tvYunfei.setText("免运费");
                    }else {
                        tvYunfei.setText("¥"+StringUtils.roundByScale(orderWeituoBean.getData().getFreightMoney(), 2));
                    }
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
                    tvAllPrice.setText("¥"+StringUtils.roundByScale((price*num), 2));
                    tvBottomPrice.setText("¥"+StringUtils.roundByScale((price*num+yunfei), 2));
                }
                break;
            case R.id.rl_jia:
                num = num + 1;
                tvNum.setText(num+"");
                tvAllPrice.setText("¥"+StringUtils.roundByScale((price*num), 2));
                tvBottomPrice.setText("¥"+StringUtils.roundByScale((price*num+yunfei), 2));
                break;
        }
    }

    private void commit() {

        if(type.equals("1")){
            if(invoiceId == 0){
                Map<String, String> map1 = new LinkedHashMap<>();
                map1.put("userId", SpUtils.getUserId(context));
                map1.put("workshopId", id);
                map1.put("startTime", start);
                map1.put("endTime", end);
                map1.put("appGoodsOrders", json);
                map1.put("invoiceId", "0");
                ViseUtil.Post(context, NetUrl.AppOrderorderSubmission, map1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        WxPayBean wxPayBean = gson.fromJson(s, WxPayBean.class);
                        wxPay(wxPayBean);
                        finish();
                    }
                });
            }else {
                Map<String, String> map2 = new LinkedHashMap<>();
                map2.put("userId", SpUtils.getUserId(context));
                map2.put("workshopId", id);
                map2.put("startTime", start);
                map2.put("endTime", end);
                map2.put("appGoodsOrders", json);
                map2.put("invoiceId", "1");
                map2.putAll(map);
                ViseUtil.Post(context, NetUrl.AppOrderorderSubmission, map2, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        WxPayBean wxPayBean = gson.fromJson(s, WxPayBean.class);
                        wxPay(wxPayBean);
                        finish();
                    }
                });
            }
        }else {
            if(invoiceId == 0){
                Map<String, String> map3 = new LinkedHashMap<>();
                map3.put("userId", SpUtils.getUserId(context));
                map3.put("goodsNum", num+"");
                map3.put("addressId", addressId);
                map3.put("wtjgId", id);
                map3.put("invoiceId", "0");
                ViseUtil.Post(context, NetUrl.AppOrderwtjgOrderConfiguration, map3, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        WxPayBean wxPayBean = gson.fromJson(s, WxPayBean.class);
                        wxPay(wxPayBean);
                    }
                });
            }else {
                Map<String, String> map4 = new LinkedHashMap<>();
                map4.put("userId", SpUtils.getUserId(context));
                map4.put("goodsNum", num+"");
                map4.put("addressId", addressId);
                map4.put("wtjgId", id);
                map4.put("invoiceId", "1");
                map4.putAll(map);
                ViseUtil.Post(context, NetUrl.AppOrderwtjgOrderConfiguration, map4, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        WxPayBean wxPayBean = gson.fromJson(s, WxPayBean.class);
                        wxPay(wxPayBean);
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
