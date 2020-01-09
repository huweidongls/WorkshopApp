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
import com.jingna.workshopapp.bean.GetOneBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.AddressActivity;
import com.jingna.workshopapp.page.CollectionActivity;
import com.jingna.workshopapp.page.CommissionActivity;
import com.jingna.workshopapp.page.CommissionIncomeActivity;
import com.jingna.workshopapp.page.ForgotPwd1Activity;
import com.jingna.workshopapp.page.MaintenanceAfterSaleActivity;
import com.jingna.workshopapp.page.MyBankCardActivity;
import com.jingna.workshopapp.page.MyOrderActivity;
import com.jingna.workshopapp.page.MySetActivity;
import com.jingna.workshopapp.page.PersonInformationActivity;
import com.jingna.workshopapp.page.SMSLoginActivity;
import com.jingna.workshopapp.page.TeamManagerActivity;
import com.jingna.workshopapp.page.YqActivity;
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
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.ll_name)
    LinearLayout llName;

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
        if (userId.equals("0")) {
            llName.setVisibility(View.GONE);
            llLogin.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(R.mipmap.weidenglu_avatar).into(ivAvatar);
        } else {
            llName.setVisibility(View.VISIBLE);
            llLogin.setVisibility(View.GONE);
            String url = "/MemUser/getOne?id="+userId;
            ViseHttp.GET(url)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                Logger.e("123123", data);
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    Gson gson = new Gson();
                                    GetOneBean bean = gson.fromJson(data, GetOneBean.class);
                                    Glide.with(getContext()).load(NetUrl.BASE_URL+bean.getData().getMemberUserInfo().getHeadPhoto()).into(ivAvatar);
                                    tvName.setText(bean.getData().getMemberUserInfo().getMemName());
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

    private void initData() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        } else {
            StatusBarUtils.setStatusBarTransparent(getActivity());
        }
    }

    @OnClick({R.id.iv_avatar, R.id.ll_login, R.id.ll_my_order, R.id.rl1, R.id.rl2, R.id.rl3, R.id.rl4, R.id.rl5, R.id.rl6
            , R.id.rl7, R.id.iv_my_set, R.id.iv_my_msg, R.id.tv_edit,R.id.ll_Maintenance,R.id.ll_daizhifu,
            R.id.ll_jinxingzhong,R.id.ll_daishouhuo, R.id.rl8})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_avatar:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), PersonInformationActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_login:
                intent.setClass(getContext(), SMSLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_my_order:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), MyOrderActivity.class);
                    intent.putExtra("index", 0);
                    startActivity(intent);
                }
                break;
            case R.id.ll_daizhifu:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), MyOrderActivity.class);
                    intent.putExtra("index", 1);
                    startActivity(intent);
                }
                break;
            case R.id.ll_jinxingzhong:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), MyOrderActivity.class);
                    intent.putExtra("index", 2);
                    startActivity(intent);
                }
                    break;
            case R.id.ll_daishouhuo:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), MyOrderActivity.class);
                    intent.putExtra("index", 2);
                    startActivity(intent);
                }
                break;
            case R.id.rl1:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), AddressActivity.class);
                    intent.putExtra("type", "wode");
                    startActivity(intent);
                }
                break;
            case R.id.rl2:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), MyBankCardActivity.class);
                    intent.putExtra("type", "my");
                    startActivity(intent);
                }
                break;
            case R.id.rl3:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), ForgotPwd1Activity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl4:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), CommissionIncomeActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl5:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), TeamManagerActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl6:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), CommissionActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl7:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), CollectionActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.iv_my_set:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), MySetActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.iv_my_msg:

                break;
            case R.id.tv_edit:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), PersonInformationActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_Maintenance:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), MaintenanceAfterSaleActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl8:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), YqActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

}
