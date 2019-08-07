package com.jingna.workshopapp.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;

import butterknife.ButterKnife;

public class CrowdDetailsSupportActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowd_details_support);
        StatusBarUtils.setStatusBar(CrowdDetailsSupportActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CrowdDetailsSupportActivity.this);
    }
}
