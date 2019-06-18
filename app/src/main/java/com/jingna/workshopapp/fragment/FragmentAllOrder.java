package com.jingna.workshopapp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.FragmentAllOrderAdapter;
import com.jingna.workshopapp.base.OrderBaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentAllOrder extends OrderBaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;

    private FragmentAllOrderAdapter adapter;
    private List<String> mList;

    private int page = 1;

    private static final int SDK_PAY_FLAG = 1;
    private int position;

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_all_order, null);

        ButterKnife.bind(this, view);
//        initData();

        return view;
    }

    @Override
    public void initData() {

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        adapter = new FragmentAllOrderAdapter(mList, new FragmentAllOrderAdapter.ClickListener() {
            @Override
            public void onPay(int pos) {

            }

            @Override
            public void onReturnPrice(int pos) {

            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void hide() {

    }
}
