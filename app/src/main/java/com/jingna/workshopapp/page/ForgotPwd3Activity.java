package com.jingna.workshopapp.page;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
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

public class ForgotPwd3Activity extends BaseActivity {

    private Context context = ForgotPwd3Activity.this;

    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.iv_is_show)
    ImageView ivIsShow;

    private boolean isShowPwd = false;
    private String phoneNumber = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd3);

        phoneNumber = getIntent().getStringExtra("phone");
        StatusBarUtils.setStatusBar(ForgotPwd3Activity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(ForgotPwd3Activity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_is_show, R.id.btn_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_is_show:
                if(isShowPwd){
                    isShowPwd = false;
                    Glide.with(context).load(R.mipmap.miwen).into(ivIsShow);
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                }else {
                    isShowPwd = true;
                    Glide.with(context).load(R.mipmap.mingwen).into(ivIsShow);
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                }
                break;
            case R.id.btn_sure:
                sure();
                break;
        }
    }

    /**
     * 验证密码是否符合规则及访问接口
     */
    private void sure() {

        String pwd = etPwd.getText().toString();
        if(TextUtils.isEmpty(pwd)){
            ToastUtil.showShort(context, "密码不能为空");
        }else if(pwd.length()<6||pwd.length()>20){
            ToastUtil.showShort(context, "密码长度为6-20位，请重新设置密码");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            String url = "/MemUser/retrievePassword";
            Map<String, String> map = new LinkedHashMap<>();
            map.put("phone", phoneNumber);
            map.put("newPassword", pwd);
            ViseUtil.Post(context, url, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    ToastUtil.showShort(context, "密码修改成功");
                    finish();
                }
            });
        }

    }

}
