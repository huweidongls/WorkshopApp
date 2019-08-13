package com.jingna.workshopapp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.FragmentInvestigatedAdapter;
import com.jingna.workshopapp.adapter.FragmentShouHouAdapter;
import com.jingna.workshopapp.base.OrderBaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/8/13.
 */

public class FragmentInvestigated extends OrderBaseFragment{
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    private FragmentInvestigatedAdapter adapter;
    private List<String> mList;
    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_investigated, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        adapter = new FragmentInvestigatedAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void hide() {

    }
}
