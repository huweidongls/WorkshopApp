package com.jingna.workshopapp.page;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.AfterSaleOrderDetailsTopayAdapter;
import com.jingna.workshopapp.bean.AfterSaleOrderDetailsToPayBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.StringUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AfterOrderDetailsPaymentedActivity extends AppCompatActivity {
    private Context context = AfterOrderDetailsPaymentedActivity.this;
    private String id="";
    private double moeny_all = 0.00;
    private AfterSaleOrderDetailsTopayAdapter adapter;
    private List<AfterSaleOrderDetailsToPayBean.DataBean.AfterSaleOrderItemsBean> mList;
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
    @BindView(R.id.tv_order)
    TextView tv_order;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_order_details_paymented);
        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(AfterOrderDetailsPaymentedActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(AfterOrderDetailsPaymentedActivity.this);
        initData();
    }
    private void initData() {
        ViseHttp.GET(NetUrl.AfterSaleOrdergetByWxPayDetails)
                .addParam("orderId", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")) {
                                Gson gson = new Gson();
                                AfterSaleOrderDetailsToPayBean bean = gson.fromJson(data, AfterSaleOrderDetailsToPayBean.class);
                                mList = bean.getData().getAfterSaleOrderItems();
                                tv_name.setText(bean.getData().getAddresUname());
                                tv_order.setText(bean.getData().getId());
                                tv_time.setText(bean.getData().getCreateTime());
                                tv_tel.setText(bean.getData().getAddresPhone());
                                tv_address.setText(bean.getData().getAddresName());
                                goods_all_price.setText("¥" + StringUtils.roundByScale(bean.getData().getRepairMoney(), 2) + "元");
                                goods_yunfei.setText("¥" + StringUtils.roundByScale(bean.getData().getRepairTimeMoney(), 2) + "元");
                                pay_price.setText("¥" + StringUtils.roundByScale(bean.getData().getCarMoney(), 2) + "元");
                                moeny_all = bean.getData().getCarMoney() + bean.getData().getRepairTimeMoney() + bean.getData().getRepairMoney();
                                conmit_all_price.setText("¥" + StringUtils.roundByScale(moeny_all, 2) + "元");
                                adapter = new AfterSaleOrderDetailsTopayAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(context){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
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
    @OnClick({R.id.rl_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }
}
