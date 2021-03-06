package com.jingna.workshopapp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.GoodsDetailsViewpagerAdapter;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.bean.BannerBean;
import com.jingna.workshopapp.bean.RaiseGetTypeBean;
import com.jingna.workshopapp.customview.ScaleTransitionPagerTitleView;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ViseUtil;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/6/18.
 */

//众筹
public class FragmentRaise extends BaseFragment {

    private List<Fragment> fragmentList;
    private ArrayList<String> mTitleDataList;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    private FragmentManager mFragmentManager;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.banner)
    Banner banner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_raise, null);
        ButterKnife.bind(this, view);
        mFragmentManager = getChildFragmentManager();
        initData();
        initBanner();
        return view;
    }

    private void initBanner() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("type", "2");
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

        fragmentList = new ArrayList<>();
        mTitleDataList = new ArrayList<>();
        ViseUtil.Get(getContext(), NetUrl.AppCrowdFundinggetType, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                RaiseGetTypeBean bean = gson.fromJson(s, RaiseGetTypeBean.class);
                List<RaiseGetTypeBean.DataBean> list = bean.getData();
                for (RaiseGetTypeBean.DataBean bean1 : list) {
                    mTitleDataList.add(bean1.getName());
                    fragmentList.add(FragmentTuijian.newInstance(bean1.getId() + ""));
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
        });

    }
}
