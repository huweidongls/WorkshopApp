package com.jingna.workshopapp.page;

import android.os.Bundle;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;

import butterknife.ButterKnife;

public class SignatureActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        ButterKnife.bind(SignatureActivity.this);
        initData();

    }
}
