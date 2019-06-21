package com.jingna.workshopapp.page;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.ShareDetailsCalendarAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.DensityTool;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.widget.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareDetailsActivity extends BaseActivity {

    private Context context = ShareDetailsActivity.this;

    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.iv_star)
    ImageView ivStar;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.ll_bottom_star)
    LinearLayout llBottomStar;
    @BindView(R.id.rv_calendar)
    RecyclerView rvCalendar;

    private ShareDetailsCalendarAdapter calendarAdapter;
    private List<String> mCalendarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_details);

        StatusBarUtils.setStatusBarTransparent(ShareDetailsActivity.this);
        ButterKnife.bind(ShareDetailsActivity.this);
        initView();
        initBottomStar();

    }

    private void initBottomStar() {

        llBottomStar.removeAllViews();
        int commentLevel = 5;
        int a = DensityTool.dp2px(context, 9);
        ImageView imageView;
        for (int i = 0; i<commentLevel; i++){
            if(i == 0){
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                llBottomStar.addView(imageView, layoutParams);
            }else {
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                layoutParams.leftMargin = 5;
                llBottomStar.addView(imageView, layoutParams);
            }
        }

    }

    private void initView() {

        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    rlTop.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));//AGB由相关工具获得，或者美工提供
//                    llSearch.setBackgroundResource(R.drawable.bg_index_search);
//                    rlSaoyisao.setBackgroundResource(R.drawable.bg_42000000_round);
//                    rlMessage.setBackgroundResource(R.drawable.bg_42000000_round);
                    Glide.with(context).load(R.mipmap.backff).into(ivBack);
                    Glide.with(context).load(R.mipmap.phone_w).into(ivPhone);
                    Glide.with(context).load(R.mipmap.star_null_w).into(ivStar);
                    Glide.with(context).load(R.mipmap.share_w).into(ivShare);
//                    tvSearchText.setTextColor(Color.parseColor("#9C9C9C"));
                } else if (y > 0 && y <= 888) {
                    float scale = (float) y / 888;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    rlTop.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    rlTop.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
//                    llSearch.setBackgroundResource(R.drawable.bg_index_search_dark);
//                    rlSaoyisao.setBackgroundResource(R.drawable.bg_ffffff_round);
//                    rlMessage.setBackgroundResource(R.drawable.bg_ffffff_round);
                    Glide.with(context).load(R.mipmap.back_b).into(ivBack);
                    Glide.with(context).load(R.mipmap.phone_b).into(ivPhone);
                    Glide.with(context).load(R.mipmap.star_null_b).into(ivStar);
                    Glide.with(context).load(R.mipmap.share_b).into(ivShare);
//                    tvSearchText.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        mCalendarList = new ArrayList<>();
        mCalendarList.add("");
        mCalendarList.add("");
        mCalendarList.add("");
        mCalendarList.add("");
        mCalendarList.add("");
        calendarAdapter = new ShareDetailsCalendarAdapter(mCalendarList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCalendar.setLayoutManager(manager);
        rvCalendar.setAdapter(calendarAdapter);

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
