package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.AfterSaleEquipmentAdapter;
import com.jingna.workshopapp.adapter.MaintenancEequipmentAdapter;
import com.jingna.workshopapp.adapter.OrderShebeiAdapter;
import com.jingna.workshopapp.bean.AddressBean;
import com.jingna.workshopapp.bean.MaintenancEequipmentBean;
import com.jingna.workshopapp.bean.PeitaoshebeiBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private String goodsId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale__commit_order);
        StatusBarUtils.setStatusBar(AfterSale_Commit_orderActivity.this, getResources().getColor(R.color.statusbar_color));
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
                    goodsId = goodsId+bean.getId()+",";
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
    @OnClick({R.id.rl_back,R.id.rl_address,R.id.tv_commit})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_address:
                intent.setClass(context, AddressActivity.class);
                intent.putExtra("type", "order");
                startActivityForResult(intent, 1001);
                break;
            case R.id.tv_commit:
                Commint_Order();
                finish();
                break;
        }
    }
    private void Commint_Order(){
        ViseHttp.GET(NetUrl.AfterSaleOrderafterSaleOrder)
                .addParam("userId",SpUtils.getUserId(context))
                .addParam("deviceId",goodsId.substring(0,goodsId.length()-1))
                .addParam("addresId",addressid)
                .addParam("orderStatus","1")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            Logger.e("123123", data);
                            if (jsonObject.optString("status").equals("200")){
                                ToastUtil.showShort(context, "订单提交成功!");
                                startActivity(new Intent(AfterSale_Commit_orderActivity.this,MaintenanceAfterSaleActivity.class)
                                );
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100&&data != null){
            AddressBean.DataBean bean = (AddressBean.DataBean) data.getSerializableExtra("address");
            addressid=bean.getId()+"";
            tv_name.setText(bean.getConsignee());
            tv_phonenum.setText(bean.getConsigneeTel());
            tv_address.setText(bean.getLocation()+bean.getAdress());
        }
    }
}
