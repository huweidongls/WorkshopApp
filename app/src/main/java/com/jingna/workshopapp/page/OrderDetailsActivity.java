package com.jingna.workshopapp.page;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailsActivity extends AppCompatActivity {
    private Context context = OrderDetailsActivity.this;
    @BindView(R.id.tv_goods_title)
    TextView tv_goods_title;
    @BindView(R.id.iv_goods_img)
    ImageView iv_goods_img;
    @BindView(R.id.r_address)
    RelativeLayout r_address;
    @BindView(R.id.sh_name)
    TextView sh_name;
    @BindView(R.id.sh_phone)
    TextView sh_phone;
    @BindView(R.id.sh_address)
    TextView sh_address;
    @BindView(R.id.order_sn)
    TextView order_sn;
    @BindView(R.id.add_time)
    TextView add_time;
    @BindView(R.id.pay_type)
    TextView pay_type;
    @BindView(R.id.sc_time)
    TextView sc_time;
    @BindView(R.id.type_time)
    TextView type_time;
    @BindView(R.id.fp_type)
    TextView fp_type;
    @BindView(R.id.count_money)
    TextView count_money;
    @BindView(R.id.yunfei)
    TextView yunfei;
    @BindView(R.id.zong_money)
    TextView zong_money;
    private String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(OrderDetailsActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(OrderDetailsActivity.this);
        initdata();
    }
    private void initdata(){
        ViseHttp.GET(NetUrl)
    }
}
