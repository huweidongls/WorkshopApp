package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.workshopapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/8/8.
 */

public class FragmentYiQuXiaoAdapter extends RecyclerView.Adapter<FragmentYiQuXiaoAdapter.ViewHolder>{
    private Context context;
    private List<String> data;

    public FragmentYiQuXiaoAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public FragmentYiQuXiaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_yiquxiao_order, parent, false);
        FragmentYiQuXiaoAdapter.ViewHolder holder = new FragmentYiQuXiaoAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentYiQuXiaoAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
