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

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageSize", "0");
        map.put("pageNum", "0");
        ViseUtil.Get(context, NetUrl.ImportantStatementsqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                SpecialStatementBean bean = gson.fromJson(s, SpecialStatementBean.class);
                HtmlFromUtils.setTextFromHtml(SpecialStatementActivity.this, tv, bean.getData().getStatementsContent());
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
