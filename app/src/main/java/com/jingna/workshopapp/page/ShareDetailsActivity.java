package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.ShareDetailsCalendarAdapter;
import com.jingna.workshopapp.adapter.ShareDetailsCommentAdapter;
import com.jingna.workshopapp.adapter.ShareDetailsPeitaoshebeiAdapter;
import com.jingna.workshopapp.adapter.ShareDetailsZerenAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.ShareDetailsBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.DensityTool;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.widget.ObservableScrollView;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

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
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ll_xinxi_show)
    LinearLayout llXinxiShow;
    @BindView(R.id.ll_chejian_describe)
    LinearLayout llChejianDescribe;
    @BindView(R.id.ll_peitaofuwu)
    LinearLayout llPeitaofuwu;
    @BindView(R.id.ll_peitaoshebei)
    LinearLayout llPeitaoshebei;
    @BindView(R.id.rv_peitaoshebei)
    RecyclerView rvPeitaoshebei;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.ll_shiyongxuzhi)
    LinearLayout llShiyongxuzhi;
    @BindView(R.id.ll_anquanxuzhi)
    LinearLayout llAnquanxuzhi;
    @BindView(R.id.ll_zeren)
    LinearLayout llZeren;
    @BindView(R.id.rv_zeren)
    RecyclerView rvZeren;
    @BindView(R.id.ll_ewai)
    LinearLayout llEwai;
    @BindView(R.id.tv_money)
    TextView tvMoney;

    private ShareDetailsCalendarAdapter calendarAdapter;
    private List<ShareDetailsBean.DataBean.TimesBean> mCalendarList;

    private String id = "";

    private ImageView imageView;
    private TextView textView;

    private ShareDetailsPeitaoshebeiAdapter peitaoshebeiAdapter;
    private List<ShareDetailsBean.DataBean.SupportingEquipmentsBean> peitaoshebeiList;
    private ShareDetailsCommentAdapter commentAdapter;
    private List<ShareDetailsBean.DataBean.ShopGoodsEvaluatesBean> commentList;
    private ShareDetailsZerenAdapter zerenAdapter;
    private List<ShareDetailsBean.DataBean.SysUserInfosBean> zerenList;

    private int isCollect;//0未收藏
    private boolean isScroll = false;

    private String type = "";//1车间  2委托

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_details);

        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBarTransparent(ShareDetailsActivity.this);
        ButterKnife.bind(ShareDetailsActivity.this);
        initView();
        initData();

    }

    private void initData() {

        ViseHttp.GET(NetUrl.AppShopCategorygetByCategoryId)
                .addParam("id", id)
                .addParam("memberId", SpUtils.getUserId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                ShareDetailsBean shareDetailsBean = gson.fromJson(data, ShareDetailsBean.class);
                                String banner1 = shareDetailsBean.getData().getBannerApp();
                                String[] bannerList = banner1.split(",");
                                List<String> bannerlist = new ArrayList<>();
                                for (int i = 0; i<bannerList.length; i++){
                                    bannerlist.add(NetUrl.BASE_URL+bannerList[i]);
                                }
                                init(banner, bannerlist);
                                tvTitle.setText(shareDetailsBean.getData().getCategoryName());
                                //可订日期
                                mCalendarList = shareDetailsBean.getData().getTimes();
                                calendarAdapter = new ShareDetailsCalendarAdapter(mCalendarList);
                                LinearLayoutManager manager = new LinearLayoutManager(context);
                                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                rvCalendar.setLayoutManager(manager);
                                rvCalendar.setAdapter(calendarAdapter);
                                //信息展示
                                String xinxi = shareDetailsBean.getData().getWorkshopInformationApp();
                                String[] xinxiList = xinxi.split(",");
                                if(xinxiList.length>0){
                                    for (int i = 0; i<xinxiList.length; i++){
                                        imageView = new ImageView(context);
                                        Glide.with(context).load(NetUrl.BASE_URL+xinxiList[i]).into(imageView);
                                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                        imageView.setAdjustViewBounds(true);
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        llXinxiShow.addView(imageView, layoutParams);
                                    }
                                }else {
                                    llXinxiShow.setVisibility(View.GONE);
                                }
                                //车间描述
                                String chejian = shareDetailsBean.getData().getCategoryTextApp();
                                String[] chejianList = chejian.split(",");
                                if(chejianList.length>0){
                                    for (int i = 0; i<chejianList.length; i++){
                                        imageView = new ImageView(context);
                                        Glide.with(context).load(NetUrl.BASE_URL+chejianList[i]).into(imageView);
                                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                        imageView.setAdjustViewBounds(true);
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        llChejianDescribe.addView(imageView, layoutParams);
                                    }
                                }else {
                                    llChejianDescribe.setVisibility(View.GONE);
                                }
                                //配套服务
                                String peitao = shareDetailsBean.getData().getSupportingServicesApp();
                                String[] peitaoList = peitao.split(",");
                                if(peitaoList.length>0){
                                    for (int i = 0; i<peitaoList.length; i++){
                                        imageView = new ImageView(context);
                                        Glide.with(context).load(NetUrl.BASE_URL+peitaoList[i]).into(imageView);
                                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                        imageView.setAdjustViewBounds(true);
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        llPeitaofuwu.addView(imageView, layoutParams);
                                    }
                                }else {
                                    llPeitaofuwu.setVisibility(View.GONE);
                                }
                                //配套设备
                                peitaoshebeiList = shareDetailsBean.getData().getSupportingEquipments();
                                if(peitaoshebeiList.size()>0){
                                    peitaoshebeiAdapter = new ShareDetailsPeitaoshebeiAdapter(peitaoshebeiList);
                                    LinearLayoutManager shebeiManager = new LinearLayoutManager(context);
                                    shebeiManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                    rvPeitaoshebei.setLayoutManager(shebeiManager);
                                    rvPeitaoshebei.setAdapter(peitaoshebeiAdapter);
                                }else {
                                    llPeitaoshebei.setVisibility(View.GONE);
                                }
                                //评价
                                commentList = shareDetailsBean.getData().getShopGoodsEvaluates();
                                if(commentList.size()>0){
                                    commentAdapter = new ShareDetailsCommentAdapter(commentList);
                                    LinearLayoutManager commentManager = new LinearLayoutManager(context){
                                        @Override
                                        public boolean canScrollVertically() {
                                            return false;
                                        }
                                    };
                                    commentManager.setOrientation(LinearLayoutManager.VERTICAL);
                                    rvComment.setLayoutManager(commentManager);
                                    rvComment.setAdapter(commentAdapter);
                                }else {
                                    llComment.setVisibility(View.GONE);
                                }
                                //使用须知
                                String shiyong = shareDetailsBean.getData().getInstructionsUseApp();
                                String[] shiyongList = shiyong.split(",");
                                if(shiyongList.length>0){
                                    for (int i = 0; i<shiyongList.length; i++){
                                        imageView = new ImageView(context);
                                        Glide.with(context).load(NetUrl.BASE_URL+shiyongList[i]).into(imageView);
                                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                        imageView.setAdjustViewBounds(true);
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        llShiyongxuzhi.addView(imageView, layoutParams);
                                    }
                                }else {
                                    llShiyongxuzhi.setVisibility(View.GONE);
                                }
                                //安全须知
                                String anquan = shareDetailsBean.getData().getSafetyInstructionsApp();
                                String[] anquanList = anquan.split(",");
                                if(anquanList.length>0){
                                    for (int i = 0; i<anquanList.length; i++){
                                        imageView = new ImageView(context);
                                        Glide.with(context).load(NetUrl.BASE_URL+anquanList[i]).into(imageView);
                                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                        imageView.setAdjustViewBounds(true);
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        llAnquanxuzhi.addView(imageView, layoutParams);
                                    }
                                }else {
                                    llAnquanxuzhi.setVisibility(View.GONE);
                                }
                                //责任人
                                zerenList = shareDetailsBean.getData().getSysUserInfos();
                                if(zerenList.size()>0){
                                    zerenAdapter = new ShareDetailsZerenAdapter(zerenList);
                                    LinearLayoutManager zerenManager = new LinearLayoutManager(context);
                                    zerenManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                    rvZeren.setLayoutManager(zerenManager);
                                    rvZeren.setAdapter(zerenAdapter);
                                }else {
                                    llZeren.setVisibility(View.GONE);
                                }
                                //额外费用
                                List<ShareDetailsBean.DataBean.AdditionalCostsBean> ewaiList = shareDetailsBean.getData().getAdditionalCosts();
                                if(ewaiList.size()>0){
                                    for (int i = 0; i<ewaiList.size(); i++){
                                        textView = new TextView(context);
                                        textView.setText(ewaiList.get(i).getAdditionalCostName()+": "+ewaiList.get(i).getAdditionalCostMoney()+"元");
                                        textView.setTextColor(Color.parseColor("#333333"));
                                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        layoutParams.topMargin = 15;
                                        llEwai.addView(textView);
                                    }
                                }else {
                                    llEwai.setVisibility(View.GONE);
                                }
                                //价格
                                tvMoney.setText("¥"+shareDetailsBean.getData().getMoney());
                                //是否收藏
                                isCollect = shareDetailsBean.getData().getIsCollect();
                                if(isCollect == 0){
                                    Glide.with(context).load(R.mipmap.star_null_w).into(ivStar);
                                }else {
                                    Glide.with(context).load(R.mipmap.star_w).into(ivStar);
                                }
                                initBottomStar(shareDetailsBean.getData().getIntEvalute());
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

    private void initBottomStar(int num) {

        llBottomStar.removeAllViews();
        int a = DensityTool.dp2px(context, 9);
        ImageView imageView;
        for (int i = 0; i<num; i++){
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
                    isScroll = false;
                    Glide.with(context).load(R.mipmap.backff).into(ivBack);
                    Glide.with(context).load(R.mipmap.phone_w).into(ivPhone);
                    if(isCollect == 0){
                        Glide.with(context).load(R.mipmap.star_null_w).into(ivStar);
                    }else {
                        Glide.with(context).load(R.mipmap.star_w).into(ivStar);
                    }
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
                    isScroll = true;
                    Glide.with(context).load(R.mipmap.back_b).into(ivBack);
                    Glide.with(context).load(R.mipmap.phone_b).into(ivPhone);
                    if(isCollect == 0){
                        Glide.with(context).load(R.mipmap.star_null_b).into(ivStar);
                    }else {
                        Glide.with(context).load(R.mipmap.star_b).into(ivStar);
                    }
                    Glide.with(context).load(R.mipmap.share_b).into(ivShare);
//                    tvSearchText.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.tv_submit, R.id.rl_star})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_submit:
                if(SpUtils.getUserId(context).equals("0")){
                    intent.setClass(context, SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    if(type.equals("1")){
                        intent.setClass(context, OrderShebeiActivity.class);
                        intent.putExtra("type", "1");
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }else if(type.equals("2")){
                        intent.setClass(context, CommitOrderActivity.class);
                        intent.putExtra("type", "2");
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.rl_star:
                if(SpUtils.getUserId(context).equals("0")){
                    intent.setClass(context, SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    onCollect();
                }
                break;
        }
    }

    private void onCollect() {

        if(isCollect == 0){
            ViseHttp.POST(NetUrl.AppGoodsShopisFollow)
                    .addParam("goodsId", id)
                    .addParam("memberId", SpUtils.getUserId(context))
                    .addParam("type", "1")
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    isCollect = 1;
                                    if(isScroll){
                                        Glide.with(context).load(R.mipmap.star_b).into(ivStar);
                                    }else {
                                        Glide.with(context).load(R.mipmap.star_w).into(ivStar);
                                    }
                                    ToastUtil.showShort(context, "收藏成功");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
        }else {
            ViseHttp.POST(NetUrl.AppGoodsShopisFollow)
                    .addParam("goodsId", id)
                    .addParam("memberId", SpUtils.getUserId(context))
                    .addParam("type", "0")
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    isCollect = 0;
                                    if(isScroll){
                                        Glide.with(context).load(R.mipmap.star_null_b).into(ivStar);
                                    }else {
                                        Glide.with(context).load(R.mipmap.star_null_w).into(ivStar);
                                    }
                                    ToastUtil.showShort(context, "取消收藏成功");
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

}
