package com.jingna.workshopapp.page;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.net.NetUrl;
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

public class SubmissionEvaluateActivity extends AppCompatActivity {

    private Context context = SubmissionEvaluateActivity.this;

    @BindView(R.id.pic)
    ImageView ivPic;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rb1)
    RatingBar ratingBar;

    private String id = "";
    private String imgUrl = "";
    private String title = "";

    private String star = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_evaluate);

        id = getIntent().getStringExtra("id");
        imgUrl = getIntent().getStringExtra("imgUrl");
        title = getIntent().getStringExtra("title");
        StatusBarUtils.setStatusBar(SubmissionEvaluateActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(SubmissionEvaluateActivity.this);
        initData();

    }

    private void initData() {

        Glide.with(context).load(NetUrl.BASE_URL+imgUrl).into(ivPic);
        tvTitle.setText(title);

    }

    @OnClick({R.id.rl_back, R.id.rl_commit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_commit:
                onCommit();
                break;
        }
    }

    private void onCommit() {

        String content = etContent.getText().toString();
        int star = (int) ratingBar.getRating();
        if(!StringUtils.isEmpty(content)&&star != 0){
            ViseHttp.POST(NetUrl.AppShopGoodsEvaluatetoUpdate)
                    .addParam("goodsEvaluate", content)
                    .addParam("synthesizeEvaluate", star+"")
                    .addParam("goodsOrderId", id)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "评价成功");
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
        }else {
            ToastUtil.showShort(context, "评价内容与星级不能为空");
        }

    }

}
