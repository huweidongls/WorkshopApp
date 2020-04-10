package com.jingna.workshopapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.StringUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.util.ViseUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity {

    private Context context = FeedbackActivity.this;

    @BindView(R.id.et)
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        StatusBarUtils.setStatusBar(FeedbackActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(FeedbackActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_sure:
                onSure();
                break;
        }
    }

    private void onSure() {

        String s = et.getText().toString();
        if(StringUtils.isEmpty(s)){
            ToastUtil.showShort(context, "反馈内容不能为空");
        }else {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("functionalFeedback", s);
            map.put("memberId", SpUtils.getUserId(context));
            ViseUtil.Post(context, NetUrl.FunctionalFeedbacktoAdd, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    ToastUtil.showShort(context, "提交成功");
                    finish();
                }
            });
        }

    }

}
