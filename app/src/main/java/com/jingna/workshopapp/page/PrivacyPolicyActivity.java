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
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

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

        ViseHttp.GET(NetUrl.PrivacyPolicyqueryList)
                .addParam("pageSize", "0")
                .addParam("pageNum", "0")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                PrivacyPolicyBean bean = gson.fromJson(data, PrivacyPolicyBean.class);
                                HtmlFromUtils.setTextFromHtml(PrivacyPolicyActivity.this, tv, bean.getData().getPrivacyPolicy());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

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
