package com.jingna.workshopapp.page;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.util.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubmissionEvaluateActivity extends AppCompatActivity {

    private Context context = SubmissionEvaluateActivity.this;

    private String id = "";
    private String imgUrl = "";
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_evaluate);

        id = getIntent().getStringExtra("id");
        imgUrl = getIntent().getStringExtra("imgUrl");
        title = getIntent().getStringExtra("title");
        StatusBarUtils.setStatusBar(SubmissionEvaluateActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(SubmissionEvaluateActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_commit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_commit:

                break;
        }
    }

}
