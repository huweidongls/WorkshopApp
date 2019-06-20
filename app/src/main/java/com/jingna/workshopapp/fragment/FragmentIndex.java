package com.jingna.workshopapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.IndexAdapter;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.page.ShareListActivity;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentIndex extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private IndexAdapter adapter;
    private List<String> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);

        StatusBarUtils.setStatusBarTransparent(getActivity());
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new IndexAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.rl1, R.id.rl2, R.id.rl3, R.id.rl4, R.id.rl5})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl1:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "共享车间");
                startActivity(intent);
                break;
            case R.id.rl2:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "共享设备");
                startActivity(intent);
                break;
            case R.id.rl3:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "共享办公室");
                startActivity(intent);
                break;
            case R.id.rl4:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "委托加工");
                startActivity(intent);
                break;
            case R.id.rl5:
                intent.setClass(getContext(), ShareListActivity.class);
                intent.putExtra("name", "共享厂房");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            StatusBarUtils.setStatusBarTransparent(getActivity());
        }
    }
}
