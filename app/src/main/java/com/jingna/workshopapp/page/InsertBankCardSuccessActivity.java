package com.jingna.workshopapp.page;

import android.os.Bundle;
import android.view.View;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsertBankCardSuccessActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_bank_card_success);

        StatusBarUtils.setStatusBar(InsertBankCardSuccessActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(InsertBankCardSuccessActivity.this);

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
