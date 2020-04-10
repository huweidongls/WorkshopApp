package com.jingna.workshopapp.page;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.CommissionIncomeItemAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.CommissionIncomeBean;
import com.jingna.workshopapp.bean.MemberCommissionAuditsBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.StringUtils;
import com.jingna.workshopapp.util.ViseUtil;
import com.jingna.workshopapp.util.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;

    private CommissionIncomeItemAdapter adapter;
    private List<CommissionIncomeBean.DataBean.CommissionRevenuesBean> mList;
    private List<MemberCommissionAuditsBean.DataBean.MemberCommissionAudits> mList1;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission_income);

        StatusBarUtils.setStatusBar(CommissionIncomeActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CommissionIncomeActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("memberId", SpUtils.getUserId(context));
        map.put("type", "1");
        ViseUtil.Get(context, NetUrl.MemUserCommissionRevenuesSum, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                MemberCommissionAuditsBean bean = gson.fromJson(s, MemberCommissionAuditsBean.class);
                mList1 = bean.getData().getMemberCommissionAudits();
                adapter = new CommissionIncomeItemAdapter(mList1, 1);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl1, R.id.rl2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl1:
                dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                rlTop.setVisibility(View.GONE);
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                tv1 .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv2 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                Map<String, String> map = new LinkedHashMap<>();
                map.put("memberId", SpUtils.getUserId(context));
                map.put("type", "1");
                ViseUtil.Get(context, NetUrl.MemUserCommissionRevenuesSum, map, dialog, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        MemberCommissionAuditsBean bean = gson.fromJson(s, MemberCommissionAuditsBean.class);
                        mList1 = bean.getData().getMemberCommissionAudits();
                        adapter = new CommissionIncomeItemAdapter(mList1, 1);
                        LinearLayoutManager manager = new LinearLayoutManager(context);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                    }
                });
                break;
            case R.id.rl2:
                dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                rlTop.setVisibility(View.VISIBLE);
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv1 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                tv2 .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                Map<String, String> map1 = new LinkedHashMap<>();
                map1.put("memberId", SpUtils.getUserId(context));
                map1.put("type", "0");
                ViseUtil.Get(context, NetUrl.MemUserCommissionRevenuesSum, map1, dialog, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        CommissionIncomeBean bean = gson.fromJson(s, CommissionIncomeBean.class);
                        mList = bean.getData().getCommissionRevenues();
                        adapter = new CommissionIncomeItemAdapter(mList);
                        LinearLayoutManager manager = new LinearLayoutManager(context);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                        tvZhichu.setText("支出 ¥"+ StringUtils.roundByScale(bean.getData().getZhuanchu(), 2));
                        tvShouru.setText("收入 ¥"+ StringUtils.roundByScale(bean.getData().getZhuanru(), 2));
                    }
                });
                break;
        }
    }

}
