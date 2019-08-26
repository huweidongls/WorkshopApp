package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.AfterSaleEquipmentAdapter;
import com.jingna.workshopapp.adapter.MaintenancEequipmentAdapter;
import com.jingna.workshopapp.adapter.OrderShebeiAdapter;
import com.jingna.workshopapp.bean.AddressBean;
import com.jingna.workshopapp.bean.MaintenancEequipmentBean;
import com.jingna.workshopapp.bean.PeitaoshebeiBean;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AfterSale_Commit_orderActivity extends AppCompatActivity {
    private Context context = AfterSale_Commit_orderActivity.this;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_bottom_price)
    TextView tv_bottom_price;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phonenum)
    TextView tv_phonenum;
    @BindView(R.id.tv_address)
    TextView tv_address;
    private String addressid="";
    private AfterSaleEquipmentAdapter adapter;
    private List<MaintenancEequipmentBean.DataBean> mList;
    private List<MaintenancEequipmentBean.DataBean> beanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale__commit_order);
        StatusBarUtils.setStatusBar(AfterSale_Commit_orderActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AfterSale_Commit_orderActivity.this);
        Intent intent = getIntent();
        if(intent != null){
            beanList= (List<MaintenancEequipmentBean.DataBean>) intent.getSerializableExtra("bean");
        }
        initData();
        LoadAddredd();
    }
    private void initData(){
        mList = new ArrayList<>();
            for (MaintenancEequipmentBean.DataBean bean : beanList){
                if(bean.getIsSelect() == 1){
                    mList.add(bean);
                }
            }
        tv_bottom_price.setText("共"+mList.size()+"件维修");
        adapter = new AfterSaleEquipmentAdapter(mList);
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
    private void LoadAddredd(){
        ViseHttp.GET("/MemAdress/queryList")
                .addParam("memberId", SpUtils.getUserId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                AddressBean bean = gson.fromJson(data, AddressBean.class);
                                List<AddressBean.DataBean> list = bean.getData();
                                for (AddressBean.DataBean bean1 : list){
                                    if(bean1.getAcquiescentAdress().equals("1")){
                                        addressid = bean1.getId()+"";
                                        tv_name.setText(bean1.getConsignee());
                                        tv_phonenum.setText(bean1.getConsigneeTel());
                                        tv_address.setText(bean1.getLocation()+bean1.getAdress());
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
}
