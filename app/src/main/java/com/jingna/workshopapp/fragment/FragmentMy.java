package com.jingna.workshopapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.page.MyOrderActivity;
import com.jingna.workshopapp.page.PersonInformationActivity;
import com.jingna.workshopapp.page.SMSLoginActivity;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentMy extends BaseFragment {

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_num)
    LinearLayout llNum;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_shop_num)
    TextView tvShopNum;
    @BindView(R.id.tv_browse_num)
    TextView tvBrowseNum;
    @BindView(R.id.tv_daifukuan_num)
    TextView tvDaifukuanNum;
    @BindView(R.id.tv_daishouhuo_num)
    TextView tvDaishouhuoNum;
    @BindView(R.id.tv_daipingjia_num)
    TextView tvDaipingjiaNum;
    @BindView(R.id.tv_tuikuan_num)
    TextView tvTuikuanNum;
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;

    private String userId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);

        userId = SpUtils.getUserId(getContext());
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.e("123123", userId);
        userId = SpUtils.getUserId(getContext());
        if(userId.equals("0")){
            tvName.setVisibility(View.GONE);
            llNum.setVisibility(View.GONE);
            llLogin.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(R.mipmap.weidenglu_avatar).into(ivAvatar);
        }else {
            tvName.setVisibility(View.VISIBLE);
            llNum.setVisibility(View.VISIBLE);
            llLogin.setVisibility(View.GONE);
        }
    }

    private void initData() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){

        }else {
            StatusBarUtils.setStatusBarTransparent(getActivity());
        }
    }

    @OnClick({R.id.iv_avatar, R.id.ll_login, R.id.ll_my_order})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.iv_avatar:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), PersonInformationActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_login:
                intent.setClass(getContext(), SMSLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_my_order:
//                if(userId.equals("0")){
//                    intent.setClass(getContext(), SMSLoginActivity.class);
//                    startActivity(intent);
//                }else {
                    intent.setClass(getContext(), MyOrderActivity.class);
                    intent.putExtra("index", 0);
                    startActivity(intent);
//                }
                break;
        }
    }

}
