package com.jingna.workshopapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.CrowdCommentAdapter;
import com.jingna.workshopapp.adapter.CrowdTuijianAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.CrowdDetailsBean;
import com.jingna.workshopapp.bean.CrowdTuijianBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CrowdDetailsActivity extends BaseActivity {

    private Context context = CrowdDetailsActivity.this;

    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.rv_tuijian)
    RecyclerView rvTuijian;
    @BindView(R.id.title)
    TextView tv_title;
    @BindView(R.id.ftitle)
    TextView tv_ftitle;
    @BindView(R.id.tvAllPeople)
    TextView tvAllPeople;
    @BindView(R.id.allmoney)
    TextView tv_allmoney;
    @BindView(R.id.percentage)
    TextView tv_percentage;
    @BindView(R.id.endTime)
    TextView tv_endTime;
    @BindView(R.id.backgroundPictureApp)
    ImageView iv_backgroundPictureApp;
    @BindView(R.id.gearMoney)
    TextView tv_gearMoney;
    @BindView(R.id.forimg)
    LinearLayout iv_forimg;
    private CrowdCommentAdapter commentAdapter;
    private ImageView imageView;
    private List<CrowdDetailsBean.DataBean.ShopGoodsEvaluatesBean> mList;

    private CrowdTuijianAdapter tuijianAdapter;
    private List<CrowdTuijianBean.DataBean> mTuijianList;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowd_details);
        LinearLayout llGroup = (LinearLayout) findViewById(R.id.forimg);
        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(CrowdDetailsActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CrowdDetailsActivity.this);

        initData();
        initTuijian();

    }

    private void initTuijian() {

        ViseHttp.GET(NetUrl.AppCrowdFundingfindAllRecommend)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                CrowdTuijianBean tuijianBean = gson.fromJson(data, CrowdTuijianBean.class);
                                mTuijianList = tuijianBean.getData();
                                tuijianAdapter = new CrowdTuijianAdapter(mTuijianList);
                                GridLayoutManager manager = new GridLayoutManager(context, 2){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                rvTuijian.setLayoutManager(manager);
                                rvTuijian.setAdapter(tuijianAdapter);
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

    private void initData() {

        ViseHttp.GET(NetUrl.AppCrowdFundinggetById)
                .addParam("id", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                CrowdDetailsBean detailsBean = gson.fromJson(data, CrowdDetailsBean.class);
                                tv_title.setText(detailsBean.getData().getTitle());//众筹标题
                                tv_ftitle.setText(detailsBean.getData().getSubtitle());//副标题
                                tvAllPeople.setText(detailsBean.getData().getAllPeople()+"");//总人数
                                tv_allmoney.setText(detailsBean.getData().getAllMoney()+"");//总金额
                                tv_percentage.setText(detailsBean.getData().getPercentage());//达成率
                                Glide.with(context).load(NetUrl.BASE_URL+detailsBean.getData().getBackgroundPictureApp()).into(iv_backgroundPictureApp);//背景图片
                                tv_endTime.setText("众筹中("+detailsBean.getData().getEndTime()+"天结束)");//还有多少天结束
                                tv_gearMoney.setText(detailsBean.getData().getGearMoney()+"起");//档位金额多少元起
                                String xinxi = detailsBean.getData().getStoryApp();
                                String[] xinxiList = xinxi.split(",");
                                if(xinxiList.length>0){
                                    for (int i = 0; i<xinxiList.length; i++){
                                        imageView = new ImageView(context);
                                        Glide.with(context).load(NetUrl.BASE_URL+xinxiList[i]).into(imageView);
                                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                        imageView.setAdjustViewBounds(true);
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        iv_forimg.addView(imageView, layoutParams);
                                    }
                                }else {
                                    iv_forimg.setVisibility(View.GONE);
                                }
                                mList = detailsBean.getData().getShopGoodsEvaluates();
                                commentAdapter = new CrowdCommentAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(context){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                rvComment.setLayoutManager(manager);
                                rvComment.setAdapter(commentAdapter);
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
    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
