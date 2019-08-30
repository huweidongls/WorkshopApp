package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.media.DrmInitData;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.GetOneBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySetActivity extends BaseActivity {

    private Context context = MySetActivity.this;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.tv_uname)
    TextView tv_uname;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.iv_sex)
    ImageView iv_sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_set);

        StatusBarUtils.setStatusBar(MySetActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(MySetActivity.this);
        initData();
    }
    private void initData(){
        String url = "/MemUser/getOne?id="+SpUtils.getUserId(context);
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                GetOneBean bean = gson.fromJson(data, GetOneBean.class);
                                if (bean.getData().getMemberUserInfo().getGender().equals("1")){
                                    Glide.with(context).load(R.mipmap.nv).into(iv_sex);
                                }
                                Glide.with(context).load(NetUrl.BASE_URL+bean.getData().getMemberUserInfo().getHeadPhoto()).into(iv_img);
                                tv_uname.setText(bean.getData().getMemberUserInfo().getMemName());
                                tv_username.setText(bean.getData().getMemberUserInfo().getMemName());
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
    @OnClick({R.id.rl_back, R.id.rl_special_statement, R.id.rl_privacy_policy, R.id.rl_feedback})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_special_statement:
                intent.setClass(context, SpecialStatementActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_privacy_policy:
                intent.setClass(context, PrivacyPolicyActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_feedback:
                intent.setClass(context, FeedbackActivity.class);
                startActivity(intent);
                break;
        }
    }

}
