package com.jingna.workshopapp.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.FragmentAllOrderAdapter;
import com.jingna.workshopapp.base.OrderBaseFragment;
import com.jingna.workshopapp.bean.CollectionListBean;
import com.jingna.workshopapp.bean.OrderListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.MyOrderActivity;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentAllOrder extends OrderBaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;

    private FragmentAllOrderAdapter adapter;
    private List<OrderListBean.DataBean> mList;

    private int page = 1;

    private static final int SDK_PAY_FLAG = 1;
    private int position;

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_all_order, null);
        ButterKnife.bind(this, view);
//        initData();

        return view;
    }

    @Override
    public void initData() {
        ViseHttp.GET(NetUrl.AppOrderActivityList)
                .addParam("pageNum","1")
                .addParam("pageSize","10")
                .addParam("userId", SpUtils.getUserId(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson =new Gson();
                                OrderListBean bean = gson.fromJson(data, OrderListBean.class);
                                mList = bean.getData();
                                adapter = new FragmentAllOrderAdapter(mList, new FragmentAllOrderAdapter.ClickListener() {
                                    @Override
                                    public void onPay(int pos) {
                                    }
                                    @Override
                                    public void onReturnPrice(int pos) {
                                    }
                                });
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
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });
       /* mList = new ArrayList<>();
        mList.add("");
        mList.add("");*/


    }

    @Override
    public void hide() {

    }
}
