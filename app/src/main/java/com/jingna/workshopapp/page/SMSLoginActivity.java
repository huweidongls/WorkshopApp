package com.jingna.workshopapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.StringUtils;
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

public class SMSLoginActivity extends BaseActivity {

    private Context context = SMSLoginActivity.this;

    @BindView(R.id.et_phonenum)
    EditText etPhoneNum;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smslogin);

        StatusBarUtils.setStatusBar(SMSLoginActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(SMSLoginActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_yzm, R.id.tv_pwd_login, R.id.tv_register})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_yzm:
                next();
                break;
            case R.id.tv_pwd_login:
                intent.setClass(context, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_register:
                intent.setClass(context, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    /**
     * 获取验证码并跳转
     */
    private void next() {

        final String phoneNum = etPhoneNum.getText().toString();
        if(TextUtils.isEmpty(phoneNum)){
            ToastUtil.showShort(context, "手机号不能为空");
        }else if(!StringUtils.isPhoneNumberValid(phoneNum)){
            ToastUtil.showShort(context, "请输入正确格式的手机号码");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "正在发送...");
            Map<String, String> map = new LinkedHashMap<>();
            map.put("phone", phoneNum);
            ViseUtil.Get(context, NetUrl.MemUsersendMessage, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    ToastUtil.showShort(context, "验证码发送成功");
                    Intent intent = new Intent();
                    intent.setClass(context, SMSLoginYzmActivity.class);
                    intent.putExtra("phone", phoneNum);
                    startActivity(intent);
                    finish();
                }
            });
        }

    }

}
