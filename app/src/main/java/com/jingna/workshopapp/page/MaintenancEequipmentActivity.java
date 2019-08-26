package com.jingna.workshopapp.page;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.MaintenancEequipmentAdapter;
import com.jingna.workshopapp.adapter.PeitaoshebeiAdapter;
import com.jingna.workshopapp.bean.MaintenancEequipmentBean;
import com.jingna.workshopapp.bean.PeitaoshebeiBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaintenancEequipmentActivity extends AppCompatActivity {
    private Context context = MaintenancEequipmentActivity.this;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private MaintenancEequipmentAdapter adapter;
    private List<MaintenancEequipmentBean.DataBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenanc_eequipment);
        StatusBarUtils.setStatusBar(MaintenancEequipmentActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MaintenancEequipmentActivity.this);
        initData();
    }
    private void initData(){
        ViseHttp.GET(NetUrl.AppAfterSaleEquipmentqueryList)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                MaintenancEequipmentBean bean = gson.fromJson(data, MaintenancEequipmentBean.class);
                                mList = bean.getData();
                                adapter = new MaintenancEequipmentAdapter(mList);
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
}
