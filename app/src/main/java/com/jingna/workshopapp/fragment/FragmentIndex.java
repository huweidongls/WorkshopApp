package com.jingna.workshopapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseFragment;
import com.jingna.workshopapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentIndex extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);

        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        if(list.contains("2")){
            ToastUtil.showShort(getContext(), "有");
        }

    }
}
