package com.jingna.workshopapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.CommissionIncomeItemAdapter;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.bean.CommissionIncomeBean;
import com.jingna.workshopapp.bean.MemberCommissionAuditsBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StringUtils;
import com.jingna.workshopapp.util.ViseUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2020/4/15.
 */

public class FragmentCommissionIncome extends BaseFragment {

    public static FragmentCommissionIncome newInstance(String type) {
        FragmentCommissionIncome newFragment = new FragmentCommissionIncome();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_zhichu)
    TextView tvZhichu;
    @BindView(R.id.tv_shouru)
    TextView tvShouru;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;

    private CommissionIncomeItemAdapter adapter;
    private List<CommissionIncomeBean.DataBean.CommissionRevenuesBean> mList;
    private List<MemberCommissionAuditsBean.DataBean.MemberCommissionAudits> mList1;

    private String type = "";

    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commission_income, null);
        Bundle args = getArguments();
        if (args != null) {
            type = args.getString("type");
        }
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("memberId", SpUtils.getUserId(getContext()));
                map.put("type", type);
                map.put("pageSize", "10");
                map.put("pageNum", "1");
                ViseUtil.Get(getContext(), NetUrl.MemUserCommissionRevenuesSum, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        if(type.equals("1")){
                            Gson gson = new Gson();
                            MemberCommissionAuditsBean bean = gson.fromJson(s, MemberCommissionAuditsBean.class);
                            mList1.clear();
                            mList1.addAll(bean.getData().getMemberCommissionAudits());
                            adapter.notifyDataSetChanged();
                            page = 2;
                        }else {
                            Gson gson = new Gson();
                            CommissionIncomeBean bean = gson.fromJson(s, CommissionIncomeBean.class);
                            mList.clear();
                            mList.addAll(bean.getData().getCommissionRevenues());
                            adapter.notifyDataSetChanged();
                            page = 2;
                        }
                    }
                });
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("memberId", SpUtils.getUserId(getContext()));
                map.put("type", type);
                map.put("pageSize", "10");
                map.put("pageNum", page+"");
                ViseUtil.Get(getContext(), NetUrl.MemUserCommissionRevenuesSum, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        if(type.equals("1")){
                            Gson gson = new Gson();
                            MemberCommissionAuditsBean bean = gson.fromJson(s, MemberCommissionAuditsBean.class);
                            mList1.addAll(bean.getData().getMemberCommissionAudits());
                            adapter.notifyDataSetChanged();
                            page = page+1;
                        }else {
                            Gson gson = new Gson();
                            CommissionIncomeBean bean = gson.fromJson(s, CommissionIncomeBean.class);
                            mList.addAll(bean.getData().getCommissionRevenues());
                            adapter.notifyDataSetChanged();
                            page = page+1;
                        }
                    }
                });
            }
        });

        if(type.equals("1")){
            rlTop.setVisibility(View.GONE);
            Map<String, String> map = new LinkedHashMap<>();
            map.put("memberId", SpUtils.getUserId(getContext()));
            map.put("type", "1");
            map.put("pageSize", "10");
            map.put("pageNum", "1");
            ViseUtil.Get(getContext(), NetUrl.MemUserCommissionRevenuesSum, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    MemberCommissionAuditsBean bean = gson.fromJson(s, MemberCommissionAuditsBean.class);
                    mList1 = bean.getData().getMemberCommissionAudits();
                    adapter = new CommissionIncomeItemAdapter(mList1, 1);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                    page = 2;
                }
            });
        }else {
            rlTop.setVisibility(View.VISIBLE);
            Map<String, String> map1 = new LinkedHashMap<>();
            map1.put("memberId", SpUtils.getUserId(getContext()));
            map1.put("type", "0");
            map1.put("pageSize", "10");
            map1.put("pageNum", "1");
            ViseUtil.Get(getContext(), NetUrl.MemUserCommissionRevenuesSum, map1, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    CommissionIncomeBean bean = gson.fromJson(s, CommissionIncomeBean.class);
                    mList = bean.getData().getCommissionRevenues();
                    adapter = new CommissionIncomeItemAdapter(mList);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                    tvZhichu.setText("支出 ¥"+ StringUtils.roundByScale(bean.getData().getZhuanchu(), 2));
                    tvShouru.setText("收入 ¥"+ StringUtils.roundByScale(bean.getData().getZhuanru(), 2));
                    page = 2;
                }
            });
        }

    }

}
