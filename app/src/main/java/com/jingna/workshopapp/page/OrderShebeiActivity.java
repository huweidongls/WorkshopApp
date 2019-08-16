package com.jingna.workshopapp.page;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.OrderShebeiAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.OrderShebeiBean;
import com.jingna.workshopapp.bean.PeitaoshebeiBean;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.StringUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderShebeiActivity extends BaseActivity {

    private Context context = OrderShebeiActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_zanwu)
    TextView tvZanwu;
    @BindView(R.id.ll_zanwu)
    LinearLayout llZanwu;

    private OrderShebeiAdapter adapter;
    private List<PeitaoshebeiBean.DataBean> mList;

    private String id = "";

    private int mYear;
    private int mMonth;
    private int mDay;

    private List<PeitaoshebeiBean.DataBean> beanList;
    private String type = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_shebei);

        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(OrderShebeiActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(OrderShebeiActivity.this);
        initData();

    }

    private void initData() {

        beanList = new ArrayList<>();
        mList = new ArrayList<>();
        adapter = new OrderShebeiAdapter(mList);
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

    @OnClick({R.id.rl_back, R.id.btn_insert, R.id.tv_reset, R.id.tv_start, R.id.tv_end, R.id.tv_commit})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_insert:
                intent.setClass(context, PeitaoshebeiActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", type);
                intent.putExtra("beanList", (Serializable) beanList);
                startActivityForResult(intent, 100);
                break;
            case R.id.tv_reset:
                mList.clear();
                adapter.notifyDataSetChanged();
                type = "0";
                tvZanwu.setVisibility(View.VISIBLE);
                llZanwu.setVisibility(View.GONE);
                break;
            case R.id.tv_start:
                new DatePickerDialog(context, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.tv_end:
                new DatePickerDialog(context, onDateSetListener1, mYear, mMonth, mDay).show();
                break;
            case R.id.tv_commit:
                String start = tvStart.getText().toString();
                String end = tvEnd.getText().toString();
                if(!StringUtils.isEmpty(start)&&!StringUtils.isEmpty(end)&&mList.size()>0){
                    Gson gson = new Gson();
                    String json = gson.toJson(mList);
                    intent.setClass(context, CommitOrderActivity.class);
                    intent.putExtra("type", "1");
                    intent.putExtra("id", id);
                    intent.putExtra("start", start);
                    intent.putExtra("end", end);
                    intent.putExtra("json", json);
                    startActivity(intent);
                }else {
                    ToastUtil.showShort(context, "请完善信息后提交");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100&&resultCode == 100&&data != null){
            List<PeitaoshebeiBean.DataBean> list = (List<PeitaoshebeiBean.DataBean>) data.getSerializableExtra("bean");
            beanList.clear();
            beanList.addAll(list);
            type = "1";
            mList.clear();
            for (PeitaoshebeiBean.DataBean bean : list){
                if(bean.getIsSelect() == 1){
                    mList.add(bean);
                }
            }
            adapter.notifyDataSetChanged();
            tvZanwu.setVisibility(View.GONE);
            llZanwu.setVisibility(View.VISIBLE);
        }
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            final String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            }
            tvStart.setText(days);
        }
    };

    private DatePickerDialog.OnDateSetListener onDateSetListener1 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            final String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            }
            tvEnd.setText(days);
        }
    };

}
