package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.AddressBean;
import com.jingna.workshopapp.bean.OrderShebeiBean;
import com.jingna.workshopapp.bean.WxPayBean;
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

    private String s = "";
    private OrderShebeiBean bean;
    private String addressId = "";

    private Map<String, String> map;//发票map
    private int invoiceId = 0;//是否开发票，0不开，1开
    private String payType = "0";//0微信  1支付宝

    private WXShare wxShare;
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_order);

        api = WXAPIFactory.createWXAPI(context, null);
        s = getIntent().getStringExtra("bean");
        StatusBarUtils.setStatusBar(CommitOrderActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CommitOrderActivity.this);
        initData();

    }

    private void initData() {

        Gson gson = new Gson();
        bean = gson.fromJson(s, OrderShebeiBean.class);
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

    }

    @OnClick({R.id.rl_back, R.id.rl_address, R.id.ll_invoice, R.id.rl_wx, R.id.rl_zfb})
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
                intent.putExtra("price", 0.00);
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
        req.appId = model.getAppId();
        req.partnerId = model.getMchId();
        req.prepayId = model.getPrepayId();
        req.nonceStr = model.getNonceStr();
        req.timeStamp = model.getTimeStamp() + "";
        req.packageValue = "Sign=WXPay";
        req.sign = model.getPaySign();
        req.extData = "app data";
        api.sendReq(req);
    }

}
