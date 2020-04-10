package com.jingna.workshopapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.PrivacyPolicyBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.HtmlFromUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
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

public class PrivacyPolicyActivity extends BaseActivity {

    private Context context = PrivacyPolicyActivity.this;

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        StatusBarUtils.setStatusBar(PrivacyPolicyActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(PrivacyPolicyActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageSize", "0");
        map.put("pageNum", "0");
        ViseUtil.Get(context, NetUrl.PrivacyPolicyqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                PrivacyPolicyBean bean = gson.fromJson(s, PrivacyPolicyBean.class);
                HtmlFromUtils.setTextFromHtml(PrivacyPolicyActivity.this, tv, bean.getData().getPrivacyPolicy());
            }
        });

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
