package com.jingna.workshopapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.FragmentCrowdTuijianAdapter;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.bean.RaiseListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentTuijian extends BaseFragment {
    @BindView(R.id.rv_evaluation)
    RecyclerView recyclerView;

    private FragmentCrowdTuijianAdapter adapter;
    private List<RaiseListBean.DataBean> mList;

    private String id = "";

    public static FragmentTuijian newInstance(String id) {
        FragmentTuijian newFragment = new FragmentTuijian();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crowd_recommend, null);

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }

        ButterKnife.bind(this, view);
        initData();
        return view;
    }
    private void initData() {

        ViseHttp.GET(NetUrl.AppCrowdFundingfindAllByType)
                .addParam("type", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                RaiseListBean bean = gson.fromJson(data, RaiseListBean.class);
                                mList = bean.getData();
                                adapter = new FragmentCrowdTuijianAdapter(mList);
                                GridLayoutManager manager = new GridLayoutManager(getContext(), 2){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
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
