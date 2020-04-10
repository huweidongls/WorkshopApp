package com.jingna.workshopapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.app.MyApplication;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.util.ViseUtil;
import com.jingna.workshopapp.util.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPwd2Activity extends BaseActivity {

    private Context context = ForgotPwd2Activity.this;

    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.tv_phonenum)
    TextView tvPhoneNum;

    private String phoneNum = "";

    public TextView getCode_btn() {
        return tvGetCode;
    }

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd2);

        phoneNum = getIntent().getStringExtra("phone");
        MyApplication.forgotTimeCount.setActivity(ForgotPwd2Activity.this);
        StatusBarUtils.setStatusBar(ForgotPwd2Activity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(ForgotPwd2Activity.this);
        initData();

    }

    private void initData() {

        MyApplication.forgotTimeCount.start();
        String showNum = phoneNum.substring(0, 3)+"****"+phoneNum.substring(7, 11);
        tvPhoneNum.setText(showNum);

    }

    @OnClick({R.id.rl_back, R.id.btn_next, R.id.tv_get_code})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_next:
                next();
                break;
            case R.id.tv_get_code:
                MyApplication.forgotTimeCount.start();
                getCode();
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getCode() {

        String url = "/MemUser/sendMessage";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("phone", phoneNum);
        ViseUtil.Get(context, url, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                ToastUtil.showShort(context, "验证码发送成功");
            }
        });

    }

    private void next() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        String code = etCode.getText().toString();
        String url = "/MemUser/matchCode";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("phone", phoneNum);
        map.put("code", code);
        ViseUtil.Get(context, url, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if(jsonObject.optString("status").equals("200")){
                        if(jsonObject.optInt("data") == 1){
                            Intent intent = new Intent();
                            intent.setClass(context, ForgotPwd3Activity.class);
                            intent.putExtra("phone", phoneNum);
                            startActivity(intent);
                            finish();
                        }else {
                            ToastUtil.showShort(context, "验证码不正确");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
