package com.jingna.workshopapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.SpecialStatementBean;
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

public class SpecialStatementActivity extends BaseActivity {

    private Context context = SpecialStatementActivity.this;

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_statement);

        StatusBarUtils.setStatusBar(SpecialStatementActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(SpecialStatementActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.GET(NetUrl.ImportantStatementsqueryList)
                .addParam("pageSize", "0")
                .addParam("pageNum", "0")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                SpecialStatementBean bean = gson.fromJson(data, SpecialStatementBean.class);
                                HtmlFromUtils.setTextFromHtml(SpecialStatementActivity.this, tv, bean.getData().getStatementsContent());
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
