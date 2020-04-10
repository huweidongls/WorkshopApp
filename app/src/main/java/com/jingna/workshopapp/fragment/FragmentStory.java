package com.jingna.workshopapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.jingna.workshopapp.adapter.FragmentStoryListAdapter;
import com.jingna.workshopapp.adapter.FragmentStoryListStheAdapter;
import com.jingna.workshopapp.adapter.FragmentStoryListTwoAdapter;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.bean.BannerBean;
import com.jingna.workshopapp.bean.StoryListBean;
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
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.banner)
    Banner banner;

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
        initBanner();
        return view;
    }

    private void initBanner() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("type", "3");
        ViseUtil.Get(getContext(), NetUrl.IndexPageApifindBanner, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                BannerBean bannerBean = gson.fromJson(s, BannerBean.class);
                List<String> bannerList = new ArrayList<>();
                for (BannerBean.DataBean bean : bannerBean.getData()){
                    bannerList.add(NetUrl.BASE_URL+bean.getAppPic());
                }
                init(banner, bannerList);
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
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageSize", "1");
                map.put("pageNum", "10");
                map.put("title", title);
                ViseUtil.Get(getContext(), NetUrl.AppShopStorysqueryList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        StoryListBean bean = gson.fromJson(s, StoryListBean.class);
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
                map.put("pageSize", page + "");
                map.put("pageNum", "10");
                map.put("title", title);
                ViseUtil.Get(getContext(), NetUrl.AppShopStorysqueryList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        StoryListBean bean = gson.fromJson(s, StoryListBean.class);
                        mList.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                        page = page + 1;
                    }
                });
            }
        });

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageSize", "1");
        map.put("pageNum", "10");
        map.put("title", title);
        ViseUtil.Get(getContext(), NetUrl.AppShopStorysqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                StoryListBean bean = gson.fromJson(s, StoryListBean.class);
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
        });

    }
}
