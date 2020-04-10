package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.PeitaoshebeiAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.PeitaoshebeiBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
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

public class PeitaoshebeiActivity extends BaseActivity {

    private Context context = PeitaoshebeiActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private PeitaoshebeiAdapter adapter;
    private List<PeitaoshebeiBean.DataBean> mList;

    private String id = "";
    private String type = "";
    private List<PeitaoshebeiBean.DataBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peitaoshebei);

        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        beanList = (List<PeitaoshebeiBean.DataBean>) getIntent().getSerializableExtra("beanList");
        StatusBarUtils.setStatusBar(PeitaoshebeiActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(PeitaoshebeiActivity.this);
        initData();

    }

    private void initData() {

        if(type.equals("0")){
            Map<String, String> map = new LinkedHashMap<>();
            map.put("workshopId", id);
            ViseUtil.Get(context, NetUrl.AppOrderworkshopEquipment, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    PeitaoshebeiBean bean = gson.fromJson(s, PeitaoshebeiBean.class);
                    mList = bean.getData();
                    adapter = new PeitaoshebeiAdapter(mList);
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
            });
        }else {
            mList = beanList;
            adapter = new PeitaoshebeiAdapter(mList);
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

    }

    @OnClick({R.id.rl_back, R.id.tv_cancel, R.id.tv_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_save:
                Intent intent = new Intent();
                intent.putExtra("bean", (Serializable) mList);
                setResult(100, intent);
                finish();
                break;
        }
    }

}
