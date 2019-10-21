package com.jingna.workshopapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.MainActivity;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.IndexAdapter;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.bean.BannerBean;
import com.jingna.workshopapp.bean.CategoryQueryChildListBean;
import com.jingna.workshopapp.bean.CrowdPopularBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.SearchActivity;
import com.jingna.workshopapp.page.ShareDetailsActivity;
import com.jingna.workshopapp.page.ShareListActivity;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.widget.ObservableScrollView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentIndex extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_show1)
    ImageView ivShow1;
    @BindView(R.id.iv_show2)
    ImageView ivShow2;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.tv_no)
    TextView tvNo;
    @BindView(R.id.iv_sao)
    ImageView ivSao;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.scrollview)
    ObservableScrollView scrollView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;

    private IndexAdapter adapter;
    private List<CrowdPopularBean.DataBean> mList;

    private int page = 1;

    private String id1 = "";
    private String id2 = "";
    private int REQUEST_CODE = 1000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);

        StatusBarUtils.setStatusBarTransparent(getActivity());
        ButterKnife.bind(this, view);
        initRefresh();
        initBanner();
        initChejianShow();
        initData();

        return view;
    }

    private void initRefresh() {

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                initBanner();
                initChejianShow();
                ViseHttp.GET(NetUrl.AppCrowdFundingfindByPopular)
                        .addParam("pageSize", "1")
                        .addParam("pageNum", "2")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        CrowdPopularBean popularBean = gson.fromJson(data, CrowdPopularBean.class);
                                        mList.clear();
                                        mList.addAll(popularBean.getData());
                                        adapter.notifyDataSetChanged();
                                        page = 2;
                                        tvMore.setVisibility(View.VISIBLE);
                                        tvNo.setVisibility(View.GONE);
                                    }
                                    refreshLayout.finishRefresh(500);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishRefresh(500);
                            }
                        });
            }
        });

    }

    /**
     * 车间展示
     */
    private void initChejianShow() {

        ViseHttp.GET(NetUrl.AppShopCategoryqueryChildList)
                .addParam("pid", "1")
                .addParam("pageSize", "1")
                .addParam("pageNum", "5")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                CategoryQueryChildListBean childListBean = gson.fromJson(data, CategoryQueryChildListBean.class);
                                List<CategoryQueryChildListBean.DataBean> list = childListBean.getData();
                                if(list.size()>1){
                                    Glide.with(getContext()).load(NetUrl.BASE_URL+list.get(0).getAppCategoryPic()).into(ivShow1);
                                    Glide.with(getContext()).load(NetUrl.BASE_URL+list.get(1).getAppCategoryPic()).into(ivShow2);
                                    id1 = list.get(0).getId()+"";
                                    id2 = list.get(1).getId()+"";
                                }
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

    private void initBanner() {

        ViseHttp.GET(NetUrl.IndexPageApifindBanner)
                .addParam("type", "0")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                BannerBean bannerBean = gson.fromJson(data, BannerBean.class);
                                List<String> bannerList = new ArrayList<>();
                                for (BannerBean.DataBean bean : bannerBean.getData()){
                                    bannerList.add(NetUrl.BASE_URL+bean.getAppPic());
                                }
                                init(banner, bannerList);
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

        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
//                    rlTop.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));//AGB由相关工具获得，或者美工提供
                    rlTop.setAlpha(0.0f);
                } else if (y > 0 && y <= 888) {
//                    float scale = (float) y / 888;
//                    float alpha = (255 * scale);
                    // 只是layout背景透明
//                    rlTop.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    float al = y/888f;
                    rlTop.setAlpha(al);
                } else {
//                    rlTop.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                    rlTop.setAlpha(1.0f);
                }
            }
        });

        ViseHttp.GET(NetUrl.AppCrowdFundingfindByPopular)
                .addParam("pageSize", page + "")
                .addParam("pageNum", "2")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                CrowdPopularBean popularBean = gson.fromJson(data, CrowdPopularBean.class);
                                mList = popularBean.getData();
                                adapter = new IndexAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext()){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                                page = page + 1;
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

    @OnClick({R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6, R.id.tv_more, R.id.iv_show1, R.id.iv_show2,
    R.id.ll_search,R.id.goods_more, R.id.iv_sao})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.iv1:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "共享车间");
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.iv2:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "共享设备");
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.iv3:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "共享办公室");
                intent.putExtra("type", "3");
                startActivity(intent);
                break;
            case R.id.iv4:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "委托加工");
                intent.putExtra("type", "4");
                startActivity(intent);
                break;
            case R.id.iv5:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "共享厂房");
                intent.putExtra("type", "5");
                startActivity(intent);
                break;
            case R.id.iv6:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "共享零件");
                intent.putExtra("type", "6");
                startActivity(intent);
                break;
            case R.id.tv_more:
                more();
                break;
            case R.id.iv_show1:
                intent.setClass(getContext(), ShareDetailsActivity.class);
                intent.putExtra("type", "1");
                intent.putExtra("id", id1);
                startActivity(intent);
                break;
            case R.id.iv_show2:
                intent.setClass(getContext(), ShareDetailsActivity.class);
                intent.putExtra("type", "1");
                intent.putExtra("id", id2);
                startActivity(intent);
                break;
            case R.id.goods_more:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "共享车间");
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.ll_search:
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_sao:
                Intent intent1 = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent1, REQUEST_CODE);
                break;
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
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void more() {

        tvMore.setVisibility(View.INVISIBLE);
        ViseHttp.GET(NetUrl.AppCrowdFundingfindByPopular)
                .addParam("pageSize", page + "")
                .addParam("pageNum", "2")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                CrowdPopularBean popularBean = gson.fromJson(data, CrowdPopularBean.class);
                                if(popularBean.getData().size()>0){
                                    mList.addAll(popularBean.getData());
                                    adapter.notifyDataSetChanged();
                                    page = page + 1;
                                    tvMore.setVisibility(View.VISIBLE);
                                }else {
                                    ToastUtil.showShort(getContext(), "已经到底了");
                                    tvMore.setVisibility(View.GONE);
                                    tvNo.setVisibility(View.VISIBLE);
                                }
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            StatusBarUtils.setStatusBarTransparent(getActivity());
        }
    }
}
