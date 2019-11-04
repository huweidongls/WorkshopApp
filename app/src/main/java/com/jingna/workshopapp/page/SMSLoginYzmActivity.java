package com.jingna.workshopapp.page;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.app.MyApplication;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.LoginBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.SpUtils;
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

public class SMSLoginYzmActivity extends BaseActivity {

    private Context context = SMSLoginYzmActivity.this;

    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.et_code)
    EditText etCode;

    private String phoneNum = "";

    public TextView getCode_btn() {
        return tvGetCode;
    }

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smslogin_yzm);

        phoneNum = getIntent().getStringExtra("phone");
        MyApplication.smsCodeTimeCount.setActivity(SMSLoginYzmActivity.this);
        StatusBarUtils.setStatusBar(SMSLoginYzmActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(SMSLoginYzmActivity.this);
        initData();

    }

    private void initData() {

        MyApplication.smsCodeTimeCount.start();

    }

    @OnClick({R.id.rl_back, R.id.tv_get_code, R.id.btn_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_get_code:
                MyApplication.smsCodeTimeCount.start();
                getCode();
                break;
            case R.id.btn_login:
                next();
                break;
        }
    }

    /**
     * 登录
     */
    private void next() {

        String code = etCode.getText().toString();
        if(TextUtils.isEmpty(code)){
            ToastUtil.showShort(context, "验证码不能为空");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            ViseHttp.GET(NetUrl.MemUserloginAPP)
                    .addParam("phoneNum", phoneNum)
                    .addParam("code", code)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Logger.e("123123", data);
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "登录成功");
                                    Gson gson = new Gson();
                                    LoginBean loginBean = gson.fromJson(data, LoginBean.class);
                                    SpUtils.setUserId(context, loginBean.getData().getUserId()+"");
                                    SpUtils.setToken(context, loginBean.getData().getToken());
                                    SpUtils.setPhoneNum(context, phoneNum);
                                    finish();
                                }else {
                                    ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                                }
                                WeiboDialogUtils.closeDialog(dialog);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {
                            WeiboDialogUtils.closeDialog(dialog);
                        }
                    });
        }

    }

    /**
     * 获取验证码
     */
    private void getCode() {

        Logger.e("123123", phoneNum);
        ViseHttp.GET(NetUrl.MemUsersendMessage)
                .addParam("phone", phoneNum)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Logger.e("123123", data);
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
                        Logger.e("123123", errMsg);
                    }
                });

    }

}
