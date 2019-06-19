package com.jingna.workshopapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.FragmentCrowdTuijianAdapter;
import com.jingna.workshopapp.adapter.FragmentCrowdXiangmuAdapter;
import com.jingna.workshopapp.adapter.FragmentStoryListAdapter;
import com.jingna.workshopapp.adapter.FragmentStoryListStheAdapter;
import com.jingna.workshopapp.adapter.FragmentStoryListTwoAdapter;
import com.jingna.workshopapp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentStory extends BaseFragment {
    @BindView(R.id.rv_a)
    RecyclerView rv_a;
    @BindView(R.id.rv_b)
    RecyclerView rv_b;
    @BindView(R.id.rv_c)
    RecyclerView rv_c;
    private List<String> mList;
    private List<String> mList2;
    private List<String> mList3;
    private FragmentStoryListAdapter adapter;
    private FragmentStoryListTwoAdapter adapter2;
    private FragmentStoryListStheAdapter adapter3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story, null);
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
        adapter = new FragmentStoryListAdapter(mList);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        rv_a.setLayoutManager(manager);
        rv_a.setAdapter(adapter);

        mList2 = new ArrayList<>();
        mList2.add("");
        mList2.add("");
        mList2.add("");
        mList2.add("");
        adapter2 = new FragmentStoryListTwoAdapter(mList2);
        LinearLayoutManager managers = new LinearLayoutManager(getContext());
        managers.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_b.setLayoutManager(managers);
        rv_b.setAdapter(adapter2);

        mList3 = new ArrayList<>();
        mList3.add("");
        mList3.add("");
        mList3.add("");
        mList3.add("");
        adapter3 = new FragmentStoryListStheAdapter(mList3);
        GridLayoutManager managerse = new GridLayoutManager(getContext(), 2);
        rv_c.setLayoutManager(managerse);
        rv_c.setAdapter(adapter3);
    }
}
