package com.jingna.workshopapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.app.MyApplication;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.util.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterYzmActivity extends BaseActivity {

    private Context context = RegisterYzmActivity.this;

    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    private String phoneNumber = "";
    private String yq = "";

    public TextView getCode_btn() {
        return tvGetCode;
    }

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_yzm);

        MyApplication.ftptimecount.setActivity(RegisterYzmActivity.this);
        phoneNumber = getIntent().getStringExtra("number");
        yq = getIntent().getStringExtra("yq");
        StatusBarUtils.setStatusBar(RegisterYzmActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(RegisterYzmActivity.this);
        initData();

    }

    private void initData() {

        MyApplication.ftptimecount.start();

    }

    @OnClick({R.id.rl_back, R.id.btn_next, R.id.tv_get_code})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_next:
                next();
                break;
            case R.id.tv_get_code:
                MyApplication.ftptimecount.start();
                getCode();
                break;
        }
    }

    /**
     * 根据手机号获取验证码
     */
    private void getCode() {

        Log.e("123123", phoneNumber);
        ViseHttp.GET(NetUrl.MemUsersendMessage)
                .addParam("phone", phoneNumber)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Log.e("123123", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                ToastUtil.showShort(context, "短信验证码发送成功");
                            }else {
                                ToastUtil.showShort(context, "短信验证码发送失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        Log.e("123123", errMsg);
                    }
                });

    }

    /**
     * 验证码是否匹配，匹配则跳转设置密码
     */
    private void next() {

        String code = etCode.getText().toString();
        if(TextUtils.isEmpty(code)){
            ToastUtil.showShort(context, "验证码不能为空");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            ViseHttp.GET(NetUrl.MemUsermatchCode)
                    .addParam("phone", phoneNumber)
                    .addParam("code", code)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                Log.e("123123", data);
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optInt("data") == 1){
                                    Intent intent = new Intent();
                                    intent.setClass(context, RegisterSetPwdActivity.class);
                                    intent.putExtra("number", phoneNumber);
                                    intent.putExtra("yq", yq);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    ToastUtil.showShort(context, "短信验证码错误");
                                }
                                WeiboDialogUtils.closeDialog(dialog);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {
                            Logger.e("123123", errMsg);
                            WeiboDialogUtils.closeDialog(dialog);
                        }
                    });
        }

    }

}
