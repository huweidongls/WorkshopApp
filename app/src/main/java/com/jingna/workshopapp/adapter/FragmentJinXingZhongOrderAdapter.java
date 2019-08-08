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

public class FragmentJinXingZhongOrderAdapter extends RecyclerView.Adapter<FragmentJinXingZhongOrderAdapter.ViewHolder>{
    private Context context;
    private List<String> data;

    public FragmentJinXingZhongOrderAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public FragmentJinXingZhongOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_jinxingzhong_order, parent, false);
        FragmentJinXingZhongOrderAdapter.ViewHolder holder = new FragmentJinXingZhongOrderAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentJinXingZhongOrderAdapter.ViewHolder holder, int position) {

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
