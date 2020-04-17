package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.MaintenancEequipmentAdapter;
import com.jingna.workshopapp.adapter.PeitaoshebeiAdapter;
import com.jingna.workshopapp.bean.MaintenancEequipmentBean;
import com.jingna.workshopapp.bean.PeitaoshebeiBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.util.ViseUtil;
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

public class MaintenancEequipmentActivity extends AppCompatActivity {
    private Context context = MaintenancEequipmentActivity.this;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private MaintenancEequipmentAdapter adapter;
    private List<MaintenancEequipmentBean.DataBean> mList;
    private int Commint_On = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenanc_eequipment);
        StatusBarUtils.setStatusBar(MaintenancEequipmentActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MaintenancEequipmentActivity.this);
        initData();
    }

    private void initData() {
        ViseUtil.Get(context, NetUrl.AppAfterSaleEquipmentqueryList, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                MaintenancEequipmentBean bean = gson.fromJson(s, MaintenancEequipmentBean.class);
                mList = bean.getData();
                adapter = new MaintenancEequipmentAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(context) {
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

    @OnClick({R.id.rl_back, R.id.tv_cancel, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_save:
                for (MaintenancEequipmentBean.DataBean bean : mList) {
                    if (bean.getIsSelect() == 1) {
                        Commint_On = 1;
                    }
                }
                if (Commint_On == 0) {
                    ToastUtil.showShort(context, "请完善信息后提交");
                } else {
                    startActivity(new Intent(MaintenancEequipmentActivity.this, AfterSale_Commit_orderActivity.class)
                            .putExtra("bean", (Serializable) mList)
                    );
                    finish();
                }

                break;
        }
    }

    private void On_Self() {
        for (MaintenancEequipmentBean.DataBean bean : mList) {
            if (bean.getIsSelect() == 1) {
                Commint_On = 1;
            }
        }
    }
}
