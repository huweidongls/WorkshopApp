package com.jingna.workshopapp.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.FragmentJinXingZhongOrderAdapter;
import com.jingna.workshopapp.base.OrderBaseFragment;
import com.jingna.workshopapp.bean.OrderListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.ToastUtil;
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
 * Created by Administrator on 2019/8/8.
 */

public class FragmentJinXingZhongOrder extends OrderBaseFragment {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv)
    TextView empty_order_bloack;
    private FragmentJinXingZhongOrderAdapter adapter;
    private List<OrderListBean.DataBean> mList;
    private int page = 1;
    private boolean isFirst = true;

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.frament_jinxingzhong_order, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isFirst) {
            isFirst = false;
        } else {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("pageNum", "1");
            map.put("pageSize", "10");
            map.put("type", "1");
            map.put("userId", SpUtils.getUserId(getContext()));
            ViseUtil.Get(getContext(), NetUrl.AppOrderActivityList, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    OrderListBean bean = gson.fromJson(s, OrderListBean.class);
                    mList = bean.getData();
                    if (mList.size() > 0) {
                        empty_order_bloack.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        empty_order_bloack.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                    adapter = new FragmentJinXingZhongOrderAdapter(mList, new FragmentJinXingZhongOrderAdapter.ClickListener() {
                        @Override
                        public void onReturnPrice(final int pos) {
                            ViseHttp.GET(NetUrl.AppOrderorderRefund)
                                    .addParam("id", mList.get(pos).getId())
                                    .request(new ACallback<String>() {
                                        @Override
                                        public void onSuccess(String data) {
                                            try {
                                                JSONObject jsonObject1 = new JSONObject(data);
                                                if (jsonObject1.optString("data").equals("已退款")) {
                                                    ToastUtil.showShort(getContext(), "退款成功!");
                                                    mList.remove(pos);
                                                    adapter.notifyDataSetChanged();
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
                    });
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                    page = 2;
                }
            });
        }
    }

    @Override
    public void initData() {
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageNum", "1");
                map.put("pageSize", "10");
                map.put("type", "1");
                map.put("userId", SpUtils.getUserId(getContext()));
                ViseUtil.Get(getContext(), NetUrl.AppOrderActivityList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        OrderListBean bean = gson.fromJson(s, OrderListBean.class);
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
                map.put("pageNum", page + "");
                map.put("pageSize", "10");
                map.put("type", "1");
                map.put("userId", SpUtils.getUserId(getContext()));
                ViseUtil.Get(getContext(), NetUrl.AppOrderActivityList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        OrderListBean bean = gson.fromJson(s, OrderListBean.class);
                        mList.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                        page = page + 1;
                    }
                });
            }
        });

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageNum", "1");
        map.put("pageSize", "10");
        map.put("type", "1");
        map.put("userId", SpUtils.getUserId(getContext()));
        ViseUtil.Get(getContext(), NetUrl.AppOrderActivityList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                OrderListBean bean = gson.fromJson(s, OrderListBean.class);
                mList = bean.getData();
                if (mList.size() > 0) {
                    empty_order_bloack.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    empty_order_bloack.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                adapter = new FragmentJinXingZhongOrderAdapter(mList, new FragmentJinXingZhongOrderAdapter.ClickListener() {
                    @Override
                    public void onReturnPrice(final int pos) {
                        ViseHttp.GET(NetUrl.AppOrderorderRefund)
                                .addParam("id", mList.get(pos).getId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject1 = new JSONObject(data);
                                            if (jsonObject1.optString("data").equals("已退款")) {
                                                ToastUtil.showShort(getContext(), "退款成功!");
                                                mList.remove(pos);
                                                adapter.notifyDataSetChanged();
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
                });
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                page = 2;
            }
        });
    }

    @Override
    public void hide() {

    }
}
