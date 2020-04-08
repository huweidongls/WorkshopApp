package com.jingna.workshopapp.page;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.CommissionIncomeItemAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.CommissionIncomeBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.StringUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommissionIncomeActivity extends BaseActivity {

    private Context context = CommissionIncomeActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_zhichu)
    TextView tvZhichu;
    @BindView(R.id.tv_shouru)
    TextView tvShouru;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    private CommissionIncomeItemAdapter adapter;
    private List<CommissionIncomeBean.DataBean.CommissionRevenuesBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission_income);

        StatusBarUtils.setStatusBar(CommissionIncomeActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CommissionIncomeActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.GET(NetUrl.MemUserCommissionRevenuesSum)
                .addParam("memberId", SpUtils.getUserId(context))
                .addParam("type", "")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                CommissionIncomeBean bean = gson.fromJson(data, CommissionIncomeBean.class);
                                mList = bean.getData().getCommissionRevenues();
                                adapter = new CommissionIncomeItemAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(context);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                                tvZhichu.setText("支出 ¥"+ StringUtils.roundByScale(bean.getData().getZhuanchu(), 2));
                                tvShouru.setText("收入 ¥"+ StringUtils.roundByScale(bean.getData().getZhuanru(), 2));
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

    @OnClick({R.id.rl_back, R.id.rl1, R.id.rl2, R.id.rl3})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl1:
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                tv1 .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv2 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv3 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                ViseHttp.GET(NetUrl.MemUserCommissionRevenuesSum)
                        .addParam("memberId", SpUtils.getUserId(context))
                        .addParam("type", "")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        CommissionIncomeBean bean = gson.fromJson(data, CommissionIncomeBean.class);
                                        mList.clear();
                                        mList.addAll(bean.getData().getCommissionRevenues());
                                        adapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
                break;
            case R.id.rl2:
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv1 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                tv2 .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv3 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                ViseHttp.GET(NetUrl.MemUserCommissionRevenuesSum)
                        .addParam("memberId", SpUtils.getUserId(context))
                        .addParam("type", "0")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        CommissionIncomeBean bean = gson.fromJson(data, CommissionIncomeBean.class);
                                        mList.clear();
                                        mList.addAll(bean.getData().getCommissionRevenues());
                                        adapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
                break;
            case R.id.rl3:
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv1 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv2 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                tv3 .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                ViseHttp.GET(NetUrl.MemUserCommissionRevenuesSum)
                        .addParam("memberId", SpUtils.getUserId(context))
                        .addParam("type", "1")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        CommissionIncomeBean bean = gson.fromJson(data, CommissionIncomeBean.class);
                                        mList.clear();
                                        mList.addAll(bean.getData().getCommissionRevenues());
                                        adapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
                break;
        }
    }

}
