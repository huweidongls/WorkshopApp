package com.jingna.workshopapp.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.AfterSaleOrderDetailsAdapter;
import com.jingna.workshopapp.adapter.MaintenanceAfterSaleAdapter;
import com.jingna.workshopapp.bean.AddressBean;
import com.jingna.workshopapp.bean.AfterSaleOrderDetailsBean;
import com.jingna.workshopapp.bean.AfterSaleOrderListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AfterSaleOrderDetailsActivity extends AppCompatActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_order)
    TextView tv_order;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phonenum)
    TextView tv_phonenum;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_bottom_price)
    TextView tv_bottom_price;
    private String id="";
    private List<AfterSaleOrderDetailsBean.DataBean.AppAfterSaleEquipmentsBean> mList;
    private AfterSaleOrderDetailsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale_order_details);
        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(AfterSaleOrderDetailsActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(AfterSaleOrderDetailsActivity.this);
        initData();
    }
    private void initData(){
        ViseHttp.GET(NetUrl.AfterSaleOrdergetOneByOrderId)
                .addParam("orderId",id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                AfterSaleOrderDetailsBean bean = gson.fromJson(data, AfterSaleOrderDetailsBean.class);
                                tv_order.setText(bean.getData().getId());
                                tv_time.setText(bean.getData().getCreateTime());
                                tv_name.setText(bean.getData().getAddresUname());
                                tv_phonenum.setText(bean.getData().getAddresPhone());
                                tv_address.setText(bean.getData().getAddresName());
                                mList = bean.getData().getAppAfterSaleEquipments();
                                tv_bottom_price.setText("共"+mList.size()+"件维修");
                                adapter = new AfterSaleOrderDetailsAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(AfterSaleOrderDetailsActivity.this);
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
