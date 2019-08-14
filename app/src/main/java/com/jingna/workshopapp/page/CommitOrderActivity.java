package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.AddressBean;
import com.jingna.workshopapp.bean.OrderShebeiBean;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommitOrderActivity extends BaseActivity {

    private Context context = CommitOrderActivity.this;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phonenum)
    TextView tvPhonenum;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    String s = "";
    private OrderShebeiBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_order);

        s = getIntent().getStringExtra("bean");
        StatusBarUtils.setStatusBar(CommitOrderActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CommitOrderActivity.this);
        initData();

    }

    private void initData() {

        Gson gson = new Gson();
        bean = gson.fromJson(s, OrderShebeiBean.class);
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
                                        tvName.setText(bean1.getConsignee());
                                        tvPhonenum.setText(bean1.getConsigneeTel());
                                        tvAddress.setText(bean1.getLocation()+bean1.getAdress());
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

    @OnClick({R.id.rl_back, R.id.rl_address})
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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100&&data != null){
            AddressBean.DataBean bean = (AddressBean.DataBean) data.getSerializableExtra("address");
            tvName.setText(bean.getConsignee());
            tvPhonenum.setText(bean.getConsigneeTel());
            tvAddress.setText(bean.getLocation()+bean.getAdress());
        }
    }
}
