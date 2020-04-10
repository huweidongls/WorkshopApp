package com.jingna.workshopapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.EntrustListAdapter;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.bean.EntrustListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.ViseUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/10/17.
 */

public class FragmentWeituo extends BaseFragment {

    public static FragmentWeituo newInstance(String id) {
        FragmentWeituo newFragment = new FragmentWeituo();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;

    private List<EntrustListBean.DataBean> mList;
    private EntrustListAdapter adapter;

    private int page = 1;
    private String id = "";
    private String type = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weituo, null);

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
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
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("type", type);
                map.put("pageSize", "1");
                map.put("pageNum", "10");
                ViseUtil.Get(getContext(), NetUrl.AppShopWtjgfindAllWtjgEquipment, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        EntrustListBean bean = gson.fromJson(s, EntrustListBean.class);
                        mList.clear();
                        mList.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                        page = 2;
                    }
                });
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("type", type);
                map.put("pageSize", page+"");
                map.put("pageNum", "10");
                ViseUtil.Get(getContext(), NetUrl.AppShopWtjgfindAllWtjgEquipment, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        EntrustListBean bean = gson.fromJson(s, EntrustListBean.class);
                        mList.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                        page = page + 1;
                    }
                });
            }
        });

        if(!id.equals("null")){
            type = id;
        }

        Map<String, String> map = new LinkedHashMap<>();
        map.put("type", type);
        map.put("pageSize", "1");
        map.put("pageNum", "10");
        ViseUtil.Get(getContext(), NetUrl.AppShopWtjgfindAllWtjgEquipment, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                EntrustListBean bean = gson.fromJson(s, EntrustListBean.class);
                mList = bean.getData();
                adapter = new EntrustListAdapter(mList);
                GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                page = 2;
            }
        });

    }

}
