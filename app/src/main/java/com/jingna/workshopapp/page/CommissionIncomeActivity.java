package com.jingna.workshopapp.page;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.CommissionIncomeItemAdapter;
import com.jingna.workshopapp.adapter.GoodsDetailsViewpagerAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.CommissionIncomeBean;
import com.jingna.workshopapp.bean.MemberCommissionAuditsBean;
import com.jingna.workshopapp.fragment.FragmentAllOrder;
import com.jingna.workshopapp.fragment.FragmentCommissionIncome;
import com.jingna.workshopapp.fragment.FragmentDaiFuKuanOrder;
import com.jingna.workshopapp.fragment.FragmentDaiShouHuoOrder;
import com.jingna.workshopapp.fragment.FragmentJinXingZhongOrder;
import com.jingna.workshopapp.fragment.FragmentYiQuXiaoOrder;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.StringUtils;
import com.jingna.workshopapp.util.ViseUtil;
import com.jingna.workshopapp.util.WeiboDialogUtils;
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

public class CommissionIncomeActivity extends BaseActivity {

    private Context context = CommissionIncomeActivity.this;

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.vp)
    ViewPager viewPager;

    private FragmentManager mFragmentManager;
    private GoodsDetailsViewpagerAdapter mViewPagerFragmentAdapter;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission_income);

        StatusBarUtils.setStatusBar(CommissionIncomeActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CommissionIncomeActivity.this);
        mFragmentManager = getSupportFragmentManager();
        initData();

    }

    private void initData() {

        fragmentList = new ArrayList<>();
        fragmentList.add(FragmentCommissionIncome.newInstance("1"));
        fragmentList.add(FragmentCommissionIncome.newInstance("0"));
        mViewPagerFragmentAdapter = new GoodsDetailsViewpagerAdapter(mFragmentManager, fragmentList);
        viewPager.setAdapter(mViewPagerFragmentAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    tv1 .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    tv2 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }else {
                    tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    tv1 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    tv2 .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl1, R.id.rl2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl1:
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                tv1 .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv2 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                viewPager.setCurrentItem(0);
                break;
            case R.id.rl2:
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv1 .setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                tv2 .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                viewPager.setCurrentItem(1);
                break;
        }
    }

}
