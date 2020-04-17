package com.jingna.workshopapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.jingna.workshopapp.util.StringUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.util.ViseUtil;
import com.jingna.workshopapp.util.WeiboDialogUtils;
import com.jingna.workshopapp.wxapi.WXShare;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AfterSaleOrderDetailsTopayActivity extends AppCompatActivity {
    private Context context = AfterSaleOrderDetailsTopayActivity.this;
    private int pay = 1;
    private double moeny_all = 0;
    private String id = "";
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
    @BindView(R.id.ll_qianmingimg)
    LinearLayout ll_qianmingimg;
    @BindView(R.id.img_qinming)
    ImageView img_qinming;
    @BindView(R.id.tv_qianmingtext)
    TextView tv_qianmingtext;
    private List<AfterSaleOrderDetailsToPayBean.DataBean.AfterSaleOrderItemsBean> mList;
    private WXShare wxShare;
    private IWXAPI api;
    private int qm = 0;
    private String qm_img = "";
    private Dialog dialog;

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

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("orderId", id);
        ViseUtil.Get(context, NetUrl.AfterSaleOrdergetByWxPayDetails, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AfterSaleOrderDetailsToPayBean bean = gson.fromJson(s, AfterSaleOrderDetailsToPayBean.class);
                mList = bean.getData().getAfterSaleOrderItems();
                tv_name.setText(bean.getData().getAddresUname());
                tv_tel.setText(bean.getData().getAddresPhone());
                tv_address.setText(bean.getData().getAddresName());
                goods_all_price.setText("¥" + StringUtils.roundByScale(bean.getData().getRepairMoney(), 2) + "元");
                goods_yunfei.setText("¥" + StringUtils.roundByScale(bean.getData().getRepairTimeMoney(), 2) + "元");
                pay_price.setText("¥" + StringUtils.roundByScale(bean.getData().getCarMoney(), 2) + "元");
                moeny_all = bean.getData().getCarMoney() + bean.getData().getRepairTimeMoney() + bean.getData().getRepairMoney();
                conmit_all_price.setText("¥" + StringUtils.roundByScale(bean.getData().getOrderRealPrice(), 2) + "元");
                adapter = new AfterSaleOrderDetailsTopayAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(AfterSaleOrderDetailsTopayActivity.this) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.iv_wx, R.id.iv_zfb, R.id.submit_order, R.id.ll_qianming})
    public void onClick(View view) {
        //Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_zfb:
                pay = 2;
                Glide.with(context).load(R.mipmap.duihao).into(iv_pay_zfb);
                Glide.with(context).load(R.drawable.img_radios).into(iv_pay_wx);
                break;
            case R.id.iv_wx:
                pay = 1;
                Glide.with(context).load(R.mipmap.duihao).into(iv_pay_wx);
                Glide.with(context).load(R.drawable.img_radios).into(iv_pay_zfb);
                break;
            case R.id.ll_qianming:
                Intent intent = new Intent();
                intent.setClass(context, SignatureActivity.class);
                startActivityForResult(intent, 1001);
                break;
            case R.id.submit_order:
                if (qm == 0) {
                    ToastUtil.showShort(context, "请先完成签名!");
                } else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待");
                    order_submit();
                }
                break;
        }
    }

    private void order_submit() {
        File file = new File(qm_img);
        ViseHttp.UPLOAD(NetUrl.AfterSaleOrdergetByWxPay)
                .addParam("orderId", id)
                .addFile("file0", file)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            WeiboDialogUtils.closeDialog(dialog);
                            Logger.e("123123", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")) {
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
                        WeiboDialogUtils.closeDialog(dialog);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && data != null) {
            qm_img = data.getStringExtra("pash");
            qm = 1;
            ll_qianmingimg.setVisibility(View.VISIBLE);
            tv_qianmingtext.setText("已签名");
            Glide.with(context).load(qm_img).into(img_qinming);
        }
    }
}
