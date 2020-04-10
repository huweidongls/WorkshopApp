package com.jingna.workshopapp.page;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.jingna.workshopapp.util.ViseUtil;
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

public class YqActivity extends BaseActivity {

    private Context context = YqActivity.this;

    @BindView(R.id.tv_yq)
    TextView tvYq;
    @BindView(R.id.iv_yq)
    ImageView ivYq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yq);

        StatusBarUtils.setStatusBar(YqActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(YqActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.MemUsergetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GetOneBean bean = gson.fromJson(s, GetOneBean.class);
                tvYq.setText(bean.getData().getMemberUserInfo().getPersonalInvitationCode());
                Bitmap bitmap;
                bitmap = CodeUtils.createImage(bean.getData().getMemberUserInfo().getPersonalInvitationCode(), 400, 400, null);
//                                Glide.with(context).load(bitmap).into(ivYq);
                ivYq.setImageBitmap(bitmap);
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
