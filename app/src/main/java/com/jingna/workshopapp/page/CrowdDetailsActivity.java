package com.jingna.workshopapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    private CrowdCommentAdapter commentAdapter;
    private List<CrowdDetailsBean.DataBean.ShopGoodsEvaluatesBean> mList;

    private CrowdTuijianAdapter tuijianAdapter;
    private List<CrowdTuijianBean.DataBean> mTuijianList;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowd_details);

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
