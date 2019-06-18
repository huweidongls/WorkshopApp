package com.jingna.workshopapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentEntrust extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entrust, null);

        ButterKnife.bind(this, view);

        return view;
    }
}
