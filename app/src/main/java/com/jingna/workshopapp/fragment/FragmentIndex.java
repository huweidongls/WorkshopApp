package com.jingna.workshopapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.IndexAdapter;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.bean.BannerBean;
import com.jingna.workshopapp.bean.CategoryQueryChildListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.ShareListActivity;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
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

    private IndexAdapter adapter;
    private List<String> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);

        StatusBarUtils.setStatusBarTransparent(getActivity());
        ButterKnife.bind(this, view);
        initBanner();
        initChejianShow();
        initData();

        return view;
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

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
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

    }

    @OnClick({R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6})
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
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            StatusBarUtils.setStatusBarTransparent(getActivity());
        }
    }
}
