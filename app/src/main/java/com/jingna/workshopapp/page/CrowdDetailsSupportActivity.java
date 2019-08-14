package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.AddressBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CrowdDetailsSupportActivity extends BaseActivity {
    private Context context = CrowdDetailsSupportActivity.this;
    @BindView(R.id.iv_pay_wx)
    ImageView iv_pay_wx;
    @BindView(R.id.iv_pay_zfb)
    ImageView iv_pay_zfb;
    @BindView(R.id.iv_check)
    ImageView iv_check;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_tel)
    TextView tvPhonenum;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    private int pay=0;
    private int paycheck=0;
    private int addressid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowd_details_support);
        StatusBarUtils.setStatusBar(CrowdDetailsSupportActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CrowdDetailsSupportActivity.this);
    }
    @OnClick({R.id.rl_back, R.id.iv_wx,R.id.iv_zfb,R.id.iv_check,R.id.rl_address})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_zfb:
                pay=2;
                Glide.with(context).load(R.mipmap.duihao).into(iv_pay_zfb);
                Glide.with(context).load(R.drawable.img_radios).into(iv_pay_wx);
                break;
            case R.id.iv_wx:
                pay=1;
                Glide.with(context).load(R.mipmap.duihao).into(iv_pay_wx);
                Glide.with(context).load(R.drawable.img_radios).into(iv_pay_zfb);
                break;
            case R.id.iv_check:
                if (paycheck==0){
                    paycheck=1;
                    Glide.with(context).load(R.mipmap.duihao).into(iv_check);
                }else{
                    paycheck=0;
                    Glide.with(context).load(R.drawable.img_radios).into(iv_check);
                }
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
            addressid=bean.getId();
            tvName.setText(bean.getConsignee());
            tvPhonenum.setText(bean.getConsigneeTel());
            tvAddress.setText(bean.getLocation()+bean.getAdress());
        }
    }
}
