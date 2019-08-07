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
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PeitaoshebeiActivity extends BaseActivity {

    private Context context = PeitaoshebeiActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private PeitaoshebeiAdapter adapter;
    private List<PeitaoshebeiBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peitaoshebei);

        StatusBarUtils.setStatusBar(PeitaoshebeiActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(PeitaoshebeiActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.GET(NetUrl.AppOrderworkshopEquipment)
                .addParam("workshopId", "9")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                PeitaoshebeiBean bean = gson.fromJson(data, PeitaoshebeiBean.class);
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
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

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
