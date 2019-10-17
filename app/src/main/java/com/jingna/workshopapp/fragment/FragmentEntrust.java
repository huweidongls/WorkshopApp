package com.jingna.workshopapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.EntrustListAdapter;
import com.jingna.workshopapp.adapter.EntrustTypeAdapter;
import com.jingna.workshopapp.adapter.GoodsDetailsViewpagerAdapter;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.bean.BannerBean;
import com.jingna.workshopapp.bean.EntrustListBean;
import com.jingna.workshopapp.bean.EntrustTypeBean;
import com.jingna.workshopapp.customview.ScaleTransitionPagerTitleView;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.SearchActivity;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.widget.ObservableScrollView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.youth.banner.Banner;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

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

public class FragmentEntrust extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    private List<Fragment> fragmentList;
    private ArrayList<String> mTitleDataList;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    private FragmentManager mFragmentManager;
    @BindView(R.id.vp)
    ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entrust, null);

        ButterKnife.bind(this, view);
        mFragmentManager = getChildFragmentManager();
        initBanner();
        initType();

        return view;
    }

    private void initType() {

        ViseHttp.GET(NetUrl.AppShopWtjgfindAllWtjgType)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                EntrustTypeBean typeBean = gson.fromJson(data, EntrustTypeBean.class);
                                mTitleDataList = new ArrayList<>();
                                fragmentList = new ArrayList<>();
                                mTitleDataList.add("全部");
                                fragmentList.add(FragmentWeituo.newInstance("null"));
                                for (EntrustTypeBean.DataBean bean : typeBean.getData()){
                                    mTitleDataList.add(bean.getName());
                                    fragmentList.add(FragmentWeituo.newInstance(bean.getId()+""));
                                }
                                GoodsDetailsViewpagerAdapter mViewPagerFragmentAdapter = new GoodsDetailsViewpagerAdapter(mFragmentManager, fragmentList);
                                mViewPager.setAdapter(mViewPagerFragmentAdapter);
                                CommonNavigator commonNavigator = new CommonNavigator(getContext());
                                commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                                    @Override
                                    public int getCount() {
                                        return mTitleDataList == null ? 0 : mTitleDataList.size();
                                    }

                                    @Override
                                    public IPagerTitleView getTitleView(Context context, final int index) {
                                        SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                                        simplePagerTitleView.setText(mTitleDataList.get(index));
                                        //设置字体
                                        simplePagerTitleView.setPadding(70, 0, 70, 0);
                                        simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                                        simplePagerTitleView.setNormalColor(Color.parseColor("#B2B2B2"));
                                        simplePagerTitleView.setSelectedColor(Color.parseColor("#ffffff"));
                                        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                mViewPager.setCurrentItem(index);
                                            }
                                        });
                                        return simplePagerTitleView;
                                    }

                                    @Override
                                    public IPagerIndicator getIndicator(Context context) {
                                        LinePagerIndicator indicator = new LinePagerIndicator(context);
                                        indicator.setColors(Color.parseColor("#ffffff"));
                                        return indicator;
                                    }
                                });
                                magicIndicator.setNavigator(commonNavigator);
                                ViewPagerHelper.bind(magicIndicator, mViewPager);
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
                .addParam("type", "1")
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            StatusBarUtils.setStatusBarTransparent(getActivity());
        }
    }

}
