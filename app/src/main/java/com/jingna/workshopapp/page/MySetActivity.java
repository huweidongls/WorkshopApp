package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySetActivity extends BaseActivity {

    private Context context = MySetActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_set);

        StatusBarUtils.setStatusBar(MySetActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(MySetActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_special_statement, R.id.rl_privacy_policy})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_special_statement:
                intent.setClass(context, SpecialStatementActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_privacy_policy:
                intent.setClass(context, PrivacyPolicyActivity.class);
                startActivity(intent);
                break;
        }
    }

}
