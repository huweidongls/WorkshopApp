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
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

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
            ViseHttp.POST(NetUrl.FunctionalFeedbacktoAdd)
                    .addParam("functionalFeedback", s)
                    .addParam("memberId", SpUtils.getUserId(context))
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Logger.e("123123", data);
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "提交成功");
                                    finish();
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

    }

}
