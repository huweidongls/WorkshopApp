package com.jingna.workshopapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.LoginBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.SpUtils;
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

public class LoginActivity extends BaseActivity {

    private Context context = LoginActivity.this;

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StatusBarUtils.setStatusBar(LoginActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(LoginActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_login, R.id.tv_code_login, R.id.tv_register, R.id.tv_forgot_pwd})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_code_login:
                intent.setClass(context, SMSLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_register:
                intent.setClass(context, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_forgot_pwd:
                intent.setClass(context, ForgotPwd1Activity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void login() {

        final String name = etName.getText().toString();
        String pwd = etPwd.getText().toString();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
            ToastUtil.showShort(context, "手机号或密码不能为空");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            Map<String, String> map = new LinkedHashMap<>();
            map.put("phoneNum", name);
            map.put("password", pwd);
            ViseUtil.Get(context, NetUrl.MemUserloginAPP, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    ToastUtil.showShort(context, "登录成功");
                    Gson gson = new Gson();
                    LoginBean loginBean = gson.fromJson(s, LoginBean.class);
                    Logger.e("123123", loginBean.getData().getUserId()+"");
                    SpUtils.setUserId(context, loginBean.getData().getUserId()+"");
                    SpUtils.setToken(context, loginBean.getData().getToken());
                    SpUtils.setPhoneNum(context, name);
                    finish();
                }
            });
        }

    }

}
