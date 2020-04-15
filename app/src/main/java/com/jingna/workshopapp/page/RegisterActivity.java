package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.dialog.RegisterDialog;
import com.jingna.workshopapp.dialog.SendYzmDialog;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.StringUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.util.ViseUtil;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private Context context = RegisterActivity.this;

    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_yq)
    EditText etYq;

    private int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        StatusBarUtils.setStatusBar(RegisterActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(RegisterActivity.this);
        initData();

    }

    private void initData() {

        RegisterDialog dialog = new RegisterDialog(context, new RegisterDialog.ClickListener() {
            @Override
            public void onSure() {

            }

            @Override
            public void onCancel() {
                RegisterActivity.this.finish();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

    }

    @OnClick({R.id.rl_back, R.id.btn_next, R.id.iv_sao})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_next:
                next();
                break;
            case R.id.iv_sao:
                Intent intent1 = new Intent(context, CaptureActivity.class);
                startActivityForResult(intent1, REQUEST_CODE);
                break;
        }
    }

    private void next() {

        final String phoneNum = etNumber.getText().toString();
        final String yq = etYq.getText().toString();
        if(TextUtils.isEmpty(phoneNum)){
            ToastUtil.showShort(context, "手机号不能为空");
        }else if(!StringUtils.isPhoneNumberValid(phoneNum)){
            ToastUtil.showShort(context, "请输入正确的手机号格式");
        }else {
            SendYzmDialog dialog = new SendYzmDialog(context, phoneNum, new SendYzmDialog.ClickListener() {
                @Override
                public void onSure() {
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("phone", phoneNum);
                    ViseUtil.Get(context, NetUrl.MemUsersendMessage, map, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            ToastUtil.showShort(context, "短信验证码发送成功");
                            Intent intent = new Intent();
                            intent.setClass(context, RegisterYzmActivity.class);
                            intent.putExtra("number", phoneNum);
                            intent.putExtra("yq", yq);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
            dialog.show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                    etYq.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(context, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
