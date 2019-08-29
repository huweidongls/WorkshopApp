package com.jingna.workshopapp.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.util.StatusBarUtils;

import butterknife.ButterKnife;

public class AfterOrderDetailsPaymentedActivity extends AppCompatActivity {
    private String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_order_details_paymented);
        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(AfterOrderDetailsPaymentedActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(AfterOrderDetailsPaymentedActivity.this);
    }
}
