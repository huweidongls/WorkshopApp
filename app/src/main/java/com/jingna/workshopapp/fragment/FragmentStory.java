package com.jingna.workshopapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.FragmentCrowdTuijianAdapter;
import com.jingna.workshopapp.adapter.FragmentCrowdXiangmuAdapter;
import com.jingna.workshopapp.adapter.FragmentStoryListAdapter;
import com.jingna.workshopapp.adapter.FragmentStoryListStheAdapter;
import com.jingna.workshopapp.adapter.FragmentStoryListTwoAdapter;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.bean.StoryListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentStory extends BaseFragment {

    @BindView(R.id.rv_a)
    RecyclerView rv_a;
    @BindView(R.id.rv_b)
    RecyclerView rv_b;
    @BindView(R.id.rv_c)
    RecyclerView rv_c;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.et_search)
    EditText etSearch;

    private List<StoryListBean.DataBean> mList;
    private List<String> mList2;
    private List<String> mList3;
    private FragmentStoryListAdapter adapter;
    private FragmentStoryListTwoAdapter adapter2;
    private FragmentStoryListStheAdapter adapter3;

    private String title = "";
    private int page = 1;

    private InputMethodManager manager;//输入法管理器

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story, null);
        ButterKnife.bind(this, view);
        manager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        initData();
        initSearch();
        return view;
    }

    private void initSearch() {

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //先隐藏键盘
                    if (manager.isActive()) {
                        manager.hideSoftInputFromWindow(etSearch.getApplicationWindowToken(), 0);
                    }
                    //自己需要的操作
                    String search = etSearch.getText().toString();
                    if(!TextUtils.isEmpty(search)){
                        title = search;
                        ViseHttp.GET(NetUrl.AppShopStorysqueryList)
                                .addParam("pageSize", "1")
                                .addParam("pageNum", "10")
                                .addParam("title", title)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if (jsonObject.optString("status").equals("200")) {
                                                Gson gson = new Gson();
                                                StoryListBean bean = gson.fromJson(data, StoryListBean.class);
                                                mList.clear();
                                                mList.addAll(bean.getData());
                                                adapter.notifyDataSetChanged();
                                                page = 2;
                                                etSearch.setText(null);
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
                }
                //记得返回false
                return false;
            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarUtils.setStatusBarTransparent(getActivity());
        }
    }

    private void initData() {

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                title = "";
                ViseHttp.GET(NetUrl.AppShopStorysqueryList)
                        .addParam("pageSize", "1")
                        .addParam("pageNum", "10")
                        .addParam("title", title)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.optString("status").equals("200")) {
                                        Gson gson = new Gson();
                                        StoryListBean bean = gson.fromJson(data, StoryListBean.class);
                                        mList.clear();
                                        mList.addAll(bean.getData());
                                        adapter.notifyDataSetChanged();
                                        page = 2;
                                    }
                                    refreshLayout.finishRefresh(1000);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishRefresh(1000);
                            }
                        });
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                ViseHttp.GET(NetUrl.AppShopStorysqueryList)
                        .addParam("pageSize", page + "")
                        .addParam("pageNum", "10")
                        .addParam("title", title)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.optString("status").equals("200")) {
                                        Gson gson = new Gson();
                                        StoryListBean bean = gson.fromJson(data, StoryListBean.class);
                                        mList.addAll(bean.getData());
                                        adapter.notifyDataSetChanged();
                                        page = page + 1;
                                    }
                                    refreshLayout.finishLoadMore(1000);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishLoadMore(1000);
                            }
                        });
            }
        });

        ViseHttp.GET(NetUrl.AppShopStorysqueryList)
                .addParam("pageSize", "1")
                .addParam("pageNum", "10")
                .addParam("title", title)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")) {
                                Gson gson = new Gson();
                                StoryListBean bean = gson.fromJson(data, StoryListBean.class);
                                mList = bean.getData();
                                adapter = new FragmentStoryListAdapter(mList);
                                GridLayoutManager manager = new GridLayoutManager(getContext(), 2) {
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                rv_a.setLayoutManager(manager);
                                rv_a.setAdapter(adapter);
                                page = 2;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

//        mList2 = new ArrayList<>();
//        mList2.add("");
//        mList2.add("");
//        mList2.add("");
//        mList2.add("");
//        adapter2 = new FragmentStoryListTwoAdapter(mList2);
//        LinearLayoutManager managers = new LinearLayoutManager(getContext()){
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
//        managers.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rv_b.setLayoutManager(managers);
//        rv_b.setAdapter(adapter2);
//
//        mList3 = new ArrayList<>();
//        mList3.add("");
//        mList3.add("");
//        mList3.add("");
//        mList3.add("");
//        adapter3 = new FragmentStoryListStheAdapter(mList3);
//        GridLayoutManager managerse = new GridLayoutManager(getContext(), 2){
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
//        rv_c.setLayoutManager(managerse);
//        rv_c.setAdapter(adapter3);
    }
}
