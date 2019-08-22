package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.CrowdDetailsBean;
import com.jingna.workshopapp.bean.OrderDetailsBean;
import com.jingna.workshopapp.bean.WxPayBean;
import com.jingna.workshopapp.dialog.DialogCustom;
import com.jingna.workshopapp.net.NetUrl;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailsActivity extends AppCompatActivity {
    private Context context = OrderDetailsActivity.this;
    @BindView(R.id.h_x)
    View h_x;
    @BindView(R.id.tv_goods_title)
    TextView tv_goods_title;

    @BindView(R.id.iv_goods_img)
    ImageView iv_goods_img;

    @BindView(R.id.r_address)
    RelativeLayout r_address;

    @BindView(R.id.sh_name)
    TextView sh_name;

    @BindView(R.id.sh_phone)
    TextView sh_phone;

    @BindView(R.id.sh_address)
    TextView sh_address;

    @BindView(R.id.order_sn)
    TextView order_sn;

    @BindView(R.id.add_time)
    TextView add_time;

    @BindView(R.id.pay_type)
    TextView pay_type;

    @BindView(R.id.sc_time)
    TextView sc_time;

    @BindView(R.id.type_time)
    TextView type_time;

    @BindView(R.id.fp_type)
    TextView fp_type;

    @BindView(R.id.count_money)
    TextView count_money;

    @BindView(R.id.yunfei)
    TextView yunfei;

    @BindView(R.id.zong_money)
    TextView zong_money;

    @BindView(R.id.fsc_time)
    LinearLayout fsc_time;

    @BindView(R.id.tv_to_pay)//去支付
    Button tv_to_pay;

    @BindView(R.id.qx_to)//取消订单
    Button qx_to;

    @BindView(R.id.del_order_to)//删除订单
    Button del_order_to;

    @BindView(R.id.qpj_to)//去评价
    Button qpj_to;

    @BindView(R.id.tk_to)//退款
    Button tk_to;

    @BindView(R.id.qrsh_to)//收货
    Button qrsh_to;

    private String id="";
    private String getGoodsPictureApp="";
    private String getGoodsTitle="";
    private int payAll=0;
    private WXShare wxShare;
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        id = getIntent().getStringExtra("id");
        api = WXAPIFactory.createWXAPI(context, null);
        StatusBarUtils.setStatusBar(OrderDetailsActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(OrderDetailsActivity.this);
        initdata();
    }
    private void initdata(){
        ViseHttp.GET(NetUrl.AppOrderorderDetails)
                .addParam("goodsOrderId",id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                OrderDetailsBean bean = gson.fromJson(data, OrderDetailsBean.class);
                                getGoodsPictureApp = bean.getData().getGoodsPictureApp();
                                getGoodsTitle = bean.getData().getGoodsTitle();
                                if (bean.getData().getGoodsId().isEmpty()){//等于空
                                    r_address.setVisibility(View.VISIBLE);//显示
                                    tv_goods_title.setText(bean.getData().getGoodsTitle());
                                    Glide.with(context).load(NetUrl.BASE_URL+bean.getData().getGoodsPictureApp()).into(iv_goods_img);
                                    sh_name.setText(bean.getData().getAddresUname());
                                    sh_phone.setText(bean.getData().getAddresPhone());
                                    sh_address.setText("地址:"+bean.getData().getAddresName());
                                    order_sn.setText(bean.getData().getId());
                                    add_time.setText(bean.getData().getCreateTime());
                                    pay_type.setText(bean.getData().getPaymentMode());
                                    fsc_time.setVisibility(View.GONE);
                                    h_x.setVisibility(View.GONE);
                                    count_money.setText("¥"+bean.getData().getOrderPrice()+"");
                                    yunfei.setText("¥"+bean.getData().getFreightMoney());
                                    payAll = bean.getData().getOrderPrice()+bean.getData().getFreightMoney();
                                    zong_money.setText("¥"+payAll);
                                    if (bean.getData().getInvoiceId().equals("0")){
                                        fp_type.setText("不开发票");
                                    }else{
                                        fp_type.setText("开发票");
                                    }
                                    if(bean.getData().getOrderStatus().equals("0")){
                                        tv_to_pay.setVisibility(View.VISIBLE);
                                        tk_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.VISIBLE);
                                    }else if(bean.getData().getOrderStatus().equals("1") || bean.getData().getOrderStatus().equals("2")){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.VISIBLE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.GONE);
                                    }else if(bean.getData().getOrderStatus().equals("3")){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.VISIBLE);
                                        qpj_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.GONE);
                                    }else if(bean.getData().getOrderStatus().equals("4")){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.VISIBLE);
                                        del_order_to.setVisibility(View.GONE);
                                    }else if(bean.getData().getOrderStatus().equals("5")){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.VISIBLE);
                                    }else if(bean.getData().getOrderStatus().equals("6") ){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.VISIBLE);
                                    }else if(bean.getData().getOrderStatus().equals("7")){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.VISIBLE);
                                    }
                                }else{//不等于空
                                    r_address.setVisibility(View.GONE);//隐藏
                                    tv_goods_title.setText(bean.getData().getGoodsTitle());
                                    Glide.with(context).load(NetUrl.BASE_URL+bean.getData().getGoodsPictureApp()).into(iv_goods_img);
                                    order_sn.setText(bean.getData().getId());
                                    add_time.setText(bean.getData().getCreateTime());
                                    pay_type.setText(bean.getData().getPaymentMode());
                                    fsc_time.setVisibility(View.VISIBLE);
                                    h_x.setVisibility(View.VISIBLE);
                                    sc_time.setText(bean.getData().getStartTime()+"至"+bean.getData().getEndTime());
                                    count_money.setText("¥"+bean.getData().getOrderPrice()+"");
                                    yunfei.setText("¥"+bean.getData().getFreightMoney());
                                    payAll = bean.getData().getOrderPrice()+bean.getData().getFreightMoney();
                                    zong_money.setText("¥"+payAll);
                                    if (bean.getData().getInvoiceId().equals("0")){
                                        fp_type.setText("不开发票");
                                    }else{
                                        fp_type.setText("开发票");
                                    }
                                    if(bean.getData().getOrderStatus().equals("0")){
                                        tv_to_pay.setVisibility(View.VISIBLE);
                                        tk_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.VISIBLE);
                                    }else if(bean.getData().getOrderStatus().equals("1") || bean.getData().getOrderStatus().equals("2")){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.VISIBLE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.GONE);
                                    }else if(bean.getData().getOrderStatus().equals("3")){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.VISIBLE);
                                        qpj_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.GONE);
                                    }else if(bean.getData().getOrderStatus().equals("4")){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.VISIBLE);
                                        del_order_to.setVisibility(View.GONE);
                                    }else if(bean.getData().getOrderStatus().equals("5")){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.VISIBLE);
                                    }else if(bean.getData().getOrderStatus().equals("6") ){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.VISIBLE);
                                    }else if(bean.getData().getOrderStatus().equals("7")){
                                        tv_to_pay.setVisibility(View.GONE);
                                        qx_to.setVisibility(View.GONE);
                                        qrsh_to.setVisibility(View.GONE);
                                        qpj_to.setVisibility(View.GONE);
                                        tk_to.setVisibility(View.GONE);
                                        del_order_to.setVisibility(View.VISIBLE);
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
    @OnClick({R.id.rl_back, R.id.tv_to_pay,R.id.qx_to,R.id.del_order_to,R.id.qpj_to,R.id.tk_to,R.id.qrsh_to})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_to_pay:
                to_pay();
                break;
            case R.id.qx_to:
                cancel_order();
                break;
            case R.id.del_order_to:
                del_order();
                break;
            case R.id.qpj_to:
                intent.setClass(context, SubmissionEvaluateActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("imgUrl", getGoodsPictureApp);
                intent.putExtra("title", getGoodsTitle);
                context.startActivity(intent);
                break;
            case R.id.tk_to:
                refund_order();
                break;
            case R.id.qrsh_to:
                intent.setClass(context, AcceptanceActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("imgUrl", getGoodsPictureApp);
                intent.putExtra("title", getGoodsTitle);
                context.startActivity(intent);
                break;
        }
    }
    public void refund_order(){
        ViseHttp.GET(NetUrl.AppOrderorderRefund)
                .addParam("id",id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(data);
                            if (jsonObject1.optString("data").equals("已退款")){
                                ToastUtil.showShort(context, "退款成功!");
                                /*mList.remove(pos);
                                adapter.notifyDataSetChanged();*/
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
    public void del_order(){
        DialogCustom dialogCustom = new DialogCustom(context, "确定删除?", new DialogCustom.OnYesListener() {
            @Override
            public void onYes() {
                ViseHttp.GET(NetUrl.AppOrdertoDelete)
                        .addParam("goodsOrderId",id)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String d) {
                                try {
                                    JSONObject jsonObject = new JSONObject(d);
                                    if (jsonObject.optString("status").equals("200")){
                                        ToastUtil.showShort(context, "删除订单成功!");
                                        /*data.remove(position);
                                        notifyDataSetChanged();*/
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
        dialogCustom.show();
    }
    public void cancel_order(){
        DialogCustom dialogCustom = new DialogCustom(context, "确认取消订单?", new DialogCustom.OnYesListener() {
            @Override
            public void onYes() {
                ViseHttp.POST(NetUrl.AppOrdercancellationOrder)
                        .addParam("goodsOrderId",id)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String d) {
                                try {
                                    JSONObject jsonObject = new JSONObject(d);
                                    if (jsonObject.optString("data").equals("Success")){
                                        ToastUtil.showShort(context, "取消订单成功!");
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
        dialogCustom.show();
    }
    public void to_pay(){//去支付
        ViseHttp.GET(NetUrl.AppOrderlistOrdersSubmitted)
                .addParam("id",id)
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
