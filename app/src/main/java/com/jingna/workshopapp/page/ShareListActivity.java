package com.jingna.workshopapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.ShareListAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.CategoryQueryChildListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareListActivity extends BaseActivity {

    private Context context = ShareListActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.empty_order_bloacks)
    RelativeLayout empty_order_bloacks;
    private ShareListAdapter adapter;
    private List<CategoryQueryChildListBean.DataBean> mList;

    private String name = "";
    private String type = "";
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_list);

        name = getIntent().getStringExtra("name");
        type = getIntent().getStringExtra("type");
        StatusBarUtils.setStatusBar(ShareListActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(ShareListActivity.this);
        initData();

    }

    private void initData() {

        tvTitle.setText(name);

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pid", type);
                map.put("pageSize", "1");
                map.put("pageNum", "5");
                ViseUtil.Get(context, NetUrl.AppShopCategoryqueryChildList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        CategoryQueryChildListBean childListBean = gson.fromJson(s, CategoryQueryChildListBean.class);
                        mList.clear();
                        mList.addAll(childListBean.getData());
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
                map.put("pid", type);
                map.put("pageSize", page+"");
                map.put("pageNum", "5");
                ViseUtil.Get(context, NetUrl.AppShopCategoryqueryChildList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        CategoryQueryChildListBean childListBean = gson.fromJson(s, CategoryQueryChildListBean.class);
                        mList.addAll(childListBean.getData());
                        adapter.notifyDataSetChanged();
                        page = page + 1;
                    }
                });
            }
        });

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pid", type);
        map.put("pageSize", "1");
        map.put("pageNum", "5");
        ViseUtil.Get(context, NetUrl.AppShopCategoryqueryChildList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                CategoryQueryChildListBean childListBean = gson.fromJson(s, CategoryQueryChildListBean.class);
                mList = childListBean.getData();
                if (mList.size()==0){
                    empty_order_bloacks.setVisibility(View.VISIBLE);
                }
                adapter = new ShareListAdapter(mList, type);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                page = 2;
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
