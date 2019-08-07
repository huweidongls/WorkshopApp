package com.jingna.workshopapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderShebeiActivity extends BaseActivity {

    private Context context = OrderShebeiActivity.this;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_shebei);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(OrderShebeiActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(OrderShebeiActivity.this);

    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
