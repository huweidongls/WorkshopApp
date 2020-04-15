package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.AddressBean;
import com.jingna.workshopapp.bean.CrowOrderBean;
import com.jingna.workshopapp.bean.WxPayBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.StringUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.util.ViseUtil;
import com.jingna.workshopapp.wxapi.WXShare;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CrowdDetailsSupportActivity extends BaseActivity {

    private Context context = CrowdDetailsSupportActivity.this;

    @BindView(R.id.iv_pay_wx)
    ImageView iv_pay_wx;
    @BindView(R.id.iv_pay_zfb)
    ImageView iv_pay_zfb;
    @BindView(R.id.iv_check)
    ImageView iv_check;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_tel)
    TextView tvPhonenum;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_invoice)
    TextView tv_invoice;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.tv_title_goods)
    TextView tv_title_goods;
    @BindView(R.id.goods_price)
    TextView goods_price;
    @BindView(R.id.goods_desc)
    TextView goods_desc;
    @BindView(R.id.time_fahuo)
    TextView time_fahuo;
    @BindView(R.id.goods_all_price)
    TextView goods_all_price;
    @BindView(R.id.goods_yunfei)
    TextView goods_yunfei;
    @BindView(R.id.pay_price)
    TextView pay_price;
    @BindView(R.id.conmit_all_price)
    TextView conmit_all_price;
    @BindView(R.id.blackOutNumber)
    EditText remarks;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_num)
    TextView tvNum;

    private int pay = 1;
    private int paycheck = 0;
    private int addressid = 0;
    private int invoiceId = 0;
    private double payAll = 0.00;
    private String gid = "";
    private Map<String, String> map;//发票map
    private WXShare wxShare;
    private IWXAPI api;
    private String id = "";
    private String num = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowd_details_support);
        api = WXAPIFactory.createWXAPI(context, null);
        id = getIntent().getStringExtra("id");
        num = getIntent().getStringExtra("num");
        StatusBarUtils.setStatusBar(CrowdDetailsSupportActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CrowdDetailsSupportActivity.this);
        LoadAddredd();
        initdata();
    }

    private void initdata() {

        tvNum.setText("x"+num);
        Map<String, String> map1 = new LinkedHashMap<>();
        map1.put("goodsNum", num);
        map1.put("gearPositionId", id);
        ViseUtil.Post(context, "/AppOrder/crowdFundingOrderConfiguration", map1, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                CrowOrderBean bean = gson.fromJson(s, CrowOrderBean.class);
                Glide.with(context).load(NetUrl.BASE_URL + bean.getData().getGearPositionPicture()).into(iv_img);
                gid = bean.getData().getSellerId();
                tvTitleName.setText(bean.getData().getGearPositionName());
                tv_title_goods.setText(bean.getData().getGearPositionTitle());
                goods_price.setText("¥" + StringUtils.roundByScale((bean.getData().getGearPositionMoney()/Double.valueOf(num)), 2));
                goods_desc.setText(bean.getData().getGearPositionSubTitle());
                time_fahuo.setText("预计" + bean.getData().getDeliveryTime() + "天后发货");
                goods_all_price.setText("¥" + StringUtils.roundByScale(bean.getData().getGearPositionMoney(), 2));
                if (bean.getData().getFreight() <= 0) {
                    goods_yunfei.setText("免运费");
                } else {
                    goods_yunfei.setText(bean.getData().getFreight() + "元");
                }
                pay_price.setText("¥" + StringUtils.roundByScale(bean.getData().getGearPositionMoney(), 2));
                payAll = bean.getData().getFreight() + bean.getData().getGearPositionMoney();
                conmit_all_price.setText("¥" + StringUtils.roundByScale(payAll, 2) + "");
            }
        });

    }

    private void LoadAddredd() {

        Map<String, String> map1 = new LinkedHashMap<>();
        map1.put("memberId", SpUtils.getUserId(context));
        ViseUtil.Get(context, "/MemAdress/queryList", map1, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AddressBean bean = gson.fromJson(s, AddressBean.class);
                List<AddressBean.DataBean> list = bean.getData();
                for (AddressBean.DataBean bean1 : list) {
                    if (bean1.getAcquiescentAdress().equals("1")) {
                        addressid = bean1.getId();
                        tvName.setText(bean1.getConsignee());
                        tvPhonenum.setText(bean1.getConsigneeTel());
                        tvAddress.setText(bean1.getLocation() + bean1.getAdress());
                    }
                }
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.iv_wx, R.id.iv_zfb, R.id.iv_check, R.id.rl_address, R.id.ll_invoice, R.id.submit_order})
    public void onClick(View view) {
        Intent intent = new Intent();
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
            case R.id.iv_check:
                if (paycheck == 0) {
                    paycheck = 1;
                    Glide.with(context).load(R.mipmap.duihao).into(iv_check);
                } else {
                    paycheck = 0;
                    Glide.with(context).load(R.drawable.img_radios).into(iv_check);
                }
                break;
            case R.id.rl_address:
                intent.setClass(context, AddressActivity.class);
                intent.putExtra("type", "order");
                startActivityForResult(intent, 1001);
                break;
            case R.id.ll_invoice:
                intent.setClass(context, InvoiceActivity.class);
                intent.putExtra("price", payAll + "");
                startActivityForResult(intent, 100);
                break;
            case R.id.submit_order:
                if (paycheck == 1) {
                    onFromOrder();
                } else {
                    ToastUtil.showShort(CrowdDetailsSupportActivity.this, "请先勾选支持者协议!");
                }
                break;
        }
    }

    private void onFromOrder() {//提交订单信息
        String search = remarks.getText().toString();
        if (invoiceId == 0) {//0是不开发票
            Map<String, String> map1 = new LinkedHashMap<>();
            map1.put("userId", SpUtils.getUserId(context));
            map1.put("sellerId", id);
            map1.put("goodsNum", num);
            map1.put("invoiceId", "0");
            map1.put("addressId", addressid + "");
            map1.put("remarks", search);
            ViseUtil.Post(context, "/AppOrder/crowdFundingOrderSubmission", map1, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    WxPayBean wxPayBean = gson.fromJson(s, WxPayBean.class);
                    wxPay(wxPayBean);
                }
            });
        } else {//1是开发票
            Map<String, String> map2 = new LinkedHashMap<>();
            map2.put("userId", SpUtils.getUserId(context));
            map2.put("sellerId", id);
            map2.put("goodsNum", num);
            map2.put("invoiceId", "1");
            map2.put("addressId", addressid + "");
            map2.put("remarks", search);
            map2.putAll(map);
            ViseUtil.Post(context, "/AppOrder/crowdFundingOrderSubmission", map2, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    WxPayBean wxPayBean = gson.fromJson(s, WxPayBean.class);
                    wxPay(wxPayBean);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100 && data != null) {
            AddressBean.DataBean bean = (AddressBean.DataBean) data.getSerializableExtra("address");
            addressid = bean.getId();
            tvName.setText(bean.getConsignee());
            tvPhonenum.setText(bean.getConsigneeTel());
            tvAddress.setText(bean.getLocation() + bean.getAdress());
        } else if (resultCode == 101 && data != null) {
            invoiceId = 1;
            tv_invoice.setText("开发票");
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
