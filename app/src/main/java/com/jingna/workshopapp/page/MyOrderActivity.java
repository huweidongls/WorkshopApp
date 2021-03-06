package com.jingna.workshopapp.page;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.GoodsDetailsViewpagerAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.customview.ScaleTransitionPagerTitleView;
import com.jingna.workshopapp.fragment.FragmentAllOrder;
import com.jingna.workshopapp.fragment.FragmentDaiFuKuanOrder;
import com.jingna.workshopapp.fragment.FragmentDaiShouHuoOrder;
import com.jingna.workshopapp.fragment.FragmentJinXingZhongOrder;
import com.jingna.workshopapp.fragment.FragmentYiQuXiaoOrder;
import com.jingna.workshopapp.util.StatusBarUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOrderActivity extends BaseActivity {

    private Context context = MyOrderActivity.this;

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.vp)
    ViewPager mViewPager;

    private FragmentManager mFragmentManager;
    private GoodsDetailsViewpagerAdapter mViewPagerFragmentAdapter;
    private List<Fragment> fragmentList;

    private ArrayList<String> mTitleDataList;

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        index = getIntent().getIntExtra("index", 0);
        StatusBarUtils.setStatusBar(MyOrderActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(MyOrderActivity.this);
        mFragmentManager = getSupportFragmentManager();
        initData();

    }

    private void initData() {

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentAllOrder());
        fragmentList.add(new FragmentDaiFuKuanOrder());
        fragmentList.add(new FragmentJinXingZhongOrder());
        fragmentList.add(new FragmentDaiShouHuoOrder());
        fragmentList.add(new FragmentYiQuXiaoOrder());
        mViewPagerFragmentAdapter = new GoodsDetailsViewpagerAdapter(mFragmentManager, fragmentList);
        mViewPager.setAdapter(mViewPagerFragmentAdapter);

        mTitleDataList = new ArrayList<>();
        mTitleDataList.add("全部");
        mTitleDataList.add("待付款");
        mTitleDataList.add("待收货");
        mTitleDataList.add("待评价");
        mTitleDataList.add("已完成");

        CommonNavigator commonNavigator = new CommonNavigator(this);
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
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
//                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setNormalColor(Color.parseColor("#FFFFFF"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#FFFFFF"));
                simplePagerTitleView.setPadding(49,0,49,0);
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
                indicator.setColors(Color.parseColor("#FFFFFF"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
//        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
//        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        titleContainer.setDividerPadding(UIUtil.dip2px(this, 15));
//        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));
        ViewPagerHelper.bind(magicIndicator, mViewPager);
        mViewPager.setCurrentItem(index);

    }

    @OnClick({R.id.rl_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
